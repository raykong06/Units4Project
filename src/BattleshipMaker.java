/**
 * The BattleshipMaker class represents the board which the game "Battleship" is played on.
 * It contains all the information associated with the game, including the coordinates and directions.
 */
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

    /**
     * Constructor for the BattleshipMaker class. This creates a new instance of a BattleshipMaker
     * and sets the instance variables to empty String values.
     * Intended for use with gradual addition of information via methods.
     */
    public BattleshipMaker()
    {
        carrierCoord = "";
        battleshipCoord = "";
        cruiserCoord = "";
        submarineCoord = "";
        destroyerCoord = "";

        carrierDirect = "";
        battleshipDirect = "";
        cruiserDirect = "";
        submarineDirect = "";
        destroyerDirect = "";
    }

    /**
     * Constructor for the BattleshipMaker class. This creates a new instance of a BattleshipMaker
     * and sets the instance variables specific coordinates and directions determined by the parameters.
     *
     * @param coord1 represents the coordinate point (in letter-number format) of the carrier ship
     * @param coord2 represents the coordinate point (in letter-number format) of the battleship
     * @param coord3 represents the coordinate point (in letter-number format) of the cruiser ship
     * @param coord4 represents the coordinate point (in letter-number format) of the submarine ship
     * @param coord5 represents the coordinate point (in letter-number format) of the destroyer ship
     * @param directCarrier represents the direction (down or right) of the carrier ship
     * @param directBattle represents the direction (down or right) of the battleship
     * @param directCruiser represents the direction (down or right) of the cruiser ship
     * @param directSub represents the direction (down or right) of the submarine ship
     * @param directDestroy represents the direction (down or right) of the destroyer ship
     */
    public BattleshipMaker(String coord1, String coord2, String coord3, String coord4, String coord5, String directCarrier, String directBattle, String directCruiser, String directSub, String directDestroy)
    {
        carrierCoord = coord1;
        battleshipCoord = coord2;
        cruiserCoord = coord3;
        submarineCoord = coord4;
        destroyerCoord = coord5;

        carrierDirect = directCarrier;
        battleshipDirect = directBattle;
        cruiserDirect = directCruiser;
        submarineDirect = directSub;
        destroyerDirect = directDestroy;
    }

    // Methods

    /**
     * The getInfo method will return the information in the parameter-specified instance variable.
     *
     * @param ship represents the type of ship that is desired to be accessed
     * @param infoType represents the type of information that is desired to be accessed (coord or direction)
     * @return returns the String which the specified instance variable contains
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

    /**
     * the makeEmptyBoard method will return a String which represents an empty battleship board.
     *
     * @return returns a String that contains a gridded board that aligns with numbers 1-10
     * and letters A-J. Coordinates are represented in the String by "+" signs.
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

    /**
     * The setDefensiveBoard method returns a String with the defensive (user) gridded board
     * that includes all 5 of the ships, represented by "o" signs. The method utilizes the existing
     * coordinates and directions in the instance variables.
     *
     * @return returns a String that represents a full defensive board with all ships positioned.
     */
    public String setDefensiveBoard()
    {
        String emptyBoard = makeEmptyBoard();
        String updateBoard = "";

        // initialize an empty board and add to it ship by ship
        updateBoard = addShip(emptyBoard,5,carrierCoord,carrierDirect);
        updateBoard = addShip(updateBoard,4,battleshipCoord,battleshipDirect);
        updateBoard = addShip(updateBoard,3,cruiserCoord,cruiserDirect);
        updateBoard = addShip(updateBoard,3,submarineCoord,submarineDirect);
        updateBoard = addShip(updateBoard,2,destroyerCoord,destroyerDirect);

        return updateBoard;
    }

    /**
     * The addShip method takes an inputted board as a base to add a ship
     * by replacing the "+" signs with "o" signs at the indicated coordinate,
     * direction, and for the indicated ship length.
     *
     * @param baseBoard represents the board for which the ship will be added onto
     * @param shipLength represents the length of units that the added ship will be
     * @param coord represents the coordinate which the added ship will start at
     * @param direction represents the orientation which the added ship will face toward
     * @return returns a String that represents the original board inputted with an added ship as described.
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

    /**
     * The limitChecker method checks if a specified ship would fit in
     * the parameters of the board. If it would fit, return true, otherwise
     * the ship would not fit and the method will return false.
     *
     * @param shipLength represents the length in units that the tested ship is
     * @param coord represents the coordinate which the tested ship starts at
     * @param direction represents the orientation of the tested ship
     * @return returns true if the hypothetical ship would fit within the parameters of the board,
     * otherwise returns false if it cannot.
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

    /**
     * The coordChecker method checks if the provided String is a valid coordinate,
     * in that the letter is the range of A-J and that the number is between 1-10 inclusive.
     * The coordinate will be checked for proper formatting as well.
     *
     * @param coord represents the coordinate point to be checked
     * @return returns true if the String is a valid coordinate, otherwise returns false.
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

    /**
     * The overlapChecker method checks if a hypothetically placed ship would
     * overlap with existing ships upon the provided board. This method checks
     * if each space the ship is to be placed already has an "o" positioned on
     * the original board.
     *
     * @param baseBoard represents the board which the ship overlap is to be tested upon
     * @param shipLength represents the units of length of the ship that is tested
     * @param coord represents the coordinate which the tested ship starts upon
     * @param direction represents the orientation of the tested ship
     * @return returns true if no overlap is detected, otherwise returns false,
     * indicating that overlap would occur.
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

    /**
     * The randomBoard method creates a String that represents a board with a randomized
     * set of ships. The respective instance variables for ship coordinates and ship
     * directions are updated to reflect their random positions.
     *
     * @return returns a String that represents a board with a randomized set of ships.
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

    /**
     * The addRandomShip method adds a ship of the specified length to the provided
     * board. The coordinate position and orientation of this added ship is randomized.
     * At the end of the String that the method returns, after the board, the
     * coordinate and orientation of the added ship are appended with a space separating them.
     *
     * @param baseBoard represents the board which the ship is added to
     * @param shipLength represents the units of length of the added ship
     * @return returns a String that represents the provided board inputted with an added ship
     * of the specified length with a randomized coordinate and a randomized orientation,
     * with that coordinate and orientation appended to the end of the String.
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

    /**
     * The randomCoord method generates a random valid coordinate String with the letter
     * being between A-J and the number being between 1-10.
     *
     * @return returns a String that represents a randomly generated valid coordinate point
     * within the board parameters.
     */
    public String randomCoord()
    {
        String coord = "";

        int random = (int) (Math.random() * 10);
        coord = LETTERS.substring(random, random + 1);
        random = 1 + (int) (Math.random() * 10);
        coord += Integer.toString(random);

        return coord;
    }

    /**
     * The takeShot method uses the provided board in the parameter and simulates a shot
     * taken at the parameter-provided coordinate point. The method will replace a marker
     * "x" on the provided board if the existing area is "+" (representing a miss), and
     * will replace a marker "#" on the provided board if the existing area is "o" (representing
     * a hit). The method will return a String of the provided board with the respective replaced
     * markers if either of these conditions solve true, however if they do not, the method will
     * return a String stating that a shot has already been taken at the coordinate described.
     *
     * @param baseBoard represents the board for which the shot will be tested then altered to simulate a shot
     * @param coord represents the coordinate point for which the shot is attempted to be taken at
     * @return returns a String that represents the board with a shot taken at the coordinate if the existing
     * marker at the coordinate is a "+" or an "o", otherwise returns a String stating that a shot has already
     * been taken at the coordinate specified.
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

    /**
     * The hitMarker method places a hit marker, represented by "#", at the coordinate specified in the
     * parameter and on the board provided in the parameter. The hit marker will replace whatever
     * symbol is currently existing at the coordinate specified.
     *
     * @param baseBoard represents the board for which the hit marker will be placed
     * @param coord represents the coordinate point for which the existing symbol will be replaced with a hit marker
     * @return returns a String representing the board provided in the parameter with a hit marker "#" placed
     * at the coordinate specified.
     */
    public String hitMarker(String baseBoard, String coord)
    {
        String updateBoard = "";
        int behind = baseBoard.indexOf(coord.substring(1)) + 1 + (LETTERS.indexOf(coord.substring(0,1)) + 1) * 2;
        String target = baseBoard.substring(behind, behind + 1);

        updateBoard = baseBoard.substring(0,behind) + "#" + baseBoard.substring(behind + 1);

        return updateBoard;
    }

    /**
     * The checkHit method will return "hit" or "miss" depending on the symbol that exists at
     * the coordinate point provided in the parameter. If the symbol at the coordinate point
     * is a "+", "x", or an "o", the method will return "miss" since they indicate a shot has
     * yet to be taken at the specified coordinate or the shot taken was a miss. Otherwise, if
     * none of those symbols are apparent, presumably signifying the symbol present is a "#",
     * then "hit" will be returned, since "#" represents a hit.
     *
     * @param baseBoard represents the board for which the hit marker is checked for
     * @param coord represents the coordinate point on the board for which the hit marker is checked for
     * @return returns a String stating "miss" if the existing symbol at the coordinate point is
     * a "+", "x", or an "o", otherwise returns a String stating "hit".
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

    /**
     * The checkShipStatus method checks if a specified ship is up and "alive" on the provided board.
     * The ship is considered down if the entire contents of that ship is composed of "#" .Returns true
     * if the specified ship is up, otherwise returns false.
     *
     * @param baseBoard represents the board for which the specified ship's status is checked for
     * @param shipLength represents the units of length of the specified ship
     * @param coord represents the coordinate point for which the specified ship starts upon
     * @param direction represents the orientation of the specified ship
     * @return returns false if the specified ship is entirely composed of "#" symbols, meaning
     * the ship is shot down, otherwise returns true, meaning the ship is still up.
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

    /**
     * toString method for the BattleshipMaker class. This method will return a String showing
     * the instructions on how to play the Battleship game.
     *
     * @return returns a String showing instructions on how to play the Battleship game and what the symbols mean.
     */
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