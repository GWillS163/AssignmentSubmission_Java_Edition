package com.mengjq.assignmentsubmission.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class utils {
    public String getCurrentDateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
