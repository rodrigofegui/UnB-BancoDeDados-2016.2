package bancoDados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	 * Nome caraterístico do BD
	 * Nome do arquivo base para o processo de ETL
	 */
	private LinkedList<TabelaSimples> tbSimples;
	private String nome;
	private String nomeArqETL;
	
	
	
	/**
	 * Construção de um Bando de Dados por maneira default
	 */
	public BancoDeDados (){
		setTbSimples(new LinkedList<TabelaSimples>());

		setNome("BDPadrao");
		
		setNomeArqETL("");

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
	 * Construção de um Banco de Dados conhecendo o nome
	 * @param nome Nome a ser atribuído
	 */
	public BancoDeDados (String nome){
		this ();
		
		setNome(nome);
	}
	
	/**
	 * Construção de um Banco de Dados, conhecendo
	 * as tabelas simples e o nome
	 * @param tbSimples Tabelas simples a serem atribuídas
	 * @param nome Nome a ser atribuído
	 */
	public BancoDeDados (LinkedList<TabelaSimples> tbSimples, String nome){
		this (tbSimples);
		
		setNome(nome);
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
	 * Valor atribuído ao nome do BD
	 * @return Nome atribuído ao BD
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Atribuição de um nome ao BD
	 * @param nome Nome a ser atribuído ao BD
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Valor atribuído ao nome do arquivo base
	 * para o ETL
	 * @return Nome do arquivo base
	 */
	public String getNomeArqETL() {
		return nomeArqETL;
	}

	/**
	 * Atribuição do nome do arquivo base
	 * para o ETL
	 * @param nomeArqETL Nome do arquivo a
	 * ser atribuído
	 */
	public void setNomeArqETL(String nomeArqETL) {
		this.nomeArqETL = nomeArqETL;
	}
	
	
	
	

	/**
	 * Inicialização do Bando de Dados específicamente
	 */
	private void iniciarEspecifico (){
		LinkedList<Campo> campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "int(255) unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));//, "varchar(50)", "NOT NULL"));
		getTbSimples().add(new TabelaSimples("Orgao_Superior", campos));
		
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "int(255) unsigned", "PRIMARY KEY "));
		campos.add(new Campo("nome"));//, "varchar(50)", "NOT NULL"));
		campos.add(new Campo("cod_superior"));//, "integer unsigned"));
		getTbSimples().add(new TabelaSimples("Orgao_Subordinado", campos));
		
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));// "int(255) unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));//, "varchar(255)", "NOT NULL"));
		campos.add(new Campo("cod_superior"));//, "int(255)", "NOT NULL"));
		campos.add(new Campo("cod_subordinado"));//, "int"));
		getTbSimples().add(new TabelaSimples("Unidade_Gestora", campos));
		
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "integer unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));//, "varchar(25)"));
		campos.add(new Campo("cod_superior"));
		campos.add(new Campo("cod_subordinado"));
		campos.add(new Campo("cod_unidade_gestora"));
		getTbSimples().add(new TabelaSimples("Funcao", campos));
		
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "integer unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));// "varchar(55)"));
		getTbSimples().add(new TabelaSimples("Sub_Funcao", campos));
		
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "integer unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));//, "varchar(115)"));
		getTbSimples().add(new TabelaSimples("Programa", campos));
		
		/*
		campos = new LinkedList<Campo>();
		campos.add(new Campo("cdAc"));
		campos.add(new Campo("nmAc"));
		getTbSimples().add(new TabelaSimples("Acao", campos));
		//*/
	}

	/**
	 * Processo de ETL (Extração, Transformação e Carregamento) sob o
	 * arquivo registrado 
	 */
	public void processoETL (){
		processoETL(getNomeArqETL());
	}
	
	/**
	 * Processo de ETL (Extração, Transformação e Carregamento) sob o
	 * arquivo informado
	 * @param nomeArqETL Nome do arquivo base para o processo de ETL
	 */
	public void processoETL (String nomeArqETL){
		System.out.println("Processando o arquivo:");
		System.out.println(nomeArqETL);
		
		eOfETL (nomeArqETL);
	}
	
	/**
	 * Etapa de extração de dados, para a construção do BD, considerando
	 * um arquivo arbitrário
	 * @param nomeArq Nome do arquivo a ser considerado
	 */
	public void eOfETL (String nomeArq){
		try {
			BufferedReader leitura = new BufferedReader(new FileReader(nomeArq));
			String linhaBruta = leitura.readLine();
			
			while (leitura.ready()){
				linhaBruta = leitura.readLine();
				
				tOfETL(linhaBruta);
			}
			
			leitura.close();
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Etapa de transformação do processo de ETL
	 * @param bruto Dados brutos, a ser minerado
	 */
	public void tOfETL (String bruto){
		String partes[] = bruto.split("\t");
		int i = 0;
		//for (int i = 0; i < 14; i += 2){
		
		//System.out.println("Qnt de partes -> " + partes.length);
		if (!partes[0].equals("Detalhamento das informa��es n�o dispon�vel.")){
			/*	Órgão Superior *
			TabelaSimples tbS = getTbSimples().get(0);
			ItemSimples itemS = new ItemSimples();
			itemS.setCodigo(Integer.parseInt(partes[0]));
			itemS.setDescricao(partes[1]);
			inserirItemTabela (itemS, tbS);
			
			/*	Órgão Subordinado *
			tbS = getTbSimples().get(1);
			itemS = new ItemSimples();
			itemS.setCodigo(Integer.parseInt(partes[2]));
			itemS.setDescricao(partes[3]);
			itemS.getCodRef().add(Integer.parseInt(partes[0]));
			inserirItemTabela (itemS, tbS);
			
			/*	Unidade Gestora *
			tbS = getTbSimples().get(2);
			itemS = new ItemSimples();
			itemS.setCodigo(Integer.parseInt(partes[4]));
			itemS.setDescricao(partes[5]);
			itemS.getCodRef().add(Integer.parseInt(partes[0]));
			itemS.getCodRef().add(Integer.parseInt(partes[2]));
			inserirItemTabela (itemS, tbS);
			
			/*	Função *
			tbS = getTbSimples().get(3);
			itemS = new ItemSimples();
			itemS.setCodigo(Integer.parseInt(partes[6]));
			itemS.setDescricao(partes[7]);
			itemS.getCodRef().add(Integer.parseInt(partes[0]));
			itemS.getCodRef().add(Integer.parseInt(partes[2]));
			itemS.getCodRef().add(Integer.parseInt(partes[4]));
			inserirItemTabela (itemS, tbS);
			//*/
			
			
			//*
			for (TabelaSimples tb : getTbSimples()){
				/*
				System.out.println("Codigo -> " + partes[i]);
				System.out.println("Descricao -> " + partes[i + 1] + "\n");
				//*/
				ItemSimples itemS = new ItemSimples(Integer.parseInt(partes[i]), partes[i + 1]);
				
				inserirItemTabela(itemS, tb);
				
				i += 2;
				//*/
			}
			//*/
			for (int ind = 1; ind < 4; ind++){
				int contador = 0;
				ItemSimples itemS = getTbSimples().get(ind).getItens().getLast();
				
				for (int j = 0; j < ind; j++, contador += 2)
					itemS.getCodRef().add(Integer.parseInt(partes[contador]));
					
			}
				
			//System.out.println("\n\n");
		}
		
	}
	
	/**
	 * Inserção de um item numa tabela, garantindo que o mesmo já não exista
	 * @param item Item a ser analisado na inserção
	 * @param tabela Tabela para inserção do item
	 */
	public void inserirItemTabela (ItemSimples item, TabelaSimples tabela){
		boolean condicao = true;
		
		for (ItemSimples itemBusca : tabela.getItens())
			if (item.compareTo(itemBusca) == 0){
				condicao = false;
				break;
			}
		
		if (condicao)
			tabela.getItens().add(item);
	}
	
	
	/**
	 * Gerar script responsável por criar o BD desenvolvido,
	 * com a inserção dos dados
	 * @return Script para criação do BD
	 */
	public String scriptSQL(){
		String toSQL = "";
		String dropSQL = "";
		String createSQL = "";
		String insertSQL = "";
		
		for (TabelaSimples tbS : getTbSimples()){
			dropSQL += tbS.dropInSQL();
			
			createSQL += tbS.criarInSQL();
			
			insertSQL += tbS.inserirInSQL();
		}
		
		dropSQL += "\n\n\n";
		createSQL += "\n\n";
		insertSQL = insertSQL.substring(0, insertSQL.length() - 2);
		
		toSQL = dropSQL + createSQL + insertSQL;
		
		return toSQL;
	}
	
	/**
	 * Gerar o script responsável por inserir os dados no
	 * BD desenvolvido
	 * @return Script para a inserção no BD
	 */
	public String insertInSQL (){
		String insertSQL = "";
		int count = getTbSimples().size();
		
		System.out.println("A ler " + count + " tabelas simples");
		for (TabelaSimples tbS : getTbSimples()){
			insertSQL += tbS.inserirInSQL();
			
			System.out.println("faltam " + --count + " tabelas");
		}
		System.out.println("Finalizou leitura tabelas simples\n");
		
		insertSQL = insertSQL.substring(0, insertSQL.length() - 2);
		
		return insertSQL;
	}
	
	/**
	 * Armazenar o arquivo em memória secundária
	 */
	public void escreverScriptSQL(){
		String nomeArq = "Scripts/full" + getNome() + ".sql";
		
		escreverScriptSQL(nomeArq);
	}
	
	/**
	 * Construção do script que cria o BD
	 * em SQL
	 * @param nomeArq Nome do arquivo do script
	 */
	public void escreverScriptSQL(String nomeArq){
		try{			
			String caminho = nomeArq;
			String scriptSQL = scriptSQL();

			BufferedWriter gravarArq = new BufferedWriter(new FileWriter (caminho));
			
			gravarArq.append(scriptSQL + "\n");
			
			gravarArq.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Construção do script que insere os dados no BD
	 * em SQL, no diretório padrão
	 */
	public void scriptInsertInSQL (){
		String nomeArq = "Scripts/insert" + getNome() + ".sql";
		
		scriptInsertInSQL(nomeArq);
	}
	
	/**
	 * Construção do script que insere os dados no BD
	 * em SQL, conforme o diretório específico
	 * @param nomeArq Nome do arquivo do script
	 */
	public void scriptInsertInSQL (String nomeArq){
		try{			
			String caminho = nomeArq;
			String scriptSQL = insertInSQL();

			BufferedWriter gravarArq = new BufferedWriter(new FileWriter (caminho));
			
			gravarArq.append(scriptSQL + "\n");
			
			gravarArq.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
