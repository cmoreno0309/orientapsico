package com.orientapsi.controller;

import com.orientapsi.dto.EntrevistaInicialDTO;
import com.orientapsi.model.EntrevistaInicial;
import com.orientapsi.service.ICasoService;
import com.orientapsi.service.IEntrevistaInicialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/entrevistas")
@RequiredArgsConstructor
public class EntrevistaInicialController {

    private final IEntrevistaInicialService service;
    private final ICasoService casoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EntrevistaInicialDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrevistaInicialDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/caso/{idCaso}")
    public ResponseEntity<EntrevistaInicialDTO> findByCaso(@PathVariable Integer idCaso) {
        return service.findByCaso(idCaso)
                .map(e -> ResponseEntity.ok(toDto(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EntrevistaInicialDTO dto) {
        EntrevistaInicial obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        EntrevistaInicial saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdEntrevista()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrevistaInicialDTO> update(@PathVariable Integer id, @Valid @RequestBody EntrevistaInicialDTO dto) {
        dto.setIdEntrevista(id);
        EntrevistaInicial obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EntrevistaInicialDTO toDto(EntrevistaInicial obj) {
        EntrevistaInicialDTO dto = modelMapper.map(obj, EntrevistaInicialDTO.class);
        dto.setIdCaso(obj.getCaso().getIdCaso());
        dto.setIdPracticante(obj.getPracticante().getIdUsuario());
        return dto;
    }

    private EntrevistaInicial toEntity(EntrevistaInicialDTO dto) { return modelMapper.map(dto, EntrevistaInicial.class); }
}
