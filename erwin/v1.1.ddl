use notify;
#tt_group_user新增主键自增
alter table tt_group_user drop primary key;
alter table tt_group_user add GROUP_USER_ID INTEGER  not null  auto_increment ,add primary key (GROUP_USER_ID);

#tt_notify新增字段
alter table tt_notify add column SCOPE smallint default 1 not null;
alter table tt_notify add column OWNER integer default 1 not null;
ALTER TABLE TT_NOTIFY
ADD FOREIGN KEY R_7 (OWNER) REFERENCES TM_USER (USER_ID);

alter table tt_notify modify column name varchar(100);
alter table tt_group_user modify column nickname varchar(100);
alter table tm_user modify column nickname varchar(100);