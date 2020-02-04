package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.jus.tream.dominio.pk.VWEncerramentoPK;

@SuppressWarnings("serial")
@Entity
@Table(name="vw_encerramento")
public class VWEncerramento implements Serializable{

	@EmbeddedId
	private VWEncerramentoPK id;
	
	private Integer zona;
	
	private Integer codmunic;
	
	@Column(name="descricao")
	private String pontoTransmissao;
	
	private String nome;
	
	private String codigo;
	
	@Column(name="datacad")
	private String dataCad;
	
	private Integer status;
	
	@Column(name="id_tecnico_resp")
	private Integer idTecnicoResp;
	
	public VWEncerramento() {
	}

	public VWEncerramento(VWEncerramentoPK id, Integer zona, Integer codmunic, String pontoTransmissao, String nome,
			String codigo, String dataCad, Integer status, Integer idTecnicoResp) {
		super();
		this.id = id;
		this.zona = zona;
		this.codmunic = codmunic;
		this.pontoTransmissao = pontoTransmissao;
		this.nome = nome;
		this.codigo = codigo;
		this.dataCad = dataCad;
		this.status = status;
		this.idTecnicoResp = idTecnicoResp;
	}

	public VWEncerramentoPK getId() {
		return id;
	}

	public void setId(VWEncerramentoPK id) {
		this.id = id;
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

	public String getPontoTransmissao() {
		return pontoTransmissao;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDataCad() {
		return dataCad;
	}

	public void setDataCad(String dataCad) {
		this.dataCad = dataCad;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIdTecnicoResp() {
		return idTecnicoResp;
	}

	public void setIdTecnicoResp(Integer idTecnicoResp) {
		this.idTecnicoResp = idTecnicoResp;
	}

	@Override
	public String toString() {
		return "VWPpo [idUS =" + id.getIdus() + ", idEleicao=" + id.getIdEleicao() + ", idTecnicoResp=" + idTecnicoResp + ","  
				+ ", pontoTransmissao=" + pontoTransmissao + ", status=" + status + ", dataEleicao=" + dataCad + ",  nome=" + nome + ", zona="
				+ zona + ", codmunic=" + codmunic + "]";
	}
	
}
