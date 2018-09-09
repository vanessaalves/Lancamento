package br.com.matrix.lancamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	private String tipo;
	private LocalDate vencimento;
	private BigDecimal valor;
	
}
