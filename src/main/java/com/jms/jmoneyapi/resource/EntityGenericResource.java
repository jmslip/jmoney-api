package com.jms.jmoneyapi.resource;

import java.io.Serializable;
import java.net.URI;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jms.jmoneyapi.model.IEntity;

public abstract class EntityGenericResource<T extends Serializable> {

	protected URI getUri(IEntity<T> entity, String path) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path(Optional.ofNullable(path).orElse(StringUtils.EMPTY))
				.buildAndExpand(entity.getId()).toUri();
	}
}
