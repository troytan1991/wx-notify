
use notify;
#new field
update tt_notify set owner=create_by;

#nickname
update tt_notify set name = to_base64(name);
update tt_group_user set nickname=to_base64(nickname);
update tm_user set nickname=to_base64(nickname);

