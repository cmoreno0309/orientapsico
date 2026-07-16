package com.orientapsi.controller;

import com.orientapsi.dto.ConsultanteDTO;
import com.orientapsi.model.Consultante;
import com.orientapsi.service.IConsultanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.ForwardedHeaderUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/v1/consultantes")
@RequiredArgsConstructor
public class ConsultanteController {

    private final IConsultanteService service;
    private final ModelMapper modelMapper;

    // findAll: List<ConsultanteDTO> -> Flux<ConsultanteDTO>
    // Spring WebFlux serializa un Flux como un array JSON normal, igual que
    // veías con la lista tradicional; el cliente no nota diferencia en la respuesta.
    @GetMapping
    public Flux<ConsultanteDTO> findAll() {
        return service.findAll().map(this::toDto);
    }

    // findById: ResponseEntity<ConsultanteDTO> -> Mono<ResponseEntity<ConsultanteDTO>>
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ConsultanteDTO>> findById(@PathVariable Integer id) {
        return service.findById(id)
                .map(c -> ResponseEntity.ok(toDto(c)));
        // Si no existe, el service lanza ModelNotFoundException dentro del Mono;
        // un @ExceptionHandler (o @ControllerAdvice) la traduce a 404, igual que en MVC.
    }

    // findByDocumento: aquí el original usaba Optional.map/.orElse, que se traduce
    // casi 1 a 1 a Mono.map/.switchIfEmpty
    @GetMapping("/documento/{doc}")
    public Mono<ResponseEntity<ConsultanteDTO>> findByDocumento(@PathVariable String doc) {
        return service.findByDocumentoIdentidad(doc)
                .map(c -> ResponseEntity.ok(toDto(c)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/verificar/{doc}")
    public Mono<ResponseEntity<Boolean>> fueAtendido(@PathVariable String doc) {
        return service.fueAtendidoAntes(doc)
                .map(ResponseEntity::ok);
    }

    /**
     * Crea un consultante y devuelve el DTO con el ID generado.
     * El frontend del registro público necesita el idConsultante para crear
     * luego el caso asociado. Cambio respecto a la versión anterior: ahora se
     * devuelve el cuerpo en lugar de solo el header Location.
     *
     * Cambio clave vs la versión MVC: ServletUriComponentsBuilder.fromCurrentRequest()
     * NO existe en WebFlux porque depende de HttpServletRequest ligado al hilo actual
     * (un concepto que no aplica cuando no hay un hilo fijo por request).
     *
     * Nota de versión: UriComponentsBuilder.fromHttpRequest(HttpRequest) fue el
     * reemplazo típico en WebFlux, pero está deprecado desde Spring Framework 6.1
     * y fue ELIMINADO en la 7.0 (la que trae Spring Boot 4.x, como este proyecto).
     * El reemplazo vigente es ForwardedHeaderUtils.adaptFromForwardedHeaders(...),
     * que arma el UriComponentsBuilder a partir de la URI + headers del request
     * (respetando X-Forwarded-* si el backend está detrás de un proxy/load balancer,
     * como suele pasar en Azure App Service).
     */
    @PostMapping
    public Mono<ResponseEntity<ConsultanteDTO>> save(@Valid @RequestBody ConsultanteDTO dto,
                                                     ServerHttpRequest request) {
        return service.save(toEntity(dto))
                .map(obj -> {
                    URI location = ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders())
                            .path("/{id}").buildAndExpand(obj.getIdConsultante()).toUri();
                    return ResponseEntity.created(location).body(toDto(obj));
                });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ConsultanteDTO>> update(@PathVariable Integer id, @Valid @RequestBody ConsultanteDTO dto) {
        dto.setIdConsultante(id);
        return service.update(toEntity(dto))
                .map(c -> ResponseEntity.ok(toDto(c)));
    }

    // delete: ResponseEntity<Void> -> Mono<ResponseEntity<Void>>
    // Mono<Void> del service no emite ningún valor (ni siquiera al completar
    // exitosamente), así que se encadena con .then(...) en vez de .map(...),
    // que solo dispara la ejecución y arma la respuesta cuando termina.
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id) {
        return service.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }

    private ConsultanteDTO toDto(Consultante obj) { return modelMapper.map(obj, ConsultanteDTO.class); }
    private Consultante toEntity(ConsultanteDTO dto) { return modelMapper.map(dto, Consultante.class); }
}
