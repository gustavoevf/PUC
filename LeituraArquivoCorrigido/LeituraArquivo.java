// Gustavo Ferreira Evaristo 
// Vinicius Santos
// João Vitor Bessa

import java.io.*;
import java.nio.charset.*;

public class LeituraArquivo
{
    public static void main(String[] args) throws UnsupportedEncodingException {
        ArquivoTextoLeitura ArquivoLeitura = new ArquivoTextoLeitura("/tmp/data.txt");
        String entrada = ArquivoLeitura.ler();
        entrada = ArquivoLeitura.ler();

        while (entrada != null) {
            Serie serie = new Serie(entrada);
            serie.imprimir();
            entrada = ArquivoLeitura.ler();
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

class ArquivoTextoLeitura {

    private BufferedReader entrada;

    ArquivoTextoLeitura(String nomeArquivo) throws UnsupportedEncodingException {

        try {
            entrada = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo),"ISO-8859-1"));
        }
        catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        }
    }

    public void fecharArquivo() {

        try {
            entrada.close();
        }
        catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
        }
    }

    @SuppressWarnings("finally")
    public String ler() {

        String textoEntrada = null;

        try {
            textoEntrada = entrada.readLine();
        }
        catch (EOFException excecao) { //Excecao de final de arquivo.
            textoEntrada = null;
        }
        catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            textoEntrada = null;
        }
        finally {
            return textoEntrada;
        }
    }
}

class ArquivoTextoEscrita {

    private BufferedWriter saida;

    ArquivoTextoEscrita(String nomeArquivo) {

        try {
            saida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), "ISO-8859-1"));
        }
        catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        }
        catch (IOException excecao) {
            System.out.println("Erro na abertura do arquivo de escrita: " + excecao);
        }
    }

    public void fecharArquivo() {

        try {
            saida.close();
        }
        catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de escrita: " + excecao);
        }
    }

    public void escrever(String textoEntrada) {

        try {
            saida.write(textoEntrada);
            saida.newLine();
        }
        catch (IOException excecao){
            System.out.println("Erro de entrada/sa?da " + excecao);
        }
    }
}
