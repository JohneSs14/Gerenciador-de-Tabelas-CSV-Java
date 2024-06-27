package usp.mac321.ep2.ex2;

import java.util.List;

import usp.mac321.ep2.Lancamento;

public interface LancamentoDAO {
	void escreveLancamentos(List<Lancamento> lancamentos, Lancamento lancamentoAdicional, String endereço, String CSV_PATH);
}
