package com.example.examen.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entity.Habilidad;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {

    
}
