package com.coatic.coatic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.coatic.coatic.entidades.*;

@Repository
public interface AreaRepository extends CrudRepository<Area, Long> {
    
     @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Curso c WHERE c.area.id = ?1")
    boolean hasReferences(Long id);

}

