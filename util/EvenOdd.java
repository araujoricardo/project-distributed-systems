package util;

public class EvenOdd {
    int sum = 0;
    int valuePlayerOne;
    String evenOddPlayerOne;
    int valuePlayerTwo;
    String playerTwoType;
    String result = "";
    String winner = "";

    public EvenOdd(int valuePlayerOne, String evenOddPlayerOne, int valuePlayerTwo, String playerTwoType) {
        this.valuePlayerOne = valuePlayerOne;
        this.evenOddPlayerOne = evenOddPlayerOne;
        this.valuePlayerTwo = valuePlayerTwo;
        this.playerTwoType = playerTwoType;
    }

    private void calcResultAndWinner() {
        sum = valuePlayerOne + valuePlayerTwo;

        if (sum % 2 == 0) {
            result = "even";
        } else {
            result = "odd";
        }

        if (evenOddPlayerOne.equals(result)) {
            winner = "playerOne";
        } else {
            winner = playerTwoType;
        }
    }

    public String getResult() {
        if (result.isEmpty()) {
            calcResultAndWinner();
        }

        return result;
    }

    public String getWinner() {
        if (winner.isBlank()) {
            calcResultAndWinner();
        }

        return winner;
    }

}
