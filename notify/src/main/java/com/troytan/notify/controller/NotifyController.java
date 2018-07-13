package com.troytan.notify.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.troytan.notify.domain.Notify;
import com.troytan.notify.dto.ConfirmDto;
import com.troytan.notify.dto.NotifyDto;
import com.troytan.notify.service.ConfirmService;
import com.troytan.notify.service.NotifyService;

@Controller
@Path("/notify")
@Consumes("application/json;charset=utf-8")
@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
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
    @GET
    @Path("/{notifyId}")
    public Notify getNotify(@PathParam("notifyId") Integer notifyId) {
        return notifyService.getNotify(notifyId);
    }

    /**
     * 删除发送通知
     *
     * @author troytan
     * @date 2018年7月13日
     * @param notifyId
     * @return
     */
    @DELETE
    @Path("/sendNotify/{notifyId}")
    public List<NotifyDto> deleteSendNotify(@PathParam("notifyId") Integer notifyId) {
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
    @DELETE
    @Path("/receiveNotify/{notifyId}")
    public List<NotifyDto> deleteReceiveNotify(@PathParam("notifyId") Integer notifyId) {
        return notifyService.deleteReceiveNotify(notifyId);
    }

    /**
     * 获取发送列表
     *
     * @author troytan
     * @date 2018年7月10日
     * @return
     */
    @GET
    @Path("/sendNotifies")
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
    @GET
    @Path("/receiveNotifies")
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
    @POST
    public Notify updateNotify(Notify notify) {
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
    @PUT
    public Notify createNotify(Notify notify) {
        return notifyService.publishNotify(notify);
    }

    /**
     * 接收、浏览通知
     *
     * @author troytan
     * @date 2018年7月11日
     * @param notifyId
     */
    @PUT
    @Path("/access/{notifyId}")
    public boolean accessNotify(@PathParam("notifyId") Integer notifyId) {
        return notifyService.accessNotify(notifyId);
    }

    /**
     * 确认通知
     *
     * @author troytan
     * @date 2018年7月11日
     * @param notifyId
     */
    @POST
    @Path("/confirm/{notifyId}")
    public void confirmNotify(@PathParam("notifyId") Integer notifyId) {
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
    @GET
    @Path("/confirms/{notifyId}")
    public List<ConfirmDto> getConfirms(@PathParam("notifyId") Integer notifyId) {
        return notifyService.listConfirm(notifyId);
    }

    @GET
    @Path("/confirmStatus/{notifyId}")
    public Short getConfirmStatus(@PathParam("notifyId") Integer notifyId) {
        return confirmService.getConfirmStatus(notifyId);
    }

}
