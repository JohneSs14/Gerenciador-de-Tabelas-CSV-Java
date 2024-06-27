package usp.mac321.ep2.ex3;

import java.time.LocalDate;
import java.util.Date;

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
	private String dataAtual; //como teriam quer ser feitos testes reproduziveis, a data atual é fixa nos testes
	private String state;

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
		System.out.println(identificador);
	}

	public int getIdentificador() {
		return identificador;
	}

	public String getData() {
		return data;
	}

	public Usuario getUsuario() {
		return usuario;}
	

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

	
	 public void setDataAtual(String novaData) {
	        this.dataAtual = novaData;
	        this.state = determineState();
	    }

	  public void setData(String novaData) {
	        this.data = novaData;
	        this.state = determineState();
	    }

	public void setUsuario(Usuario usuario) {
		
		if (state == "Invalido" || state == "Executado")
			 throw new RuntimeException("Não é permitido modificar o lancamento");
		else 
		this.usuario = usuario;
	}

	public String setCategoria() {
		if (state == "Invalido" || state == "Executado")
			 throw new RuntimeException("Não é permitido modificar o lancamento");
		else {
		
		if (RD == true)
			return "Despesa";
		else
			return "Receita";
	}}

	public void setRD(boolean posRD) {
	//	this.state = this.determineState();
		if (state == "Invalido" || state == "Executado")
			 throw new RuntimeException("Não é permitido modificar o lancamento");
		else 
		     this.RD = posRD;
	}

	public String setSubcategoria() {
		if (state == "Invalido" || state == "Executado")
			 throw new RuntimeException("Não é permitido modificar o lancamento");
		else 
		     return subcategoria;
	}

	public void setDescricao(String descricao) {
		if (state == "Invalido" || state == "Executado")
			 throw new RuntimeException("Não é permitido modificar o lancamento");
		else 
		     this.descricao = descricao;
	}

	public void setValor(double valor) {
		if (state == "Invalido" || state == "Executado")
			 throw new RuntimeException("Não é permitido modificar o lancamento");
		else 
		      this.valor = valor;
	}
	
	public String getDataAtual () {
		return this.dataAtual;
	}
	
	public String getState() {
		return this.determineState();
	}
  
	private String determineState() {

	    String[] lancamentoParts = this.getData().split("/");
	    int[] lancamentoDate = new int[3];
	    for (int i = 0; i < 3; i++) {
	        lancamentoDate[i] = Integer.parseInt(lancamentoParts[i]);
	    }


	    String[] dataAtualParts = this.getDataAtual().split("/");
	    int[] atualDate = new int[3];
	    for (int j = 0; j < 3; j++) {
	        atualDate[j] = Integer.parseInt(dataAtualParts[j]);
	    }


	    LocalDate dataLanc = LocalDate.of(lancamentoDate[2], lancamentoDate[1], lancamentoDate[0]);
	    LocalDate data_Atual = LocalDate.of(atualDate[2], atualDate[1], atualDate[0]);
	    
	    if (dataLanc.isBefore(data_Atual)) {
            return "Executado";
        } else if (dataLanc.isAfter(data_Atual)) {
            return "Planejado";
        } else {
        	if (dataLanc.equals(data_Atual)) {
          return "Executado";
        	} else return "Invalido";
        }

	}}


	// if (lancamento.getData())
	// int lancamentoDay, lancamentoMonth ,lancamentoYear, atualDay, atualMonth,
	// atualYear;

