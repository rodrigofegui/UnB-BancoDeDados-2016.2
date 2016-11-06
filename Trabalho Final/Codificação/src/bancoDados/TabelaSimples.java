package bancoDados;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Classe responsável pela implementação lógica de uma Tabela Simples
 * do BD desenvolvido
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	29/10/2016
 */
public class TabelaSimples{
	/**
	 * Lista de itens pertencentes à tabela
	 * Identificador da tabela
	 */
	private LinkedList<ItemSimples> itens;
	private LinkedList<Campo> campos;
	private String nome;
	
	
	
	/**
	 * Construção de uma Tabela Simples por maneira default
	 */
	public TabelaSimples (){
		setItens(new LinkedList<ItemSimples>());
		
		setCampos(new LinkedList<Campo>());
		
		setNome("");
	}
	
	/**
	 * Construção de uma Tabela Simples conhecendo
	 * os itens
	 * @param itens Itens a serem atribuídos
	 */
	public TabelaSimples (LinkedList<ItemSimples> itens){
		this();
		
		setItens(itens);
	}
	
	/**
	 * Construção de uma Tabela Simples conhecendo
	 * o nome
	 * @param nome Nome a ser atribuido
	 */
	public TabelaSimples (String nome){
		this ();
		
		setNome(nome);
	}
	
	/**
	 * Construção de uma Tabela Simples conhecendo 
	 * os itens e os campos
	 * @param itens Itens a serem atribuídos
	 * @param campos Campos a serem atribuídos
	 */
	public TabelaSimples (LinkedList<ItemSimples> itens, LinkedList<Campo> campos){
		this (itens);
		
		setCampos(campos);
	}
	
	/**
	 * Construção de uma Tabela Simples conhecendo
	 * o nome e os campos
	 * @param nome Nome a ser atribuido
	 * @param campos Campos a serem atribuídos
	 */
	public TabelaSimples (String nome, LinkedList<Campo> campos){
		this(nome);
		
		setCampos(campos);
	}
	
	/**
	 * Construção de uma Tabela Simples conhecendo
	 * os itens, os campos e o nome
	 * @param itens Itens a serem atribuídos
	 * @param campos Campos a serem atribuídos
	 * @param nome Nome a ser atribuído
	 */
	public TabelaSimples (LinkedList<ItemSimples> itens, LinkedList<Campo> campos, String nome){
		this (itens, campos);
		
		setNome(nome);
	}
	
	
	
	/**
	 * Valor atribuído aos itens da tabela
	 * @return Itens existentes na tabela
	 */
	public LinkedList<ItemSimples> getItens (){
		return this.itens;
	}
	
	/**
	 * Atribuição de itens à tabela
	 * @param itens Itens a serem atribuídos
	 */
	public void setItens (LinkedList<ItemSimples> itens){
		this.itens = itens;
	}
	
	/**
	 * Valores atribuídos aos campos da tabela
	 * @return Campos da tabela
	 */
	public LinkedList<Campo> getCampos (){
		return this.campos;
	}
	
	/**
	 * Atribuição de campos à tabela
	 * @param campos Campos a serem atribuídos
	 */
	public void setCampos (LinkedList<Campo> campos){
		this.campos = campos;
	}
	
	/**
	 * Valor atribuído ao nome da tabela
	 * @return Nome atribuído à tabela
	 */
 	public String getNome (){
		return this.nome;
	}
	
	/**
	 * Atribuição de nome à tabela
	 * @param nome Identificador da tabela
	 */
	public void setNome (String nome){
		this.nome = nome;
	}

	
	
	/**
	 * Ordenação dos itens da tabela
	 */
	public void ordenarItens (){
		Collections.sort(getItens(), new Comparator<ItemSimples>() {
		    public int compare (ItemSimples objetoUm, ItemSimples objetoDois) {
		        return objetoUm.compareTo(objetoDois);
		    }
		});
	}
	
	/**
	 * Representacao inteligível da tabela para
	 * remoção da tabela em SQL
	 * @return Drop da tabela em SQL
	 */
	public String dropInSQL (){
		String toSQL = "";
		
		toSQL += "DROP TABLE IF EXISTS " + getNome() + "\n";
		
		return toSQL;
	}
	
	/**
	 * Representação inteligível da tabela para criação
	 * num banco de dados em SQL
	 * @return Criação da tabela em SQL
	 */
	public String criarInSQL (){
		String toSQL = "";
		
		toSQL += "CREATE TABLE " + getNome() + "\n\t(";
		
		for (Campo campo : getCampos())
			toSQL += campo.toString() + ",\n\t ";
		
		toSQL = toSQL.substring(0, toSQL.length() - 4) + ");";
		
		return toSQL + "\n\n";
	}
	
	/**
	 * Representação inteligível da tabela para inserção
	 * num banco de dados em SQL
	 * @return Inserção para SQL
	 */
	public String inserirInSQL(){
		String toSQL = "";
		
		if (!getItens().isEmpty()){
			//System.out.println("Entrou no insert, com " + getItens().size() + " itens");
			int contador = 1;
			
			ordenarItens();
			
			for (ItemSimples item : getItens()){
				//System.out.println("Item novo");
				String linha = "insert into ";
				
				linha += getNome() + " (";
				
				for (Campo campo : getCampos())
					linha += campo.getDescricao() + ", ";
				
				linha = linha.substring(0, linha.length() - 2) + ") ";
				
				if (item.getCodigo() == 999999999)
					item.setCodigo(contador);
				
				linha += item.inserirNoSQL();
				
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
}
