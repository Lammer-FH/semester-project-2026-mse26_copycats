INSERT IGNORE INTO room_types (id, name, description, image_path) VALUES
    (1, 'Classic Double', 'Cozy room with double bed', '/images/rooms/1.jpg'),
    (2, 'Classic Double Sea View', 'Cozy room with double bed and sea view', '/images/rooms/2.jpg'),
    (3, 'Classic King', 'Spacious room with king bed', '/images/rooms/3.jpg'),
    (4, 'Classic King Sea View', 'Spacious room with king bed and sea view', '/images/rooms/4.jpg'),
    (5, 'Suite Double', 'Luxury suite with double bed', '/images/rooms/5.jpg'),
    (6, 'Suite Double Sea View', 'Luxury suite with double bed and sea view', '/images/rooms/6.jpg'),
    (7, 'Suite King', 'Luxury suite with king bed', '/images/rooms/7.jpg'),
    (8, 'Suite King Sea View', 'Luxury suite with king bed and sea view', '/images/rooms/8.jpg'),
    (9, 'Presidential Suite', 'Ultimate Presidential Suite with king bed', '/images/rooms/9.jpg');

INSERT IGNORE INTO room_extras (id, name, icon_path) VALUES
    (1, 'WiFi', '/images/icons/wifi.png'),
    (2, 'Air Conditioning', '/images/icons/air-conditioning.png'),
    (3, 'Parking', '/images/icons/parking.png'),
    (4, 'Balcony', '/images/icons/balcony.png'),
    (5, 'Mini Bar', '/images/icons/bar.png'),
    (6, 'Spa Access', '/images/icons/spa.png');

INSERT IGNORE INTO room_type_room_extras (id_room_types, id_room_extras) VALUES
    (1, 1), (1, 2), (1, 3),
    (2, 1), (2, 4), (2, 5),
    (3, 1), (3, 2), (3, 5),
    (4, 1), (4, 4), (4, 5),
    (5, 1), (5, 2), (5, 5),
    (6, 1), (6, 4), (6, 5),
    (7, 1), (7, 3), (7, 5), (7, 6),
    (8, 1), (8, 4), (8, 5), (8, 6),
    (9, 1), (9, 2), (9, 3), (9, 5), (9, 6);

INSERT IGNORE INTO rooms (id, room_nr, id_room_types) VALUES
    (1, '101', 1),
    (2, '102', 1),
    (3, '201', 2),
    (4, '202', 2),
    (5, '301', 3),
    (6, '302', 3),
    (7, '401', 4),
    (8, '501', 5),
    (9, '502', 5),
    (10, '601', 6),
    (11, '701', 7),
    (12, '702', 7),
    (13, '801', 8),
    (14, '901', 9);

INSERT IGNORE INTO guests (id, first_name, last_name, email) VALUES
(1, 'Max', 'Mustermann', 'max@example.com'),
(2, 'Anna', 'Schmidt', 'anna@example.com'),
(3, 'John', 'Doe', 'john@example.com'),
(4, 'Laura', 'Weber', 'laura@example.com'),
(5, 'David', 'Müller', 'david@example.com'),
(6, 'Sophie', 'Bauer', 'sophie@example.com'),
(7, 'Michael', 'Fischer', 'michael@example.com');

INSERT IGNORE INTO bookings  (id, room_id, guest_id, check_in, check_out, breakfast, status, created_at) VALUES
(1, 1, 1, '2026-07-01', '2026-07-05', 1, 'CONFIRMED', '2026-06-01T10:00:00Z'),
(2, 3, 2, '2026-07-10', '2026-07-15', 0, 'CONFIRMED', '2026-06-02T11:00:00Z'),
(3, 5, 3, '2026-08-01', '2026-08-03', 1, 'CONFIRMED', '2026-06-03T12:00:00Z'),
(4, 7, 4, '2026-07-01', '2026-07-31', 1, 'CONFIRMED', '2026-06-04T09:30:00Z'),
(5, 10, 5, '2026-07-01', '2026-07-31', 0, 'CONFIRMED', '2026-06-05T08:15:00Z'),
(6, 13, 6, '2026-07-01', '2026-07-31', 1, 'CONFIRMED', '2026-06-06T14:45:00Z'),
(7, 14, 7, '2026-07-01', '2026-12-31', 1, 'CONFIRMED', '2026-06-07T16:00:00Z');