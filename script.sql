--Вывести все данные, всех пользователей в алфавитном порядке имени
select * from users
order by users.login;

--Вывести ID пользователя, с наибольшим количеством постов
select users.id, count(post.user_id) maximum
from users
join post on post.user_id = users.id 
group by users.id
order by maximum desc 
limit 1;

--Вывести ID пользователя, который поставил наибольшее количество лайков
select users.id, count(likes.user_id) maximum
from users
join likes on likes.user_id = users.id
group by users.id
order by maximum desc 
limit 1;

--Вывести ID поста, с наибольшим количеством лайков
select post.id, count(likes.post_id) maximum
from post
join likes on likes.post_id = post.id
group by post.id
order by maximum desc 
limit 1;

--Выведите все данные, всех постов первого клиента, в порядке убывания даты поста
select * from post
where post.user_id = 1
order by post.created_date desc;
