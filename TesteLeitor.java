public class TesteLeitor
{
	public static void main(String[] args)
	{
		try
		{
			//Teste da classe Leitor
				Sistema sist = new Sistema("../Gauss-Project-master/gauss.txt");
				System.out.println(sist.toString());
		}
		catch(Exception err)
		{
			System.err.println(err.getMessage());
		}
	}
}