package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.TestMapper;
import com.example.demo.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String queryTest(){

        //Test test = testMapper.selectById(1);
        List<Test> testList = testMapper.queryList();

        return JSON.toJSONString(testList);
    }
}
