package usp.mac321.ep2.ex1;

import java.util.ArrayList;
import java.util.List;

import usp.mac321.ep2.Usuario;
import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;

public class TesteAPAGARDEPOIS {
	public static void main(String [] args) {
		LeitorCSVFinancas Leitor = new LeitorCSVFinancas ("csv/", "usuarios.csv", "tiposDespesas.csv", "tiposReceitas.csv", "lancamentos.csv");
		List<Usuario> Usuarios = new ArrayList<>();
		List<TipoDespesa> TiposDeDespesas = new ArrayList<>();
		List<TipoReceita> TiposDeReceitas = new ArrayList<>();
		List<Lancamento> Lancamentos = new ArrayList<>();
		Usuarios = Leitor.leUsuarios("usuarios.csv");
		TiposDeDespesas = Leitor.leTiposDespesas("tiposDespesas.csv");
		TiposDeReceitas = Leitor.leTiposReceitas("tiposReceitas.csv");
		Lancamentos = Leitor.leLancamentos("lancamentos.csv");
		System.out.println("-------------------------");
		for (Usuario usuario : Usuarios) {
            System.out.println("Apelido: " + usuario.getApelido());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println();
        }
		System.out.println("-------------------------");
		for (TipoDespesa tipodedespesa : TiposDeDespesas) {
            System.out.println("Categoria: " + tipodedespesa.getCategoria());
            System.out.println("Subcategoria: " + tipodedespesa.getSubcategoria());
            System.out.println();
        }
		System.out.println("-------------------------");
		for (TipoReceita tipodereceita : TiposDeReceitas) {
            System.out.println("Categoria: " + tipodereceita.getCategoria());
            System.out.println("Subcategoria: " + tipodereceita.getSubcategoria());
            System.out.println();
        }
		System.out.println("-------------------------");
		for (Lancamento lancamento : Lancamentos) {
            System.out.println(lancamento);
            System.out.println();
        }
		System.out.println("-------------------------");
	}
}
