package com.troytan.notify.repository;

import java.util.List;

import com.troytan.notify.domain.Customer;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_customer
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer customerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_customer
     *
     * @mbg.generated
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_customer
     *
     * @mbg.generated
     */
    Customer selectByPrimaryKey(Integer customerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_customer
     *
     * @mbg.generated
     */
    List<Customer> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tt_customer
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Customer record);


	void createCustomers();
}