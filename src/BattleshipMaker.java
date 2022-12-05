public class BattleshipMaker {
    // Instance Variables

    private String carrierCoord;

    private String battleshipCoord;
    private String cruiserCoord;
    private String submarineCoord;
    private String destroyerCoord;
    private final String LETTERS = "ABCDEFGHIJ";
    private final String NUMBERS = "1234567890";
    private String carrierDirect;
    private String battleshipDirect;
    private String cruiserDirect;
    private String submarineDirect;
    private String destroyerDirect;

    // Constructors
    /*
    empty constructor to access methods
     */
    public BattleshipMaker()
    {}

    /*
    creation of an object of this class that assigns the ship coordinates
    to specific coordinates determined by the parameters
     */
    public BattleshipMaker(String coord1, String coord2, String coord3, String coord4, String coord5)
    {
        carrierCoord = coord1;
        battleshipCoord = coord2;
        cruiserCoord = coord3;
        submarineCoord = coord4;
        destroyerCoord = coord5;
    }

    // Methods

    /*
    getter based on ship and coord/direction
     */
    public String getInfo(String ship, String infoType)
    {
        if (infoType.equals("coord"))
        {
            if (ship.equals("carrier"))
            {
                return carrierCoord;
            }
            else if (ship.equals("battleship"))
            {
                return battleshipCoord;
            }
            else if (ship.equals("cruiser"))
            {
                return cruiserCoord;
            }
            else if (ship.equals("submarine"))
            {
                return submarineCoord;
            }
            else
            {
                return destroyerCoord;
            }
        }
        else
        {
            if (ship.equals("carrier"))
            {
                return carrierDirect;
            }
            else if (ship.equals("battleship"))
            {
                return battleshipDirect;
            }
            else if (ship.equals("cruiser"))
            {
                return cruiserDirect;
            }
            else if (ship.equals("submarine"))
            {
                return submarineDirect;
            }
            else
            {
                return destroyerDirect;
            }
        }
    }

    /*
    creates an empty gridded string (A-J, 1-10)
     */
    public String makeEmptyBoard()
    {
        String emptyBoard = "";
        // 11 rows
        for (int i = 1; i < NUMBERS.length() + 1; i++)
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
            for (int index = 0; index < LETTERS.length(); index++)
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
        updateBoard = addShip(emptyBoard,5,carrierCoord,directCarrier);
        updateBoard = addShip(updateBoard,4,battleshipCoord,directBattle);
        updateBoard = addShip(updateBoard,3,cruiserCoord,directCruiser);
        updateBoard = addShip(updateBoard,3,submarineCoord,directSub);
        updateBoard = addShip(updateBoard,2,destroyerCoord,directDestroy);

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
        int behind = updateBoard.indexOf(coord.substring(1)) + 2 + LETTERS.indexOf(coord.substring(0, 1)) * 2;
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
            int letterIndex = LETTERS.indexOf(coord.substring(0,1));
            if (letterIndex <= 10 - shipLength)
            {
                return true;
            }
        }
        else
        {
            int num = NUMBERS.indexOf(coord.substring(coord.length() - 1));
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
        if ((coord.length() != 2) && (coord.length() != 3))
        {
            return false;
        }

        int checker = 0;
        for (int index = 0; index < coord.length(); index++)
        {
            for (int i = 0; i < LETTERS.length(); i++)
            {
                if (index == 0)
                {
                    if (coord.substring(index, index + 1).equals(LETTERS.substring(i, i + 1)))
                    {
                        checker++;
                    }
                }
                else
                {
                    if (coord.substring(index, index + 1).equals(LETTERS.substring(i, i + 1)))
                    {
                        return false;
                    }
                }
            }
        }

        if (checker > 0)
        {
            String letter = coord.substring(0,1);
            if (NUMBERS.indexOf(coord.substring(1,2)) == -1)
            {
                return false;
            }
            if ((coord.length() == 3) && (NUMBERS.indexOf(coord.substring(2,3)) == -1))
            {
                return false;
            }
            int num = Integer.parseInt(coord.substring(1));
            if (LETTERS.indexOf(letter) != -1 && num <= 10 && num >= 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    /*
    checks if the ship coordinates overlap
    uses the baseboard and checks if each space the ship is to be placed already has a "o" positioned
     */
    public boolean overlapChecker(String baseBoard, int shipLength, String coord, String direction)
    {
        int row = baseBoard.indexOf(coord.substring(1));
        // multiply by two to account for spaces, add 1 to make index a count (ABCDE = 12345)
        int letterIndex = (LETTERS.indexOf(coord.substring(0,1)) + 1)* 2;
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

    /*
    creates a board with a randomized set of ships
    sets the ship coords and ship directions
     */
    public String randomBoard()
    {
        String randomBoard = makeEmptyBoard();
        String directionExtract = "";

        // length of only the board itself is 253
        randomBoard = addRandomShip(randomBoard,5);
        carrierCoord = randomBoard.substring(253, 253 + (randomBoard.substring(253)).indexOf(" "));
        directionExtract = randomBoard.substring(253);
        carrierDirect = directionExtract.substring((directionExtract.indexOf(" ") + 1));
        randomBoard = randomBoard.substring(0,253);

        randomBoard = addRandomShip(randomBoard,4);
        battleshipCoord = randomBoard.substring(253, 253 + (randomBoard.substring(253)).indexOf(" "));
        directionExtract = randomBoard.substring(253);
        battleshipDirect = directionExtract.substring((directionExtract.indexOf(" ") + 1));
        randomBoard = randomBoard.substring(0,253);

        randomBoard = addRandomShip(randomBoard,3);
        cruiserCoord = randomBoard.substring(253, 253 + (randomBoard.substring(253)).indexOf(" "));
        directionExtract = randomBoard.substring(253);
        cruiserDirect = directionExtract.substring((directionExtract.indexOf(" ") + 1));
        randomBoard = randomBoard.substring(0,253);

        randomBoard = addRandomShip(randomBoard,3);
        submarineCoord = randomBoard.substring(253, 253 + (randomBoard.substring(253)).indexOf(" "));
        directionExtract = randomBoard.substring(253);
        submarineDirect = directionExtract.substring((directionExtract.indexOf(" ") + 1));
        randomBoard = randomBoard.substring(0,253);

        randomBoard = addRandomShip(randomBoard,2);
        destroyerCoord = randomBoard.substring(253, 253 + (randomBoard.substring(253)).indexOf(" "));
        directionExtract = randomBoard.substring(253);
        destroyerDirect = directionExtract.substring((directionExtract.indexOf(" ") + 1));
        randomBoard = randomBoard.substring(0,253);

        return randomBoard;
    }

    /*
    adds a ship of the specified length to the given base board
    the coordinate and direction of the ship is randomized
     */
    public String addRandomShip(String baseBoard, int shipLength)
    {
        boolean checked = false;
        String coord = "";
        String direction = "";

        while (checked == false)
        {
            coord = randomCoord();
            int random = (int) (Math.random() * 2);
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
                        // will be used to assign variables to keep track of coordinate and direction
                        baseBoard += coord + " " + direction;
                    }
                }
            }
        }
        return baseBoard;
    }

    public String randomCoord()
    {
        String coord = "";

        int random = (int) (Math.random() * 10);
        coord = LETTERS.substring(random, random + 1);
        random = 1 + (int) (Math.random() * 10);
        coord += Integer.toString(random);

        return coord;
    }

    /*
    places a marker "x" on the provided board if the existing area is "+" (miss)
    places a marker "#" on the provided board if the existing area is "o" (hit)
    returns a statement saying that the area has already been shot if above markers are already present
     */
    public String takeShot(String baseBoard, String coord)
    {
        String updateBoard = "";
        int behind = baseBoard.indexOf(coord.substring(1)) + 1 + (LETTERS.indexOf(coord.substring(0,1)) + 1) * 2;
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

    public String hitMarker(String baseBoard, String coord)
    {
        String updateBoard = "";
        int behind = baseBoard.indexOf(coord.substring(1)) + 1 + (LETTERS.indexOf(coord.substring(0,1)) + 1) * 2;
        String target = baseBoard.substring(behind, behind + 1);

            updateBoard = baseBoard.substring(0,behind) + "#" + baseBoard.substring(behind + 1);

        return updateBoard;
    }

    /*
    returns "hit" or "miss" depending on the substring at the target index
     */
    public String checkHit(String baseBoard, String coord)
    {
        String result = "";
        int behind = baseBoard.indexOf(coord.substring(1)) + 1 + (LETTERS.indexOf(coord.substring(0,1)) + 1) * 2;
        String target = baseBoard.substring(behind, behind + 1);

        if (target.equals("+") || target.equals("x") || target.equals("o"))
        {
            result = "miss";
        }
        else
        {
            result = "hit";
        }
        return result;
    }

    /*
    checks if a ship is up and alive
    returns true if up, false if down
     */
    public boolean checkShipStatus(String baseBoard, int shipLength, String coord, String direction)
    {
        String shipStatus = "";
        // gets to the index behind the coordinate
        int behind = baseBoard.indexOf(coord.substring(1)) + 1 + (LETTERS.indexOf(coord.substring(0,1)) + 1) * 2;

        if (direction.equals("right"))
        {
            // creates a String that adds true or false depending on the status of each coordinate of the ship
            for (int i = 0; i < shipLength * 2; i += 2)
            {
                String check = baseBoard.substring(behind + i, behind + i + 1);
                if (check.equals("#"))
                {
                    shipStatus += "false";
                }
                else
                {
                    shipStatus += "true";
                }
            }
        }
        else
        {
            for (int i = 0; i < shipLength; i++)
            {
                String check = baseBoard.substring(behind + (i * 23), behind + (i * 23) + 1);
                if (check.equals("#"))
                {
                    shipStatus += "false";
                }
                else
                {
                    shipStatus += "true";
                }
            }
        }

        // searches the String for "true", which shows the ship is up, if not there (-1), returns false
        if (!shipStatus.contains("true"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public String toString()
    {
        String info = "Object of the game: Be the first to sink all 5 of the opponent's ships. You are playing against the program AI.\n" +
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
                "If asked for direction, your battleship will either go \"right\" or \"down\" from your specified coordinate.\n";

        return info;
    }
}
