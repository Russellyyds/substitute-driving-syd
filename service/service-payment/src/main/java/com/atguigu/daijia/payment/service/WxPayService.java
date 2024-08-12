package com.atguigu.daijia.payment.service;

import com.atguigu.daijia.model.form.payment.PaymentInfoForm;
import com.atguigu.daijia.model.vo.payment.WxPrepayVo;
import jakarta.servlet.http.HttpServletRequest;

public interface WxPayService {

    WxPrepayVo createWxPayment(PaymentInfoForm paymentInfoForm);

    //查询支付状态
    Boolean queryPayStatus(String orderNo);

    //微信支付成功后，进行的回调
    void wxnotify(HttpServletRequest request);

    //支付成功后续处理
//    @GlobalTransactional
    void handleOrder(String orderNo);
}
