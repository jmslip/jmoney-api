package com.jms.jmoneyapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jms.jmoneyapi.domain.utils.Constants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = Constants.TB_CATEGORIA, schema = Constants.SCHEMA_NAME)
@SequenceGenerator(name = Constants.SQ_CATEGORIA, sequenceName = Constants.SQ_CATEGORIA, allocationSize = 1, schema = Constants.SCHEMA_NAME)
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SQ_CATEGORIA)
	private Long id;

	private String nome;
}
