package br.com.unidac.desafiocafedamanhaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.unidac.desafiocafedamanhaapi.modelo.AlimentoDesjejum;

public interface AlimentoDesjejumRepository extends JpaRepository<AlimentoDesjejum, Long>{

	@Query("SELECT CASE WHEN COUNT(a)> 0 THEN TRUE ELSE FALSE END FROM AlimentoDesjejum a WHERE LOWER(REPLACE(a.nome,' ','')) LIKE :nome")
	boolean existePorNomeSemEspacos(String nome);
}
