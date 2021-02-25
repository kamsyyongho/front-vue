package com.skt.doss.portal.front.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
  
  public static String getCurrentDate() {
    return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
  }
}
