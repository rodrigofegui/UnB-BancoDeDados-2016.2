package principal;

import bancoDados.BancoDeDados;


public class Principal{
	private static BancoDeDados bd = new BancoDeDados();
	
	public static void main(String[] args) {
		bd.iniciarEspecifico();
		
		//*
		System.out.println("Começou o levantamento dos dados\n");
		
		for (int i = 0; i < 6; i++){
			String nome = "Referências/" + (201501 + i) + "_Diarias.csv";
			//String nome = "Referências/" + (201501 + 0) + "_Diarias.csv";
			
			bd.processoETL(nome);
		}
		
		System.out.println("\nAcabou o levantamento dos dados\n");
		
		System.out.println("A gerar/escrever script inserção");

		bd.scriptInsertInSQL();
		
		System.out.println("Script da inserção salvo");
		//*/		
	}
}
