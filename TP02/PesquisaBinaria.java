import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;
import java.lang.reflect.Array;

/**
 * Classe Lista feita pelo professor
 * 
 * @author Rodrigo Richard Gomes (baseado no c�digo de Max do Val Machado)
 * @version 1 10/2020
 * 
 */
class Lista {
	public Object[] array;
	private int n;
    private int compareCounter;

	/**
	 * Construtor da classe.
	 */
	public Lista() {
		this(6);
	}

	/**
	 * Construtor da classe.
	 * 
	 * @param tamanho Tamanho da lista.
	 */
	public Lista(int tamanho) {
		array = new Object[tamanho];
		n = 0;
	}

	/**
	 * Insere um elemento na primeira posicao da lista e desloca os demais elementos
	 * para o fim da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirInicio(Object item) {
		if (n < array.length) {
			// Desloca elementos para o fim do array
			for (int i = n; i > 0; i--)
				array[i] = array[i - 1];

			array[0] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirFim(Object item) {
		// validar insercao
		if (n < array.length) {
			array[n] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento em uma posicao especifica e move os demais elementos para
	 * o fim da lista.
	 * 
	 * @param item: elemento a ser inserido.
	 * @param pos:  Posicao de insercao.
	 */
	public boolean inserir(Object item, int pos) {

		// validar insercao
		if (n < array.length && pos >= 0 && pos <= n) {
			// Desloca elementos para o fim do array
			for (int i = n; i > pos; i--)
				array[i] = array[i - 1];

			array[pos] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Remove um elemento da primeira posicao da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @return Elemento a ser removido.
	 */
	public Object removerInicio() {
		if (n > 0) {
			Object item = array[0];
			n--;

			for (int i = 0; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * 
	 * @return Elemento a ser removido.
	 */
	public Object removerFim() {
		if (n > 0)
			return array[--n];
		return null;
	}

	/**
	 * Remove um elemento de uma posicao especifica da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @param pos: Posicao de remocao.
	 * @return Elemento a ser removido.
	 */
	public Object remover(int pos) {
		if (n > 0 && pos >= 0 && pos < n) {
			Object item = array[pos];
			n--;

			for (int i = pos; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (int i = 0; i < n; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("]");
	}

	/**
	 * Ordena a lista em ordem alfabética
	 */
	public void ordenar() {
		Arrays.sort(array);
	}

	public boolean pesquisaBinaria(Lista str, int menor, int maior, String nome) {
		int media = (maior + menor) / 2;
		String valorMeio = str.array[media].toString();

		if (menor > maior)
			return false;
		else if(valorMeio.compareTo(nome) == 0) 
			return true;
		else if (valorMeio.compareTo(nome) < 0)
			return pesquisaBinaria(str, media + 1, maior, nome);
		else 
			return pesquisaBinaria(str, menor, media - 1, nome);
	}

    public void matricula(long time){  // criação do arq log

        try {
            File myObj = new File("matrícula_binaria.txt");
            if (myObj.createNewFile()) {
            } else {
            }
		} catch (IOException e) {
            e.printStackTrace();
		}

		try {
            FileWriter myWriter = new FileWriter("matrícula_binaria.txt");
            myWriter.write("737163" + "\t" + time + "\t" + compareCounter);
            myWriter.close();
		} 
		catch (IOException e) {
            e.printStackTrace();
		}
    }
} // Termino da Classe Lista



public class PesquisaBinaria{

    public static boolean isFim(String s){
		return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    

	public static void main(String args[]) {

		Scanner leitor = new Scanner(System.in);
		Lista listaSeries = new Lista(31);
		long startTime = System.nanoTime();

		String in = "";
	
	
		while(true){       //entra com os nomes da series
			in = leitor.nextLine();
			in = in.replaceAll("_", " ");
			in = in.replaceAll(".html", ""); 
	
			if(isFim(in) == true)      // testa se a frase digitada foi "FIM"
				break; 
			listaSeries.inserirFim(in);
	
		}		
	
		while(true){             // testa se a serie está contida na lista 
		in = leitor.nextLine();
		if(isFim(in) == true)
			break; 
						
			if(listaSeries.pesquisaBinaria(listaSeries, 0, listaSeries.array.length - 1, in) == true) {
				System.out.println("SIM");
			}
			else {
				System.out.println("NÃO");
			}

		} 

		long endTime = System.nanoTime(); 
		long duration = (endTime - startTime); 
		listaSeries.matricula(duration);

		leitor.close();
    }

}