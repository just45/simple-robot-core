/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  simple-robot-core
 * File     BotManager.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 * QQ     1149159218
 *
 */

package com.forte.qqrobot.bot;

import com.forte.qqrobot.beans.function.PathAssembler;
import com.forte.qqrobot.beans.function.VerifyFunction;

/**
 * Bot信息管理器
 * 其实现类应该存在一个公共的无参构造
 * 可以使用依赖注入的方式自动配置一个BotManager，但是只能存在一个。
 * 且应当使用单例方式注入。
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
public interface BotManager {

    /**
     * 大多数情况下，可能不一定必须指定一个bot，则此方法规定获取一个默认的bot
     * @return 获取一个默认bot
     */
    BotInfo defaultBot();

    /**
     * 设置默认bot的账号信息
     * @param botCode 默认bot的账号信息
     */
    void setDefaultBot(String botCode);

    /**
     * 通过bot的code获取一个Bot的信息
     * 参数有可能为null
     * @param botCode 账号
     * @return bot信息
     */
    BotInfo getBot(String botCode);


    /**
     * 获取全部的bot信息
     * @return bots
     */
    BotInfo[] bots();

    /**
     * 注册一个botInfo。在实现的时候需要注意线程安全问题，概率较小，但是不是没有可能
     * @param info bot信息，作为key的code信息将会从其中获取。info中的各项参数不可为null
     * @return 是否注册成功
     */
    BotInfo registerBot(BotInfo info);

    /**
     * 注册一个bot。
     * @param code 账号, 可以为null
     * @param ip   ip地址
     * @param port 端口号
     * @param path 请求路径
     * @return 是否注册成功
     */
    default BotInfo registerBot(String code, String ip, int port, String path){
        return registerBot(code, getPathAssembler().apply(ip, port, path));
    }

    /**
     * 注册一个bot。
     * @param code      账号, 可以为null
     * @param fullPath  完整路径
     * @return 是否注册成功
     */
    default BotInfo registerBot(String code, String fullPath){
        return registerBot(new BotInfoImpl(code, fullPath, null, null));
    }

    /**
     * 注册一个bot。code为null
     * @param ip        ip地址
     * @param port      端口号
     * @param path      请求路径
     * @return  是否注册成功
     */
    default BotInfo registerBot(String ip, int port, String path){
        return registerBot(null, ip, port, path);
    }

    /**
     * 注册一个bot。code为null
     * @param fullPath 完整路径
     * @return  是否注册成功
     */
    default BotInfo registerBot(String fullPath){
        return registerBot(null, fullPath);
    }

    /**
     * 注销掉一个bot，将其从bot列表中移除。
     * 注意现成安全问题。
     * @param code 要注销掉的bot账号
     * @return 被移除掉的bot
     */
    BotInfo logOutBot(String code);

    /**
     * TODO 刷新一个Bot的账号信息
     * @param code 要刷新的bot账号的信息
     */
    void refreshBot(String code);

    /**
     * TODO 刷新所有bot的信息
     */
    default void refreshBot(){
        for (BotInfo bot : bots()) {
            synchronized (bot){
                refreshBot(bot.getBotCode());
            }
        }
    }

    /**
     * 获取注册用的验证函数
     *
     * @return 验证函数
     */
    VerifyFunction getVerifyFunction();

    /**
     * 获取路径拼接函数
     * @return 拼接函数
     */
    PathAssembler getPathAssembler();



}
