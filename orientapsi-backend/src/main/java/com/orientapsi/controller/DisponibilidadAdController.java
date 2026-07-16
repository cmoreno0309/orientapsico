package com.orientapsi.controller;

import com.orientapsi.dto.DisponibilidadAdDTO;
import com.orientapsi.model.DisponibilidadAd;
import com.orientapsi.service.IAcompananteDirectoService;
import com.orientapsi.service.IDisponibilidadAdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadAdController {

    private final IDisponibilidadAdService service;
    private final IAcompananteDirectoService adService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<DisponibilidadAdDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadAdDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/ad/{idAd}")
    public ResponseEntity<List<DisponibilidadAdDTO>> findByAd(@PathVariable Integer idAd) {
        return ResponseEntity.ok(service.findByAd(idAd).stream().map(this::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DisponibilidadAdDTO dto) {
        DisponibilidadAd obj = toEntity(dto);
        obj.setAcompananteDirecto(adService.findById(dto.getIdAd()));
        DisponibilidadAd saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdDisponibilidad()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadAdDTO> update(@PathVariable Integer id, @Valid @RequestBody DisponibilidadAdDTO dto) {
        dto.setIdDisponibilidad(id);
        DisponibilidadAd obj = toEntity(dto);
        obj.setAcompananteDirecto(adService.findById(dto.getIdAd()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private DisponibilidadAdDTO toDto(DisponibilidadAd obj) {
        DisponibilidadAdDTO dto = modelMapper.map(obj, DisponibilidadAdDTO.class);
        dto.setIdAd(obj.getAcompananteDirecto().getIdAd());
        return dto;
    }

    private DisponibilidadAd toEntity(DisponibilidadAdDTO dto) { return modelMapper.map(dto, DisponibilidadAd.class); }
}
