package usp.mac321.ep2.ex4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.exceptions.CsvValidationException;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;
import usp.mac321.ep2.Usuario;
import usp.mac321.ep2.ex2.EscreveLancamento;
import usp.mac321.ep2.ex2.EscreveTipoDespesa;
import usp.mac321.ep2.ex2.EscreveTipoReceita;
import usp.mac321.ep2.ex2.EscreveUsuario;
import usp.mac321.ep2.ex1.LeitorCSVFinancas;

public class Gerenciador {
	private List<Usuario> usuarios;
	private List<TipoDespesa> tipoDespesas;
	private List<TipoReceita> tipoReceitas;
	private List<Lancamento> lancamentos;

	private EscreveUsuario escreveUsuario;
	private EscreveTipoDespesa escreveTipoDespesa;
	private EscreveTipoReceita escreveTipoReceita;
	private EscreveLancamento escreveLancamento;
	private LeitorCSVFinancas leitor;

	private String csvPath;

	public Gerenciador(String csvPath) {
		usuarios = new ArrayList<>();
		tipoDespesas = new ArrayList<>();
		tipoReceitas = new ArrayList<>();
		lancamentos = new ArrayList<>();

		escreveUsuario = new EscreveUsuario();
		escreveTipoDespesa = new EscreveTipoDespesa();
		escreveTipoReceita = new EscreveTipoReceita();
		escreveLancamento = new EscreveLancamento();

		this.csvPath = csvPath;

	}

	public void criarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public void removerUsuario(Usuario usuario) {
		usuarios.remove(usuario);
	}

	public void criarTipoDespesa(TipoDespesa tipoDespesa) {
		tipoDespesas.add(tipoDespesa);
	}

	public void removerTipoDespesa(TipoDespesa tipoDespesa) {
		tipoDespesas.remove(tipoDespesa);
	}

	public void criarTipoReceita(TipoReceita tipoReceita) {
		tipoReceitas.add(tipoReceita);
	}

	public void removerTipoReceita(TipoReceita tipoReceita) {
		tipoReceitas.remove(tipoReceita);
	}

	public void criarLancamento(Lancamento lancamento) {
		lancamentos.add(lancamento);
	}

	public void editarLancamento(Lancamento lancamentoAntigo, Lancamento lancamentoNovo) {
		int indice = lancamentos.indexOf(lancamentoAntigo);
		if (indice != -1) {
			lancamentos.set(indice, lancamentoNovo);
		} else {
			throw new IllegalArgumentException("Laçamento inválio!");
		}
	}

	public void salvarEstado(String csvPath) {
		escreveUsuario.escreveUsuarios(usuarios, null, "ArquivoUsuarioGerador.csv", csvPath);
		escreveTipoDespesa.escreveTiposDespesas(tipoDespesas, null, "ArquivoTiposDespesasGerador.csv", csvPath);
		escreveTipoReceita.escreveTiposReceitas(tipoReceitas, null, "ArquivoTiposReceitasGerador.csv", csvPath);
		escreveLancamento.escreveLancamentos(lancamentos, null, "ArquivoLancamentosGerador.csv", csvPath);
	}

	public void computarValorTotal(String dataInicio, String dataFim) {
		leitor = new LeitorCSVFinancas(csvPath, "ArquivoUsuarioGerador.csv", "ArquivoTiposDespesasGerador.csv",
				"ArquivoTiposReceitasGerador.csv", "ArquivoLancamentosGerador.csv");
		try {
			List<Lancamento> lancamentos = leitor.leLancamentos("ArquivoLancamentosGerador.csv");

			double despesas = 0;
			double receitas = 0;
			for (Lancamento lancamento : lancamentos) {
				if (lancamento.getData().compareTo(dataInicio) >= 0 && lancamento.getData().compareTo(dataFim) <= 0) {
					if (lancamento.getRD()) {
						despesas += lancamento.getValor();
					} else {
						receitas += lancamento.getValor();
					}
				}
			}
			System.out.println("Despesas entre " + dataInicio + " e " + dataFim + ": " + despesas);
			System.out.println("Receitas entre " + dataInicio + " e " + dataFim + ": " + receitas);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao computar valor total", e);
		}
	}

