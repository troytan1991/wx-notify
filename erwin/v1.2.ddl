#新增tt_notify_image表
CREATE TABLE TT_NOTIFY_IMAGE
(
	NOTIFY_IMAGE_ID      INTEGER NOT NULL AUTO_INCREMENT,
	NOTIFY_ID            INTEGER NOT NULL,
	URL                  VARCHAR(100) NOT NULL,
	CREATE_BY            INTEGER NOT NULL,
	CREATE_ON            DATETIME NOT NULL,
	PRIMARY KEY (NOTIFY_IMAGE_ID)
)
 AUTO_INCREMENT = 1;

CREATE UNIQUE INDEX XPKTT_NOTIFY_IMAGE ON TT_NOTIFY_IMAGE
(
	NOTIFY_IMAGE_ID ASC
);
ALTER TABLE TT_NOTIFY_IMAGE
ADD FOREIGN KEY R_9 (NOTIFY_ID) REFERENCES TT_NOTIFY (NOTIFY_ID);
 
#新增表 tt_form表
CREATE TABLE TT_FORM
(
	ID                   INTEGER NOT NULL AUTO_INCREMENT,
	USER_ID              INTEGER NULL,
	FORM_ID              VARCHAR(50) NOT NULL,
	CREATE_BY            INTEGER NOT NULL,
	CREATE_ON            DATETIME NOT NULL,
	PRIMARY KEY (ID)
)
 AUTO_INCREMENT = 1;

CREATE UNIQUE INDEX XPKTT_FORM ON TT_FORM
(
	ID ASC
);

CREATE UNIQUE INDEX XAK1TT_FORM ON TT_FORM
(
	FORM_ID ASC
);