package usp.mac321.ep2.ex2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.LeitorFinancasPessoaisDAO;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;
import usp.mac321.ep2.Usuario;
import usp.mac321.ep2.ex1.LeitorCSVFinancas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestaEscritaPlanilhas {

	List<Usuario> usuarios;
	List<Usuario> usuarios2;
	List<TipoDespesa> tiposDespesas;
	List<TipoDespesa> tiposDespesas2;
	List<TipoReceita> tiposReceitas;
	List<TipoReceita> tiposReceitas2;
	List<Lancamento> lancamentos;
	List<Lancamento> lancamentos2;
	LeitorFinancasPessoaisDAO leitor;
	LeitorFinancasPessoaisDAO leitor2;

	@BeforeEach
	void setUp() throws Exception {
		leitor = new LeitorCSVFinancas("csv/", "usuarios.csv", "tiposDespesas.csv", "tiposReceitas.csv",
				"lancamentos.csv");
	}

	@Test
	public void testaEscritaTiposReceitas() throws IOException {
		tiposReceitas = leitor.leTiposReceitas("tiposReceitas.csv");
		TipoReceita novaReceita = new TipoReceita("Investimentos", "Ações");

		EscreveTipoReceita escreveTipoReceita = new EscreveTipoReceita();
		String endereco = "novo_tipos_receitas.csv";
		escreveTipoReceita.escreveTiposReceitas(tiposReceitas, novaReceita, endereco, "csv/");

		File arquivo = new File("csv/", endereco);
		assertTrue(arquivo.exists());

		tiposReceitas2 = leitor.leTiposReceitas(endereco);

		List<TipoReceita> linhas = new ArrayList<>();
		linhas = leitor.leTiposReceitas("tiposReceitas.csv");
		linhas.add(novaReceita);

		assertEquals(linhas.stream().map(TipoReceita::toString).map(String::trim).collect(Collectors.toList()),
				tiposReceitas2.stream().map(TipoReceita::toString).map(String::trim).collect(Collectors.toList()));
	}

	@Test
	public void testaEscritaTiposDespesas() throws IOException {
		tiposDespesas = leitor.leTiposDespesas("tiposDespesas.csv");
		TipoDespesa novaDespesa = new TipoDespesa("Lazer", "Jogos");

		EscreveTipoDespesa escreveTipoDespesa = new EscreveTipoDespesa();
		String endereco = "novo_tipos_Despesas.csv";
		escreveTipoDespesa.escreveTiposDespesas(tiposDespesas, novaDespesa, endereco, "csv/");

		File arquivo = new File("csv/", endereco);
		assertTrue(arquivo.exists());

		tiposDespesas2 = leitor.leTiposDespesas(endereco);

		List<TipoDespesa> linhas = new ArrayList<>();
		linhas = leitor.leTiposDespesas("tiposDespesas.csv");
		linhas.add(novaDespesa);

		assertEquals(linhas.stream().map(TipoDespesa::toString).map(String::trim).collect(Collectors.toList()),
				tiposDespesas2.stream().map(TipoDespesa::toString).map(String::trim).collect(Collectors.toList()));
	}

	@Test
	public void testaEscritaUsuarios() throws IOException {
		usuarios = leitor.leUsuarios("usuarios.csv");
		Usuario novoUsuario = new Usuario("Tio", "Eduardo");

		EscreveUsuario escreveUsuario = new EscreveUsuario();
		String endereco = "novo_usuario.csv";
		escreveUsuario.escreveUsuarios(usuarios, novoUsuario, endereco, "csv/");

		File arquivo = new File("csv/", endereco);
		assertTrue(arquivo.exists());

		usuarios2 = leitor.leUsuarios(endereco);

		List<Usuario> linhas = new ArrayList<>();
		linhas = leitor.leUsuarios("usuarios.csv");
		linhas.add(novoUsuario);

		assertEquals(linhas.stream().map(Usuario::toString).map(String::trim).collect(Collectors.toList()),
				usuarios2.stream().map(Usuario::toString).map(String::trim).collect(Collectors.toList()));
	}
	@Test
	public void testaEscritaLancamentos() throws IOException {
		lancamentos = leitor.leLancamentos("lancamentos.csv");
		Usuario Pai = new Usuario("Pai","Epaminondas Encerrabodes Eleutério");
		Lancamento novoLancamento = new Lancamento("06/05/24", Pai, true, "Cinema", 50.0, "Carros 2");

		EscreveLancamento escreveLancamento = new EscreveLancamento();
		String endereco = "novo_lancamentos.csv";
		escreveLancamento.escreveLancamentos(lancamentos, novoLancamento, endereco, "csv/");

		File arquivo = new File("csv/", endereco);
		assertTrue(arquivo.exists());

		lancamentos2 = leitor.leLancamentos(endereco);
		
		List<Lancamento> linhas = new ArrayList<>();
		linhas = leitor.leLancamentos("lancamentos.csv");
		linhas.add(novoLancamento);

		assertEquals(linhas.stream().map(Lancamento::toString).map(String::trim).collect(Collectors.toList()),
				lancamentos2.stream().map(Lancamento::toString).map(String::trim).collect(Collectors.toList()));
	}
	// Os arquivos gerados estão na pasta csv caso queira checar.
}
