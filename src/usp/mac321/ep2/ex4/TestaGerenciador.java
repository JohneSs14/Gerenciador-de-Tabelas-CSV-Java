package usp.mac321.ep2.ex4;

import org.junit.jupiter.api.Test;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;
import usp.mac321.ep2.Usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestaGerenciador {
	
    @Test
    public void testRelatorioFinanceiro_Simples() {
        Gerenciador gerenciador = new Gerenciador("csv/");

        Usuario usuario1 = new Usuario("User 1", "Name 1");
        TipoDespesa tipoDespesa1 = new TipoDespesa("Despesa 1", "Categoria D");
        TipoReceita tipoReceita1 = new TipoReceita("Receita 1", "Categoria R");
        Lancamento lancamento1 = new Lancamento("01/05/24", usuario1, false, "Categoria R", 1000, "Description 1");
        Lancamento lancamento2 = new Lancamento("02/05/24", usuario1, true, "Categoria D", 500, "Description 2");

        gerenciador.criarUsuario(usuario1);
        gerenciador.criarTipoDespesa(tipoDespesa1);
        gerenciador.criarTipoReceita(tipoReceita1);
        gerenciador.criarLancamento(lancamento1);
        gerenciador.criarLancamento(lancamento2);
        
        gerenciador.salvarEstado("csv/");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        gerenciador.imprimirRelatorioFinanceiro("01/05/24", "02/05/24");
        
        System.setOut(originalOut);
        
        String output = outContent.toString();
        
        String expectedOutput = "Relatório Financeiro entre 01/05/24 e 02/05/24\r\n"
        		+ "Despesas entre 01/05/24 e 02/05/24: 500.0\r\n"
        		+ "Receitas entre 01/05/24 e 02/05/24: 1000.0\r\n"
        		+ "Receitas:\r\n"
        		+ "Categoria: Receita 1\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Receita 1: 1000.0\r\n"
        		+ "  Subcategoria: Categoria R\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria R: 1000.0\r\n"
        		+ "Despesas:\r\n"
        		+ "Categoria: Despesa 1\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Despesa 1: 500.0\r\n"
        		+ "  Subcategoria: Categoria D\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria D: 500.0\r\n"
        		+ "";
        
        assertEquals(expectedOutput, output);
    }
    
    @Test
    public void testRelatorioFinanceiro_Duplo() {
        Gerenciador gerenciador = new Gerenciador("csv/");

        Usuario usuario1 = new Usuario("User 1", "Name 1");
        TipoDespesa tipoDespesa1 = new TipoDespesa("Despesa 1", "Categoria D");
        TipoDespesa tipoDespesa2 = new TipoDespesa("Despesa 2", "Categoria DD");
        TipoReceita tipoReceita1 = new TipoReceita("Receita 1", "Categoria R");
        TipoReceita tipoReceita2 = new TipoReceita("Receita 2", "Categoria RR");
        Lancamento lancamento1 = new Lancamento("01/05/24", usuario1, false, "Categoria R", 1000, "Description Esse EP tem quantas linhas já?");
        Lancamento lancamento2 = new Lancamento("02/05/24", usuario1, true, "Categoria D", 500, "Description Tô ficando maluco");
        Lancamento lancamento3 = new Lancamento("01/05/24", usuario1, false, "Categoria RR", 500, "Description Socorro");
        Lancamento lancamento4 = new Lancamento("02/05/24", usuario1, true, "Categoria DD", 250, "Description São Três da manhã");

        gerenciador.criarUsuario(usuario1);
        gerenciador.criarTipoDespesa(tipoDespesa1);
        gerenciador.criarTipoDespesa(tipoDespesa2);
        gerenciador.criarTipoReceita(tipoReceita1);
        gerenciador.criarTipoReceita(tipoReceita2);
        gerenciador.criarLancamento(lancamento1);
        gerenciador.criarLancamento(lancamento2);
        gerenciador.criarLancamento(lancamento3);
        gerenciador.criarLancamento(lancamento4);
        
        gerenciador.salvarEstado("csv/");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        gerenciador.imprimirRelatorioFinanceiro("01/05/24", "02/05/24");
        
        System.setOut(originalOut);
        
        String output = outContent.toString();
        
        String expectedOutput = "Relatório Financeiro entre 01/05/24 e 02/05/24\r\n"
        		+ "Despesas entre 01/05/24 e 02/05/24: 750.0\r\n"
        		+ "Receitas entre 01/05/24 e 02/05/24: 1500.0\r\n"
        		+ "Receitas:\r\n"
        		+ "Categoria: Receita 1\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Receita 1: 1000.0\r\n"
        		+ "  Subcategoria: Categoria R\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria R: 1000.0\r\n"
        		+ "Categoria: Receita 2\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Receita 2: 500.0\r\n"
        		+ "  Subcategoria: Categoria RR\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria RR: 500.0\r\n"
        		+ "Despesas:\r\n"
        		+ "Categoria: Despesa 1\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Despesa 1: 500.0\r\n"
        		+ "  Subcategoria: Categoria D\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria D: 500.0\r\n"
        		+ "Categoria: Despesa 2\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Despesa 2: 250.0\r\n"
        		+ "  Subcategoria: Categoria DD\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria DD: 250.0\r\n"
        		+ "";
        
        assertEquals(expectedOutput, output);
    }
    
    @Test
    public void testRelatorioFinanceiro_Simple() {
        Gerenciador gerenciador = new Gerenciador("csv/");

        Usuario usuario1 = new Usuario("User 1", "Name 1");
        TipoDespesa tipoDespesa1 = new TipoDespesa("Despesa 1", "Categoria D");
        TipoDespesa tipoDespesa2 = new TipoDespesa("Despesa 2", "Categoria DD");
        TipoDespesa tipoDespesa3 = new TipoDespesa("Despesa 3", "Categoria DDD");
        TipoReceita tipoReceita1 = new TipoReceita("Receita 1", "Categoria R");
        TipoReceita tipoReceita2 = new TipoReceita("Receita 2", "Categoria RR");
        TipoReceita tipoReceita3 = new TipoReceita("Receita 3", "Categoria RRR");
        Lancamento lancamento1 = new Lancamento("01/05/24", usuario1, false, "Categoria R", 1000, "Description Muito obrigado pelas aulas, Finger");
        Lancamento lancamento2 = new Lancamento("02/05/24", usuario1, true, "Categoria D", 500, "Description Abraço pro Miguel");
        Lancamento lancamento3 = new Lancamento("01/05/24", usuario1, false, "Categoria RR", 500, "Description Esse EP me ensinou muito, criei carater");
        Lancamento lancamento4 = new Lancamento("02/05/24", usuario1, true, "Categoria DD", 250, "Description Tomara que não seja assim no mercado de trabalho");
        Lancamento lancamento5 = new Lancamento("01/05/24", usuario1, false, "Categoria RRR", 250, "Description Tomara que a pessoa que for minha dupla no mercado de trabalho");
        Lancamento lancamento6 = new Lancamento("02/05/24", usuario1, true, "Categoria DDD", 125.50, "Description Seja metade pelo menos da pessoa com quem fiz esse EP");

        gerenciador.criarUsuario(usuario1);
        gerenciador.criarTipoDespesa(tipoDespesa1);
        gerenciador.criarTipoDespesa(tipoDespesa2);
        gerenciador.criarTipoDespesa(tipoDespesa3);
        gerenciador.criarTipoReceita(tipoReceita1);
        gerenciador.criarTipoReceita(tipoReceita2);
        gerenciador.criarTipoReceita(tipoReceita3);
        gerenciador.criarLancamento(lancamento1);
        gerenciador.criarLancamento(lancamento2);
        gerenciador.criarLancamento(lancamento3);
        gerenciador.criarLancamento(lancamento4);
        gerenciador.criarLancamento(lancamento5);
        gerenciador.criarLancamento(lancamento6);
        
        gerenciador.salvarEstado("csv/");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        gerenciador.imprimirRelatorioFinanceiro("01/05/24", "02/05/24");
        
        System.setOut(originalOut);
        
        String output = outContent.toString();
        
        String expectedOutput = "Relatório Financeiro entre 01/05/24 e 02/05/24\r\n"
        		+ "Despesas entre 01/05/24 e 02/05/24: 875.5\r\n"
        		+ "Receitas entre 01/05/24 e 02/05/24: 1750.0\r\n"
        		+ "Receitas:\r\n"
        		+ "Categoria: Receita 1\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Receita 1: 1000.0\r\n"
        		+ "  Subcategoria: Categoria R\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria R: 1000.0\r\n"
        		+ "Categoria: Receita 2\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Receita 2: 500.0\r\n"
        		+ "  Subcategoria: Categoria RR\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria RR: 500.0\r\n"
        		+ "Categoria: Receita 3\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Receita 3: 250.0\r\n"
        		+ "  Subcategoria: Categoria RRR\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria RRR: 250.0\r\n"
        		+ "Despesas:\r\n"
        		+ "Categoria: Despesa 1\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Despesa 1: 500.0\r\n"
        		+ "  Subcategoria: Categoria D\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria D: 500.0\r\n"
        		+ "Categoria: Despesa 2\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Despesa 2: 250.0\r\n"
        		+ "  Subcategoria: Categoria DD\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria DD: 250.0\r\n"
        		+ "Categoria: Despesa 3\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a categoria Despesa 3: 125.5\r\n"
        		+ "  Subcategoria: Categoria DDD\r\n"
        		+ "Valor agregado entre 01/05/24 e 02/05/24 para a subcategoria Categoria DDD: 125.5\r\n"
        		+ "";
        
        assertEquals(expectedOutput, output);
    }
}