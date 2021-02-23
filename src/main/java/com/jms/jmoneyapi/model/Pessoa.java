package com.jms.jmoneyapi.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jms.jmoneyapi.domain.utils.Constants;
import com.jms.jmoneyapi.domain.utils.enums.StatusEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = Constants.TB_PESSOA, schema = Constants.SCHEMA_NAME)
@SequenceGenerator(name = Constants.SQ_PESSOA, sequenceName = Constants.SQ_PESSOA, allocationSize = 1, schema = Constants.SCHEMA_NAME)
public class Pessoa implements IEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SQ_PESSOA)
	private Long id;

	private String nome;

	@Embedded
	private Endereco endereco;

	@Column(name = "ativo", columnDefinition = "smallint")
	private StatusEnum status = StatusEnum.ATIVO;

}
