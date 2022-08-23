package com.jcabi.ssh;

public class ShellDemo {
    public static void main(String[] args) throws Exception {
        String command = "echo 'Hello, world!'";
        String host = "10.21.13.18";
        String user = "root";
        String password = "sidOK!123";
        Shell shell = new SshByPassword(host, 22, user, password);
        String result = new Shell.Plain(shell).exec(command);
        System.out.println(result);
    }
}
