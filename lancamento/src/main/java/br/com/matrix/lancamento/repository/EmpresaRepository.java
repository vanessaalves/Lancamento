package br.com.matrix.lancamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matrix.lancamento.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	List<Empresa> findByNomeIgnoreCaseContaining(String nome);
	
	List<Empresa> findByCnpj(String cnpj);
	
}
