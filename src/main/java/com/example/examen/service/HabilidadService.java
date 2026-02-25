package com.example.examen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen.entity.Habilidad;
import com.example.examen.repository.HabilidadRepository;


@Service
public class HabilidadService {

    @Autowired
    private HabilidadRepository habilidadRepository;

    
    public List<Habilidad> listarTodos() {
        return habilidadRepository.findAll();
    }

   
    public Habilidad buscarPorId(Long id) {
        return habilidadRepository.findById(id)
                .orElseThrow(() -> 
                new IllegalArgumentException("habilidad no encontrado con ID: " + id));
    }
  
    public Habilidad guardar(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }

    public void eliminar(Long id) {
        habilidadRepository.deleteById(id);
    }

  
}
