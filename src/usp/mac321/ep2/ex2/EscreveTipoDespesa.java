package usp.mac321.ep2.ex2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import usp.mac321.ep2.TipoDespesa;

public class EscreveTipoDespesa implements TipoDespesaDAO {
	@Override
	public void escreveTiposDespesas(List<TipoDespesa> tiposDespesas, TipoDespesa tipoDespesaAdicional, String endereço, String CSV_PATH) {
		if (tipoDespesaAdicional != null) tiposDespesas.add(tipoDespesaAdicional);
		try (FileWriter writer = new FileWriter(CSV_PATH + endereço)) {
			for (TipoDespesa tipodespesa : tiposDespesas) {
				writer.write(tipodespesa.getCategoria() + "," + tipodespesa.getSubcategoria() + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
