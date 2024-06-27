package usp.mac321.ep2.ex3;

public class LancamentoContext {
	private LancamentoState state;
	 
    public void setState(LancamentoState state) {
        this.state = state;
    }
 
    public void request() {
        state.handleRequest();
    }
}
