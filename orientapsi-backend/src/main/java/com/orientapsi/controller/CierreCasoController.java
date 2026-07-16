package com.orientapsi.controller;

import com.orientapsi.dto.CierreCasoDTO;
import com.orientapsi.model.CierreCaso;
import com.orientapsi.service.ICasoService;
import com.orientapsi.service.ICierreCasoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/cierres")
@RequiredArgsConstructor
public class CierreCasoController {

    private final ICierreCasoService service;
    private final ICasoService casoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CierreCasoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CierreCasoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/caso/{idCaso}")
    public ResponseEntity<CierreCasoDTO> findByCaso(@PathVariable Integer idCaso) {
        return service.findByCaso(idCaso)
                .map(c -> ResponseEntity.ok(toDto(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CierreCasoDTO dto) {
        CierreCaso obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        CierreCaso saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdCierre()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CierreCasoDTO> update(@PathVariable Integer id, @Valid @RequestBody CierreCasoDTO dto) {
        dto.setIdCierre(id);
        CierreCaso obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CierreCasoDTO toDto(CierreCaso obj) {
        CierreCasoDTO dto = modelMapper.map(obj, CierreCasoDTO.class);
        dto.setIdCaso(obj.getCaso().getIdCaso());
        return dto;
    }

    private CierreCaso toEntity(CierreCasoDTO dto) { return modelMapper.map(dto, CierreCaso.class); }
}
