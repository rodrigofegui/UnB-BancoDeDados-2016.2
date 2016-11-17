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
	 * Tabelas existentes no BD
	 * Nome caraterístico do BD
	 * Nome do arquivo base para o processo de ETL
	 */
	private LinkedList<Tabela> tb;
	private String nome;
	private String nomeArqETL;
	
	
	
	/**
	 * Construção de um Bando de Dados por maneira default
	 */
	public BancoDeDados (){
		//setTbSimples(new LinkedList<TabelaSimples>());
		
		setTb(new LinkedList<Tabela>());

		setNome("BDPadrao");
		
		setNomeArqETL("");
	}
	
	/**
	 * Construção de um Banco de Dados conhecendo
	 * as tabelas
	 * @param tb Tabelas a serem atribuídas
	 */
	public BancoDeDados (LinkedList<Tabela> tb){
		this();
		
		setTb(tb);
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
	 * as tabelas e o nome
	 * @param tb Tabelas a serem atribuídas
	 * @param nome Nome a ser atribuído
	 */
	public BancoDeDados (LinkedList<Tabela> tb, String nome){
		this (tb);
		
		setNome(nome);
	}
	
	

	/**
	 * Valores atribuídos às tabelas simples do BD
	 * @return Tabelas existentes
	 */
	public LinkedList<Tabela> getTb() {
		return tb;
	}

	/**
	 * Atribuição de tabelas simples ao BD
	 * @param tb Tabelas a serem atribuídas
	 */
	public void setTb(LinkedList<Tabela> tb) {
		this.tb = tb;
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
	 * Inicialização do Bando de Dados específicamente ao problema,
	 * configurando as tabelas e os campos pré-definidos
	 */
	public void iniciarEspecifico (){
		LinkedList<Campo> campos;
		
		// 0:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "int(255) unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));//, "varchar(50)", "NOT NULL"));
		getTb().add(new Tabela("ORGAO_SUPERIOR", campos));
		
		// 1:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "int(255) unsigned", "PRIMARY KEY "));
		campos.add(new Campo("nome"));//, "varchar(50)", "NOT NULL"));
		campos.add(new Campo("cod_superior"));//, "integer unsigned"));
		getTb().add(new Tabela("ORGAO_SUBORDINADO", campos));
		
		// 2:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));// "int(255) unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));//, "varchar(255)", "NOT NULL"));
		campos.add(new Campo("cod_superior"));//, "int(255)", "NOT NULL"));
		campos.add(new Campo("cod_subordinado"));//, "int"));
		getTb().add(new Tabela("UNIDADE_GESTORA", campos));
		
		// 3:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "integer unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));//, "varchar(25)"));
		campos.add(new Campo("cod_superior"));
		campos.add(new Campo("cod_subordinado"));
		campos.add(new Campo("cod_unidade_gestora"));
		getTb().add(new Tabela("FUNCAO", campos));
		
		// 4:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "integer unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));// "varchar(55)"));
		getTb().add(new Tabela("SUB_FUNCAO", campos));
		
		// 5:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));//, "integer unsigned", "PRIMARY KEY"));
		campos.add(new Campo("nome"));//, "varchar(115)"));
		getTb().add(new Tabela("PROGRAMA", campos));
				
		// 6:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("codigo"));
		campos.add(new Campo("nome"));
		campos.add(new Campo("linguagem_cidada"));
		getTb().add(new Tabela("ACAO", campos));
		
		// 7:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("cpf"));
		campos.add(new Campo("nome"));
		campos.add(new Campo("cod_superior"));
		campos.add(new Campo("cod_subordinado"));
		campos.add(new Campo("codigo_unidade_gestora"));
		getTb().add(new Tabela("PESSOA", campos));
		
		// 8:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("documento_pagamento"));
		campos.add(new Campo("gestao_pagamento"));
		getTb().add(new Tabela("PAGAMENTO", campos));
		
		// 9:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("cod_prog"));
		campos.add(new Campo("cod_acao"));
		getTb().add(new Tabela("PROG_ACAO", campos));
		
		// 10:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("cod_prog"));
		campos.add(new Campo("cod_sub"));
		getTb().add(new Tabela("PROG_ORGAO", campos));
		
		// 11:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("cod_fun"));
		campos.add(new Campo("cod_sub_fun"));
		getTb().add(new Tabela("FUN_SUBFUN", campos));
		
		// 12:
		campos = new LinkedList<Campo>();
		campos.add(new Campo("cpf_pessoa"));
		campos.add(new Campo("doc_pag"));
		campos.add(new Campo("cod_superior"));
		campos.add(new Campo("cod_subordinado"));
		campos.add(new Campo("codigo_unidade_gestora"));
		campos.add(new Campo("data_pagamento"));
		campos.add(new Campo("valor"));
		getTb().add(new Tabela("PESSOA_PAG", campos));		
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
				linhaBruta = leitura.readLine().replaceAll("�", "");
				
				//System.out.println("Depois ->\n" + linhaBruta);
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
		
		/*
		for (int i = 0; i < partes.length; i++)
			System.out.println("Parte[" + i + "] -> " + partes[i]);
		//*/

		//System.out.println("Qnt de partes -> " + partes.length);
		//*
		if (!partes[0].equals("Detalhamento das informaes no disponvel.")){
			//System.out.println("Entrou na transformação");
			Item item;
			Tabela tb;
			
			/*	Ação */
			tb = getTb().get(6);
			item = new Item();
			item.setCodigoStr(partes[12]);
			item.setDescricao(partes[13]);
			item.getCodRefStr().add(partes[14]);
			inserirItemTabela (item, tb);
			
			/*	Função */
			tb = getTb().get(3);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[6]));
			item.setDescricao(partes[7]);
			item.getCodRef().add(Integer.parseInt(partes[0]));
			item.getCodRef().add(Integer.parseInt(partes[2]));
			item.getCodRef().add(Integer.parseInt(partes[4]));
			inserirItemTabela (item, tb);
			
			/*	Função-SubFunção */
			tb = getTb().get(11);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[6]));
			item.getCodRef().add(Integer.parseInt(partes[8]));
			inserirItemTabela (item, tb);
			
			/*	Órgão Subordinado */
			tb = getTb().get(1);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[2]));
			item.setDescricao(partes[3]);
			item.getCodRef().add(Integer.parseInt(partes[0]));
			inserirItemTabela (item, tb);
			
			/*	Órgão Superior */
			tb = getTb().get(0);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[0]));
			item.setDescricao(partes[1]);
			inserirItemTabela (item, tb);
			
			/*	Pagamento */
			tb = getTb().get(8);
			item = new Item();
			item.setCodigoStr(partes[17]);
			item.setDescricao(partes[18]);
			inserirItemTabela (item, tb);
			
			/*	Pessoa */
			tb = getTb().get(7);
			item = new Item();
			item.setCodigoStr(partes[15]);
			item.setDescricao(partes[16]);
			item.getCodRef().add(Integer.parseInt(partes[0]));
			item.getCodRef().add(Integer.parseInt(partes[2]));
			item.getCodRef().add(Integer.parseInt(partes[4]));
			inserirItemTabela (item, tb);
			
			/*	Pessoa_Pag */
			tb = getTb().get(12);
			item = new Item();
			item.setCodigoStr(partes[15]);
			item.setDescricao(partes[17]);
			item.getCodRef().add(Integer.parseInt(partes[0]));
			item.getCodRef().add(Integer.parseInt(partes[2]));
			item.getCodRef().add(Integer.parseInt(partes[4]));
			item.getCodRefStr().add("STR_TO_DATE('" + partes[19] + "', '%e/%m/%Y')");
			partes[20] = partes[20].replace(',', '.');
			item.setCodRefFlo(Float.parseFloat(partes[20]));
			inserirItemTabela (item, tb);
			
			/* Programa */
			tb = getTb().get(5);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[10]));
			item.setDescricao(partes[11]);
			inserirItemTabela (item, tb);
			
			/*	Prog_Acao */
			tb = getTb().get(9);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[10]));
			item.getCodRefStr().add(partes[12]);
			inserirItemTabela (item, tb);
			
			/*	Prog_Orgao */
			tb = getTb().get(10);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[10]));
			item.getCodRef().add(Integer.parseInt(partes[2]));
			inserirItemTabela (item, tb);
			
			/* Sub Função */
			tb = getTb().get(4);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[8]));
			item.setDescricao(partes[9]);
			inserirItemTabela (item, tb);
						
			/*	Unidade Gestora */
			tb = getTb().get(2);
			item = new Item();
			item.setCodigo(Integer.parseInt(partes[4]));
			item.setDescricao(partes[5]);
			item.getCodRef().add(Integer.parseInt(partes[0]));
			item.getCodRef().add(Integer.parseInt(partes[2]));
			inserirItemTabela (item, tb);		
		}
		//*/
	}
	
	
	
	/**
	 * Inserção de um item numa tabela, garantindo que o mesmo já não exista
	 * @param item Item a ser analisado na inserção
	 * @param tabela Tabela para inserção do item
	 */
	public void inserirItemTabela (ItemSimples item, TabelaSimples tabela){
		boolean naoExiste = true;
		
		for (ItemSimples itemBusca : tabela.getItensS())
			if (item.compareTo(itemBusca) == 0){
				naoExiste = false;
				break;
			}
		
		if (naoExiste)
			tabela.getItensS().add(item);
	}
	
	/**
	 * Inserção de um item numa tabela, garantindo que o mesmo já não exista
	 * @param item Item a ser analisado na inserção
	 * @param tabela Tabela para inserção do item
	 */
	public void inserirItemTabela (Item item, Tabela tabela){
		boolean naoExiste = true;
		
		for (Item itemBusca : tabela.getItens())
			if (item.compareTo(itemBusca) == 0){
				naoExiste = false;
				break;
			}
		
		if (naoExiste)
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
		
		for (Tabela tb : getTb()){
			dropSQL += tb.dropInSQL();
			
			createSQL += tb.criarInSQL();
			
			insertSQL += tb.inserirInSQL();
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
		int count = getTb().size();
		
		System.out.println("A ler " + count + " tabelas");
		
		for (Tabela tb : getTb()){
			System.out.println("\nFaltam " + --count + " tabelas");
			
			insertSQL += tb.inserirInSQL();
		}
		
		System.out.println("Finalizou leitura tabelas\n");
		
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
