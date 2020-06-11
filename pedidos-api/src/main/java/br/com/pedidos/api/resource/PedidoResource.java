package br.com.pedidos.api.resource;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedidos.api.model.Fornecedor;
import br.com.pedidos.api.model.Item;
import br.com.pedidos.api.model.Pedido;
//import br.com.pedidos.api.repository.ItemRepository;
import br.com.pedidos.api.repository.PedidoRepository;
import br.com.pedidos.api.repository.ProdutoRepository;

@RestController
@RequestMapping("pedido")
public class PedidoResource {
	
	@Autowired
	private PedidoRepository pedidoRepository;
//	private ItemRepository itemRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> gravar(@RequestBody @Valid List<Item> itens) {
		Pedido pedido = new Pedido();
		pedido.setData(new Date());
		pedido.setFornecedor(new Fornecedor(1, null, null));
		pedido.setTotal(0d);
		pedido = pedidoRepository.save(pedido);
		pedido.setItens(itens);
		for (Item item: pedido.getItens()) {
			item.setPedido(pedido);
			item.setId(new Item.ItemId(item.getProduto().getId(), pedido.getId()));
		}
		pedido = pedidoRepository.save(pedido);
		
		return ResponseEntity.ok(pedido);
	}
	
}
