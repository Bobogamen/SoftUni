# INSERT INTO onlineshop.users (id, email, name, orders_count, orders_sum, password, registered_on) VALUES (1, 'b_davidov@abv.bg', 'BORIS ILIEV', 0, 0.00, '2792b6ee7537f636ef89fac84cc404c12a96bbff7266007cd2ed1057789f4abae70e7fa7e721b257', '2022-07-25 20:33:44.732125');
# INSERT INTO onlineshop.users (id, email, name, orders_count, orders_sum, password, registered_on) VALUES (2, 'd_borislavova@abv.bg', 'DESISLAVA NIKOLOVA', 0, 0.00, '2feb195c714986b56ca0e3efec9672421edd6b14b237ea007b93e4ded341e4bd8aae5db9bc696727', '2022-07-25 20:34:57.045090');
# INSERT INTO onlineshop.users (id, email, name, orders_count, orders_sum, password, registered_on) VALUES (3, 'test@test.com', 'Tester', 0, 0.00, '40d6ddf6388ba640cb4b11198af05d687b15afce4224651d7b446d809e575c6b393b9c9ad9621791', '2022-07-25 20:35:23.903599');
# INSERT INTO onlineshop.users (id, email, name, orders_count, orders_sum, password, registered_on) VALUES (4, 'client@mail.com', 'Client', 0, 0.00, '40d6ddf6388ba640cb4b11198af05d687b15afce4224651d7b446d809e575c6b393b9c9ad9621791', '2022-07-25 20:35:23.903599');
#
# INSERT INTO onlineshop.users_roles (user_entity_id, roles_id) VALUES (1, 1);
# INSERT INTO onlineshop.users_roles (user_entity_id, roles_id) VALUES (1, 2);
# INSERT INTO onlineshop.users_roles (user_entity_id, roles_id) VALUES (2, 2);
#
# INSERT INTO onlineshop.addresses (id, address_line, name, town, user_entity_id) VALUES (1, 'жк. Надежда 1 бл. 103 вх. А', 'Вкъщи', 'София', 1);
# INSERT INTO onlineshop.addresses (id, address_line, name, town, user_entity_id) VALUES (2, 'офис Еконт Ломско шосе', 'Еконт-Ломско шосе', 'София', 1);
# INSERT INTO onlineshop.addresses (id, address_line, name, town, user_entity_id) VALUES (3, 'жк. Враждебна', 'Работа', 'София', 1);
# INSERT INTO onlineshop.addresses (id, address_line, name, town, user_entity_id) VALUES (4, 'жк. Надежда 1 бл. 103 вх. А1', 'Вкъщи', 'София', 2);
#
#
# INSERT INTO onlineshop.categories (id, name) VALUES (1, 'LEAGUE_OF_LEGENDS');
# INSERT INTO onlineshop.categories (id, name) VALUES (2, 'FLEXI');
#
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUES (1, 'Classic skin of Jinx.
#
# Dimensions:
# Height 15cm', 5, 'Jinx from League of Legends', 'src\\main\\resources\\static\\images\\items\\Jinx from League of Legends_1.jpg', 9.00, 0, 1);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUES (2, 'Classic skin of Soraka
#
# Dimension
# Height 15cm.', 0, 'Soraka from League of Legends', 'src\\main\\resources\\static\\images\\items\\Soraka from League of Legends_2.png', 9.00, 0, 1);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUES (3, 'High noon skin of Lucian
#
# Dimensions:
# Height 20cm', 0, 'Lucian from League of Legends', 'src\\main\\resources\\static\\images\\items\\Lucian from League of Legends_3.jpg', 13.00, 0, 1);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUES (4, 'Hing noon skin of Jhin
#
# Dimensions:
# Height 21cm.', 8, 'Jhin from Leagues of Legends', 'src\\main\\resources\\static\\images\\items\\Jhin from Leagues of Legends_4.jpg', 13.00, 0, 1);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUES (5, '3D printed Rex
#
# Flexible, with hole for key chain.
#
# Dimensions;
# Height 9cm
# Width 8cm
# Depth 3cm', 0, 'Rex', 'src\\main\\resources\\static\\images\\items\\Rex_5.jpg', 4.00, 0, 2);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUES (6, '3D printed Cat
#
# Flexible
#
# Dimensions;
# Height 8cm
# Width 12cm
# Depth 3cm', 0, 'Cat', 'src\\main\\resources\\static\\images\\items\\Cat_6.jpg', 4.00, 0, 2);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUES (7, '3D printed model with very cool wings
#
# Everypart is flexible
#
# Dimensions;
# Height 12cm
# Width 19cm
# Depth 8cm
#
# ', 0, 'Dragon with wings', 'src\\main\\resources\\static\\images\\items\\Dragon with wings_7.jpg', 8.00, 0, 2);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUES (8, 'Dragon Trainer skin of Tristana
#
# Dimensions;
# Height 14cm
# ', 9, 'Tristana from League of Legends', 'src\\main\\resources\\static\\images\\items\\Tristana from League of Legends_8.jpeg', 14.00, 0, 1);
