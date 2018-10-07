package com.troytan.notify.service;

import java.util.List;

import com.troytan.notify.domain.Notify;
import com.troytan.notify.dto.ConfirmDto;
import com.troytan.notify.dto.NotifyImageDto;
import com.troytan.notify.dto.NotifyDto;

public interface NotifyService {

    NotifyImageDto getNotify(Integer notifyId);

    Notify updateNotify(NotifyImageDto notify);

    List<NotifyDto> listSendNotify();

    List<NotifyDto> listReceiveNotify();

    Notify publishNotify(NotifyImageDto notifyDto);

    List<ConfirmDto> listConfirm(Integer notifyId);

    boolean accessNotify(Integer notifyId);

    List<NotifyDto> deleteSendNotify(Integer notifyId);

    List<NotifyDto> deleteReceiveNotify(Integer notifyId);

}
