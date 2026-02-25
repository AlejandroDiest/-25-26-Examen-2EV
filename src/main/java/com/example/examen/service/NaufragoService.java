package com.example.examen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen.entity.Naufrago;
import com.example.examen.repository.NaufragoRepository;


@Service
public class NaufragoService {

    @Autowired
    private NaufragoRepository naufragoRepository;

    
    public List<Naufrago> listarTodos() {
        return naufragoRepository.findAll();
    }

   
    public Naufrago buscarPorId(Long id) {
        return naufragoRepository.findById(id)
                .orElseThrow(() -> 
                new IllegalArgumentException("Naufrago no encontrado con ID: " + id));
    }
  
    public Naufrago guardar(Naufrago naufrago) {
        return naufragoRepository.save(naufrago);
    }

    public void eliminar(Long id) {
        naufragoRepository.deleteById(id);
    }

  
}
