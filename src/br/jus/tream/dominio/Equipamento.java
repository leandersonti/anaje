package br.jus.tream.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@SuppressWarnings("serial")
@Entity
@Table(name="equipamento")
public class Equipamento implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_tipo", nullable = false)
	private EquipamentoTipo tipo;

	@ManyToOne
	@JoinColumn(name = "id_eleicao", nullable = false)
	private DataEleicao dataEleicao;

	@Column(length=70)
	private String serie;

	@Column(length=40)
	private String tomb;
	
	@Column(length=40)
	private String fone;
	
	@Column(length=70)
	private String param;
	
	@Column(length=70)
	private String chave;
}
