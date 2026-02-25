package com.example.examen.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "habilidades")
public class Habilidad {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String nombre;

	private String descripcion;
 
    private String dificultad;
    
    private Integer experiencia;
    
    private String categoria;
    


    public Habilidad() {
        super();
    }

 
    public Habilidad(String nombre, Integer experiencia, String descripcion,String dificultad,String categoria) {
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.categoria = categoria;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getDificultad() {
		return dificultad;
	}


	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}


	public Integer getExperiencia() {
		return experiencia;
	}


	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
