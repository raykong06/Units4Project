public class BattleshipMaker {
    // Instance Variables
    private String carrier;
    private String battleship;
    private String cruiser;
    private String submarine;
    private String destroyer;
    private String letters = "ABCDEFGHIJ";
    private String numbers = "1234567890";
    private String carrierC = "";

    // Constructors
    /*
    test constructor to access methods
     */
    public BattleshipMaker(){}

    /*
    creation of an object of this class that assigns the ship coordinates
    to specific coordinates determined by the parameters
     */
    public BattleshipMaker(String coord1, String coord2, String coord3, String coord4, String coord5)
    {
        carrier = coord1;
        battleship = coord2;
        cruiser = coord3;
        submarine = coord4;
        destroyer = coord5;
    }

    // Methods
    /*
    changes the coordinates associated with each battleship
     */
    public void changeCoords(String coord1, String coord2, String coord3, String coord4, String coord5)
    {
        carrier = coord1;
        battleship = coord2;
        cruiser = coord3;
        submarine = coord4;
        destroyer = coord5;
    }

    /*
    creates an empty gridded string (A-J, 1-10)
     */
    public String makeEmptyBoard()
    {
        String emptyBoard = "";
        // 11 rows
        for (int i = 1; i < numbers.length() + 1; i++)
        {
            if (i == 1)
            {
                emptyBoard = "   A B C D E F G H I J\n";
            }
            if (i < 10)
            {
                emptyBoard = emptyBoard + i + " ";
            }
            else
            {
                emptyBoard = emptyBoard + i;
            }
            // 20 columns
            for (int index = 0; index < letters.length(); index++)
            {
                emptyBoard = emptyBoard + " +";
            }
            emptyBoard = emptyBoard + "\n";
        }
        return emptyBoard;
    }

    /*
    creates a string with the defensive (user) gridded board
    that includes all the ships, represented by "o"
    utilizes addShip method
     */
    public String setDefensiveBoard(String directCarrier, String directBattle, String directCruiser, String directSub, String directDestroy)
    {
        String emptyBoard = makeEmptyBoard();
        String updateBoard = "";

        // initialize an empty board and add to it ship by ship
        updateBoard = addShip(emptyBoard,5,carrier,directCarrier);
        updateBoard = addShip(updateBoard,4,battleship,directBattle);
        updateBoard = addShip(updateBoard,3,cruiser,directCruiser);
        updateBoard = addShip(updateBoard,3,submarine,directSub);
        updateBoard = addShip(updateBoard,2,destroyer,directDestroy);

        return updateBoard;
    }

    /*
    takes a base board, adds a type of ship to it, depending on
    ship type and the direction to which the ship goes

    take the first half of the board String, add the ship,
    then add the rest of the board
     */
    public String addShip(String baseBoard, int shipLength, String coord, String direction)
    {
        // find the index of the row, and add to the index up until the coordinate (behind)
        String updateBoard = baseBoard;
        int behind = updateBoard.indexOf(coord.substring(1)) + 2 + letters.indexOf(coord.substring(0, 1)) * 2;
        updateBoard = updateBoard.substring(0, behind);

        // if ship goes right, add the ship (" o"), then add the back half of board
        if (direction.equals("right"))
        {
            for (int i = 0; i < shipLength; i++)
            {
                updateBoard = updateBoard + " o";
            }
            updateBoard = updateBoard + baseBoard.substring(behind + shipLength * 2);
        }
        /* if ship goes down, add a single ship marker,
        add the board space between, until ship length is met, then add back half of board
         */
        else
        {
            int inBetween = behind;
            for (int i = 0; i < shipLength; i++)
            {
                updateBoard = updateBoard + " o";
                // if else statement prevents out of bounds exception
                if (i < shipLength - 1)
                {
                    updateBoard = updateBoard + baseBoard.substring(inBetween + 2, inBetween + 23);
                    inBetween = inBetween + 23;
                }
                else
                {
                    inBetween = inBetween + 2;
                }
            }
            updateBoard = updateBoard + baseBoard.substring(inBetween);
        }

        return updateBoard;
    }

    /*
    checks if the ship will fit in the parameters of the board
    true if yes, false if no
     */
    public boolean limitChecker(int shipLength, String coord, String direction)
    {
        if (direction.equals("right"))
        {
            int letterIndex = letters.indexOf(coord.substring(0,1));
            if (letterIndex <= 10 - shipLength)
            {
                return true;
            }
        }
        else
        {
            int num = numbers.indexOf(coord.substring(coord.length() - 1));
            if (num <= 10 - shipLength)
            {
                return true;
            }
        }
        return false;
    }

    /*
    checks if the coordinate is a valid coordinate to be used
    true if yes, false if no
     */
    public boolean coordChecker(String coord)
    {
        String letter = coord.substring(0,1);
        int num = Integer.parseInt(coord.substring(1));

        if (letters.indexOf(letter) != -1 && num <= 10 && num >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
    checks if the ship coordinates overlap
    uses the baseboard and checks if each space the ship is to be placed already has a "o" positioned
     */
    public boolean overlapChecker(String baseBoard, int shipLength, String coord, String direction)
    {
        int row = baseBoard.indexOf(coord.substring(1));
        // multiply by two to account for spaces, add 1 to make index a count (ABCDE = 12345)
        int letterIndex = (letters.indexOf(coord.substring(0,1)) + 1)* 2;
        // add 1 to account for space after the
        int behind = row + 1 + letterIndex;

        if (direction.equals("right"))
        {
            for(int i = 0; i < shipLength * 2; i += 2)
            {
                String check = baseBoard.substring(behind + i, behind + i + 1);
                if (check.equals("o"))
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            for(int i = 0; i < shipLength; i++)
            {
                String check = baseBoard.substring(behind + (i * 23),behind + (i * 23) + 1);
                if (check.equals("o"))
                {
                    return false;
                }
            }
            return true;
        }
    }

    public String randomBoard()
    {
        String randomBoard = makeEmptyBoard();

        randomBoard = addRandomShip(randomBoard,5);
        randomBoard = addRandomShip(randomBoard,4);
        randomBoard = addRandomShip(randomBoard,3);
        randomBoard = addRandomShip(randomBoard,3);
        randomBoard = addRandomShip(randomBoard,2);


        return randomBoard;
    }

    public String addRandomShip(String baseBoard, int shipLength)
    {
        boolean checked = false;
        String coord = "";
        String direction = "";

        while (checked == false)
        {
            int random = (int) (Math.random() * 10);
            coord = letters.substring(random, random + 1);
            random = 1 + (int) (Math.random() * 10);
            coord += Integer.toString(random);
            random = (int) (Math.random() * 2);
            if (random == 0)
            {
                direction = "right";
            }
            else
            {
                direction = "down";
            }
            if (coordChecker(coord))
            {
                if (limitChecker(shipLength, coord, direction))
                {
                    if (overlapChecker(baseBoard,shipLength,coord,direction))
                    {
                        checked = true;
                        baseBoard = addShip(baseBoard, shipLength, coord, direction);
                    }
                }
            }
        }
        return baseBoard;
    }

    /*
    places a marker "x" on the provided board if the existing area is "+" (miss)
    places a marker "#" on the provided board if the existing area is "o" (hit)
    returns a statement saying that the area has already been shot if above markers are already present
     */

    public String takeShot(String baseBoard, String coord)
    {
        String updateBoard = "";
        int behind = baseBoard.indexOf(coord.substring(1)) + 1 + (letters.indexOf(coord.substring(0,1)) + 1) * 2;
        String target = baseBoard.substring(behind, behind + 1);

        if (target.equals("+"))
        {
            updateBoard = baseBoard.substring(0,behind) + "x" + baseBoard.substring(behind + 1);
        }
        else if (target.equals("o"))
        {
            updateBoard = baseBoard.substring(0,behind) + "#" + baseBoard.substring(behind + 1);
        }
        else
        {
            return "A shot has already been taken at " + coord + ". Please take another shot.";
        }
        return updateBoard;
    }

    /*
    returns "hit" or "miss" depending on the substring at the target index
     */
    public String checkHit(String baseBoard, String coord)
    {
        String result = "";
        int behind = baseBoard.indexOf(coord.substring(1)) + 1 + (letters.indexOf(coord.substring(0,1)) + 1) * 2;
        String target = baseBoard.substring(behind, behind + 1);

        if (target.equals("+"))
        {
            result = "miss";
            return result;
        }
        else if (target.equals("o"))
        {
            result = "hit";
            return result;
        }
        else
        {
            result = "space";
            return result;
        }
    }

    public boolean checkShipStatus(String checkBoard, int shipLength, String coord, String direction)
    {

    }

}
