BEGIN;

TRUNCATE TABLE
    item_request_offers,
    feedbacks,
    bookings,
    items,
    item_requests,
    users
    RESTART IDENTITY CASCADE;

COMMIT;

BEGIN;

INSERT INTO users (id, name, email, created_at)
VALUES (1, 'Alice', 'alice@example.com', NOW() - INTERVAL '30 days'),
       (2, 'Bob', 'bob@example.com', NOW() - INTERVAL '25 days'),
       (3, 'Carol', 'carol@example.com', NOW() - INTERVAL '20 days'),
       (4, 'Dave', 'dave@example.com', NOW() - INTERVAL '15 days'),
       (5, 'Eve', 'eve@example.com', NOW() - INTERVAL '10 days');


INSERT INTO items (id, name, description, available, owner_id)
VALUES (1, 'Перфоратор', 'Мощный перф для бетона', TRUE, 1),
       (2, 'Лобзик', 'Электролобзик с набором пилок', TRUE, 1),
       (3, 'Велосипед', 'Городской, рама M', TRUE, 2),
       (4, 'Палатка', '2-местная, влагостойкая', TRUE, 2),
       (5, 'Шлем', 'Велошлем, размер L', TRUE, 3),
       (6, 'Дрель', 'Дрель-шуруповерт 18V', FALSE, 3);


INSERT INTO item_requests (id, requester_id, title, description, status, created_at)
VALUES (1, 4, 'Нужна дрель на выходные', 'Планирую повесить полки', 'OPEN', NOW() - INTERVAL '7 days'),
       (2, 5, 'Ищу палатку', 'Поездка на природу', 'OPEN', NOW() - INTERVAL '5 days'),
       (3, 3, 'Нужен велошлем', 'На один день', 'CLOSED', NOW() - INTERVAL '3 days');


INSERT INTO item_request_offers (id, request_id, item_id, added_by_id, added_at, comment, offer_status)
VALUES (1, 1, 6, 3, NOW() - INTERVAL '6 days', 'Есть дрель, но сейчас недоступна', 'PROPOSED'),
       (2, 1, 1, 1, NOW() - INTERVAL '6 days', 'Перфоратор подойдёт?', 'ACCEPTED'),
       (3, 2, 4, 2, NOW() - INTERVAL '4 days', 'Палатка на 2 места свободна', 'PROPOSED'),
       (4, 3, 5, 3, NOW() - INTERVAL '2 days', 'Шлем в хорошем состоянии', 'PROPOSED');


INSERT INTO bookings (id, item_id, user_id, start_time, end_time, status)
VALUES (1, 1, 2, NOW() - INTERVAL '9 days', NOW() - INTERVAL '8 days', 'APPROVED'), -- Bob брал у Alice перфоратор
       (2, 3, 1, NOW() - INTERVAL '6 days', NOW() - INTERVAL '5 days', 'APPROVED'), -- Alice брала у Bob велосипед
       (3, 4, 5, NOW() - INTERVAL '3 days', NOW() - INTERVAL '2 days', 'REJECTED'), -- Eve пыталась взять палатку у Bob
       (4, 5, 4, NOW() - INTERVAL '2 days', NOW() - INTERVAL '1 day', 'APPROVED'),  -- Dave брал у Carol шлем
       (5, 1, 5, NOW() + INTERVAL '2 days', NOW() + INTERVAL '3 days', 'WAITING'),  -- будущая бронь Eve на перфоратор
       (6, 4, 1, NOW() + INTERVAL '4 days', NOW() + INTERVAL '6 days', 'WAITING');

INSERT INTO feedbacks (id, description, rating, user_id, item_id, created_at)
VALUES (1, 'Отличный инструмент, быстро справился.', 5, 2, 1, NOW() - INTERVAL '8 days'),
       (2, 'Велосипед норм, но цепь поскрипывала.', 4, 1, 3, NOW() - INTERVAL '5 days'),
       (3, 'Палатку не дали, бронирование отклонили.', 2, 5, 4, NOW() - INTERVAL '2 days'),
       (4, 'Шлем чистый и удобный.', 5, 4, 5, NOW() - INTERVAL '1 day'),
       (5, 'Перфоратор тяжёлый, но мощный.', 5, 2, 1, NOW() - INTERVAL '7 days'),
       (6, 'Лобзик в порядке, пилки острые.', 5, 3, 2, NOW() - INTERVAL '9 days');



COMMIT;

