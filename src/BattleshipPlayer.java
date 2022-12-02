import java.util.Scanner;
public class BattleshipPlayer {
    public static void main(String[] args) {
        // Instance Variables
        BattleshipMaker setup = new BattleshipMaker();
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

        String setupBoard = setup.makeEmptyBoard();
        String defensiveBoard = "";
        String opponentBoard = "";
        String attackBoard = "";
        String originalBoard = "";

        boolean checked = false;

        boolean checkCarrier = true;
        boolean checkBattleship = true;
        boolean checkCruiser = true;
        boolean checkSubmarine = true;
        boolean checkDestroyer = true;

        boolean carrierSunk = false;
        boolean battleshipSunk = false;
        boolean cruiserSunk = false;
        boolean submarineSunk = false;
        boolean destroyerSunk = false;

        // User Input Main Menu
        System.out.println("Welcome to the Battleship Game.\nType \"P\" to play.\nType \"I\" for info on how to play.");
        input = s.nextLine();

        while(!(input.equals("P")))
        {
            if(input.equals("I"))
            {
                System.out.println(setup);
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
        System.out.println(setupBoard);

        // Carrier
        while(checked == false)
        {
            System.out.print("Please type your carrier (5-long) coordinate: ");
            carrierCoord = s.nextLine();

            while(setup.coordChecker(carrierCoord) == false)
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
            if(setup.limitChecker(5,carrierCoord,carrierDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        setupBoard = setup.addShip(setupBoard,5,carrierCoord,carrierDirect);
        System.out.println("\nYou successfully added your carrier:\n" + setupBoard);
        checked = false;

        // Battleship
        while(checked == false)
        {
            System.out.print("Please type your battleship (4-long) coordinate: ");
            battleshipCoord = s.nextLine();

            while(setup.coordChecker(battleshipCoord) == false)
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
            if(setup.limitChecker(4,battleshipCoord,battleshipDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else if(!(setup.overlapChecker(setupBoard,4,battleshipCoord,battleshipDirect)))
            {
                System.out.println("The ship you described would overlap with an existing ship.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        setupBoard = setup.addShip(setupBoard,4,battleshipCoord,battleshipDirect);
        System.out.println("\nYou successfully added your battleship:\n" + setupBoard);
        checked = false;

        // Cruiser
        while(checked == false)
        {
            System.out.print("Please type your cruiser (3-long) coordinate: ");
            cruiserCoord = s.nextLine();

            while(setup.coordChecker(cruiserCoord) == false)
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
            if(setup.limitChecker(3,cruiserCoord,cruiserDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else if(!(setup.overlapChecker(setupBoard,3,cruiserCoord,cruiserDirect)))
            {
                System.out.println("The ship you described would overlap with an existing ship.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        setupBoard = setup.addShip(setupBoard,3,cruiserCoord,cruiserDirect);
        System.out.println("\nYou successfully added your cruiser:\n" + setupBoard);
        checked = false;

        // Submarine
        while(checked == false)
        {
            System.out.print("Please type your submarine (3-long) coordinate: ");
            submarineCoord = s.nextLine();

            while(setup.coordChecker(submarineCoord) == false)
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
            if(setup.limitChecker(3,submarineCoord,submarineDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else if(!(setup.overlapChecker(setupBoard,3,submarineCoord,submarineDirect)))
            {
                System.out.println("The ship you described would overlap with an existing ship.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        setupBoard = setup.addShip(setupBoard,3,submarineCoord,submarineDirect);
        System.out.println("\nYou successfully added your submarine:\n" + setupBoard);
        checked = false;

        // Destroyer
        while(checked == false)
        {
            System.out.print("Please type your destroyer (2-long) coordinate: ");
            destroyerCoord = s.nextLine();

            while(setup.coordChecker(destroyerCoord) == false)
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
            if(setup.limitChecker(2,destroyerCoord,destroyerDirect) == false)
            {
                System.out.println("The ship you described would not fit within the parameters of the board.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else if(!(setup.overlapChecker(setupBoard,2,destroyerCoord,destroyerDirect)))
            {
                System.out.println("The ship you described would overlap with an existing ship.\n" +
                        "Please re-enter a valid coordinate and direction.\n");
            }
            else
            {
                checked = true;
            }
        }

        setupBoard = setup.addShip(setupBoard,2,destroyerCoord,destroyerDirect);
        System.out.println("\nYou successfully added your destroyer:\n" + setupBoard);

        // Create Objects
        BattleshipMaker play = new BattleshipMaker(carrierCoord,battleshipCoord,cruiserCoord,submarineCoord,destroyerCoord);
        defensiveBoard = play.setDefensiveBoard(carrierDirect,battleshipDirect,cruiserDirect,submarineDirect,destroyerDirect);

        AI ai = new AI(defensiveBoard,carrierCoord,battleshipCoord,cruiserCoord,submarineCoord,destroyerCoord,carrierDirect,battleshipDirect,cruiserDirect,submarineDirect,destroyerDirect);

        BattleshipMaker opponent = new BattleshipMaker();
        opponentBoard = opponent.randomBoard();
        attackBoard = opponent.makeEmptyBoard();

        while ((checkCarrier || checkBattleship || checkCruiser || checkSubmarine || checkDestroyer) || (ai.checkSinkStatus(defensiveBoard)))
        {
            System.out.println(attackBoard);
            System.out.print("Your turn - Take your shot: ");
            input = s.nextLine();
            while (opponent.takeShot(opponentBoard,input).equals("A shot has already been taken at " + input + ". Please take another shot."))
            {
                    System.out.print(opponent.takeShot(attackBoard,input) + "\nTake another shot: ");
                    input = s.nextLine();
            }

            originalBoard = opponentBoard;
            opponentBoard = opponent.takeShot(opponentBoard,input);
            if ((opponent.checkHit(originalBoard,input)).equals("hit"))
            {
                attackBoard = opponent.hitMarker(attackBoard,input);
            }
            else
            {
                attackBoard = opponent.takeShot(attackBoard,input);
            }
            System.out.println("\n" + attackBoard);
            System.out.println("Your shot at " + input + " was a " + opponent.checkHit(originalBoard,input) + "!");

            checkCarrier = opponent.checkShipStatus(opponentBoard,5,opponent.getInfo("carrier","coord"),opponent.getInfo("carrier","direction"));
            checkBattleship = opponent.checkShipStatus(opponentBoard,4,opponent.getInfo("battleship","coord"),opponent.getInfo("battleship","direction"));
            checkCruiser = opponent.checkShipStatus(opponentBoard,3,opponent.getInfo("cruiser","coord"),opponent.getInfo("cruiser","direction"));
            checkSubmarine = opponent.checkShipStatus(opponentBoard,3,opponent.getInfo("submarine","coord"),opponent.getInfo("submarine","direction"));
            checkDestroyer = opponent.checkShipStatus(opponentBoard,2,opponent.getInfo("destroyer","coord"),opponent.getInfo("destroyer","direction"));

            if (!checkCarrier && !carrierSunk)
            {
                System.out.println("You sunk the opponent's carrier!");
                carrierSunk = true;
            }
            else if (!checkBattleship && !battleshipSunk)
            {
                System.out.println("You sunk the opponent's battleship!");
                battleshipSunk = true;
            }
            else if (!checkCruiser && !cruiserSunk)
            {
                System.out.println("You sunk the opponent's cruiser!");
                cruiserSunk = true;
            }
            else if (!checkSubmarine && !submarineSunk)
            {
                System.out.println("You sunk the opponent's submarine!");
                submarineSunk = true;
            }
            else if (!checkDestroyer && !destroyerSunk)
            {
                System.out.println("You sunk the opponent's destroyer!");
                destroyerSunk = true;
            }

            // AI Turn

            ai.aiTurn();
            defensiveBoard = ai.getAttackBoard();
            System.out.println(defensiveBoard);
            System.out.println("Your opponent shot a " + ai.getDisplayResult() + " at " + ai.getDisplayCoord() + "!\n\n");
        }

        if (checkCarrier || checkBattleship || checkCruiser || checkSubmarine || checkDestroyer)
        {
            System.out.println("You sunk all of your opponent's ships! You win!");
        }
        else if (ai.checkSinkStatus(defensiveBoard))
        {
            System.out.println("You lost! Your opponent sunk all of your ships!");
        }
    }
}
