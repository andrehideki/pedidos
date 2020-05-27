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

import br.com.pedidos.api.model.Historico;
import br.com.pedidos.api.model.Produto;
import br.com.pedidos.api.repository.HistoricoRepository;
import br.com.pedidos.api.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private HistoricoRepository historicoRepository;
	
	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> gravar(@RequestBody @Valid Produto produto) {
		
		Produto salvo = produtoRepository.save(produto);
		Historico historico = new Historico(salvo);
		historicoRepository.save(historico);
			
		return ResponseEntity.ok(salvo);
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
		
		Historico historico = new Historico(salvo);
		historicoRepository.save(historico);
		
		return ResponseEntity.ok(salvo);
	}
	
	
	@DeleteMapping("{id}")
	public void excluir(@PathVariable Integer id) {
		List<Historico> historicos = historicoRepository.findByProdutoId(id);
		for (Historico h: historicos) {
			historicoRepository.deleteByProduto(h.getProduto());
		}
		produtoRepository.deleteById(id);
	}
	
	@GetMapping("historico/{id}")
	public List<Historico> mostraHistorico(@PathVariable Integer id) {
		return historicoRepository.findByProdutoId(id);
	}
	
}
