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
	private LinkedList<Object> codigoComposto;
	private LinkedList<String> codRefStr;
	private float codRefFlo;
	
	
	
	/**
	 * Construção de um Item de maneira default
	 */
	public Item (){
		super();
		
		setCodigoComposto(new LinkedList<Object>());
		
		setCodRefStr(new LinkedList<String>());
		
		setCodRefFlo(0.0f);
	}

	
	/**
	 * Construção de um Item, conhecendo-se o seu código
	 * @param codigoComposto Código de inicialização
	 */
	public Item (LinkedList<Object> codigoComposto){
		this ();
		
		setCodigoComposto(codigoComposto);
	}
	
	/**
	 * Construção de um Item, conhecendo-se o seu código
	 * e sua descrição
	 * @param codigoComposto Código de inicialização
	 * @param descricao Descrição de inicialização
	 */
	public Item (LinkedList<Object> codigoComposto, String descricao){
		this (codigoComposto);
		
		setDescricao(descricao);
	}
	
	
	/**
	 * Valor atribuído ao código do item
	 * @return Código do item
	 */
	public LinkedList<Object> getCodigoComposto() {
		return codigoComposto;
	}
	
	/**
	 * Valor atribuído ao código do item
	 * @return Código do item
	 */
	public String getCodigoCompostoStr() {
		String toString = "";
		Integer compInt = 0;
		String compStr = "";
		
		for (Object codigo : getCodigoComposto()){
			if (codigo.getClass().equals(compInt.getClass())){
				toString += codigo + ", ";
			}else if (codigo.getClass().equals(compStr.getClass()))
				toString += "'" + codigo + "', ";
		}
		
		return toString;
	}

	/**
	 * Atribuição de um código ao item
	 * @param codigoComposto Código a ser atribuído
	 */
	public void setCodigoComposto(LinkedList<Object> codigoComposto) {
		this.codigoComposto = codigoComposto;
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
		
		int menor = Integer.min(getCodigoComposto().size(), objetoDois.getCodigoComposto().size());
		int maior = Integer.max(getCodigoComposto().size(), objetoDois.getCodigoComposto().size());
		
		if (menor == maior){
			int camposIguais = 0;
			int retorno = 0;
			Integer compInt = 0;
			String compStr = "";
			
			for  (int ind = 0; ind < menor; ind++){
				Object codigo1 = getCodigoComposto().get(ind);
				Object codigo2 = objetoDois.getCodigoComposto().get(ind);
				
				if (codigo1.equals(codigo2))
					camposIguais++;
				
				else if (codigo1.getClass().equals(codigo2.getClass())){
					if (codigo1.getClass().equals(compInt.getClass()))
						retorno += ((Integer) codigo1) - ((Integer) codigo2);
					else if (codigo1.getClass().equals(compStr.getClass()))
						retorno += ((String) codigo1).compareTo((String) codigo2);
				}
				//*/
			}
			
			if (camposIguais == menor) return 0;
			else return retorno;
		}
		
		return Item.maxInt;

	}
	
	/**
	 * Representação inteligível do item para inserção
	 * numa tabela em SQL
	 * @return Inserção para SQL
	 */
	@Override
	public String inserirNoSQL (){
		String toSQL = "values (";
		
		toSQL += getCodigoCompostoStr();
		
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
