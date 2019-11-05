import java.util.StringTokenizer;
import java.io.*;

import sistema.Sistema;
import teclado.Teclado;

public class Programa
{
    public static void main (String[] args)
    {
        try
        {
			System.out.println("=================================");
			System.out.println("SOLUCIONADOR DE SISTEMAS LINEARES");
			System.out.println("=================================");
			System.out.print("Por favor, digite o nome do arquivo com o sistema: ");
			Sistema sistema = new Sistema(Teclado.getUmString());
			System.out.println(sistema.resolver());
        }
        catch (Exception erro)
        {
			System.err.println(erro.getMessage());
		}
    }
}