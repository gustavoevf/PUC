package atividade2;

public class main {

	public static void main(String[] args) {
		while(true) {
			double somaNotas = 0;
			double mediaNotas = 0;
			MyIO.println("Digite as 3 notas do aluno:");
			
			for(int i=0; i<3; i++) {
				MyIO.println(String.format("Nota %d", i+1));
			    somaNotas += MyIO.readDouble();
			}
			
			mediaNotas = somaNotas/3;
			
			if(mediaNotas < 4)
				MyIO.println("Reprovado");
			else if(mediaNotas >= 4 && mediaNotas < 6)
				MyIO.println("Exame Especial");
			else
				MyIO.println("Aprovado");
		}
	}

}
