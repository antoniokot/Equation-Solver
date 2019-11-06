package sistema;

import leitor.Leitor;

/**
A classe Sistema serve para resolver qualquer sistema de equações lineares,
desde que tal seja resolvível.
Nesta classe, resolve-se o sistema de equações recebido através do método de
resolução desenvolvido por Carl Friedrich Gauss, que resolverá qualquer sistema
de equações lineares, "zerando" as incógintas e limitando cada equação à apenas
uma variável.
@author Antônio Hideto Borges Kotsubo & Matheus Seiji Luna Noda.
@see    leitor.Leitor.
@since  2019.
*/
public class Sistema
{
	protected double matriz[][];							// aqui declare-se os atributos matriz, qtdLinhas, qtdColunas e qtdEquacoes
	protected int qtdLinhas, qtdColunas, qtdEquacoes;

	/**
	Constroi uma nova instância da classe Sistema.
	Este construtor recebe um endereço de arquivo de texto, que será lido pelo método
	LerArquivo() da classe importada Leitor, e atribuirá aos atributos desta classe os
	valores correspondentes à matriz lida.
	@param  arq 	   Endereço do arquivo de texto que será lido pela classe Leitor.
	@throws Exception  Caso o endereço do arquivo de texto seja vazio ("");
	@see	leitor.Leitor#LerArquivo()
	*/
	public Sistema(String arq) throws Exception
	{
		if(arq == "")															// se o nome do arquivo for cadeia vazia, dispara-se excessão
			throw new Exception("Arquivo invalido!");

		this.matriz = Leitor.LerArquivo(arq);									// Atribui-se ao atributo matriz uma matriz retornada por LerArquivo()
		this.qtdLinhas = this.matriz.length;									// Aqui diz-se quantas linhas há na matriz
		this.qtdColunas = this.matriz[0].length;								// Aqui diz-se quantas linhas há na matriz
		this.qtdEquacoes = this.matriz.length;									// Aqui diz-se quantas equações há no sistema
	}


	/**
	Utiliza de outros métodos desta classe para resolver o sistema.
	Através de outros métodos, tais como o confereQuociente() ou o temZeroNaDiagonal(),
	manipula-se as linhas da matriz (atributo da classe) para resolver os sistemas.
	@return 		Retorna os valores, em formato de uma String, já resolvidos dos sistema.
	@throws Exception 	Caso tenhamos permutado as equações de posição, sem conseguir resolver
				o sistema.
	@see	#confereQuociente().
	@see 	#temZeroNaDiagonal().
	@see	#trocarOrdemSasLinhas().
	@see	#tornarUmOElementoDaDiagonalPrincipal(int linha).
	@see	#zerarAColunaDesejada(int colunaDesejada).
	@see	#exibirValores().
	*/
	public String resolver() throws Exception									// Método principal, aquele que vai chamar os métodos necessários para resolver os sistemas
	{
		this.confereQuocientes();												// Confere se há um mesmo quociente entre as divisões da linha de cima e a de baixo

		int i = 0;
		while(temZeroNaDiagonal() && i < this.qtdEquacoes)						// Realiza a troca de linhas enquanto houver algum zero na diagonal
		{
			this.trocarOrdemDasLinhas();										// Chama-se o método que troca as linhas de lugar
			i++;
		}
		if(i == this.qtdEquacoes)												// Aqui confere-se se já se foram todas as possibilidades de disposição de linha foram tentadas
			throw new Exception("Impossivel de solucionar esse sistema!\n");	// Dispara-se a excessão necessária

		for(int ib = 0; ib < this.qtdLinhas; ib++)
		{
			this.tornarUmOElementoDaDiagonalPrincipal(ib);						// Chama-se o método para tornar UM alguem elemento da diagonal principal
			this.zerarAColunaDesejada(ib);										// Chama-se 0 método para zerar a coluna desejada
		}

		return this.exibirValores();
	}


	/**
	Confere se o sistema é solucionável.
	Este método confere se os quociente das divisões dos coeficientes de CADA linha do sistema
	é igual, e caso seja, lança uma exceção reportando a impossibilidade da solução.
	@throws Exception	Caso os quocientes seja iguais, é impossível de solucionar o sistema.
	@see    #mesmoQuociente(double[] vetorAnalisado).
	*/
	protected void confereQuocientes() throws Exception												// Este método adiciona a um vetor os coeficientes entre a linha de cima e a de baixo
	{
		double[] quocientesPossiveis = new double[qtdColunas - 1];

		for(int linha = 0; linha < this.qtdLinhas-1; linha++)
		{
			for(int coluna = 0; coluna < this.qtdColunas-1; coluna++)
			{
				quocientesPossiveis[coluna] = (matriz[linha][coluna] / matriz[linha+1][coluna]);	// Realiza a divisão entre a linha de cima e a de baixo
			}
			if(this.mesmosQuocientes(quocientesPossiveis))										// Analisa o vetor em questão
				throw new Exception("Sistema impossível de ser solucionado!\n");					// Dispara-se a excessão necessária
		}
	}
	
