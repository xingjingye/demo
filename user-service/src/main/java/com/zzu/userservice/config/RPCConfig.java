package com.zzu.userservice.config;

import com.zzu.general.interfaces.UserService;
import common.serializer.impl.JsonSerializer;
import common.serializer.impl.KryoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import rpcServer.NettyServer;
import rpcServer.RpcServer;

import javax.annotation.PostConstruct;

/**
 * @program: demo
 * @description:
 * @author: XingJingYe
 * @create: 2023-02-04 23:07
 **/

@Configuration
public class RPCConfig {

    @Autowired
    UserService userService;

    @Value("${host}")
    private String host;

    @Value("#{${port}}")
    private int port;

    @PostConstruct
    public void register() {
        RpcServer rpcServer = new NettyServer(host,port);
        rpcServer.setSerializer(new KryoSerializer());
        rpcServer.publishService(userService,UserService.class);
    }
}
