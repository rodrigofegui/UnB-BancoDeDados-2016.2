package bancoDados;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Classe responsável pela implementação lógica de uma Tabela
 * do BD desenvolvido
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	06/11/2016
 */
public class Tabela extends TabelaSimples{
	/**
	 * Lista de itens pertencentes à Tabela 
	 */
	private LinkedList<Item> itens;

	
	
	/**
	 * Construção de uma Tabela por maneira default
	 */
	public Tabela(){
		super();
		
		setItens(new LinkedList<Item>());
	}
	
	/**
	 * Construção de uma Tabela, conhecendo os itens
	 * @param itens Itens de inicialização
	 */
	public Tabela (LinkedList<Item> itens){
		this();
		
		setItens(itens);
	}
	
	/**
	 * Construção de uma Tabela Simples conhecendo
	 * o nome e os campos
	 * @param nome Nome a ser atribuido
	 * @param campos Campos a serem atribuídos
	 */
	public Tabela (String nome, LinkedList<Campo> campos){
		this();
		
		setNome(nome);

		setCampos(campos);
	}
	
	
	
	/**
	 * Valor atribuído aos itens da tabela
	 * @return Itens existentes na tabela
	 */
	public LinkedList<Item> getItens() {
		return itens;
	}

	/**
	 * Atribuição de itens à tabela
	 * @param itens Itens a serem atribuídos
	 */
	public void setItens(LinkedList<Item> itens) {
		this.itens = itens;
	}
	
	/**
	 * Representação inteligível da tabela para inserção
	 * num banco de dados em SQL
	 * @return Inserção para SQL
	 */
	public String inserirInSQL(){
		String toSQL = "";
		
		if (!getItens().isEmpty()){
			int tot = getItens().size();
			//System.out.println("Entrou no insert, com " + tot + " itens");
			int contador = 1;
			
			ordenarItens();
			
			//System.out.println("Total d");
			
			for (Item item : getItens()){
				//System.out.println("Faltam " + --tot);
				String linha = "insert into ";
				
				linha += getNome() + " (";
				
				for (Campo campo : getCampos())
					linha += campo.getDescricao() + ", ";
				
				//System.out.println("pegou campos");
				
				linha = linha.substring(0, linha.length() - 2) + ") ";
				
				if ((item.getCodigo() == Item.maxInt)
					&& (item.getCodigoStr().equals("")))
					item.setCodigo(contador);
				
				linha += item.inserirNoSQL();
				
				/*
				System.out.println("linha insercao:");
				System.out.println(linha);
				//*/
				
				toSQL += linha;
				
				contador++;
			}
			
		}else{
			//System.out.println("Sem itens");
			toSQL = getNome() + ": ";
			
			toSQL += "Sem atributos, mas com os campos:\n";
			
			toSQL += getCampos().toString(); 
		}
		
		toSQL += "\n\n";
		
		return toSQL;
	}
	
	
	/**
	 * Ordenação dos itens da tabela
	 */
	public void ordenarItens (){
		Collections.sort(getItens(), new Comparator<Item>() {
		    public int compare (Item objetoUm, Item objetoDois) {
		        return objetoUm.compareTo(objetoDois);
		    }
		});
	}
}
