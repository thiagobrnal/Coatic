package com.coatic.coatic.repository;

import com.coatic.coatic.entidades.*;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long>{

    Optional<Rol> findByNombre(String string);
}