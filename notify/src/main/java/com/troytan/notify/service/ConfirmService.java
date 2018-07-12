package com.troytan.notify.service;

public interface ConfirmService {

    Short getConfirmStatus(Integer notifyId);

    void confirmNotify(Integer notifyId);

}
