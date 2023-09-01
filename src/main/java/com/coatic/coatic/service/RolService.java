package com.coatic.coatic.service;

import com.coatic.coatic.entidades.*;
import com.coatic.coatic.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public List<Rol> getAll(){
        List<Rol> lista = new ArrayList<Rol>();
        rolRepository.findAll().forEach(registro -> lista.add(registro));
        return lista;
    }

    public Rol getById(Long id){
        return rolRepository.findById(id).get();
    }
    
    public void save(Rol rol){
        rolRepository.save(rol);
    }

    public void delete(long id){
        rolRepository.deleteById(id);
    }
}
