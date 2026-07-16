package com.orientapsi.controller;

import com.orientapsi.dto.CasoDTO;
import com.orientapsi.model.Caso;
import com.orientapsi.model.Consultante;
import com.orientapsi.model.Convocatoria;
import com.orientapsi.service.ICasoService;
import com.orientapsi.service.IConsultanteService;
import com.orientapsi.service.IConvocatoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/casos")
@RequiredArgsConstructor
public class CasoController {

    private final ICasoService service;
    private final IConsultanteService consultanteService;
    private final IConvocatoriaService convocatoriaService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CasoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CasoDTO> findByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(toDto(service.findByCodigo(codigo)));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CasoDTO>> findByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.findByEstado(estado).stream().map(this::toDto).toList());
    }

    @GetMapping("/convocatoria/{id}")
    public ResponseEntity<List<CasoDTO>> findByConvocatoria(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findByConvocatoria(id).stream().map(this::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CasoDTO dto) {
        Caso obj = toEntity(dto);
        obj.setConsultante(consultanteService.findById(dto.getIdConsultante()));
        obj.setConvocatoria(convocatoriaService.findById(dto.getIdConvocatoria()));
        Caso saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdCaso()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasoDTO> update(@PathVariable Integer id, @Valid @RequestBody CasoDTO dto) {
        dto.setIdCaso(id);
        Caso obj = toEntity(dto);
        obj.setConsultante(consultanteService.findById(dto.getIdConsultante()));
        obj.setConvocatoria(convocatoriaService.findById(dto.getIdConvocatoria()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CasoDTO toDto(Caso obj) {
        CasoDTO dto = modelMapper.map(obj, CasoDTO.class);
        dto.setIdConsultante(obj.getConsultante().getIdConsultante());
        dto.setIdConvocatoria(obj.getConvocatoria().getIdConvocatoria());
        dto.setNombreConsultante(obj.getConsultante().getNombreCompleto());
        dto.setMotivoConsulta(obj.getConsultante().getMotivoConsulta());
        dto.setDisponibilidadHoraria(obj.getConsultante().getDisponibilidadHoraria());
        return dto;
    }

    private Caso toEntity(CasoDTO dto) { return modelMapper.map(dto, Caso.class); }
}
