package com.orientapsi.controller;

import com.orientapsi.dto.AsignacionDTO;
import com.orientapsi.model.Asignacion;
import com.orientapsi.service.IAcompananteDirectoService;
import com.orientapsi.service.IAsignacionService;
import com.orientapsi.service.ICasoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/asignaciones")
@RequiredArgsConstructor
public class AsignacionController {

    private final IAsignacionService service;
    private final ICasoService casoService;
    private final IAcompananteDirectoService adService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AsignacionDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignacionDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/activas")
    public ResponseEntity<List<AsignacionDTO>> findActivas() {
        return ResponseEntity.ok(service.findActivas().stream().map(this::toDto).toList());
    }

    @GetMapping("/caso/{idCaso}")
    public ResponseEntity<AsignacionDTO> findActivaByCaso(@PathVariable Integer idCaso) {
        return service.findActivaByCaso(idCaso)
                .map(a -> ResponseEntity.ok(toDto(a)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AsignacionDTO dto) {
        Asignacion obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        obj.setAcompananteDirecto(adService.findById(dto.getIdAd()));
        Asignacion saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdAsignacion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignacionDTO> update(@PathVariable Integer id, @Valid @RequestBody AsignacionDTO dto) {
        dto.setIdAsignacion(id);
        Asignacion obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        obj.setAcompananteDirecto(adService.findById(dto.getIdAd()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private AsignacionDTO toDto(Asignacion obj) {
        AsignacionDTO dto = modelMapper.map(obj, AsignacionDTO.class);
        dto.setIdCaso(obj.getCaso().getIdCaso());
        dto.setIdAd(obj.getAcompananteDirecto().getIdAd());
        dto.setCodigoCaso(obj.getCaso().getCodigo());
        dto.setNombreConsultante(obj.getCaso().getConsultante().getNombreCompleto());
        dto.setNombreAd(obj.getAcompananteDirecto().getNombrePreferido());
        return dto;
    }

    private Asignacion toEntity(AsignacionDTO dto) { return modelMapper.map(dto, Asignacion.class); }
}
