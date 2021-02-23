package com.jms.jmoneyapi.domain.utils.enums;

public interface BaseEnum<T> {

	public String name();

	public T getChave();

	public String getDescricao();
}
