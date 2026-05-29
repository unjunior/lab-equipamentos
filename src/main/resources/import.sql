-- EMPRESA
INSERT INTO tb_cliente(codigo, nome) values ('100AAA', 'EmpresaA');
INSERT INTO tb_cliente(codigo, nome) values ('100BBB', 'EmpresaB');


--EQUIPAMENTOS
INSERT INTO tb_equipamento (codigo, tipo, data_entrada, status_Equipamento, cliente_id) VALUES ('100AAA-1', 'NOTEBOOK', '2026-01-10', 'NO_LABORATORIO', 1);
INSERT INTO tb_equipamento (codigo, tipo, data_entrada, status_Equipamento, cliente_id) VALUES ('100AAA-2', 'DESKTOP', '2026-02-15', 'NO_LABORATORIO', 1);
INSERT INTO tb_equipamento (codigo, tipo, data_entrada, status_Equipamento, cliente_id) VALUES (null, 'IMPRESSORA', '2026-03-01', 'DEVOLVIDO', 1);
INSERT INTO tb_equipamento (codigo, tipo, data_entrada, status_Equipamento, cliente_id)  VALUES ('100BBB-1', 'SERVIDOR', '2026-03-01', 'NO_LABORATORIO', 2);