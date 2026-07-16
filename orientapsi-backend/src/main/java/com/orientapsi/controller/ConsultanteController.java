package com.orientapsi.controller;

import com.orientapsi.dto.ConsultanteDTO;
import com.orientapsi.model.Consultante;
import com.orientapsi.service.IConsultanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/consultantes")
@RequiredArgsConstructor
public class ConsultanteController {

    private final IConsultanteService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ConsultanteDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultanteDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/documento/{doc}")
    public ResponseEntity<ConsultanteDTO> findByDocumento(@PathVariable String doc) {
        return service.findByDocumentoIdentidad(doc)
                .map(c -> ResponseEntity.ok(toDto(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/verificar/{doc}")
    public ResponseEntity<Boolean> fueAtendido(@PathVariable String doc) {
        return ResponseEntity.ok(service.fueAtendidoAntes(doc));
    }

    /**
     * Crea un consultante y devuelve el DTO con el ID generado.
     * El frontend del registro público necesita el idConsultante para crear
     * luego el caso asociado. Cambio respecto a la versión anterior: ahora se
     * devuelve el cuerpo en lugar de solo el header Location.
     */
    @PostMapping
    public ResponseEntity<ConsultanteDTO> save(@Valid @RequestBody ConsultanteDTO dto) {
        Consultante obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdConsultante()).toUri();
        return ResponseEntity.created(location).body(toDto(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultanteDTO> update(@PathVariable Integer id, @Valid @RequestBody ConsultanteDTO dto) {
        dto.setIdConsultante(id);
        return ResponseEntity.ok(toDto(service.update(toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ConsultanteDTO toDto(Consultante obj) { return modelMapper.map(obj, ConsultanteDTO.class); }
    private Consultante toEntity(ConsultanteDTO dto) { return modelMapper.map(dto, Consultante.class); }
}