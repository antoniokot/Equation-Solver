import java.io.*;

import sistema.Sistema;
import leitor.Leitor;

public class TesteLeitor
{
	public static void main(String[] args)
	{
		try
		{
			//Teste da classe Leitor
				Sistema sist = new Sistema("gauss.txt");
				System.out.println(sist.toString());
		}
		catch(Exception err)
		{
			System.err.println(err.getMessage());
		}
	}
}
