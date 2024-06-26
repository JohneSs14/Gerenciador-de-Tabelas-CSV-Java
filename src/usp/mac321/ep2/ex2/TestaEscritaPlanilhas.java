package usp.mac321.ep2.ex2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;
import usp.mac321.ep2.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestaEscritaPlanilhas {

	private List<String> lerArquivo(File arquivo) throws IOException {
	    List<String> linhas = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
	        String linha;
	        while ((linha = reader.readLine()) != null) {
	            linhas.add(linha);
	        }
	    }
	    return linhas;
	}

	@Test
	public void testaEscritaTiposReceitas() throws IOException {
		List<TipoReceita> tiposReceitas = new ArrayList<>();
		tiposReceitas.add(new TipoReceita("Salário", "Trabalho"));
		tiposReceitas.add(new TipoReceita("Investimentos", "Ações"));

		EscreveTipoReceita escreveTipoReceita = new EscreveTipoReceita();
		String endereco = "tipos_receitas.csv";
		escreveTipoReceita.escreveTiposReceitas(tiposReceitas, endereco);

		File arquivo = new File("csv2/", endereco);
		assertTrue(arquivo.exists());

		List<String> linhas = new ArrayList<>();
		linhas.add("Salário,Trabalho");
		linhas.add("Investimentos,Ações");

		assertEquals(linhas, lerArquivo(arquivo));
	}
	
	@Test
	public void testaEscritaTiposDespesas() throws IOException {
		List<TipoDespesa> tiposDespesas = new ArrayList<>();
		tiposDespesas.add(new TipoDespesa("Alimentação", "Supermercado"));
		tiposDespesas.add(new TipoDespesa("Transporte", "Uber"));

		EscreveTipoDespesa escreveTipoDespesa = new EscreveTipoDespesa();
		String endereco = "tipos_despesas.csv";
		escreveTipoDespesa.escreveTiposDespesas(tiposDespesas, endereco);

		File arquivo = new File("csv2/", endereco);
		assertTrue(arquivo.exists());

		List<String> linhas = new ArrayList<>();
		linhas.add("Alimentação,Supermercado");
		linhas.add("Transporte,Uber");

		assertEquals(linhas, lerArquivo(arquivo));
	}
	
	@Test
	@DisplayName("Testa escrita de lançamentos")
	public void testaEscritaLancamentos() throws IOException {
		Usuario Pai = new Usuario("Pai", "Epaminondas");
		List<Lancamento> lancamentos = new ArrayList<>();
		lancamentos.add(new Lancamento("01/01/2022", Pai, true, "Salário", 10000.0,
				"Salário do pai"));
		lancamentos.add(new Lancamento("05/01/2022", Pai, true, "Alimentação", 555.55,
				"Compra da semana"));

		EscreveLancamento escreveLancamento = new EscreveLancamento();
		String endereco = "lancamentos.csv";
		escreveLancamento.escreveLancamentos(lancamentos, endereco);

		File arquivo = new File("csv2/", endereco);
		assertTrue(arquivo.exists());

		List<String> linhas = new ArrayList<>();
		linhas.add("0,01/01/2022,Pai,true,Salário,10000.0,Salário do pai");
		linhas.add("1,05/01/2022,Pai,true,Alimentação,555.55,Compra da semana");

		assertEquals(linhas, lerArquivo(arquivo));
	}
	
	@Test
    public void testaEscritaUsuarios() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Pai", "Epaminondas"));
        usuarios.add(new Usuario("Mãe", "Maria"));

        EscreveUsuario escreveUsuario = new EscreveUsuario();
        String endereco = "usuarios.csv";
        escreveUsuario.escreveUsuarios(usuarios, endereco);

        File arquivo = new File("csv2/", endereco);
        assertTrue(arquivo.exists());

        List<String> linhas = new ArrayList<>();
        linhas.add("Pai,Epaminondas");
        linhas.add("Mãe,Maria");

        assertEquals(linhas, lerArquivo(arquivo));
    }
	
	//Os arquivos gerados estão na pasta CSV2 caso queira checar.
}