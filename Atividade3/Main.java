package com.company;

public class Main {

    public static void main(String[] args) {
        int[][] questoes = new int[11][8];
        int[] notas = new int[10];
        int aprovacao = 0;

        MyIO.println("Digite o gabarito:");
        questoes[0] = getRespostas();

        MyIO.println("Digite a resposta dos alunos:");
        for(int i=1; i<11; i++) {
            MyIO.println(String.format("Aluno :%d", i));
            questoes[i] = getRespostas();
        }

        for(int i=1; i<11; i++) {
            for (int q=0; q<8; q++) {
                notas[i-1] += questoes[0][q] == questoes[i][q] ? 1 : 0;
            }
            aprovacao += notas[i-1] >= 5 ? 1 : 0;
        }

        MyIO.println("/nNotas:");
        for(int i=0; i<10; i++) {
            MyIO.println(String.format("Aluno %d, Nota:%d", i+1, notas[i]));
        }

        MyIO.println(String.format("/nAprovacao: %d0%%", aprovacao));
    }

    public static int[] getRespostas()  {
        int[] respostas = new int[8];
        for(int q=0; q<8; q++) {
            MyIO.println(String.format("Digite a resposta da questao %d", q+1));
            respostas[q] = MyIO.readInt();
        }
        return respostas;
    }
}