	/**
	Verifica se o quociente de UMA divisão é corresponde à outra.
	Aqui verificamos, através de um vetor, se ao dividir os coeficientes todos os quocientes 
	são iguais (utilizado no método confereQuocientes() que chamará este método a cada divisão).
	@param 	vetorAnalisado  Linha a ser analisada desta vez.
	@return 		Retorna se os quocientes são iguais (true) ou não (false).
	*/
	protected boolean mesmosQuocientes(double[] vetorAnalisado)								// É o método que analisa se em um mesmo vetor há dois valores iguais, tornando impossível a resolução do sistema
	{
		int contador = 0;
		for(int i = 0; i < vetorAnalisado.length; i++)
		{
			if(vetorAnalisado[0] == vetorAnalisado[i])
				contador++;
		}
		if(contador == this.qtdColunas-1)
			return true;
		return false;
	}


	protected void trocarOrdemDasLinhas()															// Este método troca as linhas de lugar
	{
		double[] aux = new double[this.qtdColunas];													// Cria-se um vetor para auxiliar no deslocamento das linhas

		for(int i = 0; i < this.qtdLinhas; i++)
		{
			if(this.matriz[i][i] == 0)																// Aqui occore a verificação da existência de 0 na diagonal principal
			{
				for(int coluna = 0; coluna < this.qtdColunas; coluna++)
				{
					aux[coluna] = this.matriz[i][coluna];											// Copia-se a linha, onde se encontra o zero na diagonal pricipal, para o vetor auxiliar
				}

				if(i == this.qtdLinhas-1)															// Aqui ocorre a verificação da presença de zero na última linha
				{
					for(int coluna = 0; coluna < this.qtdColunas; coluna++)
					{
						this.matriz[i][coluna] = this.matriz[0][coluna];							// Caso haja, a linha posicionada (a última) recebe os elementos da primeira linha
						this.matriz[0][coluna] = aux[coluna];										// E depois a primeira linha recebe os elementos da última linha, guardados anteriormente
					}
				}

				else																				// Caso a linha em questão não seja a última
				{
					for(int coluna = 0; coluna < this.qtdColunas; coluna++)
					{
						this.matriz[i][coluna] = this.matriz[i+1][coluna];							// Simplesmente a linha em questão recebe os elementos da próxima linha
						this.matriz[i+1][coluna] = aux[coluna];										// E a próxima linha recebe os elementos da linha em questão, armazenados no vetor auxiliar
					}
				}
			}
		}
	}
	
	/**
	Verifica se a diagonal principal tem algu zero.
	Verificamos se a diagonal principal, onde o número da coluna equivale ao da linha,
	possui algum zero (0) (Neste método ignoramos a última colunas da matriz, sendo ela
	irrelevante no momento).
	@return 	Caso exista algum zero na diagonal principal retorna true e caso não false.
	*/
	protected boolean temZeroNaDiagonal()															// Este método confere a existência de zero em qualquer posição da diagonal principal
	{
		for(int i = 0; i < this.qtdLinhas; i++)
		{
			if(this.matriz[i][i] == 0)
				return true;																		// Retorna true caso haja zero na diagonal
		}
		return false;																				// Retorna false caso não haja
	}
	
	/**
	Transforma em um (1) o elemento da diagonal principal.
	Este método recebe um parâmetro inteiro "linha" que especificará qual linha da diagonal
	principal deverá ser transformado em 1, e tratando da diagonal principal, "linha" corresponde
	também ao número da coluna.
	@param	linha		Número indicador de qual linha da diagonal principal estamos tratando.
	*/
	protected void tornarUmOElementoDaDiagonalPrincipal(int linha)									// Este método torna UM o elemento da diagonal principal
	{
		int coluna = linha;
		double divisorComum = this.matriz[linha][coluna];											// Aqui encontra-se o divisor necessário para dividir a linha por completo

		for(int i = 0; i < this.qtdColunas; i++)
		{
			this.matriz[linha][i] /= divisorComum;													// Aqui ocorre a divisão dos elementos da linha passada como parâmetro
		}
	}
	
	/**
	Zera-se (transforma em 0) a coluna desejada.
	Através do parâmetro "colunaDesejada", este método transforma em zero uma coluna desejada, 
	utilizando-se de outras equações e subtrações para fazê-lo.
	@param  colunaDesejada		Especifica qual a coluna que desaja-se zerar.
	@see	somarResultadoAsDemaisLinhas(double[] valoresASeremSomados, int lin).
	*/
	protected void zerarAColunaDesejada(int colunaDesejada)															// Este método zera a coluna desejada
	{
		double multiplicador = 0;
		double produtos[] = new double[this.qtdColunas];															// Instancia-se um vetor que armazenará os produtos gerados para serem somados
		int linhaDoElementoDaDiagonalPrincipal = colunaDesejada;

		for(int linha = 0; linha < this.qtdLinhas; linha++)
		{
			if(this.matriz[linha][colunaDesejada] != 0 && linha != colunaDesejada)									// Verifica-se se o elemento da matriz[linha][colunaDesejada] é diferente de zero e não faz parte da diagonal principal
			{
				multiplicador = -(this.matriz[linha][colunaDesejada]);												// Este é o número que desejamos zerar com o sinal trocado

				for(int coluna = 0; coluna < this.qtdColunas; coluna++)
				{
					produtos[coluna] = this.matriz[linhaDoElementoDaDiagonalPrincipal][coluna] * multiplicador; 	// Aqui ocorre a multiplicação entre os valores da linha do elemento da diagonal principal
				}
				somarResultadosAsmeMaisLinhas(produtos, linha);														// Chama-se o método para somar os valores encontrados através da multiplicação anterior
			}
		}
	}
	
	/**
	Soma duas equações de modo a zerar variáveis.
	No método zerarAColunaDesejada(), desta mesma classe busca-se somar duas equações em ordem de
	obter uma equação com uma das colunas zeradas, para isso usa-se este método que recebe como parâmetro
	um array com a equação a ser somada a linha, passada também como parâmetro.
	@param valoresASeremSomados	Esta é a nova equação que será somada à linha desejada.
	@param lin			Esta é a linha desejada.
	*/
	protected void somarResultadosAsDemaisLinhas(double[] valoresASeremSomados, int lin)							// Este método zera a coluna desejada, passada no método que chama este, através de adições
	{
		for(int coluna = 0; coluna < this.qtdColunas; coluna++)
		{
			this.matriz[lin][coluna] += valoresASeremSomados[coluna];												// Aqui ocorrem as adições
		}
	}
	
	/**
	Retorna a resolção do sistema.
	Neste método retorna-se os valores das respostas concatenados em uma string, e ordem
	respectiva aos do arquivo de texto orignalmente lido.
	@return 	Retorna uma string com as respostas do sistema.
	*/
	protected String exibirValores()																				// Este método exibi os resultados do sistema
	{
		String ret = "Os valores sao respectivamente: S = {( ";

			for(int linha = 0; linha < this.qtdLinhas; linha++)
			{
				ret += this.matriz[linha][this.qtdColunas-1] + "";													// Aqui concatena-se os respectivos resultados do sistema na variável ret que será retornada

				if(linha != this.qtdLinhas-1)
					ret += ", ";
			}
		ret += ")}\n";

		return ret;
	}

	public String toString()																						// Este método printa a matriz em seu estado atual
	{
		String ret = "";

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

	public boolean equals(Object obj)																				// Este método confere se dois objetos são iguais em conteúdo
	{
		if(obj == null)
			return false;

		if(obj == this)
			return true;

		if(obj.getClass() != this.getClass())
			return false;

		Sistema sistema = (Sistema)obj;
		if(sistema.qtdLinhas != this.qtdLinhas || sistema.qtdColunas != this.qtdColunas || sistema.qtdEquacoes != this.qtdEquacoes)
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

	public int hashCode()																							// Este método é utilizado para fazer o hash
	{
		int ret = 666;

		ret = ret * 11 + new Integer(this.qtdLinhas).hashCode();
		ret = ret * 11 + new Integer(this.qtdColunas).hashCode();
		ret = ret * 11 + new Integer(this.qtdEquacoes).hashCode();
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
			throw new Exception("Modelo invalido!");
		
		modelo.qtdLinhas = this.qtdLinhas;
		modelo.qtdLinhas = this.qtdColunas;
		modelo.qtdLinhas = this.qtdEquacoes;
		
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




