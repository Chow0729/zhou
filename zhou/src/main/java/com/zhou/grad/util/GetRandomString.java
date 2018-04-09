package com.zhou.grad.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetRandomString {

    public static String getRandom(int length) {
        //获取当前时间戳
          SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
          String temp = sf.format(new Date());
          if (length<8) {
              return "随机数的长度必须大于8";
          }
          if (length==8) {
              return temp;
          }
          
          int num = (int) (Math.random()*9*Math.pow(10, length-9)+Math.pow(10, length-9));
          String random = String.valueOf(num);
          return temp + random;
      }
}
