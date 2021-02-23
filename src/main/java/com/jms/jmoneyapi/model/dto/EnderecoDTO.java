package com.jms.jmoneyapi.model.dto;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

	@Size(min = 1, max = 100)
	private String logradouro;

	private Integer numero;

	@Size(min = 1, max = 100)
	private String complemento;

	@Size(min = 1, max = 100)
	private String bairro;

	@Size(min = 8, max = 10)
	private String cep;

	@Size(min = 1, max = 100)
	private String cidade;

	@Size(min = 2, max = 2)
	private String estado;
}
