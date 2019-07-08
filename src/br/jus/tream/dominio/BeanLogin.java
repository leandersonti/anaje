
package br.jus.tream.dominio;

public class BeanLogin {
	private String titulo;
	private String nome;
	private String firstName;
	private String matricula;
	private String senha;
	private String sigla;
	private String DescSigla;
	private String email;
	private Boolean logou;
	private Boolean isAmbienteProducao;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Boolean getLogou() {
		return logou;
	}
	public void setLogou(Boolean logou) {
		this.logou = logou;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getDescSigla() {
		return DescSigla;
	}
	public void setDescSigla(String descSigla) {
		DescSigla = descSigla;
	}
	public Boolean getIsAmbienteProducao() {
		return isAmbienteProducao;
	}
	public void setIsAmbienteProducao(Boolean isAmbienteProducao) {
		this.isAmbienteProducao = isAmbienteProducao;
	}
    
	
	
}
