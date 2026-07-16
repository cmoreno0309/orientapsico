package com.orientapsi.controller;

import com.orientapsi.dto.ConvocatoriaDTO;
import com.orientapsi.exception.ModelNotFoundException;
import com.orientapsi.model.Convocatoria;
import com.orientapsi.service.IConvocatoriaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/convocatorias")
public class ConvocatoriaController {

    private final IConvocatoriaService service;
    private final ModelMapper modelMapper;

    // Constructor manual (sin Lombok) para que @Qualifier funcione correctamente
    public ConvocatoriaController(
            IConvocatoriaService service,
            @Qualifier("convocatoriaMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ConvocatoriaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvocatoriaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/abierta")
    public ResponseEntity<ConvocatoriaDTO> findAbierta() {
        Convocatoria abierta = service.findConvocatoriaAbierta()
                .orElseThrow(() -> new ModelNotFoundException("No hay convocatoria abierta"));
        return ResponseEntity.ok(toDto(abierta));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConvocatoriaDTO dto) {
        Convocatoria obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getIdConvocatoria()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvocatoriaDTO> update(@PathVariable Integer id, @Valid @RequestBody ConvocatoriaDTO dto) {
        dto.setIdConvocatoria(id);
        return ResponseEntity.ok(toDto(service.update(toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ConvocatoriaDTO toDto(Convocatoria obj) { return modelMapper.map(obj, ConvocatoriaDTO.class); }
    private Convocatoria toEntity(ConvocatoriaDTO dto) { return modelMapper.map(dto, Convocatoria.class); }
}