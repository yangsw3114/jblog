-- user
desc user;

select * from user;
delete from user;

select id, name, password from user where id="ysw" and password="1234";

-- blog
desc blog;

select * from blog;

insert into blog values("ysw", "테스트입니당", "");

delete from blog;

-- category
desc category;

select * from category;

insert into category values(null, "카테고리명", "test임니당!", "ysw");
insert into category values(null, "카테고리명", "test임니당!22", "ysw");