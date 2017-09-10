/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AppConf {
    private static String OSS_IP;
    private static String OSS_USER;
    private static String OSS_PASS;
    private static String OSS_WORKING_DIR;
    private static String workingDir;
    private static String mydate = (java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime())).replaceAll(":", "");
    
    
    public static boolean configureApp(String path){
        try {
            RandomAccessFile raf = new RandomAccessFile(new File(path), "r");
            String line = null;
            while((line=raf.readLine())!=null){
                if(line.contains("OSS_USER")){
                    OSS_USER = line.split("~")[2];
                }else if(line.contains("OSS_PASS")){
                    OSS_PASS = line.split("~")[2];
                }else if(line.contains("OSS_IP")){
                    OSS_IP = line.split("~")[2];
                }
                else if(line.contains("OSS_WORKING_DIR")){
                    OSS_WORKING_DIR = line.split("~")[2];
                }else if(line.contains("WORKING_DIR")){
                    workingDir = line.split("~")[2];
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppConf.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(AppConf.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
            return true;
    }

    public static String getOSS_IP() {
        return OSS_IP;
    }

    public static void setOSS_IP(String OSS_IP) {
        AppConf.OSS_IP = OSS_IP;
    }

    public static String getOSS_USER() {
        return OSS_USER;
    }

    public static void setOSS_USER(String OSS_USER) {
        AppConf.OSS_USER = OSS_USER;
    }

    public static String getOSS_PASS() {
        return OSS_PASS;
    }

    public static void setOSS_PASS(String OSS_PASS) {
        AppConf.OSS_PASS = OSS_PASS;
    }

    public static String getWorkingDir() {
        return workingDir;
    }

    public static void setWorkingDir(String workingDir) {
        AppConf.workingDir = workingDir;
    }

    public static String getMydate() {
        return mydate;
    }

    public static void setMydate(String mydate) {
        AppConf.mydate = mydate;
    }

    public static String getOSS_WORKING_DIR() {
        return OSS_WORKING_DIR;
    }
 
}
