insert into Pais (id, nome, nacionalidade) values (1000, 'Brasil','Brasileiro' );
insert into Pais (id, nome, nacionalidade) values (1001, 'EUA','Americano' );
insert into UnidadeFederativa (id, nome, id_pais) values (1000, 'Rio Grande do Sul',1000 );
insert into UnidadeFederativa (id, nome, id_pais) values (1001, 'New York',1001 );
insert into Cidade (id, nome, id_uf) values (1000, 'Canoas',1000 );
insert into Cidade (id, nome, id_uf) values (1001, 'Albany',1001 );
insert into Endereco (id, cep, complemento, logradouro, numero, id_cidade) values (1000, '92330-500', 'Casa', 'Rua Um', 541, 1000 );
insert into Endereco (id, cep, complemento, logradouro, numero, id_cidade) values (1001, '91330-000', 'AP', 'Street One', 542, 1001 );
insert into usuario(id, email, nome, senha, sobrenome, apelido, telefone, celular , dataNasc, genero, tipoSanguineo , ID_ENDERECO) values (1000, '123@123.123', '123', 'f5bb0c8de146c67b44babbf4e6584cc0','123', '456' ,  '45645645656','0909090909', SYSDATE, 'Masculino',  'AB+',1001); 

---------------------------------------------------
-- @JuarezMonteiro

-- Hospitais 
	
	--Cidade
	insert into Cidade (id, nome, id_uf) values (1, 'Porto Alegre',1000);
	
	--Telefone
	insert into Telefone (id, ddd, numero) values (1000, '51','3357-2000'); -- Hospital Conceição
	insert into Telefone (id, ddd, numero) values (1001, '51','3314-5200'); -- Hospital Fêmina
	insert into Telefone (id, ddd, numero) values (1002, '51','3357-4100'); -- Hospital Cristo Redentor
	
	--Endereco
	insert into Endereco (id, cep, complemento, logradouro, numero, latitude, longitude, id_cidade, id_telefone) values (1, '00000-000', 'x', 'Av, Francisco Trein - Bairro Cristo Redentor', 296, '-30.0195467', '-51.170071', 1, 1000); -- Hospital Conceição
	insert into Endereco (id, cep, complemento, logradouro, numero, latitude, longitude, id_cidade, id_telefone) values (2, '00000-000', 'x', 'Rua Mostardeiro – Bairro Moinhos de Vento', 17, '-30.02923', '-51.206877', 1, 1001);	 -- Hospital Fêmina
	insert into Endereco (id, cep, complemento, logradouro, numero, latitude, longitude, id_cidade, id_telefone) values (3, '00000-000', 'x', 'Rua Domingos Rubbo - Bairro Cristo Redentor', 20, '-30.0100396', '-51.1591039', 1, 1002);	 -- Hospital Cristo Redentor
	insert into Endereco (id, cep, complemento, logradouro, numero, latitude, longitude, id_cidade, id_telefone) values (4, '00000-000', 'x', '', 0, '', '', 1, 1002);	 -- Hospital Cristo Redentor
	
	--Hospitais
	insert into Hospital (id, nome, id_endereco) values (1, 'Hospital Conceição', 1);
	insert into Hospital (id, nome, id_endereco) values (2, 'Hospital Fêmina', 2);
	insert into Hospital (id, nome, id_endereco) values (3, 'Hospital Cristo Redentor', 3);
	insert into Hospital (id, nome, id_endereco) values (4, 'Hospital Endereco Vazio', 4);
	
---------------------------------------------------	

insert into medicamento (id, nome) values (1001, 'Paracetamol');
insert into medicamento (id, nome) values (1002, 'Benegripe');

insert into tipoalergia (id, descricao) values (1, 'Alimento');
insert into tipoalergia (id, descricao) values (2, 'Ambiental');
insert into tipoalergia (id, descricao) values (3, 'Animal');
insert into tipoalergia (id, descricao) values (4, 'Medicamento');
insert into tipoalergia (id, descricao) values (5, 'Planta');

