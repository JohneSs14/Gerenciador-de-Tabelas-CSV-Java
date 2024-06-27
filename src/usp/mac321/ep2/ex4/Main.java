package usp.mac321.ep2.ex4;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;
import usp.mac321.ep2.Usuario;

public class Main {
	public static void main(String[] args) {
		Usuario Pai = new Usuario("Pai", "Epaminondas");
		Usuario Zezinho = new Usuario("Zezinho", "Joao");

		TipoDespesa despesa = new TipoDespesa("Despesa", "Despesa");
		TipoReceita receita = new TipoReceita("Receita", "Receita");
		
        Gerenciador gerenciador = new Gerenciador("csv/");

        gerenciador.criarUsuario(Pai);
        gerenciador.criarUsuario(Zezinho);
        
        gerenciador.criarTipoDespesa(despesa);
        gerenciador.criarTipoReceita(receita);
        
        gerenciador.salvarEstado("csv/");
        
        gerenciador.criarLancamento(new Lancamento("01/05/24", Pai, false, "Receita", 10000, "Salário do papai"));
        gerenciador.criarLancamento(new Lancamento("02/05/24", Pai, false, "Receita", 1000, "Conserto computador vizinha"));
        gerenciador.criarLancamento(new Lancamento("02/05/24", Pai, true, "Despesa", 50, "Barbie"));
        gerenciador.criarLancamento(new Lancamento("03/05/24", Pai, true, "Despesa", 48, "Oppenheimer"));
        gerenciador.criarLancamento(new Lancamento("04/04/24", Pai, true, "Despesa", 200, "Netflux"));
        gerenciador.criarLancamento(new Lancamento("04/05/24", Pai, true, "Despesa", 343.27, "Compra da semana"));
        gerenciador.criarLancamento(new Lancamento("05/05/24", Pai, true, "Despesa", 555.55, "Compra da semana"));
        gerenciador.criarLancamento(new Lancamento("05/05/24", Zezinho, true, "Despesa", 600, "Escolinha SóTela"));

        // Salvar os lançamentos no arquivo CSV
        gerenciador.salvarEstado("csv/");

        // Computar o valor total entre duas datas
        gerenciador.computarValorTotal("01/05/24", "05/05/24");
    }
}