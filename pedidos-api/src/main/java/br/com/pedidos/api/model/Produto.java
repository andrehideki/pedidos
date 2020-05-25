package br.com.pedidos.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor 
@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String descricao;
	@Min(value = 0)
	private Double valor;
	
	@OneToMany
	@JoinTable(
		name = "form_prod", 
		joinColumns = @JoinColumn(
			name = "prod_id",
			referencedColumnName = "id"
		), inverseJoinColumns = @JoinColumn(
			name = "forn_id",
			table = "fornecedor",
			referencedColumnName = "id"
		))
	private List<Fornecedor> fornecedores;
}
