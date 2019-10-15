package ex01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    /** WEB_ROOT is the directory where our HTML and other files reside.
     * For this package, WEB_ROOT is the "webroot" directory under the
     * working directory.
     * The working directory is the location in the file system
     * from where the java command was invoked. */
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";

    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    // the shutdown command received
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        //ServerSocket serverSocket = null;
        int port = 8080;

        // ServerSocket은 Socket과 달리 클라이언트 요청을 기다리는 역할을 함.
        // 클라이언트 요청이 들어오면 ServerSocket은 Socket을 생성하여 해당 요청을 처리하게 함.
        try (ServerSocket serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"))) {

            // Loop waiting for a request
            while (!shutdown) {
                try(Socket socket = serverSocket.accept();
                    InputStream input = socket.getInputStream();
                    OutputStream output = socket.getOutputStream()) {
                    // create Request object and parse
                    Request request = new Request(input);
                    request.parse();

                    // create Response object
                    Response response = new Response(output);
                    response.setRequest(request);
                    response.sendStaticResource();

                    //check if the previous URI is a shutdown command
                    shutdown = SHUTDOWN_COMMAND.equalsIgnoreCase(request.getUri());
                } catch (Exception e) {
                    e.printStackTrace ();
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}