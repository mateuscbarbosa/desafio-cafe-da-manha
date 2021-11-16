package br.com.unidac.desafiocafedamanhaapi.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorFormDto;
import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorFormDtoAtualizar;
import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorOutputDto;
import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorOutputDtoDetalhado;
import br.com.unidac.desafiocafedamanhaapi.infra.RegrasDeNegocioException;
import br.com.unidac.desafiocafedamanhaapi.modelo.Colaborador;
import br.com.unidac.desafiocafedamanhaapi.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private AlimentoDesjejumService alimentoService;
	
	@Autowired
	private ModelMapper modelMapper;

		
	public Page<ColaboradorOutputDto> listar(Pageable paginacao){
		Page<Colaborador> colaboradores = colaboradorRepository.findAll(paginacao);
		return colaboradores.map(c -> modelMapper.map(c, ColaboradorOutputDto.class));
	}

	@Transactional
	public ColaboradorOutputDto cadastrar(ColaboradorFormDto colaboradorForm) {
		Colaborador colaborador = modelMapper.map(colaboradorForm, Colaborador.class);
		colaborador.setId(null);
		
		boolean temCpfCadastrado = colaboradorRepository.existsByCpf(colaborador.getCpf());
		if(temCpfCadastrado) {
			throw new RegrasDeNegocioException("CPF indisponível!");
		}
		
		alimentoService.cadastrarAutomatico(colaboradorForm.getAlimentos(), colaborador);
		
		colaboradorRepository.save(colaborador);
		
		return modelMapper.map(colaborador, ColaboradorOutputDto.class);
	}

	@Transactional
	public ColaboradorOutputDto atualizar(ColaboradorFormDtoAtualizar colaboradorForm) {
		Colaborador colaborador = colaboradorRepository.findById(colaboradorForm.getId()).get();
		
		colaborador.atualizarInformacoes(colaboradorForm.getNome(), colaboradorForm.getCpf(), colaborador.getAlimentos());
		return modelMapper.map(colaborador, ColaboradorOutputDto.class);
	}

	@Transactional
	public void remover(Long id) {
		colaboradorRepository.deleteById(id);
	}

	public ColaboradorOutputDtoDetalhado detalhar(Long id) {
		Colaborador colaborador = colaboradorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		return modelMapper.map(colaborador, ColaboradorOutputDtoDetalhado.class);
	}

}
