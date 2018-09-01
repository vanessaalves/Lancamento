package br.com.matrix.lancamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity

public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;
	
	@NotEmpty(message = "CNPJ não pode ser vazio")
	@Size(max = 14, message = "CNPJ precisa ter até 14 caracteres")
	private String cnpj;
	private String nomeResponsavel;
	private String contato;
	
}
