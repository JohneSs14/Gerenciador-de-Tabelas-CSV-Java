package usp.mac321.ep2.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.LeitorFinancasPessoaisDAO;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;
import usp.mac321.ep2.Usuario;

public class LeitorCSVFinancas implements LeitorFinancasPessoaisDAO {
	private final String CSV_PATH;

	public LeitorCSVFinancas(String CSV_PATH) {
		this.CSV_PATH = CSV_PATH;
	}

	@Override
	public List<Usuario> leUsuarios(String nomeArquivoUsuarios) {
		List<Usuario> usuarios = new ArrayList<>();
		Set<String> apelidos = new HashSet<>();
		try (CSVReader reader = new CSVReader(new FileReader(new File(CSV_PATH + nomeArquivoUsuarios)))) {
			String[] proximaLinha;
			while ((proximaLinha = reader.readNext()) != null) {
				if (proximaLinha[0].equals("Apelido")) {
					continue;
				}
				String apelido = proximaLinha[0];
				if (apelidos.contains(apelido)) {
					throw new IllegalArgumentException("Apelido duplicado: " + apelido);
				}
				apelidos.add(apelido);
				String nome = proximaLinha[1];
				Usuario usuario = new Usuario(apelido, nome);
				usuarios.add(usuario);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public List<TipoDespesa> leTiposDespesas(String nomeArquivoTiposDespesas) {
		List<TipoDespesa> tiposdedespesas = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(new File(CSV_PATH + nomeArquivoTiposDespesas)))) {
			String[] proximaLinha;
			while ((proximaLinha = reader.readNext()) != null) {
				if (proximaLinha[0].equals("Categoria")) {
					continue;
				}
				String categoria = proximaLinha[0];
				String subcategoria = proximaLinha[1];
				TipoDespesa TipoDespesa = new TipoDespesa(categoria, subcategoria);
				tiposdedespesas.add(TipoDespesa);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		}
		return tiposdedespesas;
	}

	@Override
	public List<TipoReceita> leTiposReceitas(String nomeArquivoTiposReceitas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lancamento> leLancamentos(String nomeArquivoLancamentos) {
		// TODO Auto-generated method stub
		return null;
	}

}
