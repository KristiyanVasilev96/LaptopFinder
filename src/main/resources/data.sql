insert into user_roles(id, user_role)
values (1, 'ADMIN'),
       (2, 'USER');


insert into users(id, email, first_name, image_url, last_name, password)
values (1, 'admin@abv.bg', 'Kristiyan', null, 'Vasilev',
        '$2a$10$rdO7vSodTnit6toMa370gecft6aMtwAhZH5W7g0vylGz8fEw0ixMC'),
       (2, 'user@abv.bg', 'User', null, 'Userov', '$2a$10$zXppdC2EI0wz.NOsH8ZH7eiZ5sM0mgNsmG7cBqwYv7mFvdGTaZoyu');

INSERT INTO users_user_roles(user_entity_id, user_roles_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);

insert into brands(id, name)
values (1, 'ASUS'),
       (2, 'Lenovo'),
       (3, 'HP'),
       (4, 'DELL'),
       (5, 'Acer'),
       (6, 'Apple');

insert into models(id, category, image_url, name, brand_id)
values (1, 'GAMING',null, 'ROG', 1),
       (2, 'GAMING',null, 'Zenbook', 1),
       (3, 'BUSINESS',null, 'Legion', 2),
       (4, 'BUSINESS',null, 'ThinkPad Yoga', 2),
       (5, 'BUSINESS', null, 'ProBook 450', 3),
       (6, 'BUSINESS',null , 'Pavilion', 3),
       (7, 'BUSINESS',null , 'Vostro 3510', 4),
       (8, 'GAMING',null, 'Predator Helios', 5),
       (9, 'GAMING',null , 'Swift 5', 5),
       (10, 'GAMING',null, 'Nitro 5', 5),
       (11, 'BUSINESS',null, 'MacBook Air', 6),
       (12, 'BUSINESS',null, 'MacBook Pro', 6);

insert into offers(id, image_url, laptop_condition, operation_system, price, ram, weight, model_id, seller_id)
values (1,
        'https://walastech.com/wp-content/uploads/2022/06/asus-rog-strix-g15-2022-philippines-review-4.jpg',
        'GOOD', 'Linux', 1000, 8, 1.7, 1, 1),
       (2,
        'https://s.yimg.com/os/creatr-uploaded-images/2021-01/bf3fd110-6248-11eb-8bc3-e3c2db719baa',
        'EXCELLENT', 'Windows_10', 2200,16, 1.5, 2, 2),

       (3, 'https://www.digitaltrends.com/wp-content/uploads/2022/02/thinkpad-x13-gen-1-featured-image-copy.jpg?p=1', 'EXCELLENT',
        'Windows_10', 899.99, 12, 1.9, 4, 2),
       (4, 'https://cdn.vox-cdn.com/thumbor/7k1fVjau_shOwcfVd5QlIsQjY2M=/0x0:2040x1360/2000x1333/filters:focal(1020x680:1021x681)/cdn.vox-cdn.com/uploads/chorus_asset/file/24164211/226387_HP_Pavilion_Plus_14_MChin_0009.jpg', 'EXCELLENT',
        'Windows_11', 4299.99, 32, 2.1, 6, 2),
       (5, 'https://assets-prd.ignimgs.com/2023/02/17/dellg153-1676656986827.jpg', 'EXCELLENT',
        'Windows_10', 2999.99, 64, 2.200, 4, 2),
       (6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEYZjDhIyAzkzCw-AddVvERc666fvkHMTxYg&usqp=CAU', 'EXCELLENT',
        'Windows_11', 3299.43, 32, 1.9, 8, 1),
       (7, 'https://www.techadvisor.com/wp-content/uploads/2022/06/acer_predator_helios_300_review.jpg?quality=50&strip=all', 'EXCELLENT',
        'Windows_11', 2544.68, 16, 1.3, 8, 2),
       (8, 'https://i.rtings.com/assets/products/l3QhIc1S/apple-macbook-air-13-m1-2020/design-medium.jpg', 'EXCELLENT',
        'Mac_OS', 5299.16, 8, 1.7, 11, 1),
       (9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfVsv9PuVFEe0bhgG4SB6Oi7oqR1pRlUHOoQ&usqp=CAU', 'EXCELLENT',
         'Mac_OS', 3900.32, 24, 1.2, 12, 1),
       (10, 'https://static.techspot.com/articles-info/2405/images/2022-02-06-image-2.jpg', 'EXCELLENT',
        'Windows_11', 4299.12, 16, 1.9, 3, 2),
       (11, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStxVtaaZRnPIhvVOo4vs3GQiTEURTpEd_ioQ&usqp=CAU', 'EXCELLENT',
        'Windows_11', 3299.44, 32, 1.4, 9, 1),
       (12, 'https://www.pcworld.com/wp-content/uploads/2022/12/acer-nitro-5-an515-44-r99q-main-100865204-orig.jpeg?quality=50&strip=all&w=1024', 'EXCELLENT',
        'Windows_11', 4221.99, 32, 1.4, 10, 1);



