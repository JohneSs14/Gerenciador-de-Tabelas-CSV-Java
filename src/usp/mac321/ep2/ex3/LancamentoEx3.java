package usp.mac321.ep2.ex3;

import usp.mac321.ep2.Usuario;

public class LancamentoEx3 {
	private static int counter = 0;
	private int identificador;
	private String data;
	private boolean RD;
	private Usuario usuario;
	private String subcategoria;
	private String descricao;
	private double valor;
	private String dataAtual;
	private LancamentoContext LancContext;

	public LancamentoEx3(String data, Usuario usuario, boolean RD, String subcategoria, double valor,
			String descricao) {
		this.data = data;
		this.usuario = usuario;
		this.RD = RD;
		this.subcategoria = subcategoria;
		this.valor = valor;
		this.descricao = descricao;
		this.identificador = counter;
		counter++;
	}

	public int getIdentificador() {
		return identificador;
	}

	public String getData() {
		return data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getCategoria() {

		if (RD == true)
			return "Despesa";
		else
			return "Receita";
	}

	public boolean getRD() {
		return RD;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "Lancamento{" + "data='" + data + '\'' + ", usuario=" + usuario.getApelido() + ", isDespesa=" + RD
				+ ", subcategoria='" + subcategoria + '\'' + ", valor=" + valor + ", descricao='" + descricao + '\''
				+ '}';
	}

	

	public void setData(String data) {
		this.data = data;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String setCategoria() {

		if (RD == true)
			return "Despesa";
		else
			return "Receita";
	}

	public void setRD(boolean posRD) {
		this.RD = posRD;
	}

	public String setSubcategoria() {
		return subcategoria;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getDataAtual () {
		return this.dataAtual;
	}



	// if (lancamento.getData())
	// int lancamentoDay, lancamentoMonth ,lancamentoYear, atualDay, atualMonth,
	// atualYear;
}
