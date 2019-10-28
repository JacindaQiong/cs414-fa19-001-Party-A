import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class Client {
    public Client() {
        try {
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) socketFactory.createSocket("localhost", 8080);
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                System.out.print("[Client]: ");
                String userCommand = userInput.readLine();
                if (userCommand.equals("quit")) break;
                output.println(userCommand);
                String response = input.readLine();
                System.out.println(response);
            }
            socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        //Sets rs.store file as trustStore for SSLSocket
        System.setProperty("javax.net.ssl.trustStore", "rs.store");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        new Client();
    }
}