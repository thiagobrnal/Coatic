package com.coatic.coatic.entidades;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String dni;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    //muchos alumnos se relacionan con muchos cursos
    @ManyToMany(mappedBy = "alumnos", fetch = FetchType.EAGER)
    private Set<Curso> cursos;
  
    public void agregarCurso(Curso curso) {
        this.getCursos().add(curso);
    }
    
   
}
