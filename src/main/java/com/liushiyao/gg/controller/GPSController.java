package com.liushiyao.gg.controller;


import com.liushiyao.gg.com.BusiResult;
import com.liushiyao.gg.com.BusiStatus;
import com.liushiyao.gg.pojo.Route;
import com.liushiyao.gg.service.GGService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data")
public class GPSController {

  @Autowired
  private GGService ggService;



  private static Logger logger = Logger.getLogger(GPSController.class);

  @RequestMapping("/parse")
  @ResponseBody
  public BusiResult<List<Route>> gpsDataParse(String data){

    //数据有效性判断
    if(StringUtils.isEmpty(data) || !data.matches("^(\\d+\\.\\d+,\\d+\\.\\d+;)+$")){
      logger.debug("参数有误："+data);
      return  new BusiResult<List<Route>>(BusiStatus.PARAMETERILLEGAL);
    }
    List list = null;
    try{
      Route route = ggService.parse(data);
      list = new ArrayList();
      list.add(route);
    }catch(Exception e){
      e.printStackTrace();

    }
    return new BusiResult<List<Route>>(BusiStatus.SUCCESS,list);


  }



}
