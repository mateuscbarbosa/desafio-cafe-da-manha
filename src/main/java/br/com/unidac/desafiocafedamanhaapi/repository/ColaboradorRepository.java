package br.com.unidac.desafiocafedamanhaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unidac.desafiocafedamanhaapi.modelo.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

	Optional<Colaborador> findByCpf(String cpf);

	boolean existsByCpf(String cpf);

}
