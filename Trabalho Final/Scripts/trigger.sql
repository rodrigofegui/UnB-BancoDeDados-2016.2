CREATE 
[DEFINER = { user | CURRENT_USER}]
TRIGGER trigger1 AFTER
insert on PESSOA_PAG FOR EACH ROW update PESSOA_PAG SET valor = round(PESSOA_PAG.valor) where cpf_pessoa in (select cpf from PESSOA) 
