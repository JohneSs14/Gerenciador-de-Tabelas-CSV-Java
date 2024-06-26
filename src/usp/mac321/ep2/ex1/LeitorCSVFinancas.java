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
	private final List<Usuario> USER_LIST;
	private final List<TipoDespesa> EXPENSE_LIST;
	private final List<TipoReceita> REVENUE_LIST;
	private final List<Lancamento> ENTRY_LIST;

	public LeitorCSVFinancas(String CSV_PATH, String nomeArquivoUsuarios, String nomeArquivoTiposDespesas, String nomeArquivoTiposReceitas, String nomeArquivoLancamentos) {
		this.CSV_PATH = CSV_PATH;
		this.USER_LIST = leUsuarios(nomeArquivoUsuarios);
		this.EXPENSE_LIST = leTiposDespesas(nomeArquivoTiposDespesas);
		this.REVENUE_LIST = leTiposReceitas(nomeArquivoTiposReceitas);
		this.ENTRY_LIST = leLancamentos(nomeArquivoLancamentos);
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
	        throw new RuntimeException("Arquivo não encontrado: " + nomeArquivoUsuarios, e);
	    } catch (IOException e) {
	        throw new RuntimeException("Erro de leitura do arquivo: " + nomeArquivoUsuarios, e);
	    } catch (CsvValidationException e) {
	        throw new RuntimeException("Erro de validação do arquivo: " + nomeArquivoUsuarios, e);
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
	        throw new RuntimeException("Arquivo não encontrado: " + nomeArquivoTiposDespesas, e);
	    } catch (IOException e) {
	        throw new RuntimeException("Erro de leitura do arquivo: " + nomeArquivoTiposDespesas, e);
	    } catch (CsvValidationException e) {
	        throw new RuntimeException("Erro de validação do arquivo: " + nomeArquivoTiposDespesas, e);
	    }
		return tiposdedespesas;
	}

	@Override
	public List<TipoReceita> leTiposReceitas(String nomeArquivoTiposReceitas) {
		List<TipoReceita> tiposdereceitas = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(new File(CSV_PATH + nomeArquivoTiposReceitas)))) {
			String[] proximaLinha;
			while ((proximaLinha = reader.readNext()) != null) {
				if (proximaLinha[0].equals("Categoria")) {
					continue;
				}
				String categoria = proximaLinha[0];
				String subcategoria = proximaLinha[1];
				TipoReceita TipoReceita = new TipoReceita(categoria, subcategoria);
				tiposdereceitas.add(TipoReceita);
			}
	    } catch (FileNotFoundException e) {
	        throw new RuntimeException("Arquivo não encontrado: " + nomeArquivoTiposReceitas, e);
	    } catch (IOException e) {
	        throw new RuntimeException("Erro de leitura do arquivo: " + nomeArquivoTiposReceitas, e);
	    } catch (CsvValidationException e) {
	        throw new RuntimeException("Erro de validação do arquivo: " + nomeArquivoTiposReceitas, e);
	    }
		return tiposdereceitas;
	}

	@Override
	public List<Lancamento> leLancamentos(String nomeArquivoLancamentos) {
	    List<Lancamento> lancamentos = new ArrayList<>();
	    Set<Integer> identificadores = new HashSet<>();
	    try (CSVReader reader = new CSVReader(new FileReader(new File(CSV_PATH + nomeArquivoLancamentos)))) {
	        String[] proximaLinha;
	        while ((proximaLinha = reader.readNext())!= null) {
	            if (proximaLinha[0].equals("ID")) {
	                continue;
	            }
	            int identificador = Integer.parseInt(proximaLinha[0]);
	            if (identificadores.contains(identificador)) {
	                throw new IllegalArgumentException("Identificador duplicado: " + identificador);
	            }
	            identificadores.add(identificador);
	            String data = proximaLinha[1];
	            String apelidoResponsavel = proximaLinha[2];
	            boolean isDespesa = proximaLinha[3].equals("TRUE");
	            String subcategoria = proximaLinha[4];
	            double valor = Double.parseDouble(proximaLinha[5]);
	            if (valor < 0) {
	                throw new IllegalArgumentException("Valor negativo: " + valor);
	            }
	            String descricao = proximaLinha[6];

	            Usuario usuario = findUsuarioByApelido(this.USER_LIST, apelidoResponsavel);
	            if (usuario == null) {
	                throw new IllegalArgumentException("Usuário não encontrado: " + apelidoResponsavel);
	            }

	            TipoDespesa tipoDespesa = findTipoDespesaBySubcategoria(EXPENSE_LIST, subcategoria);
	            TipoReceita tipoReceita = findTipoReceitaBySubcategoria(REVENUE_LIST, subcategoria);
	            if ((isDespesa && tipoReceita!= null) || (!isDespesa && tipoDespesa!= null)) {
	                throw new IllegalArgumentException("Categoria de receita/despesa incompatível: " + subcategoria);
	            }

	            Lancamento lancamento = new Lancamento(data, usuario, isDespesa, subcategoria, valor, descricao);
	            lancamentos.add(lancamento);
	        }
	    } catch (FileNotFoundException e) {
	        throw new RuntimeException("Arquivo não encontrado: " + nomeArquivoLancamentos, e);
	    } catch (IOException e) {
	        throw new RuntimeException("Erro de leitura do arquivo: " + nomeArquivoLancamentos, e);
	    } catch (CsvValidationException e) {
	        throw new RuntimeException("Erro de validação do arquivo: " + nomeArquivoLancamentos, e);
	    }
	    return lancamentos;
	}

	private Usuario findUsuarioByApelido(List<Usuario> usuarios, String apelido) {
	    for (Usuario usuario : usuarios) {
	        if (usuario.getApelido().equals(apelido)) {
	            return usuario;
	        }
	    }
	    return null;
	}

	private TipoDespesa findTipoDespesaBySubcategoria(List<TipoDespesa> tipoDespesas, String subcategoria) {
	    for (TipoDespesa tipoDespesa : tipoDespesas) {
	        if (tipoDespesa.getSubcategoria().equals(subcategoria)) {
	            return tipoDespesa;
	        }
	    }
	    return null;
	}

	private TipoReceita findTipoReceitaBySubcategoria(List<TipoReceita> tipoReceitas, String subcategoria) {
	    for (TipoReceita tipoReceita : tipoReceitas) {
	        if (tipoReceita.getSubcategoria().equals(subcategoria)) {
	            return tipoReceita;
	        }
	    }
	    return null;
	}

}
