package com.example.examen.integration;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.examen.entity.Habilidad;
import com.example.examen.entity.Naufrago;
import com.example.examen.service.NaufragoService;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Utiliamos la configuracion de test en test/resources/ para usar H2 (test ewn memoria)
public class NaufragoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NaufragoService naufragoService; 
    
    @Test
    @DisplayName("Test 1: Flujo completo API REST")
    public void testFlujoCompletoAPI() throws Exception {
    	
    	//Creamos los objetos para probar en el test
        Naufrago tom = new Naufrago("Tom", 45, "Hombre", "Kanto", "Japon", "2024-01-01", 1);
        Habilidad h1 = new Habilidad("Fuego", 10, "Hace fuego", "Media", "Supervivencia");

        // Guardar via REST
        mockMvc.perform(post("/api/naufragos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tom)))
                .andExpect(status().isOk());

        // Listar via REST
        mockMvc.perform(get("/api/naufragos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].nombre").value(hasItem("Tom")));
    }

   
    @Test
    @DisplayName("Test 2: Guardado directo Chuck y Wilson")
    public void testCreacionChuckYWilsonDirecto() {
        //Crear objetos
        Naufrago chuck = new Naufrago("Chuck", 40, "Hombre", "Dressrosa", "EEUU", "2025-12-12", 2);
        Naufrago wilson = new Naufrago("Wilson", 5, "Otro", "East Blue", "Balón", "2025-12-12", 3);

        // Guardar directamente usando el service (Capa MVC) va directo a la lógica de Spring
        Naufrago guardadoChuck = naufragoService.guardar(chuck);
        Naufrago guardadoWilson = naufragoService.guardar(wilson);

        // Comprobar que se han generado id (están en la base de datos H2)
        assertNotNull(guardadoChuck.getId(), "Chuck deberia tener un ID asignado por H2");
        assertNotNull(guardadoWilson.getId(), "Wilson deberia tener un ID asignado por H2");
        
        // Comprobar que ambos existen en el listado del servicio
        boolean existenAmbos = naufragoService.listarTodos().stream()
                .anyMatch(n -> n.getNombre().equals("Chuck")) &&
                naufragoService.listarTodos().stream()
                .anyMatch(n -> n.getNombre().equals("Wilson"));

        assert(existenAmbos);
    }
}