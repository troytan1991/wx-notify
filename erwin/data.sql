#tm_user
INSERT INTO `notify`.`tm_user`(`OPEN_ID`,`AVATAR_URL`,`NICKNAME`,`PROVINCE`,`GENDER`,`CREATE_BY`,`CREATE_ON`)VALUES('oK60f0TKcoKvXlyuuV18tIDru8Mc', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKNWWKzibkQWlmvhG6gKKGCWxVjXIAyCq2XWq1r21HjTrU6T8k7jcibQzBgD4y8u4vCR2jhDiccEll5g/0',  'troytan', 'Shanghai', '1', 1, now());

#tt_notify
INSERT INTO `notify`.`tt_notify`(`TITLE`,`CONTENT`,`NAME`,`STATUS`,`GROUP_ID`,`CREATE_BY`,`CREATE_ON`)VALUES ('周末露营啦','舟山群岛，请大家自备露营设备与食物','troytan',1,'tGhL3b4jeAzJabqJDbJZY4KgWvch8',1,now());
INSERT INTO `notify`.`tt_notify`(`TITLE`,`CONTENT`,`NAME`,`STATUS`,`GROUP_ID`,`CREATE_BY`,`CREATE_ON`)VALUES ('暑假补课通知','学校将于6月10-7月10日补课，请各位家长确认','troytan',1,'tGhL3b4jeAzJabqJDbJZY4KgWvch8',1,now());

#tt_comment
INSERT INTO `notify`.`tt_comment`
(`CONTENT`,
`NOTIFY_ID`,
`CREATE_BY`,
`CREATE_ON`)
VALUES('不错，确认参加了',1,1,now());

#tt_confirm
INSERT INTO `notify`.`tt_confirm`
(`STATUS`,
`NOTIFY_ID`,
`CREATE_BY`,
`CREATE_ON`)
VALUES(2,1,1,now());

#tt_group_user
INSERT INTO `notify`.`tt_group_user`
(`NICKNAME`,
`STATUS`,
`CREATE_BY`,
`CREATE_ON`,
`GROUP_ID`,
`OPEN_ID`)
VALUES('2班班主任',1,1,now(),'tGhL3b4jeAzJabqJDbJZY4KgWvch8','oK60f0TKcoKvXlyuuV18tIDru8Mc');