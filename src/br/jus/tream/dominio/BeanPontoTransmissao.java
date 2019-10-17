package br.jus.tream.dominio;

import java.util.List;

public class BeanPontoTransmissao {

	private PontoTransmissao pontoTransmissao;
	
	private List<CADLocalvotacao> secoesDistribuidas;
	
	private List<DistribuicaoEquipamento> equipamentosDistribuidos;
	
	private List<DistribuicaoTecnico> tecnicosDistribuidos;

	public PontoTransmissao getPontoTransmissao() {
		return pontoTransmissao;
	}

	public void setPontoTransmissao(PontoTransmissao pontoTransmissao) {
		this.pontoTransmissao = pontoTransmissao;
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

	public List<DistribuicaoTecnico> getTecnicosDistribuidos() {
		return tecnicosDistribuidos;
	}

	public void setTecnicosDistribuidos(List<DistribuicaoTecnico> tecnicosDistribuidos) {
		this.tecnicosDistribuidos = tecnicosDistribuidos;
	}

	
}
