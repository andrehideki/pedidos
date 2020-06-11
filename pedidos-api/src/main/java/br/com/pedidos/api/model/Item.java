package br.com.pedidos.api.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@AllArgsConstructor
@Entity
@Table(name = "ped_prod")
public class Item {

	@EmbeddedId
	private ItemId id;
	 
	@ManyToOne
	@JoinColumn(name = "prod_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private Produto produto;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ped_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
	private Pedido pedido;
	
	private Integer qtde;
	
	public Item() {
		id = new ItemId();
	}
	
	@Data 
	@NoArgsConstructor @AllArgsConstructor
	@Embeddable
	public static class ItemId implements Serializable {
		private static final long serialVersionUID = 1L;
		@Column(name = "prod_id")
		private Integer prodId;
		@Column(name = "ped_id")
		private Integer pedId;
	}

}
