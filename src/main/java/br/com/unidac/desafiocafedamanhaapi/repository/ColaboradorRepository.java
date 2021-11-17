package br.com.unidac.desafiocafedamanhaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.unidac.desafiocafedamanhaapi.modelo.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

	Optional<Colaborador> findByCpf(String cpf);

	boolean existsByCpf(String cpf);

	@Query("SELECT c FROM AlimentoDesjejum a JOIN a.colaborador c WHERE a.id=:id ")
	Optional<Colaborador> encontrePorAlimentoId(Long id);
}
