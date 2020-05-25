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

import br.com.pedidos.api.model.Produto;
import br.com.pedidos.api.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> gravar(@RequestBody @Valid Produto produto) {
		Produto produtoSalvo = produtoRepository.save(produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, 
			@RequestBody @Valid Produto produto) {
		Optional<Produto> optionalProduto = produtoRepository.findById(id);
		if (!optionalProduto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Produto salvo = optionalProduto.get();
		BeanUtils.copyProperties(produto, salvo, "id");
		salvo = produtoRepository.save(salvo);
		
		return ResponseEntity.ok(salvo);
	}
	
	
	@DeleteMapping("{id}")
	public void excluir(@PathVariable Integer id) {
		produtoRepository.deleteById(id);
	}
}
