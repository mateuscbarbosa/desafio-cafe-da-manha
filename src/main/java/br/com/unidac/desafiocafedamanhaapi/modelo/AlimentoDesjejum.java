package br.com.unidac.desafiocafedamanhaapi.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alimentos")
@ToString
public class AlimentoDesjejum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "colaborador_id")
	private Colaborador colaborador;

	public void atualizarInformacoes(String nome, Colaborador colaborador) {
		this.nome = nome;
		this.colaborador = colaborador;
	}
	
	public boolean verificaAlimentoPertencenteAColaborador(Colaborador colaborador) {
		return this.colaborador.equals(colaborador);
	}
}
