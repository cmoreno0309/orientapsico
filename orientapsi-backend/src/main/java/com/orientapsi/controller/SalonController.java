package com.orientapsi.controller;

import com.orientapsi.dto.SalonDTO;
import com.orientapsi.model.Salon;
import com.orientapsi.service.ISalonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/salones")
@RequiredArgsConstructor
public class SalonController {

    private final ISalonService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SalonDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/ciclo/{ciclo}")
    public ResponseEntity<List<SalonDTO>> findByCiclo(@PathVariable String ciclo) {
        return ResponseEntity.ok(service.findByCiclo(ciclo).stream().map(this::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SalonDTO dto) {
        Salon obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSalon()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalonDTO> update(@PathVariable Integer id, @Valid @RequestBody SalonDTO dto) {
        dto.setIdSalon(id);
        return ResponseEntity.ok(toDto(service.update(toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private SalonDTO toDto(Salon obj) {
        SalonDTO dto = modelMapper.map(obj, SalonDTO.class);
        if (obj.getDocente() != null) {
            dto.setIdDocente(obj.getDocente().getIdUsuario());
            dto.setNombreDocente(obj.getDocente().getNombre());
        }
        return dto;
    }

    private Salon toEntity(SalonDTO dto) { return modelMapper.map(dto, Salon.class); }
}
