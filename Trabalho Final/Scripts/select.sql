select FUNCAO.nome, UNIDADE_GESTORA.nome, PESSOA.nome, PESSOA_PAG.valor from FUNCAO inner join FUNCAO_UNIDADE on
funcao.codigo = FUNCAO_UNIDADE.cod_funcao inner join UNIDADE_GESTORA on
UNIDADE_GESTORA.codigo = FUNCAO_UNIDADE.cod_unidade_gestora and
UNIDADE_GESTORA.cod_superior = FUNCAO_UNIDADE.cod_superior and
UNIDADE_GESTORA.cod_subordinado = FUNCAO_UNIDADE.cod_subordinado
inner join PESSOA on
PESSOA.codigo_unidade_gestora = UNIDADE_GESTORA.codigo  inner join 
PESSOA_PAG on PESSOA.cpf = PESSOA_PAG.cpf_pessoa inner join PAGAMENTO 
on PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag order by PESSOA_PAG.valor desc



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


select FUNCAO.nome, sum(PESSOA_PAG.valor) as total from FUNCAO inner join FUNCAO_UNIDADE on
FUNCAO.codigo = FUNCAO_UNIDADE.cod_funcao inner join UNIDADE_GESTORA on
UNIDADE_GESTORA.codigo = FUNCAO_UNIDADE.cod_unidade_gestora and
UNIDADE_GESTORA.cod_superior = FUNCAO_UNIDADE.cod_superior and
UNIDADE_GESTORA.cod_subordinado = FUNCAO_UNIDADE.cod_subordinado inner join PESSOA on PESSOA.codigo_unidade_gestora = UNIDADE_GESTORA.codigo and PESSOA.cod_superior = UNIDADE_GESTORA.cod_superior and PESSOA.cod_subordinado = UNIDADE_GESTORA.cod_subordinado inner join PESSOA_PAG on
PESSOA.cpf = PESSOA_PAG.cpf_pessoa and
PESSOA.cod_superiror = PESSOA_PAG.cod_superiror and
PESSOA.cod_subordinado = PESSOA_PAG.cod_subordinado and
PESSOA.codigo_unidade_gestora = PESSOA_PAG.codigo_unidade_gestora inner join PAGAMENTO ON
PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag
group by FUNCAO.nome order by total desc


select PESSOA_PAG.data, sum(valor) from PESSOA_PAG group by 1

