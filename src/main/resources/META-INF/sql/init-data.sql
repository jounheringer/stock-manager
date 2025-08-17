INSERT INTO usuarios (nome_usuario, email) VALUES ('Alice', 'alice@alice.com');
INSERT INTO usuarios (nome_usuario, email) VALUES ('Bruno', 'bruno@bruno.com');
INSERT INTO usuarios (nome_usuario, email) VALUES ('Carla', 'carla@carla.com');
INSERT INTO usuarios (nome_usuario, email) VALUES ('Diego', 'diego@diego.com');
INSERT INTO usuarios (nome_usuario, email) VALUES ('Julia', 'julia@julia.com');

INSERT INTO produtos (codigo, descricao, data_entrada, validade, quantidade)
VALUES ('P001', 'Produto 1 - Teclado', '2025-08-17', '2026-02-13', 100);
INSERT INTO produtos (codigo, descricao, data_entrada, validade, quantidade)
VALUES ('P002', 'Produto 2 - Mouse', '2025-08-17', '2026-04-14', 80);
INSERT INTO produtos (codigo, descricao, data_entrada, validade, quantidade)
VALUES ('P003', 'Produto 3 - Monitor', '2025-08-17', '2026-08-17', 50);
INSERT INTO produtos (codigo, descricao, data_entrada, validade, quantidade)
VALUES ('P004', 'Produto 4 - Headset', '2025-08-17', '2026-06-13', 70);
INSERT INTO produtos (codigo, descricao, data_entrada, validade, quantidade)
VALUES ('P005', 'Produto 5 - Webcam', '2025-08-17', '2026-03-05', 60);

INSERT INTO pagamentos (quantidade, id_produto, id_usuario) VALUES (5, 1, 1);
INSERT INTO pagamentos (quantidade, id_produto, id_usuario) VALUES (3, 2, 2);
INSERT INTO pagamentos (quantidade, id_produto, id_usuario) VALUES (2, 3, 3);
INSERT INTO pagamentos (quantidade, id_produto, id_usuario) VALUES (4, 4, 4);
INSERT INTO pagamentos (quantidade, id_produto, id_usuario) VALUES (1, 5, 5);