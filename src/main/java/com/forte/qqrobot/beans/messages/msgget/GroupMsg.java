/*
 * Copyright (c) 2020. ForteScarlet All rights reserved.
 * Project  simple-robot-core
 * File     GroupMsg.java
 *
 * You can contact the author through the following channels:
 * github https://github.com/ForteScarlet
 * gitee  https://gitee.com/ForteScarlet
 * email  ForteScarlet@163.com
 * QQ     1149159218
 *
 */

package com.forte.qqrobot.beans.messages.msgget;

import com.forte.qqrobot.beans.messages.CodesAble;
import com.forte.qqrobot.beans.messages.FlagAble;
import com.forte.qqrobot.beans.messages.NickOrRemark;
import com.forte.qqrobot.beans.messages.PowerAble;
import com.forte.qqrobot.beans.messages.types.GroupMsgType;

/**
 * 群消息
 **/
public interface GroupMsg extends MsgGet, CodesAble, FlagAble, PowerAble, NickOrRemark {

    /** 获取群消息发送人的qq号 */
    String getQQ();

    @Override
    default String getQQCode(){
        return getQQ();
    }

    /** 获取群消息的群号 */
    String getGroup();

    @Override
    default String getGroupCode(){
        return getGroup();
    }

    /** 获取消息类型 */
    GroupMsgType getType();

    /** flag默认使用id */
    @Override
    default String getFlag(){
        return getId();
    }


}
