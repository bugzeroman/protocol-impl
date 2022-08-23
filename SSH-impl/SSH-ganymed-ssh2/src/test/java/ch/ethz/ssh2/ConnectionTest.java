package ch.ethz.ssh2;

import org.junit.jupiter.api.Test;

public class ConnectionTest {
    @Test
    public void testDeviceZTE() {
        String command = "show interface brief include xgei-0/1/1/30";
        String QUIT = "quit";
        String host = "10.11.58.2";
        String user = "psd";
        String password = "psd";
        ConnectionDemo connectionDemo = new ConnectionDemo(host, user, password);
        connectionDemo.execute(command, QUIT);
    }
}
