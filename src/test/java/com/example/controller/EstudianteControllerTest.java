package com.example.controller;

import com.example.model.Estudiante;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void createEstudianteWithoutNombreShouldFail() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setCorreo("test@email.com");
        estudiante.setEdad(20);
        estudiante.setCurso("1A");
        // No se establece nombre

        mockMvc.perform(post("/estudiante")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(estudiante)))
                .andExpect(status().isBadRequest());
    }
}