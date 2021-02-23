package com.jms.jmoneyapi.domain.utils.enums;

public enum StatusEnum implements BaseEnum<Integer> {

	DESATIVADO(0, "Desativado"), ATIVO(1, "Ativo");

	private final Integer chave;
	private final String descricao;

	StatusEnum(Integer chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	@Override
	public Integer getChave() {
		return this.chave;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}
}
