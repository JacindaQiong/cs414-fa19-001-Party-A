import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;


class Server {
   private SSLServerSocket serverSocket;

    public Server() throws Exception {
        SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        serverSocket = (SSLServerSocket) socketFactory.createServerSocket(8080);
    }

    private void runServer() throws IOException {
                System.err.println("Waiting for connection...");
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                try {
                    while (true) {
                        String clientInput = input.readLine();
                        if(clientInput.equals("Register")){
                            output.println("[Server]: Register");

                        } else if (clientInput.equals("Login")){
                            output.println("[Server]: Login");
                        } else output.println("[Server]: Invalid command");
                    }
                } finally{
                    output.close();
                    input.close();
                    }
    }

    public static void main(String args[]) throws Exception {
        System.setProperty("javax.net.ssl.keyStore", "rs.store");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");
        Server server = new Server();
        server.runServer();
    }
}