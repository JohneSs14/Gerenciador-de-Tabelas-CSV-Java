package usp.mac321.ep2.ex2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import usp.mac321.ep2.Usuario;

public class TestaUsuario {
    @Test
    void testaEscritaLancamentos() {
    	Usuario Pai = new Usuario ("Pai", "Epaminondas");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(Pai);
        usuarios.add(Pai);
        
        File file = new File("csv2/");
        assertTrue(file.exists());
        
        EscreveUsuario usuario = new EscreveUsuario();
        usuario.escreveUsuarios(usuarios, "");
    }
}
