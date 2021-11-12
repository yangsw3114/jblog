-- user
desc user;

select * from user;
delete from user;

select id, name, password from user where id="ysw" and password="1234";

-- blog
desc blog;

select * from blog;

insert into blog values("ysw", "테스트입니당", "");
update blog set logo="" where id="ysw";
delete from blog;

-- category
desc category;

select * from category;
update category set name="미분류" where no = 1;
insert into category values(null, "카테고리명", "test임니당!", "ysw");
insert into category values(null, "카테고리명", "test임니당!22", "ysw");

select count(post.no) from category, post where category.no = post.category_no;

-- post
desc post;
select * from post;
select * from post where category_no = 3;
insert into post values(null, "tt", "ttt", "dsf", 3)