package usp.mac321.ep2.ex2;

import java.util.List;

import usp.mac321.ep2.TipoDespesa;

public interface TipoDespesaDAO {
	void escreveTiposDespesas(List<TipoDespesa> tiposDespesas, String endereço);
}