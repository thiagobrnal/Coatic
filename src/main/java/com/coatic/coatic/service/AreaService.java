package com.coatic.coatic.service;

import com.coatic.coatic.entidades.*;
import com.coatic.coatic.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AreaService {

    @Autowired
    AreaRepository areaRepository;

    // Funcion que permite obtener todas las areas en una lista
    public List<Area> getAll(){
        List<Area> lista = new ArrayList<Area>();
        areaRepository.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    //Funcion para obtener area por ID
    public Area getById(Long id){
        return areaRepository.findById(id).get();
    }

    //Guardar en la base de datos al area
    public void save(Area area){
        areaRepository.save(area);
    }

    //Borrar el area segun el id
    public void delete(Long id){
        areaRepository.deleteById(id);;
    }
    
}
