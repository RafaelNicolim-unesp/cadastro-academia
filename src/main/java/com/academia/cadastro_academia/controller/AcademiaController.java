package com.academia.cadastro_academia.controller;

import com.academia.cadastro_academia.business.AcademiaService;
import com.academia.cadastro_academia.infrastructure.entity.Academia;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/academia")
public class AcademiaController {

    private final AcademiaService academiaService;

    public AcademiaController(AcademiaService academiaService) {
        this.academiaService = academiaService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarAcademia(@RequestBody Academia academia) {
        academiaService.salvarAcademia(academia);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Academia> buscarAcademiaPorNome(@PathVariable String nome) {
        Academia academia = academiaService.buscarAcademiaPorNome(nome);
        return ResponseEntity.ok(academia);
    }

    @DeleteMapping("/cnpj/{cnpj}")
    public ResponseEntity<Void> deletarAcademiaPorCnpj(@PathVariable String cnpj) {
        academiaService.deletarAcademiaPorCnpj(cnpj);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarAcademiaPorId(@PathVariable Integer id, @RequestBody Academia academia) {
        academiaService.atualizarAcademiaPorId(id, academia);
        return ResponseEntity.ok().build();
    }


}
