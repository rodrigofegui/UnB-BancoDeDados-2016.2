package bancoDados;

import java.util.LinkedList;

/**
 * Classe responsável pela implementação lógica de uma Item Simples
 * do BD desenvolvido
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	29/10/2016
 */
public class ItemSimples{
	/**
	 * Código único para cada Item
	 * Descrição inteligível
	 * Códigos dos itens relacionados
	 */
	private int codigo;
	private String descricao;
	private LinkedList<Integer> codRef;
	public final static int maxInt = 999999999;
	
	
	
	/**
	 * Construção de um Item Simples por maneira default
	 */
	public ItemSimples (){
		setCodigo(maxInt);
		
		setDescricao("");
		
		setCodRef(new LinkedList<Integer>());
	}
	
	/**
	 * Construção de um Item Simples conhecendo
	 * o código 
	 * @param codigo Código de inicialização
	 */
	public ItemSimples(int codigo){
		this();
		
		setCodigo(codigo);
	}
	
	/**
	 * Construção de um Item Simples conhecendo
	 * a descrição 
	 * @param descricao Descrição de inicialização
	 */
	public ItemSimples(String descricao){
		this ();
		
		setDescricao(descricao);
	}
	
	/**
	 * Construção de um Item simples conhecendo
	 * os códigos relacionados
	 * @param codRef Códigos relacionados
	 */
	public ItemSimples(LinkedList<Integer> codRef){
		this();
		
		setCodRef(codRef);
	}
	
	/**
	 * Construção de um Item Simples conhecendo
	 * o código e a descrição
	 * @param codigo Código de inicialização
	 * @param descricao Descrição de inicialização
	 */
	public ItemSimples (int codigo, String descricao){
		this(codigo);
		
		setDescricao(descricao);
	}

	/**
	 * Construção de um Item Simples conhecendo
	 * o código, a descrição e os códigos relacionados
	 * @param codigo Código de inicialização
	 * @param descricao Descrição de inicialização
	 * @param codRef Códigos relacionados
	 */
	public ItemSimples (int codigo, String descricao, LinkedList<Integer> codRef){
		this (codigo, descricao);
		
		setCodRef(codRef);
	}

	
	
	/**
	 * Valor atribuído ao código do item
	 * @return Código do item
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Atribuição de um código ao item
	 * @param codigo Código a ser atribuído
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Valor atribuído à descrição do item
	 * @return Descrição inteligível do item
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Atribuição de uma descrição ao item
	 * @param descricao Descrição a ser atribuída
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Valores atribuídos aos códigos de referências
	 * a outros itens
	 * @return Códigos relacionados
	 */
	public LinkedList<Integer> getCodRef (){
		return this.codRef;
	}
	
	/**
	 * Atribuição dos códigos relacionados aos itens
	 * @param codRef Códigos relacinados
	 */
	public void setCodRef (LinkedList<Integer> codRef){
		this.codRef = codRef;
	}
	
	
	
	/**
	 * Comparação básica entre dois itens
	 * @param objetoDois Item a ser comparado
	 * @return Valor da comparação. 0 se forem iguais, senão retorna a subtração dos códigos.
	 */
	public int compareTo(ItemSimples objetoDois) {
		if (equals(objetoDois) || this.getDescricao().equals(objetoDois.getDescricao()))
			return 0;
		
		return this.getCodigo() - objetoDois.getCodigo();
	}
		
	/**
	 * Representação inteligível do item para inserção
	 * numa tabela em SQL
	 * @return Inserção para SQL
	 */
	public String inserirNoSQL (){
		String toSQL = "values (";
		
		toSQL += getCodigo() + ", '";
		toSQL += getDescricao() + "', ";
		
		if (!getCodRef().isEmpty())
			for (Integer cod : getCodRef())
				toSQL += cod + ", ";
		
		System.out.println("pegou referencias");
		
		toSQL = toSQL.substring(0, toSQL.length() - 2) + ");\n";
		
		return toSQL;
	}


}
