package com.coatic.coatic.service;

import com.coatic.coatic.entidades.*;
import com.coatic.coatic.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    // Funcion que permite obtener todas las cursos en una lista
    public List<Curso> getAll(){
        List<Curso> lista = new ArrayList<Curso>();
        cursoRepository.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    //Funcion para obtener curso por ID
    public Curso getById(Long id){
        return cursoRepository.findById(id).get();
    }

    //Guardar en la base de datos al curso
    public void save(Curso curso){
        cursoRepository.save(curso);
    }

    //Borrar el curso segun el id
    public void delete(Long id){
        cursoRepository.deleteById(id);
    }
    
}
