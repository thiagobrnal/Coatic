package com.coatic.coatic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.coatic.coatic.entidades.*;

@Repository
public interface CursoRepository extends CrudRepository <Curso, Long>{
    
}
