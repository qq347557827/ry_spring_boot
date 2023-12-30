package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 customer
 *
 * @author ruoyi
 * @date 2023-11-21
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** customer_id */
    private Long id;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String name;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 微信 */
    @Excel(name = "微信")
    private String wechat;

    /** 微信 */
    @Excel(name = "添加方式")
    private String sourceChannel;

    /** 身高 */
    @Excel(name = "身高")
    private String height;

    /** 身高 */
    @Excel(name = "年龄")
    private String age;

    /** 体重 */
    @Excel(name = "体重")
    private String weight;

    /** 减 */
    @Excel(name = "减")
    private String loseWeight;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区县 */
    @Excel(name = "区县")
    private String district;

    /** 区县 */
    @Excel(name = "区域编码")
    private String areaCode;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailAddress;


    /** 详细地址 */
    @Excel(name = "地址")
    private String address;

    /** 员工ID */

    private Long userId;

    /** 从 sys_user 表中获取的用户名称 */
    @Excel(name = "员工名")
    private String userName;

    /** 部门ID */
    private Long deptId;

    /** 从 sys_dept 表中获取的部门名称 */
    @Excel(name = "部门名")
    private String deptName;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wechatDate;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;



    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setWechat(String wechat)
    {
        this.wechat = wechat;
    }

    public String getWechat()
    {
        return wechat;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getAge()
    {
        return age;
    }
    public void setAge(String age)
    {
        this.age = age;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getHeight()
    {
        return height;
    }
    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getWeight()
    {
        return weight;
    }
    public void setLoseWeight(String loseWeight)
    {
        this.loseWeight = loseWeight;
    }

    public String getLoseWeight()
    {
        return loseWeight;
    }
    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getProvince()
    {
        return province;
    }
    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        String address = "";
        if (province != null) {
            address += " ";
        }
        if (city != null) {
            address += " ";
        }
        if (district != null) {
            address += " ";
        }

        return address + detailAddress;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setWechatDate(Date wechatDate)
    {
        this.wechatDate = wechatDate;
    }

    public Date getWechatDate()
    {
        return wechatDate;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    /** 员工姓名 通过左连接查询 */
    public String getUserName() {
        return userName;
    }
    /** 部门名 通过左连接查询 */

    public String getDeptName() {
        return deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("phone", getPhone())
                .append("wechat", getWechat())
                .append("sourceChannel", getSourceChannel())
                .append("age", getAge())
                .append("height", getHeight())
                .append("weight", getWeight())
                .append("loseWeight", getLoseWeight())
                .append("province", getProvince())
                .append("city", getCity())
                .append("district", getDistrict())
                .append("areaCode", getAreaCode())
                .append("detailAddress", getDetailAddress())
                .append("address", getAddress())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("deptName", getDeptName())
                .append("deptId", getDeptId())
                .append("wechatDate", getWechatDate())
                .append("createDate", getCreateDate())
                .toString();
    }
}
