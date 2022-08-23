package com.jcraft.jsch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class SSHLinuxServerTest {
    @Test
    public void testLinuxServerExec() throws Exception {
        String host = "10.21.13.14";
        String user = "root";
        String password = "ai123456";
        // String command = "echo 'hello SSH'";
        String command = "ls /home";

        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, 22);
        session.setPassword(password);
        // 设置第一次登陆的时候提示，可选值:(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        // 连接超时，该设备需要较长的超时时间
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
