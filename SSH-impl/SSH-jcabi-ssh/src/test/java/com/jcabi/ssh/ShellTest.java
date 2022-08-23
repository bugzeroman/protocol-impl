package com.jcabi.ssh;

import org.junit.jupiter.api.Test;

import com.jcabi.ssh.Shell.Plain;

public class ShellTest {
    @Test
    public void testLinux18() throws Exception {
        String command = "echo 'Hello, world!'";
        String host = "10.21.13.18";
        String user = "root";
        String password = "sidOK!123";
        Shell shell = new SshByPassword(host, 22, user, password);
        String result = new Shell.Plain(shell).exec(command);
        System.out.println(result);
    }

    @Test
    public void testLinux14() throws Exception {
        String command = "echo 'Hello, world!'";
        String host = "10.21.13.14";
        String user = "root";
        String password = "ai123456";
        Shell shell = new SshByPassword(host, 22, user, password);
        Plain plain = new Shell.Plain(shell);
        String result = plain.exec(command);
        System.out.println(result);
    }

    @Test
    public void testDeviceZTE() throws Exception {
        String command = "show interface brief include xgei-0/1/1/30;quit;";
        String host = "10.11.58.2";
        String user = "psd";
        String password = "psd";
        Shell shell = new SshByPassword(host, 22, user, password);
        String result = new Shell.Plain(shell).exec(command);
        System.out.println("result=" + result);
    }
}
