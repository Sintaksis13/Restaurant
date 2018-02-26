INSERT INTO public."DISH" ("Name", "Description", "Price")
VALUES ('Чай', 'Очень вкусный чай', 100);
INSERT INTO public."DISH" ("Name", "Description", "Price")
VALUES ('Самса', 'Сочная тандырная самса', 250);
INSERT INTO public."DISH" ("Name", "Description", "Price")
VALUES ('Сок', 'Апельсиновый сок', 150);
INSERT INTO public."DISH" ("Name", "Description", "Price")
VALUES ('Плов', 'Как в Шымкенте', 500);

INSERT INTO public."TABLE" ("Table_ID", "Number_Of_Seats", "Value", "Status", "Reservation_Time")
VALUES (0, 0, 'VIP', 'INACTIVE', 0);
INSERT INTO public."TABLE" ("Number_Of_Seats", "Value", "Status", "Reservation_Time")
VALUES (5, 'VIP', 'ACTIVE', 0);
INSERT INTO public."TABLE" ("Number_Of_Seats", "Value", "Status", "Reservation_Time")
VALUES (2, 'COMMON', 'ACTIVE', 0);

INSERT INTO public."USER" ("Login", "Password", "Email", "Phone_Number")
VALUES ('123', md5('123'), 'post@mail.ru', 87019003040);
INSERT INTO public."USER" ("Login", "Password", "Email", "Phone_Number")
VALUES ('321', md5('321'), 'guest@mail.ru', 87053334455);