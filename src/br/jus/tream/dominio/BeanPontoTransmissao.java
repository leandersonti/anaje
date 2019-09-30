package br.jus.tream.dominio;

import java.util.List;

public class BeanPontoTransmissao {

	private UnidadeServico unidadeServico;
	
	private List<CADLocalvotacao> secoesDistribuidas;
	
	private List<DistribuicaoEquipamento> equipamentosDistribuidos;
	
	private List<DistribuicaoTecnicoUS> tecnicosDistribuidos;

	public UnidadeServico getUnidadeServico() {
		return unidadeServico;
	}

	public void setUnidadeServico(UnidadeServico unidadeServico) {
		this.unidadeServico = unidadeServico;
	}

	public List<CADLocalvotacao> getSecoesDistribuidas() {
		return secoesDistribuidas;
	}

	public void setSecoesDistribuidas(List<CADLocalvotacao> secoesDistribuidas) {
		this.secoesDistribuidas = secoesDistribuidas;
	}

	public List<DistribuicaoEquipamento> getEquipamentosDistribuidos() {
		return equipamentosDistribuidos;
	}

	public void setEquipamentosDistribuidos(List<DistribuicaoEquipamento> equipamentosDistribuidos) {
		this.equipamentosDistribuidos = equipamentosDistribuidos;
	}

	public List<DistribuicaoTecnicoUS> getTecnicosDistribuidos() {
		return tecnicosDistribuidos;
	}

	public void setTecnicosDistribuidos(List<DistribuicaoTecnicoUS> tecnicosDistribuidos) {
		this.tecnicosDistribuidos = tecnicosDistribuidos;
	}
	
	
		
	
}
