insert into usuario(id, email, nome, senha, sobrenome) values (1000, '123@123.123', '123', 'f5bb0c8de146c67b44babbf4e6584cc0','123'); 

insert into medicamento (id, nome) values (1001, 'Paracetamol');
insert into medicamento (id, nome) values (1002, 'Benegripe');

insert into tipoalergia (id, descricao) values (1, 'Alimento');
insert into tipoalergia (id, descricao) values (2, 'Ambiental');
insert into tipoalergia (id, descricao) values (3, 'Animal');
insert into tipoalergia (id, descricao) values (4, 'MedicaÃ§Ã£o');
insert into tipoalergia (id, descricao) values (5, 'Planta');

insert into medicamentousuario (ID,DATACONSULTA, DATAFIMTRATAMENTO,DATAINICIOTRATAMENTO, DESCRICAO, DOSAGEM, FREQUENCIA, NOTA, SITUACAO, MEDICAMENTO_ID, USUARIO_ID) VALUES (1010,SYSDATE, SYSDATE, SYSDATE, 'DESCRICAO', 'DOSAGEM', 'FREQUENCIA', 'NOTA', 'SITUACAO', 1001, 1000);
insert into medicamentousuario (ID,DATACONSULTA, DATAFIMTRATAMENTO,DATAINICIOTRATAMENTO, DESCRICAO, DOSAGEM, FREQUENCIA, NOTA, SITUACAO, MEDICAMENTO_ID, USUARIO_ID) VALUES (1011,SYSDATE, SYSDATE, SYSDATE, 'DESCRICAO', 'DOSAGEM', 'FREQUENCIA', 'NOTA', 'SITUACAO', 1002, 1000);


insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1010, 'Reacao', 1, 1000, 1000);

insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1011, 'Reacao', 1, 1000, 1001);

insert into tipoAlimento (id,nome, descricao) values (1, 'AVES','EXEMPLO: FRANGO GALINHA');
insert into tipoAlimento (id,nome, descricao) values (2, 'FRUTAS','EXEMPLO: LARANJA MAï¿½A');
insert into tipoAlimento (id,nome, descricao) values (3, 'CARNES','EXEMPLO: TESTE');

insert into Alimento (id,nome, caloriasPorCemGrOuMl) values (1, 'FRANGO',250);
insert into Alimento (id,nome, caloriasPorCemGrOuMl) values (2, 'GALINHA',200);

insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1001, 'Reacao', 1, 1000, 1001);

insert into Pais (id, nome, nacionalidade) values (1, 'Brasil','Brasileiro' );
insert into UnidadeFederativa (id, nome, id_pais) values (1, 'Rio Grande do Sul',1 );
insert into Cidade (id, nome, id_uf) values (1, 'Canoas',1 );
insert into Endereco (id, cep, complemento, logradouro, numero, id_cidade) values (1, '92330-500', 'Casa', 'Rua Um', 541, 1 );

--[DIMENSï¿½ES CORPORAIS]--

--table udm
insert into Udm (id, descricao) values (1, 'cm');
insert into Udm (id, descricao) values (2, 'pol');

--table tipoMedida
insert into TipoMedida(id, descricao) values (1, 'Busto');
insert into TipoMedida(id, descricao) values (2, 'Peito');
insert into TipoMedida(id, descricao) values (3, 'Cabeca');
insert into TipoMedida(id, descricao) values (4, 'Biceps Esquerdo');
insert into TipoMedida(id, descricao) values (5, 'Biceps Direito');
insert into TipoMedida(id, descricao) values (6, 'Pescoco');
insert into TipoMedida(id, descricao) values (7, 'Coxa Esquerda');
insert into TipoMedida(id, descricao) values (8, 'Coxa Direita'); 
insert into TipoMedida(id, descricao) values (9, 'Altura');	

--table valorMedida
insert into ValorMedidaUsuario(id, USUARIO_ID, UDM_ID, TIPOMEDIDA_ID, medidaValor, dataMedida, observacao) values (100000, 1000, 1, 2, 140.39, sysdate, 'lesao muscular no lado esquerdo do peito.');
insert into ValorMedidaUsuario(id, USUARIO_ID, UDM_ID, TIPOMEDIDA_ID, medidaValor, dataMedida, observacao) values (100001, 1000, 2, 3, 40.00, sysdate, 'nao possuo lesao corporal.');


--[ Manter Atividades Fï¿½sicas ]--

insert into TipoAtividade(id, nome) values (1, 'Corrida');
insert into TipoAtividade(id, nome) values (2, 'Ciclismo');
insert into TipoAtividade(id, nome) values (3, 'Danï¿½a');

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (1, 'Jogging', 7, 1);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (2, 'Correndo em 5 mph (8 quilometros por hora)', 8, 1);

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (3, 'BMX ou Mountain Bike', 8.5, 2);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (4, 'Pedalando para o trabalho ou por diversï¿½o', 4, 2);

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (5, 'Danï¿½a aerï¿½bica', 6.5, 3);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (6, 'Ballet ou moderna, twist, jazz, tap, jitterbug', 4.8, 3);

--table TipoExameUrina
insert into TipoExameUrina (id, tipo) values (1,'Glicose');
insert into TipoExameUrina (id, tipo) values (2,'Bilirrubinas');
insert into TipoExameUrina (id, tipo) values (3,'Corpos Cetônicos');
insert into TipoExameUrina (id, tipo) values (4,'Densidade');
insert into TipoExameUrina (id, tipo) values (5,'pH');
insert into TipoExameUrina (id, tipo) values (6,'Proteínas');
insert into TipoExameUrina (id, tipo) values (7,'Urobilinogênio');
insert into TipoExameUrina (id, tipo) values (8,'Nitrito');
insert into TipoExameUrina (id, tipo) values (9,'Hemoglobina');
insert into TipoExameUrina (id, tipo) values (10,'Leucócitos');
insert into TipoExameUrina (id, tipo) values (11,'Hemácias');
insert into TipoExameUrina (id, tipo) values (12,'Células Epiteliais');
insert into TipoExameUrina (id, tipo) values (13,'Bacteriúria');
insert into TipoExameUrina (id, tipo) values (14,'Cilindros');

-- Manter Cateterismo
INSERT INTO MEDICO VALUES(1,'RS123','Dr teste 1');
INSERT INTO MEDICO VALUES(2,'RS321','Dr teste 2');
INSERT INTO CATETERISMO(id,usuario_id,dataalta,datainternacao,laudo,observacoes,medico_sol_id,medico_res_id) VALUES (1,1000,'20150201','20150130','BLABLA BLA','OBS TSTE',1,2);

