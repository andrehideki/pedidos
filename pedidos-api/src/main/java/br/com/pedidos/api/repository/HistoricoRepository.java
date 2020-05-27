package br.com.pedidos.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pedidos.api.model.Historico;
import br.com.pedidos.api.model.Historico.HistoricoId;
import br.com.pedidos.api.model.Produto;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, HistoricoId>{
	@Query("select h from Historico h where h.produto.id = ?1")
	public List<Historico> findByProdutoId(Integer id);
	
	@Transactional
	public void deleteByProduto(Produto produto);
}
