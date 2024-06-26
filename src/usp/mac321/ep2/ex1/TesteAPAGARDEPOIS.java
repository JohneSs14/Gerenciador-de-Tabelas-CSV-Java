package usp.mac321.ep2.ex1;

import java.util.ArrayList;
import java.util.List;

import usp.mac321.ep2.Usuario;

public class TesteAPAGARDEPOIS {
	public static void main(String [] args) {
		LeitorCSVFinancas Leitor = new LeitorCSVFinancas ("csv/");
		List<Usuario> Usuarios = new ArrayList<>();
		Usuarios = Leitor.leUsuarios("usuarios.csv");
		for (Usuario usuario : Usuarios) {
            System.out.println(usuario);
        }
	}
}
