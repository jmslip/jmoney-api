package com.jms.jmoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jms.jmoneyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
