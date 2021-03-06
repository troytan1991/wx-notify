package com.troytan.notify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.troytan.notify.constant.Constant;
import com.troytan.notify.domain.Confirm;
import com.troytan.notify.domain.Notify;
import com.troytan.notify.domain.NotifyImage;
import com.troytan.notify.domain.NotifyUser;
import com.troytan.notify.dto.ConfirmDto;
import com.troytan.notify.dto.NotifyImageDto;
import com.troytan.notify.dto.NotifyDto;
import com.troytan.notify.repository.ConfirmMapper;
import com.troytan.notify.repository.NotifyImageMapper;
import com.troytan.notify.repository.NotifyMapper;
import com.troytan.notify.repository.NotifyUserMapper;
import com.troytan.notify.util.StringUtils;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private NotifyMapper      notifyMapper;
    @Autowired
    private UserService       userService;
    @Autowired
    private ConfirmMapper     confirmMapper;
    @Autowired
    private NotifyUserMapper  notifyUserMapper;
    @Autowired
    private NotifyImageMapper imageMapper;

    /**
     * 获取单个通知详情
     *
     * @author troytan
     * @date 2018年7月10日
     * @param notifyId
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.NotifyService#getNotify(java.lang.Integer)
     */
    @Override
    public NotifyImageDto getNotify(Integer notifyId) {
        return notifyMapper.selectByUserAndNotify(userService.getCurrentUser(), notifyId);
    }

    /**
     * 更新通知
     *
     * @author troytan
     * @date 2018年7月10日
     * @param notify
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.NotifyService#updateNotify(com.troytan.notify.domain.Notify)
     */
    @Override
    @Transactional
    public Notify updateNotify(NotifyImageDto notify) {
        // tt_notify表
        notify.setUpdateBy(userService.getCurrentUser());
        notifyMapper.updateBySelective(notify);
        // tt_notify_image表
        imageMapper.deleteByNotifyId(notify.getNotifyId());
        for (String url : notify.getImageUrls()) {
            NotifyImage notifyImage = new NotifyImage();
            notifyImage.setNotifyId(notify.getNotifyId());
            notifyImage.setUrl(url);
            notifyImage.setCreateBy(userService.getCurrentUser());

            imageMapper.insert(notifyImage);
        }

        return notify;
    }

    /**
     * 获取发送列表
     *
     * @author troytan
     * @date 2018年7月10日
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.NotifyService#listSendNotify()
     */
    @Override
    public List<NotifyDto> listSendNotify() {
        return decodeName(notifyMapper.listSendNotifyByUserId(userService.getCurrentUser()));
    }

    /**
     * 获取接收列表
     *
     * @author troytan
     * @date 2018年7月10日
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.NotifyService#listReceiveNotify()
     */
    @Override
    public List<NotifyDto> listReceiveNotify() {
        return notifyMapper.listReceiveNotifyByUserId(userService.getCurrentUser());
    }

    /**
     * 发布通知
     *
     * @author troytan
     * @date 2018年7月10日
     * @param notify
     * @return (non-Javadoc)
     * @see com.troytan.notify.service.NotifyService#publishNotify(com.troytan.notify.domain.Notify)
     */
    @Override
    @Transactional
    public Notify publishNotify(NotifyImageDto notifyDto) {
        notifyDto.setOwner(userService.getCurrentUser());
        notifyDto.setCreateBy(userService.getCurrentUser());

        // tt_notify表
        notifyMapper.insert(notifyDto);

        // tt_notify_image表
        List<String> imageUrls = notifyDto.getImageUrls();
        for (String imageUrl : imageUrls) {
            NotifyImage notifyImage = new NotifyImage();
            notifyImage.setNotifyId(notifyDto.getNotifyId());
            notifyImage.setUrl(imageUrl);
            notifyImage.setCreateBy(notifyDto.getCreateBy());

            imageMapper.insert(notifyImage);
        }
        return notifyDto;
    }

    @Override
    public List<ConfirmDto> listConfirm(Integer notifyId) {
        return confirmMapper.listConfirmByNotifyId(notifyId);
    }

    /**
     * 接收通知
     *
     * @author troytan
     * @date 2018年7月11日
     * @param notifyId (non-Javadoc)
     * @return
     * @see com.troytan.notify.service.NotifyService#accessNotify(java.lang.Integer)
     */
    @Override
    @Transactional
    public boolean accessNotify(Integer notifyId) {
        Integer userId = userService.getCurrentUser();
        // 校验是否可以访问
        Notify notify = notifyMapper.selectByUserAndNotify(userId, notifyId);
        if (notify == null) {
            return false;
        }

        // tr_notify_user表新增关联记录
        NotifyUser notifyUser = notifyUserMapper.selectByPrimaryKey(notifyId, userId);
        if (notifyUser == null) {
            notifyUser = new NotifyUser();
            notifyUser.setNotifyId(notifyId);
            notifyUser.setUserId(userId);
            notifyUserMapper.insert(notifyUser);
        }
        // tt_confirm表添加浏览记录
        Confirm confirm = confirmMapper.selectByUserAndNotify(userId, notifyId);
        if (confirm == null) {
            confirm = new Confirm();
            confirm.setNotifyId(notifyId);
            confirm.setCreateBy(userId);
            confirm.setStatus(Constant.CONFIRM_STATUS_VIEW);
            confirmMapper.insert(confirm);
        }

        return true;
    }

    @Override
    public List<NotifyDto> deleteSendNotify(Integer notifyId) {
        // 更新通知状态为0
        notifyMapper.updateStatusByNotifyId(notifyId, Constant.NOTIFY_STATUS_DISABLE, userService.getCurrentUser());
        return decodeName(notifyMapper.listSendNotifyByUserId(userService.getCurrentUser()));
    }

    @Override
    public List<NotifyDto> deleteReceiveNotify(Integer notifyId) {
        // 删除tr_notify_user表记录
        notifyUserMapper.deleteByPrimaryKey(notifyId, userService.getCurrentUser());
        return decodeName(notifyMapper.listReceiveNotifyByUserId(userService.getCurrentUser()));
    }

    /**
     * base64解码name
     *
     * @author troytan
     * @date 2018年9月14日
     * @param list
     * @return
     */
    private List<NotifyDto> decodeName(List<NotifyDto> list) {
        for (NotifyDto notifyDto : list) {
            notifyDto.setName(StringUtils.base64Decode(notifyDto.getName()));
        }

        return list;
    }

}
