package usp.mac321.ep2.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		try (CSVReader reader = new CSVReader(new FileReader(new File(CSV_PATH + nomeArquivoUsuarios)))) {
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine[0].equals("Apelido")) {
					continue;
				}
				String apelido = nextLine[0];
				String nome = nextLine[1];
				Usuario usuario = new Usuario(apelido, nome);
				usuarios.add(usuario);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}

	@Override
	public List<TipoDespesa> leTiposDespesas(String nomeArquivoTiposDespesas) {
		// TODO Auto-generated method stub
		return null;
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
