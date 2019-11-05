package leitor;

import java.io.*;
import java.util.StringTokenizer;


public class Leitor																		// Singleton
{
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