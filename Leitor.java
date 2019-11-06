package leitor;

import java.io.*;
import java.util.StringTokenizer;

/**
A classe Leitor foi criada com o propósito de ler um arquivo 
de texto (cujo endereço é recebido pelo parâmetro do método LerArquivo())
e retornar, após ler o arquivo, uma matriz.
Esta classe, um singleton sem atributos, possui apenas um método, o LerArquivo(), 
resposável por ler o arquivo enquanto adiciona os dados em uma matriz de double.
@author Antônio Hideto Borges Kotsubo & Matheus Seiji Luna Noda.
@since	2019.
*/
public class Leitor																		// Singleton
{
	/**
	Lê o arquivo inserindo os dados em uma matriz.
	O método LerArquivo() serve para ler um arquivo enquanto adiciona os dados
	em uma matriz de double que, por sua vez, será retornada.
	@param arq	    O endereço do arquivo de texto a ser lido.
	@throws Exception   Caso o endereço do arquivo de texto seja vazio.
	*/
	public static double[][] LerArquivo(String arq) throws Exception
	{
		if(arq == "")																	// Aqui verifica-se se o nome do arquivo não é null
			throw new Exception("Arquivo inválido!");									// Dispara excessão

		double matriz[][] = null;														// Declara-se uma variável matriz
		try
		{
			BufferedReader arquivo = new BufferedReader(new FileReader(arq));

			if(arquivo == null)															// Verifica-se se o arquivo não é null
				throw new Exception("Arquivo não existe!");								// Dispara excessão

			int qtdEquacoes = Integer.parseInt (arquivo.readLine());					// Pega do arquivo a quantidade de equações, no caso, o primeiro token do arquivo
			matriz = new double[qtdEquacoes][qtdEquacoes+1];							// Instancia-se a matriz

			for (int i=0; i<qtdEquacoes; i++)
			{
				StringTokenizer quebrador = new StringTokenizer (arquivo.readLine());
				int col =0;

				while (quebrador.hasMoreTokens())
				{
					matriz[i][col] = Double.parseDouble(quebrador.nextToken());			// Converte cada token encontrado para double
					col++;
				}
			}
		}
		catch(Exception erro)
		{
			System.err.println(erro.getMessage());
		}
		return matriz;																	// Retorna a matriz criada através da leitura do arquivo texto
	}
}
