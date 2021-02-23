package com.jms.jmoneyapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jms.jmoneyapi.domain.utils.Constants;
import com.jms.jmoneyapi.model.Endereco;
import com.jms.jmoneyapi.model.Pessoa;
import com.jms.jmoneyapi.model.dto.PessoaDTO;
import com.jms.jmoneyapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaReource extends EntityGenericResource<Long> {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> getPessoas() {
		return pessoaRepository.findAll();
	}

	@GetMapping(Constants.PATH_ID)
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);

		return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Pessoa> cadastraPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {

		Endereco endereco = null;
		if (Objects.nonNull(pessoaDTO.getEndereco())) {
			endereco = buildEndereco(pessoaDTO);
		}

		Pessoa pessoa = new Pessoa();

		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setEndereco(endereco);
		pessoa.setStatus(pessoaDTO.getAtivo());

		Pessoa pessoaPersistida = pessoaRepository.save(pessoa);

		URI uri = getUri(pessoaPersistida, Constants.PATH_ID);

		return ResponseEntity.created(uri).body(pessoaPersistida);
	}

	@PutMapping(Constants.PATH_ID)
	public ResponseEntity<Pessoa> atualizaPessoa(@PathVariable Long id, @Valid @RequestBody PessoaDTO pessoaDTO) {

		Optional<Pessoa> pessoaOptioanal = pessoaRepository.findById(id);

		if (pessoaOptioanal.isPresent()) {
			Pessoa pessoa = pessoaOptioanal.get();

			if (Objects.nonNull(pessoaDTO.getEndereco())) {
				Endereco endereco = buildEndereco(pessoaDTO);
				pessoa.setEndereco(endereco);
			}

			pessoa.setNome(pessoaDTO.getNome());
			pessoa.setStatus(pessoaDTO.getAtivo());
			pessoa.setId(id);

			Pessoa pessoaPersistida = pessoaRepository.save(pessoa);

			URI uri = getUri(pessoaPersistida, null);

			return ResponseEntity.status(HttpStatus.OK).location(uri).body(pessoaPersistida);
		}

		return ResponseEntity.badRequest().build();
	}

	private Endereco buildEndereco(PessoaDTO pessoaDTO) {
		Endereco endereco;
		endereco = new Endereco();

		endereco.setLogradouro(pessoaDTO.getEndereco().getLogradouro());
		endereco.setNumero(pessoaDTO.getEndereco().getNumero());
		endereco.setComplemento(pessoaDTO.getEndereco().getLogradouro());
		endereco.setBairro(pessoaDTO.getEndereco().getBairro());
		endereco.setCep(pessoaDTO.getEndereco().getCep());
		endereco.setCidade(pessoaDTO.getEndereco().getCidade());
		endereco.setEstado(pessoaDTO.getEndereco().getEstado());
		return endereco;
	}

}
