package usp.mac321.ep2.ex2;

import java.util.List;

import usp.mac321.ep2.Usuario;

public interface UsuarioDAO {
    void escreveUsuarios(List<Usuario> usuarios, String endereço);
}
