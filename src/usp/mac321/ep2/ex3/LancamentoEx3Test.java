package usp.mac321.ep2.ex3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import usp.mac321.ep2.Usuario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LancamentoEx3Test {

    @Test
    void testConstructor() {
    	Usuario Pai = new Usuario("Pai", "Epaminondas");
        LancamentoEx3 lancamento = new LancamentoEx3("10/02/2022", Pai , true, "Alimentação", 100.0, "Comida");
        assertEquals("10/02/2022", lancamento.getData());
        assertEquals(Pai, lancamento.getUsuario());
        assertEquals(true, lancamento.getRD());
        assertEquals("Alimentação", lancamento.getSubcategoria());
        assertEquals(100.0, lancamento.getValor());
        assertEquals("Comida", lancamento.getDescricao());
    }

    @Test
    void testGetDataAtual() {
        LancamentoEx3 lancamento = new LancamentoEx3("10/02/2022",  new Usuario("Pai", "Epaminondas"), true, "Alimentação", 100.0, "Comida");
        lancamento.setDataAtual("10/01/2021");
        assertEquals("10/01/2021", lancamento.getDataAtual());
    }

    @Test
    void testGetState() {
        LancamentoEx3 lancamento = new LancamentoEx3("10/02/2022",new Usuario("Pai", "Epaminondas"), true, "Alimentação", 100.0, "Comida");
        lancamento.setDataAtual("10/01/2021");
        assertEquals("Planejado", lancamento.getState());

        lancamento.setDataAtual("11/02/2022");
        assertEquals("Executado", lancamento.getState());

        lancamento.setDataAtual("09/02/2022");
        assertEquals("Planejado", lancamento.getState());
    }

    @Test
    void testSetData() {
        LancamentoEx3 lancamento = new LancamentoEx3("10/02/2022", new Usuario("Pai", "Epaminondas"), true, "Alimentação", 100.0, "Comida");
        lancamento.setDataAtual("10/01/2023");
        lancamento.setData("11/02/2022");
        assertEquals("11/02/2022", lancamento.getData());
        assertEquals("Executado", lancamento.getState());
    }

    @Test
    void testSetUsuario() {
        LancamentoEx3 lancamento = new LancamentoEx3("10/02/2022", new Usuario("Pai", "Epaminondas"), true, "Alimentação", 100.0, "Comida");
        Usuario novoUsuario = new Usuario("Mãe", "Maria");
        lancamento.setUsuario(novoUsuario);
        lancamento.setDataAtual("10/01/2023");
        assertEquals(novoUsuario, lancamento.getUsuario());

        assertThrows(RuntimeException.class, () -> lancamento.setUsuario(new Usuario("Pai", "Epaminondas")));
    }

    @Test
    void testSetRD() {
        LancamentoEx3 lancamento = new LancamentoEx3("10/02/2022", new Usuario("Pai", "Epaminondas"), true, "Alimentação", 100.0, "Comida");
        lancamento.setDataAtual("09/01/2021");
        lancamento.setRD(false);
        assertEquals(false, lancamento.getRD());
    }

    @Test
    void testSetDescricao() {
        LancamentoEx3 lancamento = new LancamentoEx3("10/02/2022", new Usuario("Pai", "Epaminondas"), true, "Alimentação", 100.0, "Comida");
        lancamento.setDescricao("Nova descrição");
        lancamento.setDataAtual("10/01/2023");
        assertEquals("Nova descrição", lancamento.getDescricao());

        
        assertThrows(RuntimeException.class, () -> lancamento.setDescricao("Outra descrição"));
    }

    @Test
    void testSetValor() {
        LancamentoEx3 lancamento = new LancamentoEx3("10/02/2022", new Usuario("Pai", "Epaminondas"), true, "Alimentação", 100.0, "Comida");
        lancamento.setValor(200.0);
        lancamento.setDataAtual("10/01/2023");
        assertEquals(200.0, lancamento.getValor());

       
        assertThrows(RuntimeException.class, () -> lancamento.setValor(300.0));
    }
}
