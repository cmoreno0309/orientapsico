package com.orientapsi.controller;

import com.orientapsi.dto.EvaluacionCasoDTO;
import com.orientapsi.model.EvaluacionCaso;
import com.orientapsi.service.ICasoService;
import com.orientapsi.service.IEvaluacionCasoService;
import com.orientapsi.repo.IUsuarioRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionCasoController {

    private final IEvaluacionCasoService service;
    private final ICasoService casoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EvaluacionCasoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionCasoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @GetMapping("/caso/{idCaso}")
    public ResponseEntity<List<EvaluacionCasoDTO>> findByCaso(@PathVariable Integer idCaso) {
        return ResponseEntity.ok(service.findByCaso(idCaso).stream().map(this::toDto).toList());
    }

    @GetMapping("/caso/{idCaso}/count")
    public ResponseEntity<Long> countByCaso(@PathVariable Integer idCaso) {
        return ResponseEntity.ok(service.countByCaso(idCaso));
    }

    @GetMapping("/caso/{idCaso}/practicante/{idPracticante}/existe")
    public ResponseEntity<Boolean> yaEvaluo(@PathVariable Integer idCaso, @PathVariable Integer idPracticante) {
        return ResponseEntity.ok(service.yaEvaluo(idCaso, idPracticante));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EvaluacionCasoDTO dto) {
        EvaluacionCaso obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        EvaluacionCaso saved = service.save(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getIdEvaluacion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionCasoDTO> update(@PathVariable Integer id, @Valid @RequestBody EvaluacionCasoDTO dto) {
        dto.setIdEvaluacion(id);
        EvaluacionCaso obj = toEntity(dto);
        obj.setCaso(casoService.findById(dto.getIdCaso()));
        return ResponseEntity.ok(toDto(service.update(obj)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private EvaluacionCasoDTO toDto(EvaluacionCaso obj) {
        EvaluacionCasoDTO dto = modelMapper.map(obj, EvaluacionCasoDTO.class);
        dto.setIdCaso(obj.getCaso().getIdCaso());
        dto.setIdPracticante(obj.getPracticante().getIdUsuario());
        dto.setNombrePracticante(obj.getPracticante().getNombre());
        return dto;
    }

    private EvaluacionCaso toEntity(EvaluacionCasoDTO dto) { return modelMapper.map(dto, EvaluacionCaso.class); }
}
