package ch.ethz.ssh2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ConnectionDemo {

    private static final long innitTime = 100L;

    public static String QUIT = "exit";

    private int port = 22;
    private Connection conn;
    private final String host;
    private final String user;
    private final String password;

    public static void main(String[] args) {
        String command = "echo 'Hello, world!'";
        String host = "10.21.13.18";
        String user = "root";
        String password = "sidOK!123";
        ConnectionDemo connectionDemo = new ConnectionDemo(host, user, password);
        connectionDemo.execute(command, QUIT);
    }

    public ConnectionDemo(String ip, String user, String password) {
        this(ip, null, user, password);
    }

    public ConnectionDemo(String ip, Integer port, String user, String password) {
        this.host = ip;
        this.user = user;
        this.password = password;
        if (port != null) {
            this.port = port;
        }
    }

    private boolean isLogin() {
        boolean flg = false;
        try {
            conn = new Connection(host, port);
            conn.connect();
            flg = conn.authenticateWithPassword(user, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flg;
    }

    public void execute(String... commands) {
        boolean login = isLogin();
        if (!login) {
            System.out.println("登录失败,login=" + login);
            return;
        }
        Session session = null;
        try {
            System.out.println("openSession");
            session = conn.openSession();
            // 建立虚拟终端
            session.requestPTY("bash");
            // 打开一个Shell
            session.startShell();
            // 建立终端之后等待一段时间，如不等待输出不全
            Thread.sleep(innitTime);
            // 准备输入命令
            PrintWriter out = new PrintWriter(session.getStdin());
            for (String command : commands) {
                // 执行命令
                out.println(command);
                out.flush();
            }

            InputStream inputStream = session.getStdout();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            if (session != null) {
                session.close();
            }
        }
    }

}
