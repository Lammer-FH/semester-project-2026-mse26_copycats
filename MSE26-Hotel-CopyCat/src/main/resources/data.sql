INSERT IGNORE INTO room_types (id, name, description, image_path) VALUES
    (1, 'Deluxe Suite', 'A spacious suite with a view of the city.', '/images/rooms/1.jpg'),
    (2, 'Family Room', 'A comfortable room for families with additional sleeping space.', '/images/rooms/2.jpg'),
    (3, 'Business Room', 'A quiet room tailored for work trips and short stays.', '/images/rooms/3.jpg');

INSERT IGNORE INTO room_extras (id, name, icon_path) VALUES
    (1, 'WiFi', '/images/extras/wifi.svg'),
    (2, 'Air Conditioning', '/images/extras/air-conditioning.svg'),
    (3, 'Parking', '/images/extras/parking.svg'),
    (4, 'Balcony', '/images/extras/balcony.svg');

INSERT IGNORE INTO room_type_room_extras (id_room_types, id_room_extras) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 2),
    (2, 1),
    (2, 2),
    (2, 4),
    (3, 1),
    (3, 3);

INSERT IGNORE INTO rooms (id, room_nr, id_room_types) VALUES
    (1, '101', 1),
    (2, '102', 1),
    (3, '201', 2),
    (4, '202', 2),
    (5, '301', 3);
