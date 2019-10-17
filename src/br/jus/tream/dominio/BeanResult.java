package br.jus.tream.dominio;

public class BeanResult {

	private int id;
	private String type;
	private String mensagem;
	private String btn;
	private String page;
	private int ret;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String message) {
		this.mensagem = message;
	}
	public void setMsg(String message, String type) {
		this.mensagem = message;
		this.type = type;
	}
	public String getBtn() {
		return btn;
	}
	public void setBtn(String btn) {
		this.btn = btn;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	
	
	
}
