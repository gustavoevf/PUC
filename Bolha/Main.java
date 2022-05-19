import java.io.*;

public class Main {

    public static void main(String[] args) {
        DataBase db = new DataBase();

        int quantidade = MyIO.readInt();
        Serie[] vetorOrdenar = new Serie[quantidade];

        for (int i = 0; i < quantidade; i++) {
            String nome = MyIO.readLine();
            vetorOrdenar[i] = db.buscarPorNome(nome);
        }

        vetorOrdenar = ordenar(vetorOrdenar);

        for (int i =0; i < quantidade; i++) {
            vetorOrdenar[i].imprimir();
        }

    }

    public static Serie[] ordenar(Serie[] v) {
        int comparacoes = 0, movimentacoes = 0;
        long inicio = System.currentTimeMillis();
        for(int i = 0; i < v.length - 1; i++) {
            boolean estaOrdenado = true;
            for(int j = 0; j < v.length - 1 - i; j++) {
                if(v[j].getDuracao().compareTo(v[j + 1].getDuracao()) > 0) {
                    Serie aux = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = aux;
                    estaOrdenado = false;
                    movimentacoes++;
                    comparacoes=comparacoes+1;
                }
                else if(v[j].getDuracao().compareTo(v[j + 1].getDuracao()) == 0 &&
                v[j].getNome().compareTo(v[j+ 1].getNome()) > 0) {
                    Serie aux = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = aux;
                    estaOrdenado = false;
                    movimentacoes++;
                    comparacoes+=comparacoes+2;
                }

                comparacoes=comparacoes+2;
            }
            // Se o vetor está ordenado então para as iterações sobre ele.
            if(estaOrdenado)
                break;
        }
        long tempo = (inicio - System.currentTimeMillis())/1000;
        logarInformacoes("1337437", tempo, comparacoes, movimentacoes);
        return v;
    }

    public static void logarInformacoes(String matricula, long tempo, int comparacoes, int movimentacoes) {
        ArquivoTextoEscrita escrita = new ArquivoTextoEscrita("1337437_bolha.txt");
        escrita.escrever(String.format("%s \t %d \t %d \t %d", matricula, tempo, comparacoes, movimentacoes));
        escrita.fecharArquivo();
    }
}

class DataBase {
    Serie[] db;
    int tamanho;

    DataBase() {
        preencheDB();
    }

    public Serie[] getDb() {
        return db;
    }

    private void preencheDB() {
        ArquivoTextoLeitura arquivoLeitura = new ArquivoTextoLeitura("/tmp/data.txt");
        arquivoLeitura.ler();
        String entrada = arquivoLeitura.ler();
        this.db = new Serie[100];
        int i;
        for (i = 0; entrada != null && !entrada.equals("FIM"); i++) {
            this.db[i] = new Serie(entrada);
            entrada = arquivoLeitura.ler();
        }

        tamanho = i;
    }

    public Serie buscarPorNome(String nome) {
        Serie retorno = null;
        for (int i = 0; i < this.tamanho && retorno == null; i++) {
            if(this.db[i].getNome().equals(nome)) {
                retorno = this.db[i];
            }
        }

        return retorno;
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
    private static String charset = "ISO-8859-1";

    ArquivoTextoLeitura(String nomeArquivo) {

        try {
            entrada = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), charset));
        }
        catch (Exception excecao) {
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
            saida = new BufferedWriter(new FileWriter(nomeArquivo));
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
            System.out.println("Erro de entrada/saída " + excecao);
        }
    }
}



