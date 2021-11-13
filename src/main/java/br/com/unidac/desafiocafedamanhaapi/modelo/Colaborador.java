package br.com.unidac.desafiocafedamanhaapi.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "colaboradores")
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Long cpf;
	
	@OneToMany
	private List<AlimentoDesjejum> alimentos = new ArrayList<>();

	public void atualizarInformacoes(String nome, Long cpf, List<AlimentoDesjejum> alimentos) {
		this.nome=nome;
		this.cpf=cpf;
		this.alimentos=alimentos;
	}
}
