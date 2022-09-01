-- 123QWEasd pass
INSERT INTO users (first_name, last_name, phone_number, email, password, role, enabled)
VALUES ('Adminskiy', 'Admin', '+7(777)7777777', 'admin@admin.com', '$2a$12$rjwtguqNDPeOXfqYd4.ADOQmB4vLw9mx887Jl6ilO2bsW0DkA6xCC', 'ADMIN', true)
ON CONFLICT DO NOTHING;
