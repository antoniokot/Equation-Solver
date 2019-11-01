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
		{
			this.trocarOrdemDasLinhas();
			i++;
		}

		while(i < this.qtdLinhas)
		{
			this.tornarUmOElementoDaDiagonalPrincipal(i);
			this.zerarAColunaDesejada(i);
		}

		return this.exibirValores();
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

	private static boolean mesmosQuocientes(double[] vetorAnalisado)								// AJEITAR
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

	private void tornarUmOElementoDaDiagonalPrincipal(int linCol)			// adaptado
	{
		double divisorComum = this.matriz[linCol][linCol];

		for(int i = 0; i < this.qtdColunas; i++)
		{
			this.matriz[linCol][i] /= divisorComum;
		}
	}

	private void zerarAColunaDesejada(int colunaDesejada)					// adaptado
	{
		double multiplicador = 0;
		double produtos[] = new double[this.qtdColunas];

		for(int linha = 0; linha < this.qtdLinhas; linha++)
		{
			if(this.matriz[linha][colunaDesejada] != 0 && linha != colunaDesejada)
			{
				multiplicador = -(this.matriz[linha][colunaDesejada]);	// -1/3

				for(int coluna = 0; coluna < this.qtdColunas; coluna++)
				{
					produtos[coluna] = this.matriz[colunaDesejada][coluna] * multiplicador; // 0, 0, 1/3, 8/3
				}
				somarResultadosAsDeMaisLinhas(produtos, linha);
			}
		}
	}

	private void somarResultadosAsDeMaisLinhas(double[] valoresASeremSomados, int lin)
	{
		for(int coluna = 0; coluna < this.qtdColunas; coluna++)
		{
			this.matriz[lin][coluna] += valoresASeremSomados[coluna];
		}
	}

	private String exibirValores()
	{
		String ret = "S = {( ";

			for(int linha = 0; linha < this.qtdLinhas; linha++)
			{
				ret += this.matriz[linha][this.qtdColunas-1] + "";

				if(linha != this.qtdLinhas-1)
					ret += ", ";
			}
		ret += ")}";

		return ret;
	}

	public String toString()
	{
		String ret = "";

		ret = this.qtdLinhas + "x" + this.qtdColunas + "\n";
		for(int linha = 0; linha < this.qtdLinhas; linha++)
		{
			for(int coluna = 0; coluna < this.qtdColunas; coluna++)
			{
				ret += (this.matriz[linha][coluna]>=10?"  ":" ") + this.matriz[linha][coluna];
			}
			ret += "\n";
		}
		return ret;
	}

	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;

		if(obj == this)
			return true;

		if(obj.getClass() != this.getClass())
			return false;

		Sistema sistema = (Sistema)obj;
		if(sistema.qtdLinhas != this.qtdLinhas || sistema.qtdColunas != this.qtdColunas)
			return false;

		for(int linha = 0; linha < this.qtdLinhas; linha++)
		{
			for(int coluna = 0; coluna < this.qtdColunas; coluna++)
			{
				if(sistema.matriz[linha][coluna] != this.matriz[linha][coluna])
					return false;
			}
		}

		return true;
	}

	public int hashCode()
	{
		int ret = 666;

		ret = ret * 11 + new Integer(this.qtdLinhas).hashCode();
		ret = ret * 11 + new Integer(this.qtdColunas).hashCode();
		for(int linha = 0; linha < this.qtdLinhas; linha++)
		{
			for(int coluna = 0; coluna < this.qtdColunas; coluna++)
			{
				ret = ret * 11 + new Double(this.matriz[linha][coluna]).hashCode();

			}
		}
		return ret;
	}

	public Object clone()
	{
		Sistema ret = null;
		try
		{
			ret = new Sistema(this);
		}
		catch(Exception erro)
		{
			// sei que não vai dar erro
		}
		return ret;
	}

	public Sistema(Sistema modelo) throws Exception
	{
		if(modelo == null)
			throw new Exception("Modelo inválido!");

		modelo.qtdLinhas = this.qtdLinhas;
		modelo.qtdColunas = this.qtdColunas;

		modelo.matriz = new double[modelo.qtdLinhas][modelo.qtdColunas];
		for(int linha = 0; linha < modelo.qtdLinhas; linha++)
		{
			for(int coluna = 0; coluna < modelo.qtdColunas; coluna++)
			{
				 modelo.matriz[linha][coluna] = this.matriz[linha][coluna];
			}
		}
	}
}




