package com.troytan.notify.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.troytan.notify.domain.Notify;
import com.troytan.notify.dto.NotifyDto;

public interface NotifyMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tt_notify
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer notifyId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tt_notify
     *
     * @mbg.generated
     */
    int insert(Notify record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tt_notify
     *
     * @mbg.generated
     */
    Notify selectByPrimaryKey(Integer notifyId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tt_notify
     *
     * @mbg.generated
     */
    List<Notify> selectAll();

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table tt_notify
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Notify record);

    int updateBySelective(Notify record);

    List<NotifyDto> listSendNotifyByUserId(Integer userId);

    List<NotifyDto> listReceiveNotifyByUserId(Integer userId);

    Notify selectByUserAndNotify(@Param("userId") Integer userId, @Param("notifyId") Integer notifyId);

}
