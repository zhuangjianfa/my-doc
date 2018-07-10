/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.analyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.owner.common.constants.CommonConStants;
import com.owner.java.api.doc.constants.CommonConstants;
import com.owner.java.api.doc.enums.YesOrNo;
import com.owner.java.api.doc.model.AttributeNode;
import com.owner.java.api.doc.model.ReqRespNode;

/**
 * description:读取REQ,RESP文件，获取ReqRespNode
 * Author	Date	Changes
 * zhuangjianfa  2018年3月8日 Created
 */
@Component
public class ReqRespNodeFile {
    
    @Value("${java.doc.requestPath}")
    private String requestPath;
    
    @Value("${java.doc.responsePath}")
    private String responsePath;
    
    
    /**
     * 
     * description: 获取request 
     * @return
     * @throws IOException
     * createBy:zhuangjianfa            
     * createDate:2018年3月8日
     */
    public Map<String,ReqRespNode> getReqNodes() throws IOException{
        try{
            return readReqResp(requestPath);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
        
    }
    
    /**
     * 
     * description: 获取response
     * @return
     * @throws IOException
     * createBy:zhuangjianfa            
     * createDate:2018年3月8日
     */
    public Map<String,ReqRespNode> getRespNodes() throws IOException{
        try{
            return readReqResp(responsePath);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    private Map<String,ReqRespNode> readReqResp(String path) throws IOException {
        InputStreamReader reader = null;
        BufferedReader br = null;
        String name = "";
        int num = 0;
        Map<String,ReqRespNode> map  = new HashMap<String,ReqRespNode>();
        try {
            File file = new File(path);
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                for (File f : subFiles) {
                    name = f.getName().replace(".java","");
                    ReqRespNode requestModel = new ReqRespNode();
                    reader = new InputStreamReader(new FileInputStream(f));
                    br = new BufferedReader(reader);
                    String line;
                    AttributeNode attrModel = null;
                    while (null != (line=br.readLine())) {
                        num++;
                        if (line.equals("") || line.indexOf("/")!=-1 || line.indexOf("static")!=-1) {
                            continue;
                        }
                        if(null==attrModel){
                            attrModel = new AttributeNode();
                        }
                        //取到包名+文件名=带包路径接口全称
                        if (line.indexOf("package") != -1) {
                            String packagePath = line.replace("package", "").replace(";",".").trim();
                            requestModel.setFullName(packagePath+name);
                            continue;
                        }
                        if (line.indexOf("extends")!=-1) {
                            requestModel.setExtendsClass(line.split("extends")[1].trim().split(" ")[0].replace("{",""));
                            continue;
                        }
                        if (line.indexOf("description:") != -1) {
                            String[] descriptArray =line.split("description:");
                            if(descriptArray.length > 1){
                                attrModel.setDescript(descriptArray[1].trim());
                            }
                            continue;
                        }
                        if (line.indexOf("remark:") != -1) {
                            String[] remarkArray =line.split("remark:");
                            if(remarkArray.length > 1){
                                attrModel.setRemark(remarkArray[1].trim());
                            }
                            continue;
                        }
                        if (Pattern.matches(CommonConstants.REQUIREDREGX, line)) {
                            attrModel.setIsRequired(YesOrNo.YES.getValue());
                            continue;
                        }

                        if (Pattern.matches(CommonConstants.ATTRREGX, line)) {
                            String[] array = line.split(" ");
                            boolean attrType = false;
                            boolean attrName = false;
                            for (String str : array) {
                                if ("".equals(str)) {
                                    continue;
                                }
                                if ("private".equals(str.trim())) {
                                    attrType = true;
                                    continue;
                                }
                                //取出类型
                                if (attrType && Pattern.matches(CommonConstants.ATTRTYPEREGX, str)) {
                                    attrModel.setAttrType(str);
                                    attrType = false;
                                    attrName = true;
                                    //非泛型的类型,判断是否基础类型
                                    if (str.indexOf("<") < 0 && !Pattern.matches(CommonConstants.DATATYPEREGX, str.toLowerCase())) {
                                        requestModel.getObjList().add(str.trim());
                                    } else if (str.indexOf("<") != -1) {
                                        String generic = str.split("<")[1].replace(">", "");
                                        //k,v情况的处理
                                        if(generic.contains(",")){
                                            generic = generic.split(",")[1];
                                        }
                                        if (!Pattern.matches(CommonConstants.DATATYPEREGX, generic.toLowerCase())) {
                                            requestModel.getObjList().add(generic);
                                        }
                                    }
                                    continue;
                                }
                                //取出参数名称
                                if (attrName && Pattern.matches(CommonConstants.ATTRNAMEREGX, str.trim())) {
                                    attrName = false;
                                    String attrNames = str.replace(";", "");
                                    if(-1!=attrNames.indexOf(CommonConStants.EQUALSIGN)){
                                        attrModel.setAttrName(attrNames.split("=")[0]);
                                    }else{
                                        attrModel.setAttrName(attrNames);
                                    }
                                    continue;
                                }
                            }
                            requestModel.getAttrList().add(attrModel);
                            attrModel = null;
                            continue;
                        }
                        if (Pattern.matches(CommonConstants.ENDREGX, line) && line.indexOf("class") < 0) {
                        	num=0;
                            map.put(name, requestModel);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            String msg = String.format("error in class=%s,num=%s", name,num);
            e.printStackTrace();
            throw new RuntimeException(msg,e);
        } finally {
            if (null != br) {
                br.close();
            }
            if (null != reader) {
                reader.close();
            }
        }
        return map;
    }
}
