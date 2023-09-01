package com.coatic.coatic.repository;

import com.coatic.coatic.entidades.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

    Usuario findByEmail(String email);

    boolean existsByEmail(String email);

}
