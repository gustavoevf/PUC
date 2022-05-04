// Gustavo Ferreira Evaristo
// Vinicius Santos
// João Vitor Bessa
import java.io.*;

public class FilaNetflix {

    public static void main(String[] args) {
        ArquivoTextoLeitura arquivoLeitura = new ArquivoTextoLeitura("/tmp/data.txt");
        arquivoLeitura.ler();
        String entradaArquivo = arquivoLeitura.ler();
        Serie[] series = new Serie[200];
        int posicaoSerie = 0;

        while (entradaArquivo != null) {
            Serie serieEntrada = new Serie(entradaArquivo);
            series[posicaoSerie] = serieEntrada;
            entradaArquivo = arquivoLeitura.ler();
            posicaoSerie++;
        }

        FilaSerie fila = new FilaSerie(21);

        String entrada = MyIO.readLine();
        while(!entrada.equals("FIM")) {
            Serie serieCompleta = null;
            for(int i = 0; i < posicaoSerie && serieCompleta == null; i++) {
                if(series[i].getNome().equals(entrada))
                    serieCompleta = series[i];
            }

            if(serieCompleta != null)
                fila.enfileirar(serieCompleta);

            entrada = MyIO.readLine();
        }

        int qtdAcoes = MyIO.readInt();
        for (int i = 0; i < qtdAcoes; i++) {
            String comando = MyIO.readLine();

            if(comando.equals("R")) {
                Serie desenfileirada = fila.desenfileirar();
                MyIO.println("(R) " + desenfileirada.getNome());
            } else {
                String nomeSerieInserir = comando.split(" ", 2)[1];
                Serie serieCompleta = null;
                for(int j = 0; j < posicaoSerie && serieCompleta == null; j++) {
                    if(series[j].getNome().equals(nomeSerieInserir))
                        serieCompleta = series[j];
                }

                if(serieCompleta != null)
                    fila.enfileirar(serieCompleta);
            }

        }

        fila.mostrar();

    }

}

class FilaSerie {
    int tamanho;
    int tras;
    int frente;
    int temporadas;
    int quantidade;

    Serie[] fila;

    FilaSerie(int tamanho) {
        fila = new Serie[tamanho];
        this.tamanho = tamanho;
        this.frente = 0;
        this.tras = 0;
    }

    FilaSerie() {
        this.fila = new Serie[20];
        this.tamanho = 20;
        this.frente = 0;
        this.tras = 0;
    }

    void enfileirar(Serie serie) {
        if(filaCheia()) {
            this.desenfileirar();
        }

        this.fila[tras] = serie;
        this.tras = (this.tras + 1) % this.tamanho;
        this.temporadas += serie.getTemporadas();
        this.quantidade++;
        MyIO.println(String.valueOf(Math.round(this.obterMediaTemporadas())));
    }

    Serie desenfileirar() {
        Serie retorno = this.fila[frente];
        if(!filaVazia()) {
            this.temporadas -= this.fila[frente].getTemporadas();
            this.fila[frente] = null;
            this.frente = (this.frente + 1) % this.tamanho;
            this.quantidade--;
        }

        return retorno;
    }

    double obterMediaTemporadas() {
        return this.temporadas/(double)this.quantidade;
    }

    void mostrar() {
        for(int i = frente, posicao = 0; i != this.tras;i = (i + 1) % tamanho) {
            MyIO.print(String.format("[%d]", posicao));
            this.fila[i].imprimir();
            posicao++;
        }
    }

    Boolean filaCheia() {
        return (this.tras + 1) % this.tamanho == this.frente;
    }

    Boolean filaVazia() {
        return this.tras == this.frente;
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

        MyIO.println(saida);
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

class ArquivoTextoLeitura {

    private BufferedReader entrada;

    ArquivoTextoLeitura(String nomeArquivo) {

        try {
            entrada = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), "ISO-8859-1"));
        }
        catch (Exception excecao) {
            System.out.println("Arquivo nao encontrado: " + excecao.getMessage());
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