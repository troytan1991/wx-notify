package com.troytan.notify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.troytan.notify.constant.Constant;
import com.troytan.notify.repository.ConfirmMapper;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private ConfirmMapper confirmMapper;
    @Autowired
    private UserService   userService;

    @Override
    public Short getConfirmStatus(Integer notifyId) {
        return confirmMapper.selectByUserAndNotify(userService.getCurrentUser(), notifyId).getStatus();
    }

    /**
     * 确认通知
     *
     * @author troytan
     * @date 2018年7月10日
     * @param notifyId (non-Javadoc)
     * @see com.troytan.notify.service.NotifyService#confirmNotify(java.lang.Integer)
     */
    @Override
    @Transactional
    public void confirmNotify(Integer notifyId) {
        confirmMapper.updateStatusByNotifyId(notifyId, Constant.CONFIRM_STATUS_CONFIRM, userService.getCurrentUser());
    }
}
