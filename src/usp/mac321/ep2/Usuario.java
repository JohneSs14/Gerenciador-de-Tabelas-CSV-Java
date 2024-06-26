package usp.mac321.ep2;

public class Usuario {
	private String apelido;
	private String nome;

	public Usuario(String apelido, String nome) {
		this.apelido = apelido;
		this.nome = nome;
	}
	
    @Override
    public String toString() {
        return "Apelido: " + apelido + ", Nome: " + nome;
    }
}
