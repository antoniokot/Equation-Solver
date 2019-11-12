import java.io.*;

import sistema.Sistema;

public class TesteSistema{
	public static void main(String[] args){
		try{
				//Teste de Sistema
				Sistema sist = new Sistema("gauss2.txt");
					//toString()
						System.out.println("Eis a matriz de Sistema: ");
						System.out.println(sist.toString());
					//clone()
						System.out.println("		Teste de Clone()\n");

						System.out.println("Uma copia de sist: ");
						Sistema sistClonado = (Sistema)sist.clone();
						System.out.println(sistClonado.toString());
						System.out.println("Agora resolvemos sist: ");
						System.out.println(sist.resolver());
						System.out.println("E ao printar sistClonado novamente: ");
						System.out.println(sistClonado.toString());
					//contrutor de cópia
						System.out.println("		Teste de Construtor de copia\n");

						Sistema sist2 = new Sistema("gauss2.txt");
						System.out.println("Uma copia de sist2: ");
						Sistema sistCopiado = new Sistema(sist2);
						System.out.println(sistCopiado.toString());
						System.out.println("Agora resolvemos sist2: ");
						System.out.println(sist2.resolver());
						System.out.println("E ao printar sistCopiado novamente: ");
						System.out.println(sistCopiado.toString());
					//resolver()
						System.out.println("		Teste de resolver()\n");

						Sistema sist3 = new Sistema("gauss2.txt");
						System.out.println(sist3.resolver());
					//hashCode()
						System.out.println("		Teste de hashCode()\n");

						System.out.print("Eis aqui o hashCode de Sistema: ");
						System.out.println(sist.hashCode()+"\n");
					//equals()
						System.out.println("		Teste de equals()\n");

						Sistema sistIgual = sist;
						System.out.print("O sistIgual eh equals ao sist? ");
						System.out.println(sist.equals(sistIgual)+"");

						Sistema sistDiferente = new Sistema("gauss.txt");
						System.out.print("O sistDiferente eh equals ao sist? ");
						System.out.println(sist.equals(sistDiferente)+"\n");


		}
		catch(Exception err){
			System.err.println(err.getMessage());
		}
	}
}
