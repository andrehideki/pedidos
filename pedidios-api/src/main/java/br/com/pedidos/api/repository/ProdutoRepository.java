package br.com.pedidos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedidos.api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
