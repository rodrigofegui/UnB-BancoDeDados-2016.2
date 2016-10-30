package bancoDados;

import java.util.LinkedList;

/**
 * Classe responsável pela implementação lógica de um
 * Banco de Dados
 * @author	Rodrigo Guimarães
 * @version	1.0
 * @since	29/10/2016
 */
public class BancoDeDados{
	/**
	 * Tabelas simples existentes no BD
	 */
	private LinkedList<TabelaSimples> tbSimples;

	
	
	/**
	 * Construção de um Bando de Dados por maneira default
	 */
	public BancoDeDados (){
		setTbSimples(new LinkedList<TabelaSimples>());
		
		iniciarEspecifico();
	}
	
	/**
	 * Construção de um Banco de Dados conhecendo
	 * as tabelas simples
	 * @param tbSimples Tabelas simples a serem atribuídas
	 */
	public BancoDeDados (LinkedList<TabelaSimples> tbSimples){
		this();
		
		setTbSimples(tbSimples);
	}
	
	
	
	/**
	 * Valores atribuídos às tabelas simples do BD
	 * @return Tabelas Simples existentes
	 */
	public LinkedList<TabelaSimples> getTbSimples() {
		return tbSimples;
	}

	/**
	 * Atribuição de tabelas simples ao BD
	 * @param tbSimples Tabelas simples a serem atribuídas
	 */
	public void setTbSimples(LinkedList<TabelaSimples> tbSimples) {
		this.tbSimples = tbSimples;
	}

	/**
	 * Inicialização do Bando de Dados específicamente
	 */
	private void iniciarEspecifico (){
		LinkedList<String> campos = new LinkedList<String>();
		campos.add("cdOrSup");
		campos.add("nmOrSup");
		getTbSimples().add(new TabelaSimples("OrgaoSuperior", campos));
		
		campos = new LinkedList<String>();
		campos.add("cdOrSub");
		campos.add("nmOrSub");
		getTbSimples().add(new TabelaSimples("OrgaoSubordinado", campos));
		
		campos = new LinkedList<String>();
		campos.add("cdUniGes");
		campos.add("nmUniGes");
		getTbSimples().add(new TabelaSimples("UnidadeGestora", campos));
		
		campos = new LinkedList<String>();
		campos.add("cdFun");
		campos.add("nmFun");
		getTbSimples().add(new TabelaSimples("Funcao", campos));
		
		campos = new LinkedList<String>();
		campos.add("cdSubFun");
		campos.add("nmSubFun");
		getTbSimples().add(new TabelaSimples("SubFuncao", campos));
		
		campos = new LinkedList<String>();
		campos.add("cdProg");
		campos.add("nmProg");
		getTbSimples().add(new TabelaSimples("Programa", campos));
		
		campos = new LinkedList<String>();
		campos.add("cdAc");
		campos.add("nmAc");
		getTbSimples().add(new TabelaSimples("Acao", campos));
	}


	public String criarInSQL(){
		String toSQL = "";
		
		for (TabelaSimples tbS : getTbSimples())
			toSQL += tbS.inserirInSQL();
		
		return toSQL;
	}
}
