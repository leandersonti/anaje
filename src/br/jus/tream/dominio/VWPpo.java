package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="vw_ppo")
public class VWPpo implements Serializable{

	@Id
	@Column(name="id_tecnico")
	private Integer idTecnico;
	
	@Column(name="id_tecnico_resp")
	private Integer idTecnicoResp;
	
	@Column(name="idus")
	private Integer idus;
	
	@Column(name="descricao")
	private String pontoTransmissao;
	
	private Integer oficial;
	
	private String nome;
	
	private Integer zona;
	
	private Integer codmunic;

	@Column(name="cc")
	private String chegouCartorio;
	
	@Column(name="cp")
	private String chegouPonto;
	
	@Column(name="os")
	private String oficializouSistema;
	
	@Column(name="en")
	private String encerrou;
	
	public VWPpo() {
	}

	public VWPpo(Integer idTecnico, Integer idTecnicoResp, Integer idus, String pontoTransmissao, String nome,
			Integer zona, Integer codmunic, String chegouCartorio, String chegouPonto, String oficializouSistema,
			String encerrou, Integer ofic) {
		super();
		this.idTecnico = idTecnico;
		this.idTecnicoResp = idTecnicoResp;
		this.idus = idus;
		this.pontoTransmissao = pontoTransmissao;
		this.nome = nome;
		this.zona = zona;
		this.codmunic = codmunic;
		this.chegouCartorio = chegouCartorio;
		this.chegouPonto = chegouPonto;
		this.oficializouSistema = oficializouSistema;
		this.encerrou = encerrou;
		this.oficial = ofic;
	}

	public Integer getIdTecnicoResp() {
		return idTecnicoResp;
	}

	public void setIdTecnicoResp(Integer idTecnicoResp) {
		this.idTecnicoResp = idTecnicoResp;
	}

	public Integer getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Integer idTecnico) {
		this.idTecnico = idTecnico;
	}

	public Integer getIdus() {
		return idus;
	}

	public void setIdus(Integer idus) {
		this.idus = idus;
	}

	public String getPontoTransmissao() {
		return pontoTransmissao;
	}

	public Integer getOficial() {
		return oficial;
	}

	public void setOficial(Integer oficial) {
		this.oficial = oficial;
	}

	public void setPontoTransmissao(String pontoTransmissao) {
		this.pontoTransmissao = pontoTransmissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona = zona;
	}

	public Integer getCodmunic() {
		return codmunic;
	}

	public void setCodmunic(Integer codmunic) {
		this.codmunic = codmunic;
	}

	public String getChegouCartorio() {
		return chegouCartorio;
	}

	public void setChegouCartorio(String chegouCartorio) {
		this.chegouCartorio = chegouCartorio;
	}

	public String getChegouPonto() {
		return chegouPonto;
	}

	public void setChegouPonto(String chegouPonto) {
		this.chegouPonto = chegouPonto;
	}

	public String getOficializouSistema() {
		return oficializouSistema;
	}

	public void setOficializouSistema(String oficializouSistema) {
		this.oficializouSistema = oficializouSistema;
	}

	public String getEncerrou() {
		return encerrou;
	}

	public void setEncerrou(String encerrou) {
		this.encerrou = encerrou;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTecnico == null) ? 0 : idTecnico.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VWPpo other = (VWPpo) obj;
		if (idTecnico == null) {
			if (other.idTecnico != null)
				return false;
		} else if (!idTecnico.equals(other.idTecnico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VWPpo [idTecnico=" + idTecnico + ", idTecnicoResp=" + idTecnicoResp + ", idus=" + idus
				+ ", pontoTransmissao=" + pontoTransmissao + ", oficial=" + oficial + ", nome=" + nome + ", zona="
				+ zona + ", codmunic=" + codmunic + ", chegouCartorio=" + chegouCartorio + ", chegouPonto="
				+ chegouPonto + ", oficializouSistema=" + oficializouSistema + ", encerrou=" + encerrou + "]";
	}
	
}
