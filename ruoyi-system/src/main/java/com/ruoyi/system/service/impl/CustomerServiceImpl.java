package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.mapper.CustomerMapper;
import com.ruoyi.system.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-21
 */
@Service
public class CustomerServiceImpl implements ICustomerService
{
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Customer selectCustomerById(Long id)
    {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param customer 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer)
    {
        List<Customer> requset = customerMapper.selectCustomerList(customer);
        System.out.printf("selectCustomerList--customer", requset);
        return requset;
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param customer 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long deptId = loginUser.getDeptId();
        Long userId = loginUser.getUserId();
        customer.setUserId(userId);
        customer.setDeptId(deptId);
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param customer 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer)
    {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids)
    {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Long id)
    {
        return customerMapper.deleteCustomerById(id);
    }
}
