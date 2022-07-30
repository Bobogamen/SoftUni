# # Users
# INSERT INTO onlineshop.users (id, email, name, orders_count, orders_sum, password, registered_on) VALUES (1, 'b_davidov@abv.bg', 'BORIS ILIEV', 0, 0.00, '2792b6ee7537f636ef89fac84cc404c12a96bbff7266007cd2ed1057789f4abae70e7fa7e721b257', '2022-07-25 20:33:44.732125');
# INSERT INTO onlineshop.users (id, email, name, orders_count, orders_sum, password, registered_on) VALUES (2, 'd_borislavova@abv.bg', 'DESISLAVA NIKOLOVA', 0, 0.00, '2feb195c714986b56ca0e3efec9672421edd6b14b237ea007b93e4ded341e4bd8aae5db9bc696727', '2022-07-25 20:34:57.045090');
# INSERT INTO onlineshop.users (id, email, name, orders_count, orders_sum, password, registered_on) VALUES (3, 'test@test.com', 'Tester', 0, 0.00, '40d6ddf6388ba640cb4b11198af05d687b15afce4224651d7b446d809e575c6b393b9c9ad9621791', '2022-07-25 20:35:23.903599');
# INSERT INTO onlineshop.users (id, email, name, orders_count, orders_sum, password, registered_on) VALUES (4, 'client@mail.com', 'Client', 0, 0.00, '40d6ddf6388ba640cb4b11198af05d687b15afce4224651d7b446d809e575c6b393b9c9ad9621791', '2022-07-25 20:35:23.903599');
#
#
#
# # ADMIN
# INSERT INTO onlineshop.users_roles (user_entity_id, roles_id) VALUES (1, 1);
# INSERT INTO onlineshop.users_roles (user_entity_id, roles_id) VALUES (1, 2);
#
#
# # MODERATOR
# INSERT INTO onlineshop.users_roles (user_entity_id, roles_id) VALUES (2, 2);
#
# #ITEMS
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUE (1, 'Description for Item1', 0, 'Item1', null, 1, 0, 2);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUE (2, 'Description for Item2', 0, 'Item2', null, 1, 0, 2);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUE (3, 'Description for Item3', 0, 'Item3', null, 1, 0, 2);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUE (4, 'Description for Item4', 0, 'Item4', null, 1, 0, 2);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUE (5, 'Description for Item5', 0, 'Item5', null, 1, 0, 2);
# INSERT INTO onlineshop.items (id, description, discount, name, picture, price, times_ordered, category_id) VALUE (6, 'Description for Item6', 0, 'Item6', null, 1, 0, 2);
