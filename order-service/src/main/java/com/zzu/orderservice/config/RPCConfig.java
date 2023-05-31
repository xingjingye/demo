package com.zzu.orderservice.config;

import common.serializer.impl.JsonSerializer;
import common.serializer.impl.KryoSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import registry.balancerImpl.RandomLoadBalancer;
import registry.balancerImpl.RoundRobinLoadBalancer;
import rpcCli.NettyClient;
import rpcCli.RpcClient;
import rpcCli.RpcClientProxy;

/**
 * @program: demo
 * @description:
 * @author: XingJingYe
 * @create: 2023-02-04 23:33
 **/

@Configuration
public class RPCConfig {

    @Bean(name = "proxy")
    public RpcClientProxy getProxy() {
        RpcClient client = new NettyClient(new RoundRobinLoadBalancer());
        client.setSerializer(new JsonSerializer());
        return new RpcClientProxy(client);
    }
}
