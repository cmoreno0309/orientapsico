package com.orientapsi.controller;

import com.orientapsi.dto.SesionDTO;
import com.orientapsi.model.Sesion;
import com.orientapsi.service.IAsignacionService;
import com.orientapsi.service.ISesionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/sesiones")
@RequiredArgsConstructor
public class SesionController {

    private final ISesionService service;
    private final IAsignacionService asignacionService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SesionDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/asignacion/{idAsignacion}")
    public ResponseEntity<List<SesionDTO>> findByAsignacion(@PathVariable Integer idAsignacion) {
        return ResponseEntity.ok(service.findByAsignacion(idAsignacion).stream().map(this::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SesionDTO dto) {
        Sesion obj = toEntity(dto);
        obj.setAsignacion(asignacionService.findById(dto.getIdAsignacion()));
        Sesion saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdSesion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionDTO> update(@PathVariable Integer id, @Valid @RequestBody SesionDTO dto) {
        dto.setIdSesion(id);
        Sesion obj = toEntity(dto);
        obj.setAsignacion(asignacionService.findById(dto.getIdAsignacion()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private SesionDTO toDto(Sesion obj) {
        SesionDTO dto = modelMapper.map(obj, SesionDTO.class);
        dto.setIdAsignacion(obj.getAsignacion().getIdAsignacion());
        return dto;
    }

    private Sesion toEntity(SesionDTO dto) { return modelMapper.map(dto, Sesion.class); }
}
