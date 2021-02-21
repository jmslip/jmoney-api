package com.jms.jmoneyapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jms.jmoneyapi.model.Categoria;
import com.jms.jmoneyapi.model.dto.CategoriaDTO;
import com.jms.jmoneyapi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public List<Categoria> buscarTodasCategorias() {
		return categoriaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria.setNome(categoriaDTO.getNome());

		Categoria categoriaPersistida = categoriaRepository.save(categoria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(categoriaPersistida.getId()).toUri();

		return ResponseEntity.created(uri).body(categoriaPersistida);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarCategoriaById(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);

		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}

}
