//package com.mri.util;
//
//public class TestExecutionContext {
//    private static final ThreadLocal<ScreenshotUtil> screenshotUtil = new ThreadLocal<>();
//
//    public static ScreenshotUtil getScreenshotUtil(){
//        return screenshotUtil.get();
//    }
//
//    public static void setScreenshotUtil(ScreenshotUtil util){
//        screenshotUtil.set(util);
//    }
//
//    public static void clear(){
//        screenshotUtil.remove();
//    }
//}