package com.jms.jmoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jms.jmoneyapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
