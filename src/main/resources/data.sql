# https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts
# messages
INSERT INTO messages (`description`, author_id)
VALUES ('Щастлив съм, че направих тази стъпка', 3);
INSERT INTO messages (`description`, author_id)
VALUES ('Вече познавам себе си!', 2);
INSERT INTO messages (`description`, author_id)
VALUES ('Благодарим от сърце, променихте живота ми', 4);

-- users_messages
INSERT INTO users_messages(`user_id`, `messages_id`)
VALUES (3, 1);
INSERT INTO users_messages(`user_id`, `messages_id`)
VALUES (2, 2);
INSERT INTO users_messages(`user_id`, `messages_id`)
VALUES (4, 3);

-- аppointments
INSERT INTO appointments (name, description, img, price, therapist_id)
VALUES ('Семейна терапия',
        'Ролята на семейния консултант е да даде един нов прочит на отношенията и на поведението на всеки един член от семейството. «Зациклянето» и «въртенето в омагьосан кръг» могат да бъдат преодолени с помощта на семейната терапия.',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104409/d101ebdf-d734-4277-9bf4-bcf6014b24f7.webp',
        60,
        2);
INSERT INTO appointments (name, description, img, price, therapist_id)
VALUES ('Фирми/Организации',
        'Да използвате максимума от потенциала на своите служители. Да се погрижите служителите Ви да искат да останат да работят и да се развиват при Вас. Да намалите текучеството на персонала си. Да повишите ниво на Мотивация, Лидерски качества и Екипна работа. Да се погрижите за Психичното здраве и Емоционалната Стабилност на Вашите служители.',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589294/samples/MyKnitShop/appointment-2_lvfxip.jpg',
        100,
        3);
INSERT INTO appointments (name, description, img, price, therapist_id)
VALUES ('Индивидуална терапия',
        'Първата консултация служи за ориентир, както на терапевта, така и на клиента. Продължава 60 мин. и по време на сесията терапевтът се стреми да събере възможно най-много информация за клиента и неговия проблем. В края на срещата, терапевтът връща обратна връзка на клиента, изговаря какви според него са вариантите за работа, с каква честота да са срещите и какъв е планът, който предлага за справяне с проблема.',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104409/c7f7ba00-278a-425f-8125-e725451473e8.webp',
        40,
        1);

INSERT INTO appointments (name, description, img, price, therapist_id)
VALUES ('Арт терапия',
        'Арт-терапията е широкоспектърен метод на психотерапия, който се използва за повлияване (лечение) и психотерапевтична корекция, чрез изкуство и творчество.',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104409/c7f7ba00-278a-425f-8125-e725451473e8.webp',
        30,
        1);

INSERT INTO purchase_appointments(name, img, price, count, appointment_sum)
VALUES ('Арт терапия',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104407/appointment-7_bo02ka.jpg',
        20,
        1,
        20);
INSERT INTO purchase_appointments(name, img, price, count, appointment_sum)

VALUES ('Арт терапия"',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1678104408/appointment-12_fw7s27.jpg',
        30,
        1,
        30);
INSERT INTO purchase_appointments(name, img, price, count, appointment_sum)

VALUES ('Арт терапия',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/appointment-5_haow2h.jpg',
        30,
        1,
        30);
INSERT INTO purchase_appointments(name, img, price, count, appointment_sum)

VALUES ('Арт терапия',
        'https://res.cloudinary.com/dmowldibf/image/upload/v1677589295/samples/MyKnitShop/appointment-1_irpbq2.jpg',
        56,
        1,
        168);


-- users_buy_аppointments
INSERT INTO users_purchase_appointments(user_id, purchase_appointments_id)
VALUES (2,1);
INSERT INTO users_purchase_appointments(user_id, purchase_appointments_id)
VALUES (2,2);
INSERT INTO users_purchase_appointments(user_id, purchase_appointments_id)
VALUES (2,3);
INSERT INTO users_purchase_appointments(user_id, purchase_appointments_id)
VALUES (3,4);
INSERT INTO users_purchase_appointments(user_id, purchase_appointments_id)
VALUES (4,6);
INSERT INTO users_purchase_appointments(user_id, purchase_appointments_id)
VALUES (4,5);
INSERT INTO users_purchase_appointments(user_id, purchase_appointments_id)
VALUES (5,7);

-- requests
INSERT INTO requests (`date_requested`, `client_id`, `request_status`, `request_sum`, `message_id`)
VALUES ('2020-05-01', 2, 'COMPLETED', 320, 2);
INSERT INTO requests (`date_requested`, `client_id`, `request_status`, `request_sum`, `message_id`)
VALUES ('2020-06-01', 3, 'COMPLETED', 30, 1);
INSERT INTO requests (`date_requested`, `client_id`, `request_status`, `request_sum`, `message_id`)
VALUES ('2023-07-01', 4, 'COMPLETED', 191, 3);
INSERT INTO requests (`date_requested`, `client_id`, `request_status`, `request_sum`)
VALUES ('2023-08-01', 5, 'COMPLETED', 12);
INSERT INTO requests (`date_requested`, `client_id`, `request_status`, `request_sum`)
VALUES ('2023-05-01', 2, 'COMPLETED', 320);
INSERT INTO requests (`date_requested`, `client_id`,`request_status`, `request_sum`)
VALUES ('2023-06-01', 2, 'OPEN', 320);
INSERT INTO requests (`date_requested`, `client_id`, `request_status`, `request_sum`)
VALUES ('2023-06-01', 3, 'OPEN', 30);



INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (1, 1);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (1, 2);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (1, 3);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (2, 4);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (3, 5);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (3, 6);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (4, 7);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (5, 1);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (5, 2);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (5, 3);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (6, 1);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (6, 2);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (6, 3);
INSERT INTO appointments_requests(`request_id`,`purchase_appointment_id`)
VALUES (7, 4);


INSERT INTO users_requests(`user_id`,`requests_id`)
VALUES (2, 1);
INSERT INTO users_requests(`user_id`,`requests_id`)
VALUES (3, 2);
INSERT INTO users_requests(`user_id`,`requests_id`)
VALUES (4, 3);
INSERT INTO users_requests(`user_id`,`requests_id`)
VALUES (5, 4);
INSERT INTO users_requests(`user_id`,`requests_id`)
VALUES (2, 5);
INSERT INTO users_requests(`user_id`,`requests_id`)
VALUES (2, 6);
INSERT INTO users_requests(`user_id`,`requests_id`)
VALUES (3, 7);
