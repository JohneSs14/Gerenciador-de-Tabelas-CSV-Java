package usp.mac321.ep2.ex2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import usp.mac321.ep2.Usuario;

public class EscreveUsuario implements UsuarioDAO {
	@Override
	public void escreveUsuarios(List<Usuario> usuarios, String endereço) {
		try (FileWriter writer = new FileWriter("csv2/" + endereço)) {
			for (Usuario usuario : usuarios) {
				writer.write(usuario.getApelido() + "," + usuario.getNome() + "\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