insert into MedicamentoUsuario (ID,DATACONSULTA, DATAFIMTRATAMENTO,DATAINICIOTRATAMENTO, DESCRICAO, DOSAGEM, FREQUENCIA, NOTA, SITUACAO, MEDICAMENTO_ID, USUARIO_ID) VALUES (1010,SYSDATE, SYSDATE, SYSDATE, 'DESCRICAO', 'DOSAGEM', 'FREQUENCIA', 'NOTA', 'SITUACAO', 1001, 1000);
insert into MedicamentoUsuario (ID,DATACONSULTA, DATAFIMTRATAMENTO,DATAINICIOTRATAMENTO, DESCRICAO, DOSAGEM, FREQUENCIA, NOTA, SITUACAO, MEDICAMENTO_ID, USUARIO_ID) VALUES (1011,SYSDATE, SYSDATE, SYSDATE, 'DESCRICAO', 'DOSAGEM', 'FREQUENCIA', 'NOTA', 'SITUACAO', 1002, 1000);


insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1010, 'Reacao', 4, 1000, 1000);
insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1011, 'Reacao', 4, 1000, 1001);
insert into AlergiaUsuario (dataPrimeiraOcorrencia, MEDICAMENTOUSUARIO_ID, reacao, tipoAlergia_id, USUARIO_ID, id) values (sysdate, 1001, 'Reacao', 4, 1000, 1002);
insert into tipoAlimento (id,nome, descricao) values (1000, 'AVES','EXEMPLO: FRANGO GALINHA');
insert into tipoAlimento (id,nome, descricao) values (1001, 'FRUTAS','EXEMPLO: LARANJA MAï¿½A');
insert into tipoAlimento (id,nome, descricao) values (1002, 'CARNES','EXEMPLO: TESTE');

insert into Alimento (id,nome, caloriasPorCemGrOuMl,TIPOALIMENTO_ID) values (1000, 'FRANGO',250,1000);
insert into Alimento (id,nome, caloriasPorCemGrOuMl,TIPOALIMENTO_ID) values (1001, 'GALINHA',200,1000);
insert into Alimento (id,nome, caloriasPorCemGrOuMl,TIPOALIMENTO_ID) values (1002, 'MAï¿½ï¿½',200,1001);
insert into Alimento (id,nome, caloriasPorCemGrOuMl,TIPOALIMENTO_ID) values (1003, 'BOI',200,1002);





--[DIMENSï¿½ES CORPORAIS]--

--table udm
insert into Udm (id, descricao) values (1, 'cm');
insert into Udm (id, descricao) values (2, 'pol');
insert into Udm (id, descricao) values (3, 'kg');
insert into Udm (id, descricao) values (4, 'lbs');

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


--[ Manter Atividades ]-- Removido

--Manter Exame Urina
insert into TipoAnalise (id, tipo,valorReferencia) values (10001,'Glicose','180');
insert into TipoAnalise (id, tipo,valorReferencia) values (10002,'Bilirrubinas','1100 a 1600');
insert into TipoAnalise (id, tipo,valorReferencia) values (10003,'Corpos Cetonicos','1');
insert into TipoAnalise (id, tipo,valorReferencia) values (10004,'Densidade','1000 a 1400');
insert into TipoAnalise (id, tipo,valorReferencia) values (10005,'pH','5 a 7');
insert into TipoAnalise (id, tipo,valorReferencia) values (10006,'Proteinas','30 a 500');
insert into TipoAnalise (id, tipo,valorReferencia) values (10007,'Urobilinogenio','1,5');
insert into TipoAnalise (id, tipo,valorReferencia) values (10008,'Nitrito','1000');
insert into TipoAnalise (id, tipo,valorReferencia) values (10009,'Hemoglobina','4');
insert into TipoAnalise (id, tipo,valorReferencia) values (10010,'Leucocitos','5');
insert into TipoAnalise (id, tipo,valorReferencia) values (10011,'Hemacias','3 a 5');
insert into TipoAnalise (id, tipo,valorReferencia) values (10012,'Celulas Epiteliais','1');
insert into TipoAnalise (id, tipo,valorReferencia) values (10013,'Bacteria','1000 a 1500');
insert into TipoAnalise (id, tipo,valorReferencia) values (10014,'Cilindros','1000 a 2000');

insert into ExameUrinaUsuario(id,ID_USUARIO,data,observacao) values (1001,1000,SYSDATE,'OBSERVACAO');

