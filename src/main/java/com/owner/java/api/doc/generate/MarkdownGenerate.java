/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.generate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.owner.java.api.doc.constants.CommonConstants;
import com.owner.java.api.doc.enums.YesOrNo;
import com.owner.java.api.doc.model.AttributeNode;
import com.owner.java.api.doc.model.InterfaceNode;
import com.owner.java.api.doc.model.MethodNode;
import com.owner.java.api.doc.model.ReqRespNode;

/**
 * description:markdonw生成类
 * Author	Date	Changes
 * zhuangjianfa  2018年3月8日 Created
 */
@Component("markdownGenerate")
public class MarkdownGenerate extends AbstractGenerate{
    
    @Value("${java.doc.application}")
    private String applicationName;
    
    /* (non-Javadoc)
     * @see com.owner.java.api.doc.generate.AbstractGenerate#generateFile(java.util.List, java.util.Map, java.util.Map)
     */
    @Override
    public void generateFile(List<InterfaceNode> interfaceNodeList, Map<String, ReqRespNode> reqMap,
                             Map<String, ReqRespNode> respMap) {
        try{
        interfaceNodeList.forEach(interfaceNode -> {
            String serviceName = String.format(CommonConstants.SERVICEMARK, interfaceNode.getInterfaceName());
            List<MethodNode> mothodNodeList = interfaceNode.getMethodNodeList();
            mothodNodeList.forEach(methodNode->{
                StringBuilder markdown = new StringBuilder();
                String methodDescript = String.format(CommonConstants.DESCRIPTMARK,methodNode.getDescription());
                String methodName = String.format(CommonConstants.METHODMARK, methodNode.getMethodName());
                String applicatonName = String.format(CommonConstants.APPLICATIONMARK, applicationName);
                String pathName = String.format(CommonConstants.PATHMARK, methodNode.getPath());
                String methodTypeName = String.format(CommonConstants.METHODTYPEMARK, methodNode.getMethodType());
                String createdBy = String.format(CommonConstants.CREATEDBYMARK, methodNode.getCreatedBy());
                String created = String.format(CommonConstants.CREATEDMARK, methodNode.getCreated());
                markdown.append(methodDescript).append(serviceName).append(methodName).append(applicatonName).append(pathName).append(methodTypeName).append(createdBy).append(created);
                //入参markdown代码生成
                if(!StringUtils.isEmpty(methodNode.getRequestName())){
                    markdown.append(CommonConstants.REQMARK);
                    List<String> typeList = new ArrayList<>();
                    //普通类型,如String等处理
                    if(methodNode.getRequestName().contains("-")){
                        String[] javaType = methodNode.getRequestName().split("-");
                        markdown.append(CommonConstants.REQTHMARK);
                        markdown.append(String.format(CommonConstants.REQTDMARK, javaType[1],javaType[0],YesOrNo.YES.getValue(),"",""));
                    }else{
                        typeList.add(methodNode.getRequestName());
                        buildObjList(methodNode.getRequestName(),reqMap,typeList);
                        for(String str : typeList){
                            buildAttr(str,reqMap,markdown,true);
                        }
                    }
                }
                //出参markdown代码生成
                buildResult(markdown,methodNode.getResponseName(),methodNode.getDataType());
                if(!StringUtils.isEmpty(methodNode.getDataType())){
                    List<String> typeList = new ArrayList<>();
                    if(!StringUtils.isEmpty(methodNode.getDataType())){
                        typeList.add(methodNode.getDataType());
                        buildObjList(methodNode.getDataType(),respMap,typeList);
                        for(String str : typeList){
                            buildAttr(str,respMap,markdown,false);
                        }
                    }
                }
                System.out.println(markdown.toString());
                try {
                    //获取最后个小数位置
                    Integer index = interfaceNode.getInterfaceName().lastIndexOf(".");
                    //文件路径:配置路径+接口名+方法(文件)
                    createFile(interfaceNode.getInterfaceName().substring(index+1),methodNode.getMethodName(),markdown.toString());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        });
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    /**
     * 
     * description: 构建属性 
     * @param name
     * @param reqRespMap
     * @param markdown
     * createdBy:zhuangjianfa           
     * created:2018年3月9日
     */
    private void buildAttr(String name,Map<String, ReqRespNode> reqRespMap,StringBuilder markdown,boolean isReq){
        ReqRespNode reqRespNode = reqRespMap.get(name);
        if(null!=reqRespNode){
            markdown.append(reqRespNode.getFullName()).append("\r\n");
            if(isReq)
                markdown.append(CommonConstants.REQTHMARK);
            else
                markdown.append(CommonConstants.RESPTHMARK);
            for(AttributeNode attribute : reqRespNode.getAttrList()){
                if(isReq)
                    markdown.append(String.format(CommonConstants.REQTDMARK, attribute.getAttrName(),attribute.getAttrType(),attribute.getIsRequired(),attribute.getDescript(),attribute.getRemark()));
                else
                    markdown.append(String.format(CommonConstants.RESPTDMARK, attribute.getAttrName(),attribute.getAttrType(),attribute.getDescript(),attribute.getRemark()));
            }
            if(!StringUtils.isEmpty(reqRespNode.getExtendsClass())){
                buildExtendAttr(reqRespNode.getExtendsClass(),reqRespMap,markdown,isReq);
            }
        }
    }
    
    /**
     * 
     * description: 追加继承的属性
     * @param name
     * @param reqRespMap
     * @param markdown
     * @param isReq
     * createdBy:zhuangjianfa           
     * created:2018年3月9日
     */
    private void buildExtendAttr(String name,Map<String, ReqRespNode> reqRespMap,StringBuilder markdown,boolean isReq){
        ReqRespNode reqRespNode = reqRespMap.get(name);
        if(null!=reqRespNode){
            for(AttributeNode attribute : reqRespNode.getAttrList()){
                if(isReq)
                    markdown.append(String.format(CommonConstants.REQTDMARK, attribute.getAttrName(),attribute.getAttrType(),attribute.getIsRequired(),attribute.getDescript(),attribute.getRemark()));
                else
                    markdown.append(String.format(CommonConstants.RESPTDMARK, attribute.getAttrName(),attribute.getAttrType(),attribute.getDescript(),attribute.getRemark()));
            }
            if(!StringUtils.isEmpty(reqRespNode.getExtendsClass())){
                buildExtendAttr(reqRespNode.getExtendsClass(),reqRespMap,markdown,isReq);
            }
        }
    }
    
    /**
     * 
     * description: 构建属性对象(复要对象属性)列表对方法
     * @param name
     * @param reqRespMap
     * @param typeList
     * createdBy:zhuangjianfa           
     * created:2018年3月9日
     */
    private void buildObjList(String name,Map<String, ReqRespNode> reqRespMap,List<String> typeList){
        ReqRespNode reqRespNode = reqRespMap.get(name);
        if(null!=reqRespNode && null!=reqRespNode.getObjList()){
            for(String type : reqRespNode.getObjList()){
                if(!typeList.contains(type)){
                    typeList.add(type);
                }
                if(reqRespMap.containsKey(type)){
                    buildObjList(type,reqRespMap,typeList);
                }
            }
        }
    }
    
    /**
     * 
     * description: 生成Result的Markdown的代码
     * @param markdown
     * @param responseName
     * @param dataType
     * createdBy:zhuangjianfa           
     * created:2018年3月9日
     */
    private void buildResult(StringBuilder markdown,String responseName,String dataType){
        markdown.append(String.format(CommonConstants.RESPMARK, responseName));
        markdown.append(CommonConstants.RESPTHMARK);
        markdown.append(String.format(CommonConstants.RESPTDMARK, "code","Integer","返回编码,0为正常","默认为0,其它自定义"));
        markdown.append(String.format(CommonConstants.RESPTDMARK, "message","String","返回消息","默认为操作成功"));
        markdown.append(String.format(CommonConstants.RESPTDMARK, "totalNum","Integer","分页时使用,存放总数",""));
        markdown.append(String.format(CommonConstants.RESPTDMARK, "data",dataType,"返回数据对象",""));
        markdown.append(String.format(CommonConstants.RESPTDMARK, "error","Integer","返回编码,0为正常,兼容原APP的返回值","默认为0,其它自定义"));
    }
}
