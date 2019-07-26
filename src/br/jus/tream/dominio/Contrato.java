package br.jus.tream.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name="contrato")
public class Contrato implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_eleicao", nullable=false)
	private DataEleicao dataEleicao;
	
	@Column(length=50, nullable=false)
	private String sigla;
	
	@Column(length=50, nullable=false)
	private String descricao;
	
	@Column(length=50, nullable=false)
	private String empresa;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio", nullable=false)
	private Date dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim", nullable=false)
	private Date dataFim;
		
	@ManyToOne
	@JoinColumn(name = "id_cargo", nullable=false)
	private Cargo cargo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_insc_ini")
	private Date dataInscIni;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_insc_fim")
	private Date dataInscFim;
	
	@Column(length=10)
	private String num_contrato_tse;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_limite_confirmacao")
	private Date dtLimiteConfirmacao;
	
	@Column(length=70)
	private Integer totalTecnicos;
	

	public Contrato() {
		super();
	}

	public Contrato(Integer id, DataEleicao dataEleicao, String sigla, String descricao, String empresa,
			Date dataInicio, Date dataFim, Cargo cargo, Date dataInscIni, Date dataInscFim, String num_contrato_tse,
			Date dtLimiteConfirmacao, Integer totalTecnicos) {
		super();
		this.id = id;
		this.dataEleicao = dataEleicao;
		this.sigla = sigla;
		this.descricao = descricao;
		this.empresa = empresa;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.cargo = cargo;
		this.dataInscIni = dataInscIni;
		this.dataInscFim = dataInscFim;
		this.num_contrato_tse = num_contrato_tse;
		this.dtLimiteConfirmacao = dtLimiteConfirmacao;
		this.totalTecnicos = totalTecnicos;
	}
	
	public Contrato(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DataEleicao getDataEleicao() {
		return dataEleicao;
	}

	public void setDataEleicao(DataEleicao dataEleicao) {
		this.dataEleicao = dataEleicao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Date getDataInscIni() {
		return dataInscIni;
	}

	public void setDataInscIni(Date dataInscIni) {
		this.dataInscIni = dataInscIni;
	}

	public Date getDataInscFim() {
		return dataInscFim;
	}

	public void setDataInscFim(Date dataInscFim) {
		this.dataInscFim = dataInscFim;
	}

	public String getNum_contrato_tse() {
		return num_contrato_tse;
	}

	public void setNum_contrato_tse(String num_contrato_tse) {
		this.num_contrato_tse = num_contrato_tse;
	}

	public Date getDtLimiteConfirmacao() {
		return dtLimiteConfirmacao;
	}

	public void setDtLimiteConfirmacao(Date dtLimiteConfirmacao) {
		this.dtLimiteConfirmacao = dtLimiteConfirmacao;
	}

	public Integer getTotalTecnicos() {
		return totalTecnicos;
	}

	public void setTotalTecnicos(Integer totalTecnicos) {
		this.totalTecnicos = totalTecnicos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Contrato other = (Contrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
