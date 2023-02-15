package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Entities.Produtos;

public class Main {

	public static void main(String[] args) {

		// instancia o metodo locale e scanner
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		// cria uma lista do tipo produto
		List<Produtos> list = new ArrayList<>();
		
		// solicita que o usuario insira o caminho
		System.out.println("Digite o caminho: ");
		String caminhoArquivo = sc.nextLine();
		
		// cria o caminho
		File file = new File(caminhoArquivo);
		// pega o caminho do arquivo
		String caminhoPasta = file.getParent();
		// cria a pasta out
		boolean sucesso = new File(caminhoPasta + "\\out").mkdir();
		// cria  apasta out e summary
		String caminhoEscrita = caminhoPasta + "\\out\\summary.txt";
		
		// trenta fazer a leitura do arquivo no caminho digitado
		try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){
			// define o metodo leitura de linha na variavel 'line'
			String line = br.readLine();
			// cria um laço para ler a linha até que seja diferente de vazia
			while(line != null) {
				// imprime a linha
				System.out.println(line);
				// separa os items pelo delimitador virgula
				String[] produtos = line.split(",");
				// armazena as posições separadas em cada variavel de acordo com a ordem
				String nome = produtos[0];
				Double valor = Double.parseDouble(produtos[1]);
				int quantidade = Integer.parseInt(produtos[2]);
				// instancia um produto a partir das variaveis criadas
				list.add(new Produtos(nome, valor, quantidade));
				// passa para o proximo item
				line = br.readLine();	
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoEscrita))){
				
				// percorre a lista de produtos criada
				for(Produtos produtos : list) {
					// escreve o nome do produto e chama a função total da classe produtos
					bw.write(produtos.getNome() + "," + String.format("%.2f", produtos.total()));
					// pula para a proxima linha
					bw.newLine();
				}
				
			}
			catch(IOException e){
				System.out.println("Erro de Escrita " + e.getMessage());
			}
		// trata a exceção caso aconteça
		}
		catch(IOException e){
			System.out.println("Erro de leitura " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("A pasta contendo as informações foi criada com sucesso!" + sucesso);
		
		sc.close();
	}
}
