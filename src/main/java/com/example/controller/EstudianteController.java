package com.example.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Estudiante;
import com.example.service.EstudianteService;

@RestController
@RequestMapping("/estudiante")
@Validated
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Estudiante> getEstudianteByUsuarioId(@PathVariable Long usuarioId) {
        return estudianteService.getEstudianteByUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Solo admin puede crear estudiantes
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Estudiante> createEstudiante(@Valid @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.createEstudiante(estudiante));
    }

    // Solo admin puede ver todos los estudiantes
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Estudiante>> getAllEstudiantes() {
        return ResponseEntity.ok(estudianteService.getAllEstudiantes());
    }

    // Admin puede ver cualquiera, estudiante solo el suyo
    @PreAuthorize("hasRole('ADMIN') or @estudianteService.isOwner(#id, principal.username)")
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id, Principal principal) {
        return estudianteService.getEstudianteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Admin puede editar cualquiera, estudiante solo el suyo
    @PreAuthorize("hasRole('ADMIN') or @estudianteService.isOwner(#id, principal.username)")
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Long id,
            @Valid @RequestBody Estudiante estudianteDetails, Principal principal) {
        return ResponseEntity.ok(estudianteService.updateEstudiante(id, estudianteDetails));
    }

    // Solo admin puede eliminar estudiantes
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return ResponseEntity.ok().build();
    }
}