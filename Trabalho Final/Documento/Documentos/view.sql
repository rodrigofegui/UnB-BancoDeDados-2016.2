create view view1 as 

select ACAO.nome, ACAO.codigo, PROGRAMA.nome, PROGRAMA.codigo,
ORGAO_SUBORDINADO.nome, ORGAO_SUBORDINADO.codigo, 
UNIDADE_GESTORA.nome, UNIDADE_GESTORA.codigo, PESSOA.nome, PESSOA_PAG.valor
from ACAO
		inner join PROG_ACAO on
			ACAO.codigo = PROG_ACAO.cod_acao
		inner join PROGRAMA ON
			PROGRAMA.codigo = PROG_ACAO.cod_prog
		inner join PROG_ORGAO on
			PROGRAMA.codigo = PROG_ORGAO.cod_prog
		inner join ORGAO_SUBORDINADO ON
			ORGAO_SUBORDINADO.codigo = PROG_ORGAO.cod_sub
		inner join UNIDADE_GESTORA ON
			UNIDADE_GESTORA.cod_subordinado = ORGAO_SUBORDINADO.codigo
		inner join PESSOA ON
			PESSOA.codigo_unidade_gestora = UNIDADE_GESTORA.codigo
		inner join PESSOA_PAG ON
			PESSOA.cpf = PESSOA_PAG.cpf_pessoa
		inner join PAGAMENTO ON
			PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag
