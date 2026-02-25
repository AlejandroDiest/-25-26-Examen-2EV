package com.example.examen.restcontroller;

import com.example.examen.entity.Habilidad;
import com.example.examen.service.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadRestController {

    @Autowired
    private HabilidadService habilidadService;

    @GetMapping
    public List<Habilidad> listarTodas() {
        return habilidadService.listarTodos();
    }

    @PostMapping
    public Habilidad crear(@RequestBody Habilidad habilidad) {
        return habilidadService.guardar(habilidad);
    }
}