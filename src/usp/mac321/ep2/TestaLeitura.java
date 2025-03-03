package usp.mac321.ep2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usp.mac321.ep2.ex1.LeitorCSVFinancas;


class TestaLeitura {

    List<Usuario> usuarios;
    List<TipoDespesa> tiposDespesas;
    List<TipoReceita> tiposReceitas;
    List<Lancamento> lancamentos;
    LeitorFinancasPessoaisDAO leitor;

    @BeforeEach
    void setUp() throws Exception {
        leitor = new LeitorCSVFinancas("csv/", "usuarios.csv", "tiposDespesas.csv", "tiposReceitas.csv", "lancamentos.csv");
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void testTiposDespesas(){
        tiposDespesas = leitor.leTiposDespesas("tiposDespesas.csv");
        assertEquals(6, tiposDespesas.size());
    }

    @Test
    public void testTiposReceitas(){
        tiposReceitas = leitor.leTiposReceitas("tiposReceitas.csv");
        assertEquals(4, tiposReceitas.size());
    }

    @Test
    public void testUsuarios(){
        usuarios = leitor.leUsuarios("usuarios.csv");
        assertEquals(2, usuarios.size());
    }

    @Test
    public void testLancamentosOK(){
        lancamentos = leitor.leLancamentos("lancamentos.csv");
        assertEquals(8, lancamentos.size());
    }

    @Test
    public void testLancamentoUsuarioDesconhecido(){
        try {
            leitor.leLancamentos("lancamentosSemResponsa.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Usuário não encontrado: Pá", e.getMessage());
        }
    }

    @Test
    public void testLancamentoDespesaDesconhecida(){
        try {
            leitor.leLancamentos("lancamentosDespesaErrada.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Categoria não encontrada: Filme", e.getMessage());
        }
    }

    @Test
    public void testLancamentoReceitaDesconhecida(){
        try {
            leitor.leLancamentos("lancamentosReceitaErrada.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Categoria não encontrada: Salário", e.getMessage());
        }
    }
    
    @Test
    public void testArquivoNaoEncontrado() {
        try {
            leitor.leUsuarios("arquivo_nao_existe.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Arquivo não encontrado: arquivo_nao_existe.csv", e.getMessage());
        }
    }

    @Test
    public void testDoisUsuariosMesmoApelido() {
        try {
            leitor.leUsuarios("usuariosduplicados.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Apelido duplicado: Pai", e.getMessage());
        }
    }

    @Test
    public void testDoisLancamentosMesmoIdentificador() {
        try {
            leitor.leLancamentos("lancamentoduplicado.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Identificador duplicado: 1", e.getMessage());
        }
    }

    @Test
    public void testLancamentoReceitaCategoriaDespesa() {
        try {
            leitor.leLancamentos("categoriainvertida.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Categoria de receita/despesa incompatível: Supermercado", e.getMessage());
        }
    }

    @Test
    public void testLancamentoUsuarioNaoExiste() {
        try {
            leitor.leLancamentos("usuarionaoexiste.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Usuário não encontrado: Tio", e.getMessage());
        }
    }

    @Test
    public void testLancamentoValorNegativo() {
        try {
            leitor.leLancamentos("valornegativo.csv");
            fail("Deveria ter lançado uma exceção");
        } catch (RuntimeException e) {
            assertEquals("Valor negativo: -10000.0", e.getMessage());
        }
    }
}
