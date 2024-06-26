package usp.mac321.ep2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usp.mac321.ep2.ex1.LeitorCSVFinancas;

class TestaLeitorFinancasPessoais {

    private LeitorCSVFinancas leitor;

    @BeforeEach
    void setUp() {
        leitor = new LeitorCSVFinancas("csv/", "usuarios.csv", "tiposDespesas.csv", "tiposReceitas.csv", "lancamentos.csv");
    }

    @Test
    void testLeUsuarios() {
        List<Usuario> usuarios = leitor.leUsuarios("usuarios.csv");
        assertNotNull(usuarios);
        assertEquals(2, usuarios.size());
        for (Usuario usuario : usuarios) {
            assertNotNull(usuario.getApelido());
            assertNotNull(usuario.getNome());
        }
    }

    @Test
    void testLeTiposDespesas() {
        List<TipoDespesa> tiposDespesas = leitor.leTiposDespesas("tiposDespesas.csv");
        assertNotNull(tiposDespesas);
        assertEquals(6, tiposDespesas.size());
        for (TipoDespesa tipoDespesa : tiposDespesas) {
            assertNotNull(tipoDespesa.getCategoria());
            assertNotNull(tipoDespesa.getSubcategoria());
        }
    }

    @Test
    void testLeTiposReceitas() {
        List<TipoReceita> tiposReceitas = leitor.leTiposReceitas("tiposReceitas.csv");
        assertNotNull(tiposReceitas);
        assertEquals(4, tiposReceitas.size());
        for (TipoReceita tipoReceita : tiposReceitas) {
            assertNotNull(tipoReceita.getCategoria());
            assertNotNull(tipoReceita.getSubcategoria());
        }
    }

    @Test
    void testLeLancamentos() {
        List<Lancamento> lancamentos = leitor.leLancamentos("lancamentos.csv");
        assertNotNull(lancamentos);
        assertEquals(8, lancamentos.size());
        for (Lancamento lancamento : lancamentos) {
            assertNotNull(lancamento);
        }
    }
}
