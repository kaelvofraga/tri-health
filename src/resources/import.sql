insert into Pais (id, nome, nacionalidade) values (1000, 'Brasil','Brasileiro' );
insert into Pais (id, nome, nacionalidade) values (1001, 'EUA','Americano' );
insert into UnidadeFederativa (id, nome, id_pais) values (1000, 'Rio Grande do Sul',1000 );
insert into UnidadeFederativa (id, nome, id_pais) values (1001, 'Nova York',1001 );
insert into Cidade (id, nome, id_uf) values (1000, 'Canoas',1000 );
insert into Cidade (id, nome, id_uf) values (1001, 'Albany',1001 );
insert into Endereco (id, cep, complemento, logradouro, numero, id_cidade) values (1000, '92330-500', 'Casa', 'Rua Um', 541, 1000 );
insert into Endereco (id, cep, complemento, logradouro, numero, id_cidade) values (1001, '91330-000', 'AP', 'Street One', 542, 1001 );
insert into usuario(id, email, nome, senha, sobrenome, apelido, telefone, celular , dataNasc, genero, tipoSanguineo , ID_ENDERECO) values (1000, '123@123.123', '123', 'f5bb0c8de146c67b44babbf4e6584cc0','123', '456' ,  '456456456','0909090909', SYSDATE, 'Masculino',  'AB+',1001); 

insert into medicamento (id, nome) values (1001, 'Paracetamol');
insert into medicamento (id, nome) values (1002, 'Benegripe');

insert into tipoalergia (id, descricao) values (1, 'Alimento');
insert into tipoalergia (id, descricao) values (2, 'Ambiental');
insert into tipoalergia (id, descricao) values (3, 'Animal');
insert into tipoalergia (id, descricao) values (4, 'Medicação');
insert into tipoalergia (id, descricao) values (5, 'Planta');

insert into medicamentousuario (ID,DATACONSULTA, DATAFIMTRATAMENTO,DATAINICIOTRATAMENTO, DESCRICAO, DOSAGEM, FREQUENCIA, NOTA, SITUACAO, MEDICAMENTO_ID, USUARIO_ID) VALUES (1010,SYSDATE, SYSDATE, SYSDATE, 'DESCRICAO', 'DOSAGEM', 'FREQUENCIA', 'NOTA', 'SITUACAO', 1001, 1000);
insert into medicamentousuario (ID,DATACONSULTA, DATAFIMTRATAMENTO,DATAINICIOTRATAMENTO, DESCRICAO, DOSAGEM, FREQUENCIA, NOTA, SITUACAO, MEDICAMENTO_ID, USUARIO_ID) VALUES (1011,SYSDATE, SYSDATE, SYSDATE, 'DESCRICAO', 'DOSAGEM', 'FREQUENCIA', 'NOTA', 'SITUACAO', 1002, 1000);


insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1010, 'Reacao', 1, 1000, 1000);

insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1011, 'Reacao', 1, 1000, 1001);

insert into tipoAlimento (id,nome, descricao) values (1000, 'AVES','EXEMPLO: FRANGO GALINHA');
insert into tipoAlimento (id,nome, descricao) values (1001, 'FRUTAS','EXEMPLO: LARANJA MA�A');
insert into tipoAlimento (id,nome, descricao) values (1002, 'CARNES','EXEMPLO: TESTE');

insert into Alimento (id,nome, caloriasPorCemGrOuMl,TIPOALIMENTO_ID) values (1000, 'FRANGO',250,1000);
insert into Alimento (id,nome, caloriasPorCemGrOuMl,TIPOALIMENTO_ID) values (1001, 'GALINHA',200,1000);
insert into Alimento (id,nome, caloriasPorCemGrOuMl,TIPOALIMENTO_ID) values (1002, 'MA��',200,1001);
insert into Alimento (id,nome, caloriasPorCemGrOuMl,TIPOALIMENTO_ID) values (1003, 'BOI',200,1002);


insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1001, 'Reacao', 1, 1000, 1001);

--[DIMENS�ES CORPORAIS]--

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


--[ Manter Atividades ]--

insert into TipoAtividade(id, nome) values (1, 'Corrida');
insert into TipoAtividade(id, nome) values (2, 'Ciclismo');
insert into TipoAtividade(id, nome) values (3, 'Dan�a');

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (1, 'Jogging', 7, 1);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (2, 'Correndo em 5 mph (8 quilometros por hora)', 8, 1);

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (3, 'BMX ou Mountain Bike', 8.5, 2);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (4, 'Pedalando para o trabalho ou por divers�o', 4, 2);

insert into Atividade(id, descricao, MET, tipoAtividade_id) values (5, 'Dan�a aer�bica', 6.5, 3);
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (6, 'Ballet ou moderna, twist, jazz, tap, jitterbug', 4.8, 3);

--table TipoExameUrina
insert into TipoExameUrina (id, tipo) values (10001,'Glicose');
insert into TipoExameUrina (id, tipo) values (10002,'Bilirrubinas');
insert into TipoExameUrina (id, tipo) values (10003,'Corpos Cet�nicos');
insert into TipoExameUrina (id, tipo) values (10004,'Densidade');
insert into TipoExameUrina (id, tipo) values (10005,'pH');
insert into TipoExameUrina (id, tipo) values (10006,'Prote�nas');
insert into TipoExameUrina (id, tipo) values (10007,'Urobilinog�nio');
insert into TipoExameUrina (id, tipo) values (10008,'Nitrito');
insert into TipoExameUrina (id, tipo) values (10009,'Hemoglobina');
insert into TipoExameUrina (id, tipo) values (10010,'Leuc�citos');
insert into TipoExameUrina (id, tipo) values (10011,'Hem�cias');
insert into TipoExameUrina (id, tipo) values (10012,'C�lulas Epiteliais');
insert into TipoExameUrina (id, tipo) values (10013,'Bacteri�ria');
insert into TipoExameUrina (id, tipo) values (10014,'Cilindros');

-- Manter Cateterismo
INSERT INTO MEDICO VALUES(1,'RS123','Dr teste 1');
INSERT INTO MEDICO VALUES(2,'RS321','Dr teste 2');
INSERT INTO MEDICO VALUES(2,'RS441','Dr teste 3');

INSERT INTO CONSULTA (id,diagnostico,sintomas,id_usuario,data) values (1001,'Gripe','coriza',1000,sysdate);
INSERT INTO CONSULTA (id,diagnostico,sintomas,id_usuario,data) values (1002,'Febre','dor no corpo',1000,sysdate);