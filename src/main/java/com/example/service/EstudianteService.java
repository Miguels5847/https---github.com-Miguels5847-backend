package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Estudiante;
import com.example.model.User;
import com.example.repository.EstudianteRepository;
import com.example.repository.UserRepository;

@Service
@Transactional
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private UserRepository userRepository;

    public Estudiante createEstudiante(Estudiante estudiante) {
        // Adjunta el usuario existente antes de guardar
        Long userId = estudiante.getUsuario().getId();
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        estudiante.setUsuario(usuario);
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudianteByUsuarioId(Long usuarioId) {
        return estudianteRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante updateEstudiante(Long id, Estudiante estudianteDetails) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        estudiante.setNombre(estudianteDetails.getNombre());
        estudiante.setCorreo(estudianteDetails.getCorreo());
        estudiante.setEdad(estudianteDetails.getEdad());
        estudiante.setCurso(estudianteDetails.getCurso());
        // No actualices el usuario aquí por seguridad

        return estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    // Método para verificar si el usuario autenticado es dueño del estudiante
    public boolean isOwner(Long estudianteId, String username) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(estudianteId);
        return estudiante.isPresent() && estudiante.get().getUsuario().getUsername().equals(username);
    }
}