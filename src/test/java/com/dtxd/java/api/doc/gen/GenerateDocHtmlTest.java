/**
 * Copyright © 2014 GZJF Corp. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of 深圳市大头兄弟文化传播有限公司 Corp. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from 深圳市大头兄弟文化传播有限公司 Corp or an authorized sublicensor.
 */
package com.owner.java.api.doc.gen;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.owner.java.api.doc.Application;
import com.owner.java.api.doc.analyze.InterfaceNodeFile;
import com.owner.java.api.doc.analyze.ReqRespNodeFile;
import com.owner.java.api.doc.generate.IGenerate;
import com.owner.java.api.doc.model.InterfaceNode;
import com.owner.java.api.doc.model.ReqRespNode;


/**
 * 
 * description:TODO
 * Author	Date	Changes
 * zhuangjianfa  2018年3月6日 Created
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class GenerateDocHtmlTest {
    
    @Autowired
    ReqRespNodeFile reqRespNodeFile;
   
    @Autowired
    InterfaceNodeFile interfaceNodeFile;
    
    @Resource(name="markdownGenerate")
    IGenerate markdownGenerate;
    
    Map<String,ReqRespNode> reqMap;
    
    Map<String,ReqRespNode> respMap;
    
    List<InterfaceNode> interfaceNodeList;
    @Test
    public void initTest() throws IOException{
        markdownGenerate.generate();
    }
}
