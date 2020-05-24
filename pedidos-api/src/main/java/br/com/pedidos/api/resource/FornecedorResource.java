package br.com.pedidos.api.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedidos.api.model.Fornecedor;
import br.com.pedidos.api.repository.FornecedorRepository;

@RestController
@RequestMapping("fornecedor")
public class FornecedorResource {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@GetMapping
	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> gravar(@RequestBody @Valid Fornecedor fornecedor) {
		Fornecedor salvo = fornecedorRepository.save(fornecedor);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, 
			@RequestBody @Valid Fornecedor fornecedor) {
		Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
		if (!fornecedorOptional.isPresent()) 
			return ResponseEntity.notFound().build();
		Fornecedor salvo = fornecedorOptional.get();
		BeanUtils.copyProperties(fornecedor, salvo, "id");
		salvo = fornecedorRepository.save(salvo);
		return ResponseEntity.ok(salvo); 
	}
	
	@DeleteMapping("{id}")
	public void excluir(@PathVariable Integer id) {
		fornecedorRepository.deleteById(id);
	}
}
