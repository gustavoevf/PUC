// Gustavo Ferreira Evaristo 
// Vinicius Santos
// João Vitor Bessa

import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) {
        int quantidade = InOut.readInt();
        Serie[] series = new Serie[quantidade];

        for(int i = 0; i < series.length; i++) {
            String informacoes = InOut.readLine();
            series[i] = new Serie(informacoes);
        }


        List<String> listaBusca = new ArrayList<String>();
        String textoBusca = InOut.readLine();

        while(!textoBusca.contains("FIM")) {
            listaBusca.add(textoBusca);
            textoBusca = InOut.readLine();
        }

        for(int i = 0; i < listaBusca.size(); i++) {
            String elemento = listaBusca.get(i);
            boolean encontrou = false;

            for(int j = 0; j < series.length && !encontrou; j++) {
                if(series[j].getNome().equals(elemento)) {
                    series[j].imprimir();
                    encontrou = true;
                }
            }
        }
    }
}

class Serie {
    private String nome;
    private String formato;
    private String duracao;
    private String pais;
    private String idioma;
    private String emissora;
    private String inicioTrans;
    private int temporadas;
    private int episodios;

    Serie(String atributos) {
        this.ler(atributos);
    }

    Serie() {
    }

    public String getNome() {
        return this.nome;
    }

    public String getFormato() {
        return this.formato;
    }

    public String getDuracao() {
        return this.duracao;
    }

    public String getPais() {
        return this.pais;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public String getEmissora() {
        return this.emissora;
    }

    public String getInicioTrans() {
        return this.inicioTrans;
    }

    public int getTemporadas() {
        return this.temporadas;
    }

    public int getEpisodios() {
        return this.episodios;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public void setInicioTrans(String inicioTrans) {
        this.inicioTrans = inicioTrans;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    public void imprimir() {
        String saida = "";
        saida += this.nome + " ## ";
        saida += this.formato + " ## ";
        saida += this.duracao + " ## ";
        saida += this.pais + " ## ";
        saida += this.idioma + " ## ";
        saida += this.emissora + " ## ";
        saida += this.inicioTrans + " ## ";
        saida += this.temporadas + " ## ";
        saida += this.episodios;

        InOut.println(saida);
    }

    public void ler(String atributos) {
        String[] atributosArr = atributos.split(";");
        this.nome = atributosArr[0];
        this.formato = atributosArr[1];
        this.duracao = atributosArr[2];
        this.pais = atributosArr[3];
        this.idioma = atributosArr[4];
        this.emissora = atributosArr[5];
        this.inicioTrans = atributosArr[6];
        this.temporadas = Integer.parseInt(atributosArr[7]);
        this.episodios = Integer.parseInt(atributosArr[8]);
    }
}

class InOut {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
    private static String charset = "ISO-8859-1";

    public static void println(String x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static int readInt() {
        int i = -1;
        try {
            i = Integer.parseInt(readString().trim());
        } catch (Exception e) {
        }
        return i;
    }

    public static String readString(){
        String s = "";
        char tmp;
        try{
            do{
                tmp = (char)in.read();
                if(tmp != '\n' && tmp != ' ' && tmp != 13){
                    s += tmp;
                }
            }while(tmp != '\n' && tmp != ' ');
        }catch(IOException ioe){
            System.out.println("lerPalavra: " + ioe.getMessage());
        }
        return s;
    }

    public static String readLine(){
        String s = "";
        char tmp;
        try{
            do{
                tmp = (char)in.read();
                if(tmp != '\n' && tmp != 13){
                    s += tmp;
                }
            }while(tmp != '\n');
        }catch(IOException ioe){
            System.out.println("lerPalavra: " + ioe.getMessage());
        }
        return s;
    }
}
