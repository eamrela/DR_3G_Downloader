/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vodafone.dr.main;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.vodafone.dr.configuration.AppConf;
import java.io.File;

/**
 *
 * @author Admin
 */
public class Processor {
    
    private static String workingDir = null;
    private static String printoutsDir = null;
    
    public static void initApp(String confPath){
            System.out.println("Initializing App");
            AppConf.configureApp(confPath);
            
            workingDir = AppConf.getWorkingDir()+"\\DR_3G_"+AppConf.getMydate();
            printoutsDir = workingDir+"\\printouts";
            new File(printoutsDir).mkdirs();
    }
    
    public static void main(String[] args) {
        
        
//        if(args.length!=1){
//            System.out.println("Please set the input paramters");
//            System.out.println("Configuration File");
//            System.exit(1);
//        }
//        String conf = args[0];
//        initApp(conf);
        System.out.println("Downloading Files");
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(AppConf.getOSS_USER(), AppConf.getOSS_IP(), 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(AppConf.getOSS_PASS());
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd("/home/"+AppConf.getOSS_USER()+"/"+AppConf.getOSS_WORKING_DIR()+"/");
            sftpChannel.get("*", printoutsDir);
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
}
