package com.coatic.coatic.entidades;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 100, message = "Nombre demasiado largo")
    @Column(unique = true)
    private String nombre;

    private int meses;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date inicio;

    private String imagen;

    private boolean presencial;

    //muchos cursos se relacionan con el mismo area
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Area area;
    
    //Muchos cursos se relacionan con muchos alumnos
    @ManyToMany
    @JoinTable(name = "alumno_curso",//nombre tabla intermedia
            joinColumns = @JoinColumn(name = "id_curso"),//nombre de la fila de id cursos
            inverseJoinColumns = @JoinColumn(name = "id_alumno")//nombre de la fila de id alumnos (la cual es la inversa o la del otro lado de la tabla)
    )
    private Set<Alumno> alumnos;
    
    public void agregarAlumno(Alumno alumno) {
        this.getAlumnos().add(alumno);
    }
    
   
}
