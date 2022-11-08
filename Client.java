import util.MsgReq;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {

    public static void main(String[] args) {
        Socket socket;
        final String IP = "localhost";
        final int PORT = 9876;
        ObjectOutputStream out;
        ObjectInputStream in;

        try {
            socket = new Socket(IP, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());

            MsgReq msgReq = new MsgReq("P", 3, "odd");
            out.writeObject(msgReq);

            in = new ObjectInputStream(socket.getInputStream());
            System.out.println(in.readObject());

            while (in.toString() != "exit") {
                System.out.println(in.readObject());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
