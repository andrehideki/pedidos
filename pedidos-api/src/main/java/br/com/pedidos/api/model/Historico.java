package br.com.pedidos.api.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Historico {
	private Long id;
	private Date data;
	private Double valor;
}