	public void computarValorAgregadoCategoria(String dataInicio, String dataFim, String categoria) {
		leitor = new LeitorCSVFinancas(csvPath, "ArquivoUsuarioGerador.csv", "ArquivoTiposDespesasGerador.csv",
				"ArquivoTiposReceitasGerador.csv", "ArquivoLancamentosGerador.csv");
		try {
			List<Lancamento> lancamentos = leitor.leLancamentos("ArquivoLancamentosGerador.csv");
			List<TipoDespesa> tipoDespesas = leitor.leTiposDespesas("ArquivoTiposDespesasGerador.csv");
			List<TipoReceita> tipoReceitas = leitor.leTiposReceitas("ArquivoTiposReceitasGerador.csv");

			double valorAgregado = 0;
			for (Lancamento lancamento : lancamentos) {
				if (lancamento.getData().compareTo(dataInicio) >= 0 && lancamento.getData().compareTo(dataFim) <= 0) {
					String subcategoria = lancamento.getSubcategoria();
					for (TipoDespesa tipoDespesa : tipoDespesas) {
						if (tipoDespesa.getSubcategoria().equals(subcategoria)
								&& tipoDespesa.getCategoria().equals(categoria)) {
							valorAgregado += lancamento.getValor();
							break;
						}
					}
					for (TipoReceita tipoReceita : tipoReceitas) {
						if (tipoReceita.getSubcategoria().equals(subcategoria)
								&& tipoReceita.getCategoria().equals(categoria)) {
							valorAgregado += lancamento.getValor();
							break;
						}
					}
				}
			}
			System.out.println("Valor agregado entre " + dataInicio + " e " + dataFim + " para a categoria " + categoria
					+ ": " + valorAgregado);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao computar valor agregado", e);
		}
	}

	public void computarValorAgregadoSubCategoria(String dataInicio, String dataFim, String tipo) {
		leitor = new LeitorCSVFinancas(csvPath, "ArquivoUsuarioGerador.csv", "ArquivoTiposDespesasGerador.csv",
				"ArquivoTiposReceitasGerador.csv", "ArquivoLancamentosGerador.csv");
		try {
			List<Lancamento> lancamentos = leitor.leLancamentos("ArquivoLancamentosGerador.csv");

			double valorAgregado = 0;
			for (Lancamento lancamento : lancamentos) {
				if (lancamento.getData().compareTo(dataInicio) >= 0 && lancamento.getData().compareTo(dataFim) <= 0) {
					if (lancamento.getSubcategoria().equals(tipo)) {
						valorAgregado += lancamento.getValor();
					}
				}
			}
			System.out.println("Valor agregado entre " + dataInicio + " e " + dataFim + " para a subcategoria " + tipo + ": "
					+ valorAgregado);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao computar valor agregado", e);
		}
	}

	public void imprimirRelatorioFinanceiro(String dataInicio, String dataFim) {
		System.out.println("Relatório Financeiro entre " + dataInicio + " e " + dataFim);

		computarValorTotal(dataInicio, dataFim);

		System.out.println("Receitas:");
	    List<TipoReceita> tipoReceitas = leitor.leTiposReceitas("ArquivoTiposReceitasGerador.csv");
	    Set<String> processedCategories = new HashSet<>();
	    for (TipoReceita tipoReceita : tipoReceitas) {
	        String categoria = tipoReceita.getCategoria();
	        if (!processedCategories.contains(categoria)) {
	            System.out.println("Categoria: " + categoria);
	            computarValorAgregadoCategoria(dataInicio, dataFim, categoria);
	            Set<String> processedSubcategories = new HashSet<>();
	            for (TipoReceita subcategoria : tipoReceitas) {
	                if (subcategoria.getCategoria().equals(categoria) && !processedSubcategories.contains(subcategoria.getSubcategoria())) {
	                    System.out.println("  Subcategoria: " + subcategoria.getSubcategoria());
	                    computarValorAgregadoSubCategoria(dataInicio, dataFim, subcategoria.getSubcategoria());
	                    processedSubcategories.add(subcategoria.getSubcategoria());
	                }
	            }
	            processedCategories.add(categoria);
	        }
	    }

	    System.out.println("Despesas:");
	    List<TipoDespesa> tipoDespesas = leitor.leTiposDespesas("ArquivoTiposDespesasGerador.csv");
	    processedCategories.clear();
	    for (TipoDespesa tipoDespesa : tipoDespesas) {
	        String categoria = tipoDespesa.getCategoria();
	        if (!processedCategories.contains(categoria)) {
	            System.out.println("Categoria: " + categoria);
	            computarValorAgregadoCategoria(dataInicio, dataFim, categoria);
	            Set<String> processedSubcategories = new HashSet<>();
	            for (TipoDespesa subcategoria : tipoDespesas) {
	                if (subcategoria.getCategoria().equals(categoria) && !processedSubcategories.contains(subcategoria.getSubcategoria())) {
	                    System.out.println("  Subcategoria: " + subcategoria.getSubcategoria());
	                    computarValorAgregadoSubCategoria(dataInicio, dataFim, subcategoria.getSubcategoria());
	                    processedSubcategories.add(subcategoria.getSubcategoria());
	                }
	            }
	            processedCategories.add(categoria);
	        }
	    }
	}
}