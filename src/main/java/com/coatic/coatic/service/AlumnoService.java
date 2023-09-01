package com.coatic.coatic.service;

import com.coatic.coatic.entidades.*;
import com.coatic.coatic.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    // Funcion que permite obtener todas las alumnos en una lista
    public List<Alumno> getAll(){
        List<Alumno> lista = new ArrayList<Alumno>();
        alumnoRepository.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    //Funcion para obtener alumno por ID
    public Alumno getById(Long id){
        return alumnoRepository.findById(id).get();
    }

    //Guardar en la base de datos al alumno
    public void save(Alumno alumno){
        alumnoRepository.save(alumno);
    }

    //Borrar el alumno segun el id
    public void delete(Long id){
        alumnoRepository.deleteById(id);;
    }
    
}
