package com.troytan.notify.service;

import java.util.List;

import com.troytan.notify.domain.Notify;
import com.troytan.notify.dto.ConfirmDto;
import com.troytan.notify.dto.NotifyDto;

public interface NotifyService {

    Notify getNotify(Integer notifyId);

    Notify updateNotify(Notify notify);

    List<NotifyDto> listSendNotify();

    List<NotifyDto> listReceiveNotify();

    Notify publishNotify(Notify notify);

    List<ConfirmDto> listConfirm(Integer notifyId);

    boolean accessNotify(Integer notifyId);

    List<NotifyDto> deleteSendNotify(Integer notifyId);

    List<NotifyDto> deleteReceiveNotify(Integer notifyId);

}
