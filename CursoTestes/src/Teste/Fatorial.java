package Teste;

public class Fatorial {
   public int calcula(int numero) {
	   if (numero == 0) 
		   return(1);
	   else if (numero == 1)
		   return(1);
	   else return (numero*calcula(numero-1));
   }
}
