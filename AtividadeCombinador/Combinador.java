import java.util.List;
import java.util.ArrayList;

public class Combinador
{
    public static void main(String[] args) {
        List<String> entradas = new ArrayList<String>();

		String entrada = MyIO.readLine();

        while(!entrada.equals("FIM")) {
            entradas.add(entrada);
            entrada = MyIO.readLine();
        }

        for(int i =0; i < entradas.size(); i++) {
            String[] palavras = entradas.get(i).split(" ", 2);            

            String retorno = "";
            boolean continuar = true;

            for(int j =0; continuar; j++) {
                continuar = false;
                if(j < palavras[0].length()) {
                    retorno += palavras[0].charAt(j);
                    continuar = true;
                }
                if(j < palavras[1].length()) {
                    retorno += palavras[1].charAt(j);
                    continuar = true;
                }
            }

            System.out.println(retorno);           
        }
	}
}