public class Sistema implements Cloneable
{
	private double matriz[][];

	public Sistema(String arq) throws Exception
	{
		this.matriz = Leitor.LerArquivo(arq);
	}

	public String resolver()
	{

	}
}