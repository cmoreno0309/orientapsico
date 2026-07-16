package com.orientapsi.controller;

import com.orientapsi.dto.AcompananteDirectoDTO;
import com.orientapsi.model.AcompananteDirecto;
import com.orientapsi.service.IAcompananteDirectoService;
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
@RequestMapping("/v1/acompanantes")
@RequiredArgsConstructor
public class AcompananteDirectoController {

    private final IAcompananteDirectoService service;
    private final ISalonService salonService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AcompananteDirectoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcompananteDirectoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<AcompananteDirectoDTO>> findDisponibles() {
        return ResponseEntity.ok(service.findDisponibles().stream().map(this::toDto).toList());
    }

    @GetMapping("/salon/{idSalon}")
    public ResponseEntity<List<AcompananteDirectoDTO>> findBySalon(@PathVariable Integer idSalon) {
        return ResponseEntity.ok(service.findBySalon(idSalon).stream().map(this::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AcompananteDirectoDTO dto) {
        AcompananteDirecto obj = toEntity(dto);
        obj.setSalon(salonService.findById(dto.getIdSalon()));
        AcompananteDirecto saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdAd()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcompananteDirectoDTO> update(@PathVariable Integer id, @Valid @RequestBody AcompananteDirectoDTO dto) {
        dto.setIdAd(id);
        AcompananteDirecto obj = toEntity(dto);
        obj.setSalon(salonService.findById(dto.getIdSalon()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private AcompananteDirectoDTO toDto(AcompananteDirecto obj) {
        AcompananteDirectoDTO dto = modelMapper.map(obj, AcompananteDirectoDTO.class);
        dto.setIdUsuario(obj.getUsuario().getIdUsuario());
        dto.setIdSalon(obj.getSalon().getIdSalon());
        dto.setNombreSalon(obj.getSalon().getNombre());
        return dto;
    }

    private AcompananteDirecto toEntity(AcompananteDirectoDTO dto) { return modelMapper.map(dto, AcompananteDirecto.class); }
}
