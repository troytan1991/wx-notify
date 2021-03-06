package com.troytan.notify.repository;

import com.troytan.notify.domain.NotifyUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotifyUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_notify_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(@Param("notifyId") Integer notifyId, @Param("userId") Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_notify_user
     *
     * @mbg.generated
     */
    int insert(NotifyUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_notify_user
     *
     * @mbg.generated
     */
    NotifyUser selectByPrimaryKey(@Param("notifyId") Integer notifyId, @Param("userId") Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_notify_user
     *
     * @mbg.generated
     */
    List<NotifyUser> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_notify_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(NotifyUser record);
}