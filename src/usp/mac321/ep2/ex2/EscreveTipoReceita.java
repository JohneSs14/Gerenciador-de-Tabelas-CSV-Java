package usp.mac321.ep2.ex2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import usp.mac321.ep2.TipoReceita;

public class EscreveTipoReceita implements TipoReceitaDAO {
	@Override
	public void escreveTiposReceitas(List<TipoReceita> tiposReceitas, TipoReceita tipoReceitaAdicional, String endereço, String CSV_PATH) {
		if (tipoReceitaAdicional != null) tiposReceitas.add(tipoReceitaAdicional);
		try (FileWriter writer = new FileWriter(CSV_PATH + endereço)) {
			for (TipoReceita tiporeceita : tiposReceitas) {
				writer.write(tiporeceita.getCategoria() + "," + tiporeceita.getSubcategoria() + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
