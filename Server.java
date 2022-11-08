import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
// import java.util.Scanner;
import java.util.Random;

import util.EvenOdd;
import util.MsgReq;
import util.SendResponse;

import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        final int PORT_PLAYER_ONE = 9876;
        final int PORT_PLAYER_TWO = 9877;
        ServerSocket playerOne;
        ServerSocket playerTwo;
        // Scanner scanner = new Scanner(System.in);
        // String serverInput = "";

        ObjectInputStream playerOneInput;
        ObjectInputStream playerTwoInput;

        Socket playerOneCli;
        Socket playerTwoCli;

        String operation;

        System.out.println("Server is running");
        try {
            playerOne = new ServerSocket(PORT_PLAYER_ONE);
            playerOneCli = playerOne.accept();

            playerOneInput = new ObjectInputStream(playerOneCli.getInputStream());
            MsgReq playerOneMsgReq = (MsgReq) playerOneInput.readObject();
            operation = playerOneMsgReq.getOperation();

            ObjectOutputStream playerOneOutput = new ObjectOutputStream(playerOneCli.getOutputStream());
            SendResponse response = new SendResponse(playerOneOutput);

            response.sentToPlayerOne("PlayerOne connected");

            if (operation.equals("P")) {
                response.sentToPlayerOne("Multiplayer mode chosen, waiting for player two...");

                playerTwo = new ServerSocket(PORT_PLAYER_TWO);
                playerTwoCli = playerTwo.accept();

                ObjectOutputStream playerTwoOutput = new ObjectOutputStream(playerTwoCli.getOutputStream());
                response.initOutPlayerTwo(playerTwoOutput);
                response.sendToBothPlayers("PlayerTwo connected");

                playerTwoInput = new ObjectInputStream(playerTwoCli.getInputStream());
                MsgReq playerTwoMsgReq = (MsgReq) playerTwoInput.readObject();

                EvenOdd calcResult = new EvenOdd(playerOneMsgReq.getValue(), playerOneMsgReq.getEvenOdd(),
                        playerTwoMsgReq.getValue(), "playerTwo");

                response.sendToBothPlayers("Result " + calcResult.getResult());
                response.sendToBothPlayers("Winner " + calcResult.getWinner());
                response.sendToBothPlayers("exit");
            } else if (operation.equals("C")) {
                response.sentToPlayerOne("PlayerOne chose to play vs computer");

                Random generator = new Random();
                int valueComputer = generator.nextInt(11);

                EvenOdd calcResult = new EvenOdd(playerOneMsgReq.getValue(), playerOneMsgReq.getEvenOdd(),
                        valueComputer, "computer");

                response.sentToPlayerOne("Result " + calcResult.getResult());
                response.sentToPlayerOne("Winner " + calcResult.getWinner());
            } else {
                System.out.println("Operation invalid, select P or C");
            }

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

    }

}
