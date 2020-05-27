package br.com.pedidos.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hist_prod")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Historico {

	@Id
	private Date data;
	
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Produto produto;
	
	public Historico(Produto produto) {
		this.produto = produto;
		this.valor = produto.getValor();
		this.data = new Date();
	}
	
	@Data
	@NoArgsConstructor @AllArgsConstructor
	public static class HistoricoId implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer id;
		private Date data;
	}
}
