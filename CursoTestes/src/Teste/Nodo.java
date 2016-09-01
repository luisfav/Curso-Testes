package Teste;

public class Nodo {
   private int numero;
   private String expressao;
   
   public Nodo(int numero) {
	  this.numero = numero;
   }

   public Nodo(String expressao) {
	  this.expressao = expressao;
   }
   
   public int avalia() {
	   char operacao;
	   if (expressao.contains("*"))
		   operacao = '*';
	       return(expressao.)
	   else if (expressao.contains("+"))
		   operacao = '+';
	   else
	      return numero;
   }
}
