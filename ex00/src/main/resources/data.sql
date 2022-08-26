INSERT INTO users (first_name, last_name, phone_number, email, password)
VALUES ('Testovoe', 'Imya', '789-465-1230', 'test@test', '$2a$10$tEfQBjchyI99KVWxDh7I8ekj00So.WPc85rpYysX45CA4Vd17p8zW')
ON CONFLICT DO NOTHING;