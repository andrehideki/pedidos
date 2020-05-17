package br.com.pedidos.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedidos.api.model.Produto;
import br.com.pedidos.api.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoResource {
	
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	public ResponseEntity<Produto> salvar(Produto produto) {
		Produto produtoSalvo = produtoRepository.save(produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	
}
