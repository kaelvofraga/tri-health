insert into usuario(id, email, nome, senha, sobrenome) values (1000, '123@123.123', '123', 'f5bb0c8de146c67b44babbf4e6584cc0','123'); 

insert into medicamento (id, nome) values (1001, 'Paracetamol');
insert into medicamento (id, nome) values (1002, 'Benegripe');

insert into tipoalergia (id, descricao) values (1, 'Alimento');
insert into tipoalergia (id, descricao) values (2, 'Ambiental');
insert into tipoalergia (id, descricao) values (3, 'Animal');
insert into tipoalergia (id, descricao) values (4, 'Medicação');
insert into tipoalergia (id, descricao) values (5, 'Planta');

insert into medicamentousuario (ID,DATACONSULTA, DATAFIMTRATAMENTO,DATAINICIOTRATAMENTO, DESCRICAO, DOSAGEM, FREQUENCIA, NOTA, SITUACAO, MEDICAMENTO_ID, USUARIO_ID) VALUES (1000,SYSDATE, SYSDATE, SYSDATE, 'DESCRICAO', 'DOSAGEM', 'FREQUENCIA', 'NOTA', 'SITUACAO', 1001, 1000);
insert into medicamentousuario (ID,DATACONSULTA, DATAFIMTRATAMENTO,DATAINICIOTRATAMENTO, DESCRICAO, DOSAGEM, FREQUENCIA, NOTA, SITUACAO, MEDICAMENTO_ID, USUARIO_ID) VALUES (1001,SYSDATE, SYSDATE, SYSDATE, 'DESCRICAO', 'DOSAGEM', 'FREQUENCIA', 'NOTA', 'SITUACAO', 1002, 1000);


insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1000, 'Reacao', 1, 1000, 1000);

insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1001, 'Reacao', 1, 1000, 1001);

insert into tipoAlimento (id,nome, descricao) values (1, 'AVES','EXEMPLO: FRANGO GALINHA');
insert into tipoAlimento (id,nome, descricao) values (2, 'FRUTAS','EXEMPLO: LARANJA MA�A');
insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1001, 'Reacao', 1, 1000, 1001);

insert into Pais (id, nome, nacionalidade) values (1, 'Brasil','Brasileiro' );
insert into UnidadeFederativa (id, nome, id_pais) values (1, 'Rio Grande do Sul',1 );
insert into Cidade (id, nome, id_uf) values (1, 'Canoas',1 );
insert into Endereco (id, cep, complemento, logradouro, numero, id_cidade) values (1, '92330-500', 'Casa', 'Rua Um', 541, 1 );
