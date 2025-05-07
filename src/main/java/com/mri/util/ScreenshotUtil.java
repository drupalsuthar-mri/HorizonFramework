package com.mri.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    static Date date = new Date();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
    static String dateTime = sdf.format(date);

}