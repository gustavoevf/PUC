package atividade2;

public class main {

	public static void main(String[] args) {
		MyIO.println("Digite as 5 velocidades(1 por linha):");
		int totalMultas = 0;
		
		for(int i=0; i<5; i++) {
			int velocidade = MyIO.readInt();
			totalMultas = velocidade > 60 ? 1 : 0 ;
		}
		
		MyIO.println(String.format("Quantidade de veiculos acima: %d. Valor arrecadado: %d", totalMultas, totalMultas*150));
	}

}
