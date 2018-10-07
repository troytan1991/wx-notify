package com.troytan.notify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.troytan.notify.domain.Notify;
import com.troytan.notify.dto.ConfirmDto;
import com.troytan.notify.dto.NotifyDto;
import com.troytan.notify.service.ConfirmService;
import com.troytan.notify.service.NotifyService;
import com.troytan.notify.util.StringUtils;

@RestController
@RequestMapping(path = "/notify", produces = "application/json;charset=UTF-8")
public class NotifyController {

    @Autowired
    private NotifyService  notifyService;
    @Autowired
    private ConfirmService confirmService;

    /**
     * 访问单个通知
     *
     * @author troytan
     * @date 2018年7月10日
     * @param notifyId
     * @return
     */
    @GetMapping("/{notifyId}")
    public Notify getNotify(@PathVariable("notifyId") Integer notifyId) {
        Notify notify = notifyService.getNotify(notifyId);
        notify.setName(StringUtils.base64Decode(notify.getName()));
        return notify;
    }

    /**
     * 删除发送通知
     *
     * @author troytan
     * @date 2018年7月13日
     * @param notifyId
     * @return
     */
    @DeleteMapping("/sendNotify/{notifyId}")
    public List<NotifyDto> deleteSendNotify(@PathVariable("notifyId") Integer notifyId) {
        return notifyService.deleteSendNotify(notifyId);
    }

    /**
     * 删除接收通知
     *
     * @author troytan
     * @date 2018年7月13日
     * @param notifyId
     * @return
     */
    @DeleteMapping("/receiveNotify/{notifyId}")
    public List<NotifyDto> deleteReceiveNotify(@PathVariable("notifyId") Integer notifyId) {
        return notifyService.deleteReceiveNotify(notifyId);
    }

    /**
     * 获取发送列表
     *
     * @author troytan
     * @date 2018年7月10日
     * @return
     */
    @GetMapping("/sendNotifies")
    public List<NotifyDto> getSendNotifies() {
        return notifyService.listSendNotify();
    }

    /**
     * 获取接收列表
     *
     * @author troytan
     * @date 2018年7月10日
     * @return
     */
    @GetMapping("/receiveNotifies")
    public List<NotifyDto> getReceiveNotifies() {
        return notifyService.listReceiveNotify();
    }

    /**
     * 更新通知
     *
     * @author troytan
     * @date 2018年7月10日
     * @param notify
     * @return
     */
    @PostMapping
    public Notify updateNotify(@RequestBody Notify notify) {
        return notifyService.updateNotify(notify);
    }

    /**
     * 发布通知
     *
     * @author troytan
     * @date 2018年7月10日
     * @param notify
     * @return
     */
    @PutMapping
    public Notify createNotify(@RequestBody Notify notify) {
        return notifyService.publishNotify(notify);
    }

    /**
     * 接收、浏览通知
     *
     * @author troytan
     * @date 2018年7月11日
     * @param notifyId
     */
    @PutMapping("/access/{notifyId}")
    public boolean accessNotify(@PathVariable("notifyId") Integer notifyId) {
        return notifyService.accessNotify(notifyId);
    }

    /**
     * 确认通知
     *
     * @author troytan
     * @date 2018年7月11日
     * @param notifyId
     */
    @PostMapping("/confirm/{notifyId}")
    public void confirmNotify(@PathVariable("notifyId") Integer notifyId) {
        confirmService.confirmNotify(notifyId);
    }

    /**
     * 获取确认列表
     *
     * @author troytan
     * @date 2018年7月10日
     * @param notifyId
     * @return
     */
    @GetMapping("/confirms/{notifyId}")
    public List<ConfirmDto> getConfirms(@PathVariable("notifyId") Integer notifyId) {
        return notifyService.listConfirm(notifyId);
    }

    @GetMapping("/confirmStatus/{notifyId}")
    public Short getConfirmStatus(@PathVariable("notifyId") Integer notifyId) {
        return confirmService.getConfirmStatus(notifyId);
    }

}
