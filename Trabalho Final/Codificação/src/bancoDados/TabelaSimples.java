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
	private LinkedList<String> campos;
	private String nome;
	
	
	
	/**
	 * Construção de uma Tabela Simples por maneira default
	 */
	public TabelaSimples (){
		setItens(new LinkedList<ItemSimples>());
		
		setCampos(new LinkedList<String>());
		
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
	public TabelaSimples (LinkedList<ItemSimples> itens, LinkedList<String> campos){
		this (itens);
		
		setCampos(campos);
	}
	
	/**
	 * Construção de uma Tabela Simples conhecendo
	 * o nome e os campos
	 * @param nome Nome a ser atribuido
	 * @param campos Campos a serem atribuídos
	 */
	public TabelaSimples (String nome, LinkedList<String> campos){
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
	public TabelaSimples (LinkedList<ItemSimples> itens, LinkedList<String> campos, String nome){
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
	public LinkedList<String> getCampos (){
		return this.campos;
	}
	
	/**
	 * Atribuição de campos à tabela
	 * @param campos Campos a serem atribuídos
	 */
	public void setCampos (LinkedList<String> campos){
		this.campos = campos;
	}
	
	/**
	 * Valor atribuído ao nome da tabela
	 * @return
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
	 * Representação inteligível do item para inserção
	 * numa tabela em SQL
	 * @return Inserção para SQL
	 */
	public String inserirInSQL(){
		String toSQL = "";
		
		if (!getItens().isEmpty()){
			int contador = 1;
			
			ordenarItens();
			
			for (ItemSimples item : getItens()){
				String linha = "insert into ";
				
				linha += getNome() + " (";
				
				for (String campo : getCampos())
					linha += campo + ", ";
				
				linha = linha.substring(0, linha.length() - 2) + ") ";
				
				if ((item.getCodigo() == -1)
					|| (item.getCodigo() == 999999999))
					item.setCodigo(contador);
				
				linha += item.inserirNoSQL();
				
				toSQL += linha;
				
				contador++;
			}
			
		}else{
			toSQL = getNome() + ": ";
			
			toSQL += "Sem atributos, mas com os campos:\n\t";
			
			toSQL += getCampos().toString(); 
		}
		
		toSQL += "\n\n";
		
		return toSQL;
	}
}
