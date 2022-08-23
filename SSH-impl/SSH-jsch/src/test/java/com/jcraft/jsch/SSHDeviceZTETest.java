package com.jcraft.jsch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class SSHDeviceZTETest {
    @Test
    public void testDeviceZTEExec() throws Exception {
        String host = "10.11.58.2";
        String user = "psd";
        String password = "psd";
        String command = "show interface brief include xgei-0/1/1/30";

        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, 22);
        session.setPassword(password);
        // 设置第一次登陆的时候提示，可选值:(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        // 连接超时
        session.connect(1000 * 100);

        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
        String msg;
        while ((msg = in.readLine()) != null) {
            System.out.println(msg);
        }
        channel.disconnect();
        session.disconnect();

    }
}
