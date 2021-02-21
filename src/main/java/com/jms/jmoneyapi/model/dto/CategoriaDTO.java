package com.jms.jmoneyapi.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO {

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 30)
	private String nome;
}
