package com.jms.jmoneyapi.model;

import java.io.Serializable;

public interface IEntity<T extends Serializable> {
	public T getId();
}
