package com.troytan.notify.repository;

import java.util.List;

import com.troytan.notify.domain.Fitment;
import com.troytan.notify.dto.FitmentDto;

public interface FitmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_fitment
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer fitmentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_fitment
     *
     * @mbg.generated
     */
    int insert(Fitment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_fitment
     *
     * @mbg.generated
     */
    Fitment selectByPrimaryKey(Integer fitmentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_fitment
     *
     * @mbg.generated
     */
    List<Fitment> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_fitment
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Fitment record);

	List<FitmentDto> listByDetail();

}