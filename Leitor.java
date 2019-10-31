import java.io.*;
import java.util.StringTokenizer;

public class Leitor
{
	public static double[][] LerArquivo(String arq) throws Exception
	{
		if(arq == "")
			throw new Exception("Arquivo inválido!");

		double matriz[][] = null;
		try
		{
			BufferedReader arquivo = new BufferedReader(new FileReader(arq));

			if(arquivo == null)
				throw new Exception("Arquivo não existe!");

			int qtdEquacoes = Integer.parseInt (arquivo.readLine());
			matriz = new double[qtdEquacoes][qtdEquacoes+1];

			for (int i=0; i<qtdEquacoes; i++)
			{
				StringTokenizer quebrador = new StringTokenizer (arquivo.readLine());
				int col =0;

				while (quebrador.hasMoreTokens())
				{
					matriz[i][col] = Double.parseDouble(quebrador.nextToken());
					col++;
				}
			}
		}
		catch(Exception erro)
		{
			System.err.println(erro.getMessage());
		}
		return matriz;
	}
}