insert into ItemExameUrina (id,ID_TIPO_ANALISE,ID_EXAME_URINA_USUARIO, resultado) values (2000,10001,1001,'ok');
insert into ItemExameUrina (id,ID_TIPO_ANALISE,ID_EXAME_URINA_USUARIO, resultado) values (2001,10002,1001,'ok');
insert into ItemExameUrina (id,ID_TIPO_ANALISE,ID_EXAME_URINA_USUARIO, resultado) values (2002,10003,1001,'ok');
insert into ItemExameUrina (id,ID_TIPO_ANALISE,ID_EXAME_URINA_USUARIO, resultado) values (2003,10004,1001,'ok');

-- Manter Cateterismo
INSERT INTO MEDICO VALUES(1001,'RS123','Dr teste 1');
INSERT INTO MEDICO VALUES(1002,'RS321','Dr teste 2');
INSERT INTO MEDICO VALUES(1003,'RS441','Dr teste 3');

INSERT INTO CONSULTA (id,diagnostico,sintomas,usuario_id,data,medico_id) values (1001,'Gripe','coriza',1000,sysdate,1001);
INSERT INTO CONSULTA (id,diagnostico,sintomas,usuario_id,data,medico_id) values (1002,'Febre','dor no corpo',1000,sysdate,1002);

insert into Idioma (id, nome) values (1000, 'Portuguï¿½s' );
insert into Idioma (id, nome) values (1001, 'Inglï¿½s' );
insert into Idioma (id, nome) values (1002, 'Espanhï¿½l' );
insert into Idioma (id, nome) values (1003, 'Francï¿½s' );
insert into Idioma (id, nome) values (1004, 'Alemï¿½o' );
insert into Idioma (id, nome) values (1005, 'Mandarin' );
insert into Idioma (id, nome) values (1006, 'Japonï¿½s' );

insert into TipoExame (id, descricao, unidadeDeMedida, valorReferencia) values (1001, 'Eletrocardiograma(ECG)', 'x1', '80');
insert into TipoExame (id, descricao, unidadeDeMedida, valorReferencia) values (1002, 'Radiografia de torax (Raio X de torax)', 'x2', '90');

--Exame
insert into Exame (id, nome, descricao) values (1001, 'HEMOGRAMA', 'EXAME HEMOGRAMA' );
insert into Exame (id, nome, descricao) values (1002, 'COLESTEROl', 'EXAME COLESTEROL' );

--Exames Campos 0 string 1 numérico
insert into ExameCampos (id,exame_id, nomeCampo) values (1001, 1001,'RESULTADO');
insert into ExameCampos (id,exame_id, nomeCampo) values (1002, 1001,'OBS');
insert into ExameCampos (id,exame_id, nomeCampo) values (1003, 1002,'RESULTADO');
insert into ExameCampos (id,exame_id, nomeCampo) values (1004, 1002,'OBS');


insert into Resultado (id, USUARIO_ID, exame_id, resultadoData) values (1001, 1000, 1001, sysdate);
insert into ResultadoCampos (id , resultado_id, examecampos_id, resultadoValor) values (1001, 1001, 1001, '50');
insert into ResultadoCampos (id , resultado_id, examecampos_id, resultadoValor) values (1002, 1001, 1002, 'teste');

insert into Resultado (id, USUARIO_ID, exame_id, resultadoData) values (1002, 1000, 1002, sysdate);
insert into ResultadoCampos (id , resultado_id, examecampos_id, resultadoValor) values (1003, 1002, 1003, '100');
insert into ResultadoCampos (id , resultado_id, examecampos_id, resultadoValor) values (1004, 1002, 1004, 'tes2te');

/*Atividades Físicas: Para testar compartilhamento no Facebook*/
/*comentada para cadastro de categoria*/
/*insert into TipoAtividade(id, nome) values (1000, 'Teste Facebook');
insert into Atividade(id, descricao, MET, tipoAtividade_id) values (1000, 'Compartilhar no Facebook', 2.0, 1);*/

/*Exame visao*/

INSERT INTO EXAMEVISAO (id,usuario_id,data,medico_id) values (1001,1000,sysdate,1001);
INSERT INTO EXAMEVISAO (id,usuario_id,data,medico_id) values (1002,1000,sysdate,1002);

insert into tipograu(id, descricao) values (1001,'Miopia');
insert into tipograu(id, descricao) values (1002,'Astigmatismo');

insert into grau(id, direito, esquerdo, TIPOGRAU_ID,GRAUS_ID) values (1001,0.25, 1.5, 1001, 1001);
insert into grau(id, direito, esquerdo, TIPOGRAU_ID,GRAUS_ID) values (1002,2.50, 1.0, 1002, 1002);