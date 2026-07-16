package com.orientapsi.controller;

import com.orientapsi.dto.DerivacionDTO;
import com.orientapsi.model.Derivacion;
import com.orientapsi.service.ICasoService;
import com.orientapsi.service.IDerivacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/derivaciones")
@RequiredArgsConstructor
public class DerivacionController {

    private final IDerivacionService service;
    private final ICasoService casoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DerivacionDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DerivacionDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/caso/{idCaso}")
    public ResponseEntity<DerivacionDTO> findByCaso(@PathVariable Integer idCaso) {
        return service.findByCaso(idCaso)
                .map(d -> ResponseEntity.ok(toDto(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DerivacionDTO dto) {
        Derivacion obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        Derivacion saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdDerivacion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DerivacionDTO> update(@PathVariable Integer id, @Valid @RequestBody DerivacionDTO dto) {
        dto.setIdDerivacion(id);
        Derivacion obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private DerivacionDTO toDto(Derivacion obj) {
        DerivacionDTO dto = modelMapper.map(obj, DerivacionDTO.class);
        dto.setIdCaso(obj.getCaso().getIdCaso());
        return dto;
    }

    private Derivacion toEntity(DerivacionDTO dto) { return modelMapper.map(dto, Derivacion.class); }
}
