package br.com.lettersonEnterprise.modelos;

public class ArvoreDeDependencias {
	private TarefaNo raiz;

	public ArvoreDeDependencias(TarefaNo raiz) {
		super();
		this.raiz = raiz;
	}

	public TarefaNo getRaiz() {
		return raiz;
	}

	public void setRaiz(TarefaNo raiz) {
		this.raiz = raiz;
	}
	
	
}
