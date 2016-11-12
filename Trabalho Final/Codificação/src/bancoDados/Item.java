package bancoDados;

import java.util.LinkedList;

/**
 * Classe responsável pela implementação lógica de uma Item
 * do BD desenvolvido
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	06/11/2016
 */
public class Item extends ItemSimples{
	/**
	 * Código único para cada Item
	 */
	private String codigoStr;
	private LinkedList<String> codRefStr;
	private float codRefFlo;
	
	
	
	/**
	 * Construção de um Item de maneira default
	 */
	public Item (){
		super();
		
		setCodigoStr("");
		
		setCodRefStr(new LinkedList<String>());
		
		setCodRefFlo(0.0f);
	}
	
	/**
	 * Construção de um Item, conhecendo-se o seu código
	 * @param codigoStr Código de inicialização
	 */
	public Item (String codigoStr){
		this();
		
		setCodigoStr(codigoStr);
	}

	/**
	 * Construção de um Item, conhecendo-se o seu código
	 * e sua descrição
	 * @param codigoStr Código de inicialização
	 * @param descricao Descrição de inicialização
	 */
	public Item (String codigoStr, String descricao){
		this(codigoStr);
		
		setDescricao(descricao);
	}
	
	
	
	/**
	 * Valor atribuído ao código do item
	 * @return Código do item
	 */
	public String getCodigoStr() {
		return codigoStr;
	}

	/**
	 * Atribuição de um código ao item
	 * @param codigoStr Código a ser atribuído
	 */
	public void setCodigoStr(String codigoStr) {
		this.codigoStr = codigoStr;
	}
	
	/**
	 * Valores atribuídos aos códigos de referências
	 * a outros itens
	 * @return Códigos relacionados
	 */
	public LinkedList<String> getCodRefStr() {
		return codRefStr;
	}
	
	/**
	 * Atribuição dos códigos relacionados aos itens
	 * @param codRefStr Códigos relacinados
	 */

	public void setCodRefStr(LinkedList<String> codRefStr) {
		this.codRefStr = codRefStr;
	}
	
	/**
	 * Valor atribuído à referência
	 * @return Códigos relacionados
	 */

	public float getCodRefFlo() {
		return codRefFlo;
	}
	
	/**
	 * Atribuição dos códigos relacionados aos itens
	 * @param codRefFlo Códigos relacinados
	 */

	public void setCodRefFlo(float codRefFlo) {
		this.codRefFlo = codRefFlo;
	}
	
	
	

	/**
	 * Comparação básica entre dois itens
	 * @param objetoDois Item a ser comparado
	 * @return Valor da comparação. 0 se forem iguais, senão retorna a subtração dos códigos
	 * ou o valor máximo para inteiro.
	 */
	public int compareTo(Item objetoDois) {
		boolean codigoNulo = false;
		boolean codigoStrNulo = false;
		
		/*
		System.out.println("Compando:");
		System.out.println("Codigo -> " + getCodigo());
		System.out.println("Codigo Str -> " + getCodigoStr());
		System.out.println("Com:");
		System.out.println("Codigo -> " + objetoDois.getCodigo());
		System.out.println("Codigo Str -> " + objetoDois.getCodigoStr() + "\n\n");
		//*/
		
		if (equals(objetoDois))
			return 0;
		
		if ((getCodigo() == maxInt) || (objetoDois.getCodigo() == maxInt))
			codigoNulo = true;
		
		if (getCodigoStr().equals("") || objetoDois.getCodigoStr().equals(""))
			codigoStrNulo = true;
		
		if (!codigoNulo && codigoStrNulo && (getCodigo() == objetoDois.getCodigo()))
			return 0;
		
		if (codigoNulo && !codigoStrNulo && (getCodigoStr().equals(objetoDois.getCodigoStr())))
			return 0;
		
		if (codigoNulo && codigoStrNulo && (getDescricao().equals(objetoDois.getDescricao())))
			return 0;
		
		if (!codigoNulo)
			return getCodigo() - objetoDois.getCodigo();
		
		if (!codigoStrNulo)
			return getCodigoStr().compareTo(objetoDois.getCodigoStr());
		
		return maxInt;
	}
	
	/**
	 * Representação inteligível do item para inserção
	 * numa tabela em SQL
	 * @return Inserção para SQL
	 */
	@Override
	public String inserirNoSQL (){
		String toSQL = "values (";
		
		if (getCodigoStr().equals(""))
			toSQL += getCodigo() + ", ";
		else
			toSQL += "'" + getCodigoStr() +  "', ";
		
		if (!getDescricao().equals(""))
			toSQL += "'" + getDescricao() + "', ";
		
		if (!getCodRef().isEmpty())
			for (Integer cod : getCodRef())
				toSQL += cod + ", ";
		
		if (!getCodRefStr().isEmpty())
			for (String cod : getCodRefStr()){
				if (cod.contains("STR_TO")){
					toSQL += tratarCaracteristicas(cod) + ", ";
				}else
					toSQL += "'" + tratarCaracteristicas(cod) + "', ";
				
			}
		
		if (getCodRefFlo() != 0.0f)
			toSQL += getCodRefFlo() + ", ";
		
		//System.out.println("pegou referencias");
		
		toSQL = toSQL.substring(0, toSQL.length() - 2) + ");\n";
		
		/*
		System.out.println("Inserção ->");
		System.out.println(toSQL);
		//*/
		return toSQL;
	}
	
	/**
	 * Tratamento de caraterísticas que possam vir a ser nulas
	 * @param entrada Caracteristica a ser avaliada
	 * @return Característica tratada
	 */
	private String tratarCaracteristicas (String entrada){
		if (entrada.length() <= 1)
			return "NULL";
		
		return entrada;
	}
}
