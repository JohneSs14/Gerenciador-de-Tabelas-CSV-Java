package usp.mac321.ep2.ex2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.Usuario;

public class TestaLancamentoDAO {
    @Test
    void testaEscritaLancamentos() {
    	Usuario Pai = new Usuario ("Pai", "Epaminondas");
        List<Lancamento> lancamentos = new ArrayList<>();
        lancamentos.add(new Lancamento("01/05/24", Pai, false, "Supermercado", -10000, "Salário do papai"));
        
        File file = new File("csv2/");
        assertTrue(file.exists());
        
        EscreveLancamento lancamento = new EscreveLancamento();
        lancamento.escreveLancamentos(lancamentos, "");
    }
}
