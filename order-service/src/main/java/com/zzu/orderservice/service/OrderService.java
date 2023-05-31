package com.zzu.orderservice.service;

import com.zzu.general.interfaces.UserService;
import com.zzu.general.pojo.User;
import com.zzu.orderservice.dao.OrderMapper;
import com.zzu.orderservice.pojo.Order;
import common.serializer.impl.KryoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registry.balancerImpl.RandomLoadBalancer;
import rpcCli.NettyClient;
import rpcCli.RpcClient;
import rpcCli.RpcClientProxy;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RpcClientProxy proxy;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        // 4.返回
        Order order = orderMapper.findById(orderId);

        //进行rpc调用
        UserService userService = proxy.getProxy(UserService.class);
        User user = userService.queryById(order.getUserId());

        order.setUser(user);
        return order;
    }
}
