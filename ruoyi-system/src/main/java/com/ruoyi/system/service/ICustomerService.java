package com.ruoyi.system.service;

import com.ruoyi.system.domain.Customer;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2023-11-21
 */
public interface ICustomerService
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param customer 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增【请填写功能名称】
     *
     * @param customer 【请填写功能名称】
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改【请填写功能名称】
     *
     * @param customer 【请填写功能名称】
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);
}
