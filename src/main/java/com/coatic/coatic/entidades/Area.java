 package com.coatic.coatic.entidades;

import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Campo obligatorio")
    @Size(max = 100, message = "Nombre demasiado largo")
    @Column(unique = true)
    private String nombre;
    
    //El area conoce a muchos cursos
    @OneToMany(mappedBy = "area")
    private Set<Curso> cursos;

    public void quitarCurso(Curso curso) {
        this.getCursos().remove(curso);
    }
    
    public void agregarCurso(Curso curso) {
        this.getCursos().add(curso);
    }
   
}