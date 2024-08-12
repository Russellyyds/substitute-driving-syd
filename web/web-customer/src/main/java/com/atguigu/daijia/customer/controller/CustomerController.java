package com.atguigu.daijia.customer.controller;

import com.atguigu.daijia.common.constant.RedisConstant;
import com.atguigu.daijia.common.execption.GuiguException;
import com.atguigu.daijia.common.login.GuiguLogin;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.common.util.AuthContextHolder;
import com.atguigu.daijia.customer.client.CustomerInfoFeignClient;
import com.atguigu.daijia.customer.service.CustomerService;
import com.atguigu.daijia.customer.service.OrderService;
import com.atguigu.daijia.model.form.customer.SubmitOrderForm;
import com.atguigu.daijia.model.form.customer.UpdateWxPhoneForm;
import com.atguigu.daijia.model.vo.customer.CustomerLoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "客户API接口管理")
@RestController
@RequestMapping("/customer")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CustomerInfoFeignClient customerInfoFeignClient;
    @Autowired
    private OrderService orderService;
    @Operation(summary = "小程序授权登录")
    @GetMapping("/login/{code}")
    public Result<String> login(@PathVariable String code) {
        return Result.ok(customerService.login(code));
    }

//    @Operation(summary = "获取登录用户的信息")
//    @GetMapping("/getCustomerLoginInfo")
//    public Result<CustomerLoginVo> getConsumerLoginInfo(@RequestHeader(value = "token") String token) {
//        CustomerLoginVo  customerLoginVo=customerService.getCustomerLoginInfo(token);
//        return Result.ok(customerLoginVo);
//    }
    @Operation(summary = "获取登录用户的信息")
    @GuiguLogin
    @GetMapping("/getCustomerLoginInfo")
    public Result<CustomerLoginVo> getConsumerLoginInfo() {
        Long userId = AuthContextHolder.getUserId();
        CustomerLoginVo  customerLoginVo=customerService.getCustomerLoginInfo(userId);
        return Result.ok(customerLoginVo);
    }
    @Operation(summary = "更新用户微信手机号")
    @PostMapping("/updateWxPhone")
    public Result updateWxPhone(@RequestBody UpdateWxPhoneForm updateWxPhoneForm){
        updateWxPhoneForm.setCustomerId(AuthContextHolder.getUserId());
        Boolean result=customerService.updateWxPhone(updateWxPhoneForm);
        return Result.ok(result);
    }
    @Operation(summary = "乘客下单")
    @GuiguLogin
    @PostMapping("/submitOrder")
    public Result<Long> submitOrder(@RequestBody SubmitOrderForm submitOrderForm) {
        submitOrderForm.setCustomerId(AuthContextHolder.getUserId());
        return Result.ok(orderService.submitOrder(submitOrderForm));
    }


}

