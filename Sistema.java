public class Sistema implements Cloneable
{
	private double matriz[][];					// linha, coluna
	private int qtdLinhas, qtdColunas;


	public Sistema(String arq) throws Exception
	{
		if(arq == "")
			throw new Exception("Arquivo inválido!");

		this.matriz = Leitor.LerArquivo(arq);
		this.qtdLinhas = this.matriz.length;			// 3
		this.qtdColunas = this.matriz[0].length;		// 4
	}

	public String resolver() throws Exception			// método principal
	{
		this.confereQuocientes();

		int i = 0;
		while(temZeroNaDiagonal() && i < Sistema.fatorial(this.qtdLinhas))
			this.trocarOrdemDasLinhas();

		this.tornarUmOPrimeiroElemento();

		return "";
	}

	private void confereQuocientes() throws Exception
	{
		double[] quocientesPossiveis = new double[qtdColunas - 1];

		for(int linha = 0; linha < this.qtdLinhas-1; linha++)										// pega até a segunda linha
		{
			for(int coluna = 0; coluna < this.qtdColunas-1; coluna++)								// pega até a terceira coluna
			{
				quocientesPossiveis[coluna] = (matriz[linha][coluna] / matriz[linha+1][coluna]);	// realiza a divisão
			}
			if(Sistema.mesmosQuocientes(quocientesPossiveis))
				throw new Exception("Sistema impossível de ser solucionado!");
		}
	}

	private static boolean mesmosQuocientes(double[] vetorAnalisado)
	{
		for(int aux = 0; aux < vetorAnalisado.length; aux++)
		{
			for(int i = 0; i < vetorAnalisado.length; i++)
			{
				if(vetorAnalisado[i] == vetorAnalisado[aux])
					return true;
			}
		}
		return false;
	}

	private void trocarOrdemDasLinhas()
	{
		double[] aux = new double[this.qtdColunas];

		for(int i = 0; i < this.qtdLinhas; i++)
		{
			if(this.matriz[i][i] == 0)
			{
				for(int coluna = 0; coluna < this.qtdColunas; coluna++)
				{
					aux[coluna] = this.matriz[i][coluna];
				}

				if(i == this.qtdLinhas-1)
				{
					for(int coluna = 0; coluna < this.qtdColunas; coluna++)
					{
						this.matriz[i][coluna] = this.matriz[0][coluna];
						this.matriz[0][coluna] = aux[coluna];
					}
				}

				else
				{
					for(int coluna = 0; coluna < this.qtdColunas; coluna++)
					{
						this.matriz[i][coluna] = this.matriz[i+1][coluna];
						this.matriz[i+1][coluna] = aux[coluna];
					}
				}
			}
		}
	}

	private boolean temZeroNaDiagonal()
	{
		for(int i = 0; i < this.qtdLinhas; i++)
		{
			if(this.matriz[i][i] == 0)
				return true;
		}
		return false;
	}

	private static int fatorial(int valor)
	{
		int prod = 1;

		for(int i = valor; i < 0; i--)
			prod *= i;

		return prod;
	}

	private void tornarUmOPrimeiroElemento()
	{
		double divisorComum = this.matriz[0][0];

		for(int i = 0; i < this.qtdColunas; i++)
		{
			this.matriz[0][i] = (this.matriz[0][i] / divisorComum);
		}
	}
}