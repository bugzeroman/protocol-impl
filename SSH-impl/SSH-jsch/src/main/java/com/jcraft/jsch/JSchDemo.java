package com.jcraft.jsch;

import java.io.InputStream;

public class JSchDemo {

    /**
     * JSch Example Tutorial Java SSH Connection Program
     */
    public static void main(String[] args) {
        String host = "10.11.58.2";
        String user = "psd";
        String password = "psd";
        String command1 = "show interface brief include xgei-0/1/1/30;quit;";
        try {

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            config.put("quiet", "false");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            Channel channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(command1);

            channel.setInputStream(System.in, true);
            // ((ChannelExec) channel).setErrStream(System.err);
            // channel.setOutputStream(System.out, true);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
                System.out.println("Doing");
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
