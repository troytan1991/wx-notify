
#创建数据库
CREATE SCHEMA  'notify';

#创建账号
create user 'notify'@'%' identified by '2wsx#EDC';
grant all ON notify.* to 'notify'@'%';

#初始化表
use notify;
DROP INDEX XAK1TT_CONFIRM ON TT_CONFIRM;

DROP INDEX XAK1TM_USER ON TM_USER;

DROP TABLE TT_COMMENT;

DROP TABLE TT_GROUP_USER;

DROP TABLE TT_CONFIRM;

DROP TABLE TR_NOTIFY_USER;

DROP TABLE TM_USER;

DROP TABLE TT_NOTIFY;

CREATE TABLE TM_USER
(
	USER_ID              INTEGER NOT NULL AUTO_INCREMENT,
	OPEN_ID              VARCHAR(50) NULL,
	AVATAR_URL           VARCHAR(200) NULL,
	NICKNAME             VARCHAR(20) NULL,
	PROVINCE             VARCHAR(20) NULL,
	GENDER               DECIMAL(1) NULL,
	CREATE_BY            INTEGER NOT NULL,
	CREATE_ON            DATETIME NOT NULL,
	UPDATE_BY            INTEGER NULL,
	UPDATE_ON            DATETIME NULL,
	PRIMARY KEY (USER_ID)
)
 AUTO_INCREMENT = 1;

CREATE UNIQUE INDEX XPKTM_USER ON TM_USER
(
	USER_ID ASC
);

CREATE UNIQUE INDEX XAK1TM_USER ON TM_USER
(
	OPEN_ID ASC
);

CREATE TABLE TR_NOTIFY_USER
(
	NOTIFY_ID            INTEGER NOT NULL,
	USER_ID              INTEGER NOT NULL,
	CREATE_ON            DATETIME NOT NULL,
	PRIMARY KEY (NOTIFY_ID,USER_ID)
);

CREATE UNIQUE INDEX XPKTR_NOTIFY_USER ON TR_NOTIFY_USER
(
	NOTIFY_ID ASC,
	USER_ID ASC
);

CREATE TABLE TT_COMMENT
(
	COMMENT_ID           INTEGER NOT NULL AUTO_INCREMENT,
	CONTENT              VARCHAR(200) NOT NULL,
	NOTIFY_ID            INTEGER NOT NULL,
	CREATE_BY            INTEGER NOT NULL,
	CREATE_ON            DATETIME NOT NULL,
	PRIMARY KEY (COMMENT_ID)
)
 AUTO_INCREMENT = 1;

CREATE UNIQUE INDEX XPKTT_COMMENT ON TT_COMMENT
(
	COMMENT_ID ASC
);

CREATE TABLE TT_CONFIRM
(
	CONFIRM_ID           INTEGER NOT NULL AUTO_INCREMENT,
	STATUS               SMALLINT NULL,
	NOTIFY_ID            INTEGER NOT NULL,
	CREATE_BY            INTEGER NOT NULL,
	CREATE_ON            DATETIME NOT NULL,
	UPDATE_BY            INTEGER NULL,
	UPDATE_ON            DATETIME NULL,
	PRIMARY KEY (CONFIRM_ID)
)
 AUTO_INCREMENT = 1;

CREATE UNIQUE INDEX XPKTT_CONFIRM ON TT_CONFIRM
(
	CONFIRM_ID ASC
);

CREATE UNIQUE INDEX XAK1TT_CONFIRM ON TT_CONFIRM
(
	NOTIFY_ID ASC,
	CREATE_BY ASC
);

CREATE TABLE TT_GROUP_USER
(
	NICKNAME             VARCHAR(20) NULL,
	STATUS               SMALLINT NULL,
	CREATE_BY            INTEGER NOT NULL,
	CREATE_ON            DATETIME NOT NULL,
	UPDATE_BY            INTEGER NULL,
	UPDATE_ON            DATETIME NULL,
	GROUP_ID             VARCHAR(30) NOT NULL,
	OPEN_ID              VARCHAR(50) NOT NULL,
	PRIMARY KEY (GROUP_ID,OPEN_ID)
);

CREATE UNIQUE INDEX XPKTT_GROUP_USER ON TT_GROUP_USER
(
	GROUP_ID ASC,
	OPEN_ID ASC
);

CREATE TABLE TT_NOTIFY
(
	NOTIFY_ID            INTEGER NOT NULL AUTO_INCREMENT,
	TITLE                VARCHAR(30) NULL,
	CONTENT              VARCHAR(200) NULL,
	NAME                 VARCHAR(20) NOT NULL,
	STATUS               SMALLINT NOT NULL,
	GROUP_ID             VARCHAR(30) NULL,
	CREATE_BY            INTEGER NOT NULL,
	CREATE_ON            DATETIME NOT NULL,
	UPDATE_BY            INTEGER NULL,
	UPDATE_ON            DATETIME NULL,
	PRIMARY KEY (NOTIFY_ID)
)
 AUTO_INCREMENT = 1;

CREATE UNIQUE INDEX XPKTT_NOTIFY ON TT_NOTIFY
(
	NOTIFY_ID ASC
);

ALTER TABLE TR_NOTIFY_USER
ADD FOREIGN KEY R_4 (NOTIFY_ID) REFERENCES TT_NOTIFY (NOTIFY_ID);

ALTER TABLE TR_NOTIFY_USER
ADD FOREIGN KEY R_6 (USER_ID) REFERENCES TM_USER (USER_ID);

ALTER TABLE TT_COMMENT
ADD FOREIGN KEY R_1 (NOTIFY_ID) REFERENCES TT_NOTIFY (NOTIFY_ID);

ALTER TABLE TT_CONFIRM
ADD FOREIGN KEY R_3 (NOTIFY_ID) REFERENCES TT_NOTIFY (NOTIFY_ID);