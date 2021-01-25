INSERT INTO collector (collector_name, id) values
('udin', 1), ('ayu', 2), ('budi', 3), ('rudi', 4), ('dino', 5);

insert into customer (address, is_active, name, phone_number, id) values
('Jakarta', 1, 'sisi', '0999', 1),
('Bekasi', 1, 'susi', '02333', 2),
('Sukabumi', 1, 'jono', '033434', 3),
('Surabaya', 1, 'isdu', '03535454', 4),
('Semarang', 1, 'oki', '045656', 5);

insert into billing (billing_amount, id_customer, is_close, payment_date, id) values
(67000, 1, 0, '2020-12-09', 1),
(89000, 2, 0, '2020-12-10', 2),
(12000, 3, 0, '2020-12-11', 3),
(23000, 4, 1, '2020-12-12', 4),
(23450, 5, 1, '2020-12-13', 5);