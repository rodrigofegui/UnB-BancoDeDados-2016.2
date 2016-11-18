package principal;

import bancoDados.BancoDeDados;
import bancoDados.Item;


public class Principal{
	private static BancoDeDados bd = new BancoDeDados();
	
	public static void main(String[] args) {
		/*
		Item item = new Item();
		item.getCodigoComposto().add("teste");
		item.getCodigoComposto().add(2);
		item.getCodigoComposto().add(3);
		item.setDescricao("texto");
		item.getCodRefStr().add("t1");
		
		Item item2 = new Item();
		item2.getCodigoComposto().add("teste");
		item2.getCodigoComposto().add(4);
		item2.setDescricao("texto");
		item2.getCodRefStr().add("t1");
		
		System.out.println("Comparação -> " + item.compareTo(item2));
		//*/
		
		
		
		//*
		bd.iniciarEspecifico();
		System.out.println("Começou o levantamento dos dados\n");
		
		//for (int i = 0; i < 6; i++){
			//String nome = "Referências/" + (201501 + i) + "_Diarias.csv";
			String nome = "Referências/" + (201501 + 0) + "_Diarias.csv";
			
			bd.processoETL(nome);
		//}
		
		System.out.println("\nAcabou o levantamento dos dados\n");
		
		System.out.println("A gerar/escrever script inserção");

		bd.scriptInsertInSQL();
		
		System.out.println("Script da inserção salvo");
		//*/		
	}
}
