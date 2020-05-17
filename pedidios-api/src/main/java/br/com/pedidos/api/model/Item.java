package br.com.pedidos.api.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Item {
	
	@EmbeddedId
	private ItemId id;
	
	@ManyToOne
	@MapsId("produtoId")
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("pedidoId")
	private Pedido pedido;
	
	private Integer qtde;
	
	public Item(ItemId id) {
		this.id = id;
	}
	
	@Data @NoArgsConstructor @AllArgsConstructor
	@Embeddable
	public static class ItemId implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long produtoId;
		private Long pedidoId;
	}
}
