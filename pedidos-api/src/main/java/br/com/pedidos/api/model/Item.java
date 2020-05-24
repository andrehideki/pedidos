package br.com.pedidos.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "ped_prod")
@Data 
@AllArgsConstructor @NoArgsConstructor
public class Item {

	@Id
	@Column(name = "prod_id", insertable = true)
	private Integer id;
	@OneToOne
	@JoinColumn(name = "prod_id")
	private Produto produto;
	
	private Integer qtde;

}
