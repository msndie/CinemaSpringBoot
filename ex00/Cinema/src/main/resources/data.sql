INSERT INTO ex00_boot.users (first_name, last_name, phone_number, email, password, role)
VALUES ('Adminskiy', 'Admin', '789-465-1230', 'admin@admin.com', '$2a$10$tEfQBjchyI99KVWxDh7I8ekj00So.WPc85rpYysX45CA4Vd17p8zW', 'ADMIN')
ON CONFLICT DO NOTHING;

INSERT INTO ex00_boot.films(id, title, year, age_restrictions, description, image_id)
VALUES
    (1, 'Bad film', 2017, 0, 'Such a shame', null),
    (2, 'Good film', 2017, 18, 'Good, just good', null),
    (3, 'Best film ever :D', 2014, 16, 'Best of the best', null)
ON CONFLICT DO NOTHING;

INSERT INTO ex00_boot.halls(id, serial_number, number_of_seats)
VALUES
    (1, 1, 30),
    (2, 2, 50),
    (3, 3, 10)
ON CONFLICT DO NOTHING;

INSERT INTO ex00_boot.sessions(id, film_id, hall_id, price, date_time)
VALUES
    (1, 1, 3, 10, '2022-09-20 15:00:00'),
    (2, 2, 1, 220, '2022-09-20 17:00:00'),
    (3, 3, 2, 250, '2022-09-20 19:00:00')
ON CONFLICT DO NOTHING;

INSERT INTO ex00_boot.messages(id, user_name, message, film_id)
VALUES
    (1, 'Bran_an_Tuirseach', 'Bad movie', 1),
    (2, 'Brouver_Hoog', 'Agree', 1),
    (3, 'Brouver_Hoog', 'Good movie', 2),
    (4, 'Bran_an_Tuirseach', 'Agree', 2),
    (5, 'Priscilla', 'Great movie', 3),
    (6, 'Imlerith', 'For sure', 3)
ON CONFLICT DO NOTHING;