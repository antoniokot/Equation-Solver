import java.util.StringTokenizer;
import java.io.*;

public class Programa
{
    public static void main (String[] args)
    {
        try
        {
		Sistema sistema = new Sistema("gauss.txt");
		sistema.resolver();
        }
        catch (Exception erro)
        {
		//...
	}
    }
}
