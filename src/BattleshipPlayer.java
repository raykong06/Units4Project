import java.util.Scanner;
public class BattleshipPlayer {
    public static void main(String[] args) {
        // Instance Variables
        BattleshipMaker test = new BattleshipMaker();
        Scanner s = new Scanner(System.in);
        String input = "";

        String carrierCoord = "";
        String battleshipCoord = "";
        String cruiserCoord = "";
        String submarineCoord = "";
        String destroyerCoord = "";
        String carrierDirect = "";
        String battleshipDirect = "";
        String cruiserDirect = "";
        String submarineDirect = "";
        String destroyerDirect = "";
        String testBoard = test.makeEmptyBoard();
        String playBoard = "";

        boolean checked = false;

        // User Input Main Menu
        /*
        System.out.println("Welcome to the Battleship Game.\nType \"P\" to play.\nType \"I\" for info on how to play.");
        input = s.nextLine();

        while(!(input.equals("P")))
        {
            if(input.equals("I"))
            {
                System.out.println("Object of the game: Be the first to sink all 5 of the opponent's ships. You are playing against the program AI.\n" +
                        "Symbol Meaning:\n" +
                        "+ : Empty area\n" +
                        "o : Your Battleship (on Defense)\n" +
                        "x : Your Missed Attacks (on Offense)\n" +
                        "# : Successful Hit Areas\n" +
                        "\n" +
                        "You have a fleet of 5 total battleships:\n" +
                        "Carrier (5-long)\n" +
                        "Battleship (4-long)\n" +
                        "Cruiser (3-long)\n" +
                        "Submarine (3-long)\n" +
                        "Destroyer (2-long)\n" +
                        "\n" +
                        "You will be spectating an offensive board and a defensive board.\n" +
                        "Your attacks and board setup must be within the parameters of the board.\n" +
                        "Please specify coordinates in this format: A1 (letter must be capitalized)\n" +
                        "If asked for direction, your battleship will either go \"right\" or \"down\" from your specified coordinate.\n");
            }
            else
            {
                System.out.println("That is an invalid character.");
            }
            System.out.println("Type \"P\" to play. Type \"I\" for info on how to play.");
            input = s.nextLine();
        }

        // Ask for Ship Coords
        System.out.println("This is an example empty battleship grid:");
        System.out.println(testBoard);

        // Carrier
        while(checked == false)
        {
            System.out.print("Please type your carrier (5-long) coordinate: ");
            carrierCoord = s.nextLine();

            while(test.coordChecker(carrierCoord) == false)
            {
                System.out.print("That is not a valid coordinate. Please re-enter a valid coordinate: ");
                carrierCoord = s.nextLine();
            }

            System.out.print("Please type the direction (right or down): ");
            carrierDirect = s.nextLine();

            while(!(carrierDirect.equals("right") || carrierDirect.equals("down")))
            {
                System.out.print("That is not a valid direction. Please re-enter a valid direction: ");
                carrierDirect = s.nextLine();
            }
            if(test.limitChecker(5,carrierCoord,carrierDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        testBoard = test.addShip(testBoard,carrierCoord,5,carrierDirect);
        System.out.println("\nYou successfully added your carrier:\n" + testBoard);
        checked = false;

        // Battleship
        while(checked == false)
        {
            System.out.print("Please type your battleship (4-long) coordinate: ");
            battleshipCoord = s.nextLine();

            while(test.coordChecker(battleshipCoord) == false)
            {
                System.out.print("That is not a valid coordinate. Please re-enter a valid coordinate: ");
                battleshipCoord = s.nextLine();
            }

            System.out.print("Please type the direction (right or down): ");
            battleshipDirect = s.nextLine();

            while(!(battleshipDirect.equals("right") || battleshipDirect.equals("down")))
            {
                System.out.print("That is not a valid direction. Please re-enter a valid direction: ");
                battleshipDirect = s.nextLine();
            }
            if(test.limitChecker(4,battleshipCoord,battleshipDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else if(!(test.overlapChecker(testBoard,battleshipCoord,4,battleshipDirect)))
            {
                System.out.println("The ship you described would overlap with an existing ship.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        testBoard = test.addShip(testBoard,battleshipCoord,4,battleshipDirect);
        System.out.println("\nYou successfully added your battleship:\n" + testBoard);
        checked = false;

        // Cruiser
        while(checked == false)
        {
            System.out.print("Please type your cruiser (3-long) coordinate: ");
            cruiserCoord = s.nextLine();

            while(test.coordChecker(cruiserCoord) == false)
            {
                System.out.print("That is not a valid coordinate. Please re-enter a valid coordinate: ");
                cruiserCoord = s.nextLine();
            }

            System.out.print("Please type the direction (right or down): ");
            cruiserDirect = s.nextLine();

            while(!(cruiserDirect.equals("right") || cruiserDirect.equals("down")))
            {
                System.out.print("That is not a valid direction. Please re-enter a valid direction: ");
                cruiserDirect = s.nextLine();
            }
            if(test.limitChecker(3,cruiserCoord,cruiserDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else if(!(test.overlapChecker(testBoard,cruiserCoord,3,cruiserDirect)))
            {
                System.out.println("The ship you described would overlap with an existing ship.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        testBoard = test.addShip(testBoard,cruiserCoord,3,cruiserDirect);
        System.out.println("\nYou successfully added your cruiser:\n" + testBoard);
        checked = false;

        // Submarine
        while(checked == false)
        {
            System.out.print("Please type your submarine (3-long) coordinate: ");
            submarineCoord = s.nextLine();

            while(test.coordChecker(submarineCoord) == false)
            {
                System.out.print("That is not a valid coordinate. Please re-enter a valid coordinate: ");
                submarineCoord = s.nextLine();
            }

            System.out.print("Please type the direction (right or down): ");
            submarineDirect = s.nextLine();

            while(!(submarineDirect.equals("right") || submarineDirect.equals("down")))
            {
                System.out.print("That is not a valid direction. Please re-enter a valid direction: ");
                submarineDirect = s.nextLine();
            }
            if(test.limitChecker(3,submarineCoord,submarineDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else if(!(test.overlapChecker(testBoard,submarineCoord,3,submarineDirect)))
            {
                System.out.println("The ship you described would overlap with an existing ship.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        testBoard = test.addShip(testBoard,submarineCoord,3,submarineDirect);
        System.out.println("\nYou successfully added your submarine:\n" + testBoard);
        checked = false;

        // Destroyer
        while(checked == false)
        {
            System.out.print("Please type your destroyer (2-long) coordinate: ");
            destroyerCoord = s.nextLine();

            while(test.coordChecker(destroyerCoord) == false)
            {
                System.out.print("That is not a valid coordinate. Please re-enter a valid coordinate: ");
                destroyerCoord = s.nextLine();
            }

            System.out.print("Please type the direction (right or down): ");
            destroyerDirect = s.nextLine();

            while(!(destroyerDirect.equals("right") || destroyerDirect.equals("down")))
            {
                System.out.print("That is not a valid direction. Please re-enter a valid direction: ");
                destroyerDirect = s.nextLine();
            }
            if(test.limitChecker(2,destroyerCoord,destroyerDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else if(!(test.overlapChecker(testBoard,destroyerCoord,2,destroyerDirect)))
            {
                System.out.println("The ship you described would overlap with an existing ship.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        testBoard = test.addShip(testBoard,destroyerCoord,2,destroyerDirect);
        System.out.println("\nYou successfully added your destroyer:\n" + testBoard);

        // Create Battleship Object
        BattleshipMaker play = new BattleshipMaker(carrierCoord,battleshipCoord,cruiserCoord,submarineCoord,destroyerCoord);
        playBoard = play.setDefensiveBoard(carrierDirect,battleshipDirect,cruiserDirect,submarineDirect,destroyerDirect);

         */

        BattleshipMaker opponent = new BattleshipMaker();
        String opponentBoard = opponent.randomBoard();
        System.out.println(opponentBoard);

        /*
        String emptyBoard = test.makeEmptyBoard();

        emptyBoard = test.addShip(emptyBoard,5,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(",")));
        System.out.println(test.addShip(emptyBoard,5,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(","))));
        opponentBoard = opponentBoard.substring(opponentBoard.indexOf(", ") + 2);

        emptyBoard = test.addShip(emptyBoard,4,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(",")));
        System.out.println(test.addShip(emptyBoard,4,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(","))));
        opponentBoard = opponentBoard.substring(opponentBoard.indexOf(", ") + 2);

        emptyBoard = test.addShip(emptyBoard,3,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(",")));
        System.out.println(test.addShip(emptyBoard,3,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(","))));
        opponentBoard = opponentBoard.substring(opponentBoard.indexOf(", ") + 2);

        emptyBoard = test.addShip(emptyBoard,3,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(",")));
        System.out.println(test.addShip(emptyBoard,3,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(","))));
        opponentBoard = opponentBoard.substring(opponentBoard.indexOf(", ") + 2);

        emptyBoard = test.addShip(emptyBoard,2,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(",")));
        System.out.println(test.addShip(emptyBoard,2,opponentBoard.substring(0,opponentBoard.indexOf(" ")),opponentBoard.substring(opponentBoard.indexOf(" ") + 1, opponentBoard.indexOf(","))));


         */
    }
}