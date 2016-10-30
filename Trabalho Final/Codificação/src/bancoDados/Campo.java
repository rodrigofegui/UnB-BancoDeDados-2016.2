package bancoDados;

/**
 * Classe responsável pela implementação lógica de um Campo de tabela
 * do BD
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	30/10/2016
 */
public class Campo {
	/**
	 * Descrição do campo
	 * Tipo da variável do campo
	 * Restrição do campo 
	 */
	private String descricao;
	private String tipo;
	private String restricao;
	
	
	
	/**
	 * Construção de um Campo, de maneira default
	 */
	public Campo(){
		setDescricao("");
		
		setTipo("");
		
		setRestricao("");
	}

	/**
	 * Construção de um Campo, conhecendo sua descrição
	 * @param descricao Descrição a ser atribuída
	 */
	public Campo (String descricao){
		this ();
		
		setDescricao(descricao);
	}
	
	/**
	 * Construção de um Campo, conhecendo sua descrição
	 * e seu tipo
	 * @param descricao Descrição a ser atribuída
	 * @param tipo Tipo a ser atribuído
	 */
	public Campo (String descricao, String tipo){
		this (descricao);
		
		setTipo(tipo);
	}
	
	/**
	 * Construção de um Campo, conhecendo sua descrição,
	 * seu tipo e sua restrição
	 * @param descricao Descrição a ser atribuída
	 * @param tipo Tipo a ser atribuído
	 * @param restricao Restrição a ser atribuída
	 */
	public Campo (String descricao, String tipo, String restricao){
		this (descricao, tipo);
		
		setRestricao(restricao);
	}

	
	
	/**
	 * Valor atribuído a descrição do Campo
	 * @return Descrição do Campo
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Atribuição de uma descrição ao campo
	 * @param descricao Descrição a ser atribuída
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	/**
	 * Valor atribuído ao tipo do Campo
	 * @return Tipo característico
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Atribuição de um tipo ao Campo
	 * @param tipo Tipo a ser atribuído
	 */

	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}

	/**
	 * Valor atribuído às restrições do Campo
	 * @return Restrições associadas
	 */
	public String getRestricao() {
		return restricao;
	}

	/**
	 * Atribuição de restrições ao Campo
	 * @param restricao Restrição a ser atribuída
	 */
	public void setRestricao(String restricao) {
		this.restricao = restricao;
	}
	
	
	
	@Override
	public String toString (){
		String toString = "";
		
		toString += getDescricao() + "\t";
		toString += getTipo() + "\t";
		toString += getRestricao();
	
		return toString;
	}
}
