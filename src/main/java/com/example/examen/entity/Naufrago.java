package com.example.examen.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "naufragos")
public class Naufrago {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer edad;
    
	private String sexo;

    private String isla;
    
    private String nacionalidad;
    
    private String fecha_rescate;
    
    private Integer habilidad;

    public Naufrago() {
        super();
    }

 
    public Naufrago(String nombre, Integer edad, String sexo,String isla,String nacionalidad,String fecha_rescate, Integer habilidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.isla = isla;
        this.nacionalidad = nacionalidad;
        this.fecha_rescate = fecha_rescate;
        this.habilidad = habilidad;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getIsla() {
		return isla;
	}


	public void setIsla(String isla) {
		this.isla = isla;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getFecha_rescate() {
		return fecha_rescate;
	}


	public void setFecha_rescate(String fecha_rescate) {
		this.fecha_rescate = fecha_rescate;
	}


	public Integer getHabilidad() {
		return habilidad;
	}


	public void setHabilidad(Integer habilidad) {
		this.habilidad = habilidad;
	}

}
