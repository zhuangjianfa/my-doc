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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.owner.common.constants.CommonConStants;
import com.owner.common.utils.StringHelper;
import com.owner.java.api.doc.constants.CommonConstants;
import com.owner.java.api.doc.model.InterfaceNode;
import com.owner.java.api.doc.model.MethodNode;

/**
 * description:TODO
 * Author	Date	Changes
 * zhuangjianfa  2018年3月8日 Created
 */
@Component
public class InterfaceNodeFile {
    
    @Value("${java.doc.servicePath}")
    private String servicePath;
    
    /**
     * java数据类型判断
     */
    private String dataTypeRegx     = "(?:long|int|integer|string|short|byte|character|char|float|double|boolean|bigdecimal|date)";
    
    public List<InterfaceNode> getInterfaceNodes() throws IOException {
        File file = new File(servicePath);
        List<InterfaceNode> interfaceNodeList = new ArrayList<>();
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            for (File f : subFiles) {
                InterfaceNode interfaceNode = readInterface(f);
                if(null!=interfaceNode){
                    interfaceNodeList.add(interfaceNode);
                }
            }
        }else{
            InterfaceNode interfaceNode = readInterface(file);
            if(null!=interfaceNode){
                interfaceNodeList.add(interfaceNode);
            } 
        }
        return interfaceNodeList;
    }
    
    /**
     * 
     * description: 读取接口类
     * @param f
     * @return
     * @throws IOException
     * createBy:zhuangjianfa            
     * createDate:2018年3月8日
     */
    private InterfaceNode readInterface(File f) throws IOException {
        InputStreamReader reader = null;
        BufferedReader br = null;
        InterfaceNode interfaceNode = null;
        reader = new InputStreamReader(new FileInputStream(f));
        //读取文件行数
        int num = 0;
        String name = f.getName().replace(".java","");
        try {
            br = new BufferedReader(reader);
            interfaceNode = new InterfaceNode();
            String line;
            boolean isMethod = false;
            MethodNode methodNode = new MethodNode();
            while (null != (line=br.readLine())) {
                num++;
                //空行，注释开头行及一行只有*
                if (line.equals("") || line.trim().startsWith("/") || "*".equals(line.trim())) {
                    continue;
                }
                //接口相关信息
                if(!isMethod){
                    //取到包名+文件名=带包路径接口全称
                    if (line.indexOf("package") != -1) {
                        String interfacePackage = line.replace("package", "").replace(";",".").trim();
                        interfaceNode.setInterfaceName(interfacePackage+name);
                        continue;
                    }
//                    //接口说明,比如通用用户服务接口,暂时没什么用，不取此值
//                    if (line.indexOf("description:") != -1) {
//                        String[] descriptionArray =line.split("description:");
//                        if(descriptionArray.length>1){
//                            interfaceNode.setInterfaceDescript(descriptionArray[1].trim());
//                        }
//                        continue;
//                    }
                    //当出现接口的作用域时标识后续为方法的说明
                    if (line.indexOf("public") != -1) {
                        isMethod = true;
                        continue;
                    }
                }else{
                   //路径
                   if (line.indexOf("@RequestMapping") != -1) {
                        String newLine = line.replaceAll(CommonConstants.PATH, "");
                        Map<String,String> map = StringHelper.splitToMap(newLine, CommonConStants.COMMA, CommonConStants.EQUALSIGN);
                        if(map.containsKey("value")){
                            methodNode.setPath(map.get("value"));
                        }
                        if(map.containsKey("method")){
                            if(map.get("method").contains("POST")){
                                methodNode.setMethodType("post");
                            }
                            if(map.get("method").contains("GET")){
                                methodNode.setMethodType("get");
                            }
                            if(map.get("method").contains("PUT")){
                                methodNode.setMethodType("put");
                            }
                            if(map.get("method").contains("DELETE")){
                                methodNode.setMethodType("delete");
                            }
                        }
                    }
                    //方法描述
                    if (line.indexOf("description:") != -1) {
                        String[] descriptionArray =line.split("description:");
                        if(descriptionArray.length>1){
                            methodNode.setDescription(line.split("description:")[1].trim());
                        }
                    }
                    //方法创建人
                    if (line.indexOf("createdBy:") != -1) {
                        String[] createdByArray =line.split("createdBy:");
                        if(createdByArray.length>1){
                            methodNode.setCreatedBy(createdByArray[1].trim());
                        }
                        continue;
                    }
                    //方法创建时间
                    if (line.indexOf("created:") != -1) {
                        String[] createdArray =line.split("created:");
                        if(createdArray.length>1){
                            methodNode.setCreated(createdArray[1].trim());
                        }
                        continue;
                    }
                    //读取方法行，接口方法一定要Resutl或Resutl<你的数据类型>
                    if (Pattern.matches("^Result.*;$", line.trim())){
                        //解释方法行1分离返回值与方法名
                        String[] methodParam = line.trim().split("\\(");
                        for(String str :methodParam){
                            if("".equals(str)){
                                continue;
                            }
                            //返回值与方法名
                            if(Pattern.matches("^Result.*$",str) || Pattern.matches("^BaseResult.*$",str)){
                                String[] array = str.split(" ");
                                String responstName = array[0].trim();
                                methodNode.setResponseName(responstName);
                                if(responstName.indexOf("<")!=-1){
                                    int n = responstName.length()-responstName.replaceAll(">", "").length();
                                    //result内的泛型不再包含泛型
                                    String dataType = responstName.replace("Result<","").replace("BaseResult<","").replace(">","");
                                    if(!"?".equals(dataType)){
                                        methodNode.setDataType(dataType);
                                    }else{
                                        methodNode.setDataType("");
                                    }
                                       
                                }
                                for(int i  = 1;i < array.length; i++){
                                    if(!StringUtils.isEmpty(array[i].trim())){
                                        //设置方法名
                                        methodNode.setMethodName(array[i].trim());
                                    }
                                }
                                continue;
                            }
                            //请求对象
                            if(str.indexOf(")")!=-1){
                                String[] requestNameArray = str.trim().split(" ");
                                if(!Pattern.matches(dataTypeRegx,requestNameArray[0].toLowerCase())){
                                    methodNode.setRequestName(requestNameArray[0].trim());
                                }else{
                                    methodNode.setRequestName(requestNameArray[0]+"-"+requestNameArray[1].replace(")","").replace(";",""));
                                }
                                interfaceNode.getMethodNodeList().add(methodNode);
                                methodNode = new MethodNode();
                                break;
                            }
                        }
                        continue;
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
        return interfaceNode.getMethodNodeList().size() > 0 ?interfaceNode:null;
    }
}
