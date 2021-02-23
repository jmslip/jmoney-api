package com.jms.jmoneyapi.model;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Endereco {

	private String logradouro;

	private Integer numero;

	private String complemento;

	private String bairro;

	private String cep;

	private String cidade;

	private String estado;
}
