package com.orientapsi.controller;

import com.orientapsi.dto.ConfiguracionDTO;
import com.orientapsi.model.Configuracion;
import com.orientapsi.service.IConfiguracionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/configuracion")
@RequiredArgsConstructor
public class ConfiguracionController {

    private final IConfiguracionService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ConfiguracionDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{clave}")
    public ResponseEntity<ConfiguracionDTO> findById(@PathVariable String clave) {
        return ResponseEntity.ok(toDto(service.findById(clave)));
    }

    @PostMapping
    public ResponseEntity<ConfiguracionDTO> save(@Valid @RequestBody ConfiguracionDTO dto) {
        return ResponseEntity.ok(toDto(service.save(toEntity(dto))));
    }

    @PutMapping("/{clave}")
    public ResponseEntity<ConfiguracionDTO> update(@PathVariable String clave, @Valid @RequestBody ConfiguracionDTO dto) {
        dto.setClave(clave);
        return ResponseEntity.ok(toDto(service.update(toEntity(dto))));
    }

    @DeleteMapping("/{clave}")
    public ResponseEntity<Void> delete(@PathVariable String clave) {
        service.deleteById(clave);
        return ResponseEntity.noContent().build();
    }

    private ConfiguracionDTO toDto(Configuracion obj) { return modelMapper.map(obj, ConfiguracionDTO.class); }
    private Configuracion toEntity(ConfiguracionDTO dto) { return modelMapper.map(dto, Configuracion.class); }
}
