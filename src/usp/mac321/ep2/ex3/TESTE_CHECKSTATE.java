package usp.mac321.ep2.ex3;

public class TESTE_CHECKSTATE {

    private static int checkState() {
        String lancamento = "22/05/2012";
        String dataAtual = "22/05/2010";

        String[] lancamentoParts = lancamento.split("/");
        int[] lancamentoDate = new int[3];
        for (int i = 0; i < 3; i++) {
            lancamentoDate[i] = Integer.parseInt(lancamentoParts[i]);
        }

        String[] dataAtualParts = dataAtual.split("/");
        int[] atualDate = new int[3];
        for (int j = 0; j < 3; j++) {
            atualDate[j] = Integer.parseInt(dataAtualParts[j]);
        }

        if (lancamentoDate[2] < atualDate[2]) {
            return 0; // Executado
        } else if (lancamentoDate[2] > atualDate[2]) {
            return 1; // Planejado
        } else if (lancamentoDate[1] < atualDate[1]) {
            return 0; // Executado
        } else if (lancamentoDate[1] > atualDate[1]) {
            return 1; // Planejado
        } else if (lancamentoDate[0] <= atualDate[0]) {
            return 0; // Executado
        } else {
            return 1; // Planejado
        }
    }

    public static void main(String[] args) {
        System.out.println(checkState());
    }
}





/*public class TESTE_CHECKSTATE {

	private static int checkState() {
		int[] lancamentoDate,  atualDate;
		int Executado = 0, Planejado = 1;
		String lancamento = "22/05/2012";
		String dataAtual = "20/03/2011";
		
		String[] parts1 = lancamento.split("/");
		for (int i = 0; i< 3; i++) {
			lancamentoDate[i] = Integer.parseInt(parts1[i]);}
		
		String[] parts2 = dataAtual.split("/");
		for (int j = 0; j< 3; j++) {
			atualDate[j] = Integer.parseInt(parts2[j]);}
		
			if (lancamentoDate[2] < atualDate[2]) 
				return Executado;
			else if (lancamentoDate[2] > atualDate[2])
				return Planejado;
			 else if (lancamentoDate[1] < atualDate[1])
				 return Executado;
			 else if (lancamentoDate[1] > atualDate[1])
					return Planejado;
			 else if (lancamentoDate[0] <= atualDate[0])
				 return Executado;
			 else if (lancamentoDate[0] > atualDate[0])
					return Planejado; 
					
		}
	public static void main(String[] args) {
	
	System.out.println (TESTE_CHECKSTATE.checkState());}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
		private int checkState(LancamentoEx3 lancamento) {
		int[] lancamentoDate, atualDate;
		int Executado = 0, Planejado = 1;

		String[] parts1 = lancamento.getData().split("/");
		for (int i = 0; i < 3; i++) {
			lancamentoDate[i] = Integer.parseInt(parts1[i]);
		}

		String[] parts2 = lancamento.getDataAtual().split("/");
		for (int j = 0; j < 3; j++) {
			atualDate[j] = Integer.parseInt(parts2[j]);
		}

		if (lancamentoDate[2] < atualDate[2])
			return Executado;
		else if (lancamentoDate[2] > atualDate[2])
			return Planejado;
		else if (lancamentoDate[1] < atualDate[1])
			return Executado;
		else if (lancamentoDate[1] > atualDate[1])
			return Planejado;
		else if (lancamentoDate[0] <= atualDate[0])
			return Executado;
		else if (lancamentoDate[0] > atualDate[0])
			return Planejado;

	}
	
	/////////////////////////////////////////////////////////////////////////
	
	
	// pode modificar - Data, - Responsavel, - Despesa, - Subcategoria, - Valor, -
	// Descrição
	// não pode modificar o ID pra não dar pau

	/*
	 * public int setIdentificador() { return identificador; }
	 */
	
	
}*/
