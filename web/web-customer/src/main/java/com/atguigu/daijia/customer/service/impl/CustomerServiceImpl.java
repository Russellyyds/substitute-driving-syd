package com.atguigu.daijia.customer.service.impl;

import com.atguigu.daijia.common.constant.RedisConstant;
import com.atguigu.daijia.common.execption.GuiguException;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.customer.client.CustomerInfoFeignClient;
import com.atguigu.daijia.customer.service.CustomerService;
import com.atguigu.daijia.model.form.customer.UpdateWxPhoneForm;
import com.atguigu.daijia.model.vo.customer.CustomerLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerServiceImpl implements CustomerService {

    //注入远程调度的接口
    @Autowired
    private CustomerInfoFeignClient customerInfoFeignClient;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String login(String code) {
        Result<Long> longResult = customerInfoFeignClient.login(code);
        Integer resultCode = longResult.getCode();
        if (resultCode != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        Long userId = longResult.getData();
        if (userId == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        redisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_KEY_PREFIX + token,
                userId.toString(),
                RedisConstant.USER_LOGIN_KEY_TIMEOUT,
                TimeUnit.SECONDS);
        return token;


    }

//    @Override
//    public CustomerLoginVo getCustomerLoginInfo(String token) {
//        String consumerId = (String) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_KEY_PREFIX + token);
//        if (!StringUtils.hasText(consumerId)) {
//            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
//        }
//        Result<CustomerLoginVo> customerLoginInfoResult = customerInfoFeignClient.getCustomerLoginInfo(Long.parseLong(consumerId));
//        Integer code = customerLoginInfoResult.getCode();
//        if (code != 200) {
//            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
//        }
//        CustomerLoginVo customerLoginVo = customerLoginInfoResult.getData();
//        if (customerLoginVo == null) {
//            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
//        }
//
//        return customerLoginVo;
//    }

    @Override
    public CustomerLoginVo getCustomerLoginInfo(Long userId) {
        Result<CustomerLoginVo> customerLoginInfoResult = customerInfoFeignClient.getCustomerLoginInfo(userId);
        Integer code = customerLoginInfoResult.getCode();
        if (code != 200) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        CustomerLoginVo customerLoginVo = customerLoginInfoResult.getData();
        if (customerLoginVo == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

        return customerLoginVo;
    }

    @Override
    public Boolean updateWxPhone(UpdateWxPhoneForm updateWxPhoneForm) {
        Result<Boolean> booleanResult = customerInfoFeignClient.updateWxPhoneNumber(updateWxPhoneForm);
        return true;
    }

}
