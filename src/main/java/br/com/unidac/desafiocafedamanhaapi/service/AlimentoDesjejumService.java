package br.com.unidac.desafiocafedamanhaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumFormDto;
import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumFormDtoAtualizar;
import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumOutputDto;
import br.com.unidac.desafiocafedamanhaapi.infra.RegrasDeNegocioException;
import br.com.unidac.desafiocafedamanhaapi.modelo.AlimentoDesjejum;
import br.com.unidac.desafiocafedamanhaapi.modelo.Colaborador;
import br.com.unidac.desafiocafedamanhaapi.repository.AlimentoDesjejumRepository;
import br.com.unidac.desafiocafedamanhaapi.repository.ColaboradorRepository;

@Service
public class AlimentoDesjejumService {

	@Autowired
	private AlimentoDesjejumRepository alimentoRepository;
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Page<AlimentoDesjejumOutputDto> listar(Pageable paginacao) {
		Page<AlimentoDesjejum> alimentos = alimentoRepository.findAll(paginacao);
		return alimentos.map(a -> modelMapper.map(alimentos, AlimentoDesjejumOutputDto.class));
	}

	@Transactional
	public void cadastrar(List<AlimentoDesjejumFormDto> alimentosForm,Colaborador colaborador){
		List<AlimentoDesjejum> alimentos = alimentosForm.stream()
															.map(a -> modelMapper.map(a, AlimentoDesjejum.class))
															.collect(Collectors.toList()); 
		if(!verificaAlimentosCadastrados(alimentos)) {
			colaborador.setAlimentos(alimentos);
			alimentos.stream().forEach(a -> a.setColaborador(colaborador));
		}
	}
	
	@Transactional
	public AlimentoDesjejumOutputDto atualizar(AlimentoDesjejumFormDtoAtualizar alimentoFormDto) {
		AlimentoDesjejum alimento = alimentoRepository.
										findById(alimentoFormDto.getId())
										.orElseThrow(() -> new RegrasDeNegocioException("Alimento não encontrado!"));
		Colaborador colaborador = colaboradorRepository.findByCpf(alimentoFormDto.getColaboradorCpf())
														.orElseThrow(() -> new RegrasDeNegocioException("Colaborador não encontrado!"));
		
		if(!alimento.verificaAlimentoPertencenteAColaborador(colaborador)) {
			lancarExceptionAlimentoDeOutroColaborador();
		}
		
		alimento.atualizarInformacoes(alimentoFormDto.getNome(),colaborador);
		
		return modelMapper.map(alimento, AlimentoDesjejumOutputDto.class);
	}
	
	public void remover(Long id, String cpf) {
		AlimentoDesjejum alimento = alimentoRepository.findById(id).orElseThrow();
		Colaborador colaborador = colaboradorRepository.findByCpf(cpf).orElseThrow();
		
		if(!alimento.verificaAlimentoPertencenteAColaborador(colaborador)) {
			lancarExceptionAlimentoDeOutroColaborador();
		}
		
		alimentoRepository.deleteById(id);
	}
	
	private boolean verificaAlimentosCadastrados(List<AlimentoDesjejum> alimentos) {
		String resposta;
		for (AlimentoDesjejum alimentoDesjejum : alimentos) {
			boolean existe = alimentoRepository
					.existePorNomeSemEspacos(alimentoDesjejum.getNome().toLowerCase().trim().replaceAll("\\s+", ""));
			if (existe) {
				resposta = "Alguém já irá trazer " + alimentoDesjejum.getNome() + ". Tente outro alimento.";
				throw new RegrasDeNegocioException(resposta);
			}
		}

		return false;
	}
	
	private void lancarExceptionAlimentoDeOutroColaborador() {
		throw new RegrasDeNegocioException("Esse alimento é de outro colaborador. Informe um dos seus!");
	}
}
