/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.constants;


/**
 * description:公共常量类
 * Author	Date	Changes
 * zhuangjianfa  2018年3月9日 Created
 */
public interface CommonConstants {
    
    String DESCRIPTMARK = "接口描述：%s\r\n";
    
    String SERVICEMARK = "接口名称：%s\r\n";
    
    String METHODMARK = "调用方法：%s\r\n";
    
    String APPLICATIONMARK = "应用：%s\r\n";
    
    String PATHMARK = "路径：%s\r\n";
    
    String METHODTYPEMARK = "方法类型：%s\r\n";
    
    String CREATEDBYMARK = "创建人：   %s\r\n";
    
    String CREATEDMARK = "创建时间：%s\r\n";
    
    String REQMARK = "入参：\r\n";
    
    String REQTHMARK = "||名称||类型||是否必填||说明||备注||\r\n";
    
    String REQTDMARK = "||%s||%s||%s||%s||%s||\r\n";
    
    String RESPMARK = "出参：\r\n%s\r\n";
    
    String RESPTHMARK = "||名称||类型||说明||备注||\r\n";
    
    String RESPTDMARK = "||%s||%s||%s||%s||\r\n";
    
    /**
     * 必填的判断
     */
    String REQUIREDREGX     = "(?:.*@NotEmpty.*|.*@NotBlank.*|.*@NotNull.*)";

    /**
     * 属性行的判断
     */
    String ATTRREGX     = ".*private.*;$";

    /**
     * 属性类型正则,普通类型或泛型或数组
     */
    String ATTRTYPEREGX = "^[a-zA-Z].*(?:[a-z]|>|])$";
    /**
     * 属性名称判断
     */
    String ATTRNAMEREGX = "^[a-z][A-Za-z].*(?:[a-z]|;)$";

    /**
     * java数据类型判断
     */
    String DATATYPEREGX     = "(?:long|int|integer|string|short|byte|character|char|float|double|boolean|bigdecimal|date)";

    /**
     * 结束标志，到get,set标识
     */
    String ENDREGX          = "^.*public.*$";
    
    /**
     * 路径排除字符
     */
    String PATH     = "(@RequestMapping|\\(|\\))";
}
