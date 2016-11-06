package principal;

import java.util.LinkedList;

import bancoDados.BancoDeDados;
import bancoDados.Campo;
import bancoDados.ItemSimples;
import bancoDados.TabelaSimples;


public class Principal{
	private static BancoDeDados bd = new BancoDeDados();
	
	public static void main(String[] args) {
		System.out.println("Começou a aplicação\n");
		/*
		TabelaSimples tb = new TabelaSimples("ORGAO_SUPERIOR");
		
		tb.getCampos().add(new Campo("cdOrSup", "integer unsigned", "primary key"));
		tb.getCampos().add(new Campo("nmOrSup", "varchar(50)", "not null"));
		
		
		tb.getItens().add(new ItemSimples(1, "teste"));
		tb.getItens().add(new ItemSimples("testando"));
		tb.getItens().add(new ItemSimples("testando2"));
		tb.getItens().add(new ItemSimples(3));
		tb.getItens().add(new ItemSimples(2, "mais um"));
		tb.getItens().add(new ItemSimples("testando2"));

		//System.out.println(tb.inserirInSQL());

		bd.getTbSimples().add(tb);
		//bd.escreverScriptSQL();
		//*/

		//*
		System.out.println("Começou o levantamento dos dados\n");
		for (int i = 0; i < 6; i++){
			String nome = "Referências/" + (201501 + i) + "_Diarias.csv";
			
			bd.processoETL(nome);
		}
		
		System.out.println("Acabou o levantamento dos dados\n");
		
		System.out.println("A gerar/escrever script inserção");

		bd.scriptInsertInSQL();
		
		System.out.println("Script da inserção salvo");
		//*/

		
	}
}
