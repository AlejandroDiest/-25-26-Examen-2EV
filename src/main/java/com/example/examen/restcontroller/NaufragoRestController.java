package com.example.examen.restcontroller;

import com.example.examen.entity.Naufrago;
import com.example.examen.service.NaufragoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/naufragos")
public class NaufragoRestController {

    @Autowired
    private NaufragoService naufragoService;

    @GetMapping
    public List<Naufrago> listarTodos() {
        return naufragoService.listarTodos();
    }

    @PostMapping
    public Naufrago crear(@RequestBody Naufrago naufrago) {
        return naufragoService.guardar(naufrago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Naufrago> actualizar(@PathVariable Long id, @RequestBody Naufrago naufragoActualizado) {
        Naufrago naufragoExistente = naufragoService.buscarPorId(id);
        
        if (naufragoExistente != null) {
            naufragoActualizado.setId(id);
            return ResponseEntity.ok(naufragoService.guardar(naufragoActualizado));
        }
        
        return ResponseEntity.notFound().build();
    }
}