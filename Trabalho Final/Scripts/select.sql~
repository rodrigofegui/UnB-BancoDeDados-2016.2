select FUNCAO.nome, UNIDADE_GESTORA.nome, PESSOA.nome, PESSOA_PAG.valor from FUNCAO inner join UNIDADE_GESTORA on
FUNCAO.cod_unidade_gestora = UNIDADE_GESTORA.codigo inner join PESSOA on
PESSOA.codigo_unidade_gestora = UNIDADE_GESTORA.codigo  inner join 
PESSOA_PAG on PESSOA.cpf = PESSOA_PAG.cpf_pessoa inner join PAGAMENTO 
on PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag order by PESSOA.nome asc

SELECT ORGAO_SUPERIOR.nome, sum(PESSOA_PAG.valor) as total FROM ORGAO_SUPERIOR INNER JOIN PESSOA ON
ORGAO_SUPERIOR.codigo = PESSOA.cod_superior inner join 
PESSOA_PAG on PESSOA.cpf = PESSOA_PAG.cpf_pessoa inner join PAGAMENTO 
on PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag group by(ORGAO_SUPERIOR.nome) 
order by total desc


select PROGRAMA.nome, sum(PESSOA_PAG.valor) as total from PROGRAMA INNER JOIN PROG_ORGAO ON
PROGRAMA.codigo = PROG_ORGAO.cod_prog INNER JOIN ORGAO_SUBORDINADO ON
PROG_ORGAO.cod_sub = ORGAO_SUBORDINADO.codigo INNER JOIN PESSOA ON
ORGAO_SUBORDINADO.codigo = PESSOA.cod_subordinado inner join 
PESSOA_PAG on PESSOA.cpf = PESSOA_PAG.cpf_pessoa inner join PAGAMENTO 
on PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag group by (PROGRAMA.nome)
order by total desc

select PESSOA.nome, PESSOA_PAG.valor from PESSOA inner join 
PESSOA_PAG on PESSOA.cpf = PESSOA_PAG.cpf_pessoa inner join PAGAMENTO 
on PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag order by valor desc



