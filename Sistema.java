public class Sistema implements Cloneable
{
	private double matriz[][];

	public Sistema(String arq) throws Exception
	{
		if(arq == "")
			throw new Exception("Arquivo inv�lido!");

		this.matriz = Leitor.LerArquivo(arq);
	}

	public String resolver()
	{

	}
}