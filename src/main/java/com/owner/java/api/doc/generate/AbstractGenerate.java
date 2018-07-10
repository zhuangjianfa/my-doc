/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.owner.java.api.doc.analyze.InterfaceNodeFile;
import com.owner.java.api.doc.analyze.ReqRespNodeFile;
import com.owner.java.api.doc.model.InterfaceNode;
import com.owner.java.api.doc.model.ReqRespNode;

/**
 * description:生成抽像类
 * Author	Date	Changes
 * zhuangjianfa  2018年3月8日 Created
 */
public abstract class AbstractGenerate implements IGenerate{

    @Autowired
    ReqRespNodeFile reqRespNodeFile;
   
    @Autowired
    InterfaceNodeFile interfaceNodeFile;
    
    Map<String,ReqRespNode> reqMap;
    
    Map<String,ReqRespNode> respMap;
    
    List<InterfaceNode> interfaceNodes;
    
    @Value("${java.doc.targetPath}")
    private String targetPath;
    
    /* (non-Javadoc)
     * @see com.owner.java.api.doc.generate.IGenerate#generate()
     */
    @Override
    public void generate() {
        try{
            getNode();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        generateFile(interfaceNodes,reqMap,respMap);
    }
    
    public void getNode() throws IOException{
        reqMap = reqRespNodeFile.getReqNodes();
        respMap = reqRespNodeFile.getRespNodes();
        interfaceNodes = interfaceNodeFile.getInterfaceNodes();
    }
    
    public void createFile(String folderName,String fileName,String markdown) throws IOException{
        
        String path = targetPath+folderName;  
        File file = new File(path);  
        if(!file.exists()){  
            file.mkdirs();          
        }  
        file = new File(path+File.separator+fileName);
  
        // write  
        FileWriter fw = new FileWriter(file, false);  
        BufferedWriter bw = new BufferedWriter(fw);  
        bw.write(markdown);  
        bw.flush();  
        bw.close();  
        fw.close();  
    }

    public abstract void generateFile(List<InterfaceNode> interfaceNodeList,Map<String,ReqRespNode> reqMap,Map<String,ReqRespNode> respMap);
}
