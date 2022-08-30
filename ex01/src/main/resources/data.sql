INSERT INTO users (first_name, last_name, phone_number, email, password, role)
VALUES ('Adminskiy', 'Admin', '789-465-1230', 'admin@admin.com', '$2a$10$tEfQBjchyI99KVWxDh7I8ekj00So.WPc85rpYysX45CA4Vd17p8zW', 'ADMIN')
ON CONFLICT DO NOTHING;