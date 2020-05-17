package br.com.pedidos.api.resource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedidos.api.model.Item;
import br.com.pedidos.api.model.Item.ItemId;
import br.com.pedidos.api.model.Pedido;
import br.com.pedidos.api.model.Produto;
import br.com.pedidos.api.repository.ItemRepository;
import br.com.pedidos.api.repository.PedidoRepository;

@RestController
@RequestMapping("pedido")
public class PedidoResource {
	
	@Autowired
	private PedidoRepository repository;
	@Autowired
	private ItemRepository itemRepository;
	
	
	@GetMapping
	public List<Pedido> listar() {
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pedido> inserir() {
		Set<Item> itens = new HashSet();
		Pedido p = new Pedido(null, "Fornecedor", "", null);
		p = repository.save(p);
		Produto prod = new Produto(1l, null, null);
		Item i = new Item();
		i.setPedido(p);
		i.setProduto(prod);
		i.setId(new ItemId(prod.getId(), p.getId()));
		itemRepository.save(i);
		return null;
	}
	
}
