package principal;

import bancoDados.BancoDeDados;
import bancoDados.Item;


public class Principal{
	private static BancoDeDados bd = new BancoDeDados();
	
	public static void main(String[] args) {
		/*
		Item item1 = new Item();
		//item1.setCodigo(12);
		//item1.setCodigoStr("na");
		item1.setDescricao("Testando");
		
		Item item2 = new Item();
		//item2.setCodigo(12);
		//item2.setCodigoStr("n");
		item2.setDescricao("Testando");
		
		System.out.println("comparação -> " + item1.compareTo(item2));
		//*/
		
		//System.out.println("� -> " + Integer.parseInt("�"));
		
		//*
		bd.iniciarEspecifico();
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
