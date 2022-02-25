package atividade2;

public class main {

	public static void main(String[] args) {
		while(true) {
			MyIO.println("Digite o salario do funcionario:");
			double salarioAtual = MyIO.readDouble();
			
			double salarioAjustado = salarioAtual <= 1200 ? salarioAtual*1.1 : salarioAtual*1.05;
			
			MyIO.println(String.format("Salário do funcionario com ajuste: %.2f", salarioAjustado));
		}
	}
}
