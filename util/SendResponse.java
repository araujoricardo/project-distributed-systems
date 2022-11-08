package util;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SendResponse {

    ObjectOutputStream playerOneOutput;
    ObjectOutputStream playerTwoOutput;

    public SendResponse(ObjectOutputStream playerOneOutput) {
        this.playerOneOutput = playerOneOutput;
    }

    public void initOutPlayerTwo(ObjectOutputStream playerTwoOutput) {
        this.playerTwoOutput = playerTwoOutput;
    }

    public void sentToPlayerOne(String msg) {
        System.out.println(msg);
        try {
            playerOneOutput.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToBothPlayers(String msg) {
        System.out.println(msg);
        try {
            playerOneOutput.writeObject(msg);
            playerTwoOutput.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
