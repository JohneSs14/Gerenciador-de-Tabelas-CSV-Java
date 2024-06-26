package usp.mac321.ep2.ex1;

import java.util.ArrayList;
import java.util.List;

import usp.mac321.ep2.Usuario;
import usp.mac321.ep2.TipoDespesa;

public class TesteAPAGARDEPOIS {
	public static void main(String [] args) {
		LeitorCSVFinancas Leitor = new LeitorCSVFinancas ("csv/");
		List<Usuario> Usuarios = new ArrayList<>();
		List<TipoDespesa> TiposDeDespesas = new ArrayList<>();
		Usuarios = Leitor.leUsuarios("usuarios.csv");
		TiposDeDespesas = Leitor.leTiposDespesas("tiposDespesas.csv");
		for (Usuario usuario : Usuarios) {
            System.out.println("Apelido: " + usuario.getApelido());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println();
        }
		for (TipoDespesa tipodedespesa : TiposDeDespesas) {
            System.out.println("Categoria: " + tipodedespesa.getCategoria());
            System.out.println("Subcategoria: " + tipodedespesa.getSubcategoria());
            System.out.println();
        }
	}
}
