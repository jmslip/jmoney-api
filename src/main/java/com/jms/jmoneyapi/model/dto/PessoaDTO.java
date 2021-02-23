package com.jms.jmoneyapi.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jms.jmoneyapi.domain.utils.enums.StatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 100)
	private String nome;

	private EnderecoDTO endereco;

	@NotNull
	private StatusEnum ativo;
}
