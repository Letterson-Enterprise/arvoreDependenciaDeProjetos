package br.com.lettersonEnterprise.enumeradores;

public enum Posicao {
	ESQUERDA(1,"ESQUERDA"),
	DIREITA(2, "DIREITA");
	
	private int codigo;
	private String descricao;
	
	private Posicao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
