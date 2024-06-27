package usp.mac321.ep2.ex2;

import java.util.List;

import usp.mac321.ep2.TipoReceita;

public interface TipoReceitaDAO {
	void escreveTiposReceitas(List<TipoReceita> tiposReceitas, TipoReceita tipoReceitaAdicional, String endereço, String CSV_PATH);
}
