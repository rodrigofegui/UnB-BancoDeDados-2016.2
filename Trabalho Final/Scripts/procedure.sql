delimiter $$
create procedure sp_lista_clientes(in opcao integer)
begin
    if opcao == 0 then
        select PESSOA.nome, sum(PESSOA_PAG.valor) as total from PESSOA inner join 
PESSOA_PAG on PESSOA.cpf = PESSOA_PAG.cpf_pessoa inner join PAGAMENTO 
on PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag where total > 5000 group by PESSOA.nome 
    else
        if opcao == 1 then
                  select PESSOA.nome, sum(PESSOA_PAG.valor) as total from PESSOA inner join 
PESSOA_PAG on PESSOA.cpf = PESSOA_PAG.cpf_pessoa inner join PAGAMENTO 
on PAGAMENTO.documento_pagamento = PESSOA_PAG.doc_pag where total > 10000 group by PESSOA.nome 
        
        end if;
    end if;
  end $$
delimiter ;
