package com.atguigu.daijia.order.handle;


import com.atguigu.daijia.order.service.OrderInfoService;
import jakarta.annotation.PostConstruct;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//监听延迟队列
@Component
public class RedisDelayHandle {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private OrderInfoService orderInfoService;

    @PostConstruct
    public void listener(){
        new Thread(()->{
            while (true){
                RBlockingDeque<Object> queueCancel =
                        redissonClient.getBlockingDeque("queue_cancel");
                try {
                    String orderId = (String) queueCancel.take();
                    if (StringUtils.hasText(orderId)){
                        orderInfoService.orderCancel(Long.parseLong(orderId));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
