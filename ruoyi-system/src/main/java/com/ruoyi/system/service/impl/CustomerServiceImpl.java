package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.domain.Guoxue;
import com.ruoyi.system.mapper.CustomerMapper;
import com.ruoyi.system.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-21
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;



    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Customer selectCustomerById(Long id) {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param customer 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer) {
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
    public int insertCustomer(Customer customer) {
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
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids) {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Long id) {
        return customerMapper.deleteCustomerById(id);
    }

    public static HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("authority", "www.dajiazhao.com");
        headers.set("method", "POSR");
        headers.set("path", "/sm/scbz.asp");
        headers.set("accept-language", "zh-CN,zh;q=0.9");
        headers.set("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.set("referer","https://www.dajiazhao.com/sm/scbz.asp");
        headers.set("sec-ch-ua","\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"");
        headers.set("sec-ch-ua-mobile","?0");
        headers.set("sec-ch-ua-platform","Windows");
        headers.set("sec-fetch-dest","document");
        headers.set("sec-fetch-mode","navigate");
        headers.set("sec-fetch-site","same-origin");
        headers.set("sec-fetch-user","?1");
        headers.set("upgrade-insecure-requests","1");
        headers.set("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.3");
//        headers.set("content-type","application/x-www-form-urlencoded");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("Cookie", "ASPSESSIONIDSETCTASA=KEIBPOCCOKJDJHKGNNEPKKDE");
//        headers.add("Cookie", "Hm_lvt_1d379575d2847bbd3081e28d52c4633e=1704302054");
//        headers.add("Cookie", "ASPSESSIONIDQGQDSATA=GNFGKCJAHALEHMDDBFIECNHB");
//        headers.add("Cookie", "Hm_lpvt_1d379575d2847bbd3081e28d52c4633e=1705679431");
        return headers;
    }

    @Override
    public String guoxue(Guoxue guoxue) {
        HttpHeaders headers = createHeaders();
        RestTemplate restTemplate = new RestTemplate();
//        Guoxue guoxue = new Guoxue();
        System.out.printf("122", guoxue);

        String url = "https://www.dajiazhao.com/sm/scbz.asp";
        String requestPayload = "{\"xing\":\"%D5%D4\",\"ming\":\"%C1%E9%D6%A5\",\"xingbie\":\"%C4%D0\",\"nian\":\"1994\",\"yue\":\"3\",\"ri\":\"16\",\"hh\":\"17\",\"mm\":\"16\"}";
//        // 设置消息转换器，指定字符集为 GB2312
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("GB2312")));
////        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("GB2312")));
//
//        String result = restTemplate.postForObject(url, createHttpEntity(requestPayload, headers), String.class);
////        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(
////                url,
////                HttpMethod.POST,
////                createHttpEntity(requestPayload, headers),
////                byte[].class
////        );
//        System.out.println(result);
////        // 从 ResponseEntity 获取响应体
////        byte[] responseBytes = responseEntity.getBody();
////        // 将响应体字节数组按 GB2312 转换成字符串
////        String responseBody = new String(responseBytes, Charset.forName("GB2312"));
//
//
////        String response = responseEntity.getBody();
////        System.out.println(responseBody);
//        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//
//        // 向表单数据中添加字段
////        formData.add("xing", guoxue.getXing()); // 添加单个值的字段
////        formData.add("ming", guoxue.getMing());
////        formData.add("xingbie", guoxue.getXingbie());
////        formData.add("nian", guoxue.getNian());
////        formData.add("yue", guoxue.getYue());
////        formData.add("ri", guoxue.getRi());
////        formData.add("hh", guoxue.getHh());
////        formData.add("mm", guoxue.getMm());
//
//        formData.add("xing", "张"); // 添加单个值的字段
//        formData.add("ming", "三");
//        formData.add("xingbie", "女");
//        formData.add("nian", "1993");
//        formData.add("yue", guoxue.getYue());
//        formData.add("ri", guoxue.getRi());
//        formData.add("hh", guoxue.getHh());
//        formData.add("mm", guoxue.getMm());
//        String k = "xing=%D5%D4&ming=%C1%E9%D6%A5&xingbie=%C4%D0&nian=1994&yue=3&ri=16&hh=17&mm=0";

        HttpEntity<String> requestEntity = new HttpEntity(MessageFormat.format("xing={0}&ming={1}&xingbie={2}&nian={3}&yue={4}&ri={5}&hh={6}&mm={7}","赵", "灵芝", "女", guoxue.getNian(), guoxue.getYue(), guoxue.getRi(), guoxue.getHh(), guoxue.getMm()),headers);
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        System.out.printf("111", requestEntity);
        // 使用 postForObject 方法发送请求并接收响应
        // 注意：这里的 String.class 指定了响应体的类型，你可能需要根据实际情况修改
//        String result = restTemplate.postForObject(url, requestEntity, String.class);
        String result = restTemplate.postForObject(url,requestEntity, String.class);
//        System.out.printf( result);
        return result;
    }

    private HttpEntity<String> createHttpEntity(String requestBody, HttpHeaders headers) {
        return new HttpEntity<>(requestBody, headers);
    }
}
