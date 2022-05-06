package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] gameBoard = {
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
        };

        printGameBoard(gameBoard);

        int counter = 1;
        int moves = 0;

        while (true) {
            System.out.print("Enter the coordinates: "); // from 1 to 3. Example (1 3, 2 1, 3 3).
            String coordinates = scanner.nextLine();

            if (inputCheck(gameBoard, coordinates)) {
                continue;
            } else if (counter % 2 == 0) {
                playerMove(gameBoard, coordinates, "secondPlayer");
            } else {
                playerMove(gameBoard, coordinates, "firstPlayer");
            }

            counter++;
            printGameBoard(gameBoard);

            if (isTheWinner(gameBoard, 'X')) {
                System.out.println("X wins");
                break;
            }

            if (isTheWinner(gameBoard, 'O')) {
                System.out.println("O wins");
                break;
            }

            moves++;
            if (moves == 9) {
                break;
            }
        }

        if (moves == 9) {
            System.out.println("Draw");
            scanner.close();
        }
    }

    private static void printGameBoard(char[][] gameBoard) {
        System.out.println("---------");
        for (char[] row : gameBoard) {
            for (char chars : row) {
                System.out.print(chars);
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    private static boolean inputCheck(char[][] gameBoard, String coordinates) {
        int first;
        int second;

        try {
            String[] pieces = coordinates.split(" ");
            first = Integer.parseInt(pieces[0]);
            second = Integer.parseInt(pieces[1]);

        } catch (NumberFormatException e) {
            System.out.println("You should enter two numbers separated by a space!");
            return true;
        }

        if (first < 1 || first > 3 || second < 1 || second > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return true;
        }

        if (cellCheck(gameBoard, coordinates)) {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }
        return false;
    }

    private static boolean cellCheck(char[][] gameBoard, String coordinates) {
        return switch (coordinates) {
            case "1 1" -> gameBoard[0][2] == 'X' || gameBoard[0][2] == 'O';
            case "1 2" -> gameBoard[0][4] == 'X' || gameBoard[0][4] == 'O';
            case "1 3" -> gameBoard[0][6] == 'X' || gameBoard[0][6] == 'O';
            case "2 1" -> gameBoard[1][2] == 'X' || gameBoard[1][2] == 'O';
            case "2 2" -> gameBoard[1][4] == 'X' || gameBoard[1][4] == 'O';
            case "2 3" -> gameBoard[1][6] == 'X' || gameBoard[1][6] == 'O';
            case "3 1" -> gameBoard[2][2] == 'X' || gameBoard[2][2] == 'O';
            case "3 2" -> gameBoard[2][4] == 'X' || gameBoard[2][4] == 'O';
            case "3 3" -> gameBoard[2][6] == 'X' || gameBoard[2][6] == 'O';
            default -> true;
        };
    }

    private static void playerMove(char[][] gameBoard, String coordinates, String user) {
        char symbol = ' ';

        if (user.equals("firstPlayer")) {
            symbol = 'X';
        } else if (user.equals("secondPlayer")) {
            symbol = 'O';
        }

        switch (coordinates) {
            case "1 1" -> gameBoard[0][2] = symbol;
            case "1 2" -> gameBoard[0][4] = symbol;
            case "1 3" -> gameBoard[0][6] = symbol;
            case "2 1" -> gameBoard[1][2] = symbol;
            case "2 2" -> gameBoard[1][4] = symbol;
            case "2 3" -> gameBoard[1][6] = symbol;
            case "3 1" -> gameBoard[2][2] = symbol;
            case "3 2" -> gameBoard[2][4] = symbol;
            case "3 3" -> gameBoard[2][6] = symbol;
            default -> {
            }
        }
    }

    private static boolean isTheWinner(char[][] gameBoard, char symbol) {
        return ((gameBoard[0][2] == symbol && gameBoard[0][4] == symbol && gameBoard[0][6] == symbol) ||
                (gameBoard[1][2] == symbol && gameBoard[1][4] == symbol && gameBoard[1][6] == symbol) ||
                (gameBoard[2][2] == symbol && gameBoard[2][4] == symbol && gameBoard[2][6] == symbol) ||

                (gameBoard[0][2] == symbol && gameBoard[1][2] == symbol && gameBoard[2][2] == symbol) ||
                (gameBoard[0][4] == symbol && gameBoard[1][4] == symbol && gameBoard[2][4] == symbol) ||
                (gameBoard[0][6] == symbol && gameBoard[1][6] == symbol && gameBoard[2][6] == symbol) ||

                (gameBoard[0][2] == symbol && gameBoard[1][4] == symbol && gameBoard[2][6] == symbol) ||
                (gameBoard[0][6] == symbol && gameBoard[1][4] == symbol && gameBoard[2][2] == symbol));
    }
}

