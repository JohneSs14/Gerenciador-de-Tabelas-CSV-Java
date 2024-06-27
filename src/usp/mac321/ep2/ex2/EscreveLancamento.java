package usp.mac321.ep2.ex2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import usp.mac321.ep2.Lancamento;

public class EscreveLancamento implements LancamentoDAO {
    @Override
    public void escreveLancamentos(List<Lancamento> lancamentos, Lancamento lancamentoAdicional, String endereço, String CSV_PATH) {
    	lancamentos.add(lancamentoAdicional);
        try (FileWriter writer = new FileWriter(CSV_PATH + endereço)) {
            for (Lancamento lancamento : lancamentos) {
                writer.write(lancamento.getIdentificador() + "," + lancamento.getData() + "," + lancamento.getUsuario().getApelido() + "," +  (lancamento.getRD()? "TRUE" : "FALSE") + "," + lancamento.getSubcategoria() + "," + lancamento.getValor() + "," + lancamento.getDescricao() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
