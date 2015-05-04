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

--DIMENS�ES CORPORAIS
insert into Udm (id, descricao) values (1, 'cm');
insert into Udm (id, descricao) values (2, 'pol');

insert into TipoMedida(id, descricao) values (1, 'Busto');
insert into TipoMedida(id, descricao) values (2, 'Peito');
insert into TipoMedida(id, descricao) values (3, 'Cabeca');
insert into TipoMedida(id, descricao) values (4, 'Biceps Esquerdo');
insert into TipoMedida(id, descricao) values (5, 'Biceps Direito');
insert into TipoMedida(id, descricao) values (6, 'Pescoco');
insert into TipoMedida(id, descricao) values (7, 'Coxa Esquerda');
insert into TipoMedida(id, descricao) values (8, 'Coxa Direita'); 
insert into TipoMedida(id, descricao) values (9, 'Altura');	

--[ Manter Atividades F�sicas ]--

insert into TipoAtividade(id, nome) values (1, 'Corrida');
insert into TipoAtividade(id, nome) values (2, 'Ciclismo');
insert into TipoAtividade(id, nome) values (3, 'Dan�a');

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (1, 'Jogging', 7, 1);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (2, 'Correndo em 5 mph (8 quilometros por hora)', 8, 1);

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (3, 'BMX ou Mountain Bike', 8.5, 2);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (4, 'Pedalando para o trabalho ou por divers�o', 4, 2);

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (5, 'Dan�a aer�bica', 6.5, 3);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (6, 'Ballet ou moderna, twist, jazz, tap, jitterbug', 4.8, 3);

insert into AtividadeUsuario(id, atividade_id, usuario_id, dataInicio, dataFim, distancia, notas) values (1000, 2, 1000, SYSDATE, SYSDATE, 1000.0, 'Corridinha');
insert into AtividadeUsuario(id, atividade_id, usuario_id, dataInicio, dataFim, distancia, notas) values (2000, 5, 1000, SYSDATE, SYSDATE, 0.0, '');

-- Manter Cateterismo
INSERT INTO MEDICO VALUES(1,'RS123','Dr teste 1');
INSERT INTO MEDICO VALUES(2,'RS321','Dr teste 2');
INSERT INTO CATETERISMO(id,usuario_id,dataalta,datainternacao,laudo,observacoes,medico_sol_id,medico_res_id) VALUES (1,1000,'20150201','20150130','BLABLA BLA','OBS TSTE',1,2);