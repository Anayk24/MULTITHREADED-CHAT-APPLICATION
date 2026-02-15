import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 12345);

        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));
        BufferedReader serverIn =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);

        // Thread to read messages from server
        new Thread(() -> {
            try {
                String msg;
                while ((msg = serverIn.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        }).start();

        // Send messages to server
     System.out.print("Enter your name: ");
String name = input.readLine();

String userMsg;
while ((userMsg = input.readLine()) != null) {
    out.println(name + ": " + userMsg);
}
    }
}
