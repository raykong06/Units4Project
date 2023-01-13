/**
 * The AI class represents the programmed opponent which the user plays against.
 * It contains all the information associated with the user's board, such as
 * ships and coordinates, as well as the methods to take simulate turns.
 */
public class AI {

    private String attackBoard;

    private boolean sinkStatus;
    private boolean hitStatus;
    private boolean continueShot;
    private String continueCoord;
    private int direction;
    private String change;
    private String successfulCoord;
    private String displayCoord;
    private String displayResult;

    private String carrierCoord;

    private String battleshipCoord;
    private String cruiserCoord;
    private String submarineCoord;
    private String destroyerCoord;

    private String carrierDirect;
    private String battleshipDirect;
    private String cruiserDirect;
    private String submarineDirect;
    private String destroyerDirect;

    private final String LETTERS = "ABCDEFGHIJ";

    BattleshipMaker ai = new BattleshipMaker();

    /**
     * Constructor for the AI class. This creates a new instance of an AI, and assigns the information provided
     * by the parameters to the instance variables. The information passed through the parameters represents
     * the information about the user's board.
     *
     * @param attackBoard represents the user's board, with all ships filled in.
     * @param carrierCoord represents the coordinate point (in letter-number format) of the user's carrier ship
     * @param battleshipCoord represents the coordinate point (in letter-number format) of the user's battleship
     * @param cruiserCoord represents the coordinate point (in letter-number format) of the user's cruiser ship
     * @param submarineCoord represents the coordinate point (in letter-number format) of the user's submarine
     * @param destroyerCoord represents the coordinate point (in letter-number format) of the user's destroyer ship
     * @param carrierDirect represents the direction (down or right) of the user's carrier ship
     * @param battleshipDirect represents the direction (down or right) of the user's battleship
     * @param cruiserDirect represents the direction (down or right) of the user's cruiser ship
     * @param submarineDirect represents the direction (down or right) of the user's submarine
     * @param destroyerDirect represents the direction (down or right) of the user's destroyer ship
     */
    public AI(String attackBoard, String carrierCoord, String battleshipCoord, String cruiserCoord, String submarineCoord, String destroyerCoord, String carrierDirect, String battleshipDirect, String cruiserDirect, String submarineDirect, String destroyerDirect)
    {
        this.attackBoard = attackBoard;
        sinkStatus = true;
        hitStatus = false;

        this.carrierCoord = carrierCoord;
        this.carrierDirect = carrierDirect;

        this.battleshipCoord = battleshipCoord;
        this.battleshipDirect = battleshipDirect;

        this.cruiserCoord = cruiserCoord;
        this.cruiserDirect = cruiserDirect;

        this.submarineCoord = submarineCoord;
        this.submarineDirect = submarineDirect;

        this.destroyerCoord = destroyerCoord;
        this.destroyerDirect = destroyerDirect;

        change = "ttttt";
    }

    /**
     * The aiTurn method will simulate a turn and shot by the AI opponent. The results from the turn will implicate
     * the decisions that will be made for subsequent turns. For example, if the shot was a hit, subsequent turns
     * will take shots in the general vicinity until the ship gets taken down.
     */
    public void aiTurn()
    {
        boolean successfulShot = false;
        String coord = "";
        String checkChange;

        if (ai.checkShipStatus(attackBoard,5,carrierCoord,carrierDirect))
        {
            checkChange = "t";
        } else checkChange = "f";
        if (ai.checkShipStatus(attackBoard,4,battleshipCoord,battleshipDirect))
        {
            checkChange += "t";
        } else checkChange += "f";
        if (ai.checkShipStatus(attackBoard,3,cruiserCoord,cruiserDirect))
        {
            checkChange += "t";
        } else checkChange += "f";
        if (ai.checkShipStatus(attackBoard,3,submarineCoord,submarineDirect))
        {
            checkChange += "t";
        } else checkChange += "f";
        if (ai.checkShipStatus(attackBoard,2,destroyerCoord,destroyerDirect))
        {
            checkChange += "t";
        } else checkChange += "f";


        if (hitStatus && change.equals(checkChange))
        {
            while (!successfulShot)
            {
                if (((LETTERS.indexOf(successfulCoord.substring(0,1)) - 1 == -1) && direction == 1) ||
                        ((Integer.parseInt(successfulCoord.substring(1)) + 1 == 11) && direction == 2) ||
                        ((LETTERS.indexOf(successfulCoord.substring(0,1)) + 1 == 10) && direction == 3))
                {
                    direction++;
                }

                if (continueShot)
                {
                    if (direction == 1)
                    {
                        coord = LETTERS.substring(LETTERS.indexOf(continueCoord.substring(0,1)) - 1,LETTERS.indexOf(continueCoord.substring(0,1))) + continueCoord.substring(1);
                    }
                    else if (direction == 2)
                    {
                        coord = continueCoord.substring(0,1) + (Integer.parseInt(continueCoord.substring(1)) + 1);
                    }
                    else if (direction == 3)
                    {
                        coord = LETTERS.substring(LETTERS.indexOf(continueCoord.substring(0,1)) + 1,LETTERS.indexOf(continueCoord.substring(0,1)) + 2) + continueCoord.substring(1);
                    }
                    else if (direction == 4)
                    {
                        coord = continueCoord.substring(0,1) + (Integer.parseInt(continueCoord.substring(1)) - 1);
                    }

                    if ((ai.checkHit(attackBoard,displayCoord).equals("miss")))
                    {
                        if (direction == 2)
                        {
                            direction = 3;
                        }
                        else if (direction == 3)
                        {
                            direction = 4;
                        }

                        if (direction == 3)
                        {
                            coord = LETTERS.substring(LETTERS.indexOf(successfulCoord.substring(0,1)) + 1,LETTERS.indexOf(successfulCoord.substring(0,1)) + 2) + successfulCoord.substring(1);
                        }
                        else if (direction == 4)
                        {
                            coord = successfulCoord.substring(0,1) + (Integer.parseInt(successfulCoord.substring(1)) - 1);
                        }
                    }
                }
                else if (direction == 1)
                {
                    coord = LETTERS.substring(LETTERS.indexOf(successfulCoord.substring(0,1)) - 1,LETTERS.indexOf(successfulCoord.substring(0,1))) + successfulCoord.substring(1);
                }
                else if (direction == 2)
                {
                    coord = successfulCoord.substring(0,1) + (Integer.parseInt(successfulCoord.substring(1)) + 1);
                }
                else if (direction == 3)
                {
                    coord = LETTERS.substring(LETTERS.indexOf(successfulCoord.substring(0,1)) + 1,LETTERS.indexOf(successfulCoord.substring(0,1)) + 2) + successfulCoord.substring(1);
                }
                else if (direction == 4)
                {
                    coord = successfulCoord.substring(0,1) + (Integer.parseInt(successfulCoord.substring(1)) - 1);
                }

                if (ai.checkHit(attackBoard,coord).equals("miss"))
                {
                    successfulShot = true;
                }
                if ((ai.takeShot(attackBoard,coord)).equals("A shot has already been taken at " + coord + ". Please take another shot."))
                {
                    successfulShot = false;
                    direction++;
                }
            }

            attackBoard = ai.takeShot(attackBoard,coord);

            if (ai.checkHit(attackBoard,coord).equals("hit"))
            {
                continueCoord = coord;
                continueShot = true;
                if (((LETTERS.indexOf(continueCoord.substring(0, 1)) - 1 == -1) && direction == 1) ||
                        ((Integer.parseInt(continueCoord.substring(1)) + 1 == 11) && direction == 2) ||
                        ((LETTERS.indexOf(continueCoord.substring(0, 1)) + 1 == 10) && direction == 3))
                {
                    continueShot = false;
                }
            }
            else
            {
                direction++;
            }

        }
        else
        {
            hitStatus = false;
            continueShot = false;
            direction = 1;
            change = checkChange;
            coord = ai.randomCoord();

            while ((ai.takeShot(attackBoard,coord)).equals("A shot has already been taken at " + coord + ". Please take another shot."))
            {
                coord = ai.randomCoord();
            }
            attackBoard = ai.takeShot(attackBoard,coord);
            if (ai.checkHit(attackBoard,coord).equals("hit"))
            {
                hitStatus = true;
                successfulCoord = coord;
                continueShot = false;
            }
        }

        displayResult = ai.checkHit(attackBoard,coord);
        displayCoord = coord;
    }

    /**
     * The getAttackBoard method is an accessor method to return the attackBoard instance variable.
     * The attackBoard represents the board which the AI is attacking on.
     *
     * @return returns the attackBoard instance variable, representing the board which the AI attacks.
     */
    public String getAttackBoard()
    {
        return attackBoard;
    }

    /**
     * The checkSinkStatus method checks whether the AI has taken down all the user's ships.
     * If all the ships are downed, the method returns false. Otherwise, the method returns true.
     *
     * @return returns false if all the user's ships are downed, otherwise returns true.
     */
    public boolean checkSinkStatus()
    {
        String checkChange;

        if (ai.checkShipStatus(attackBoard,5,carrierCoord,carrierDirect))
        {
            checkChange = "t";
        } else checkChange = "f";
        if (ai.checkShipStatus(attackBoard,4,battleshipCoord,battleshipDirect))
        {
            checkChange += "t";
        } else checkChange += "f";
        if (ai.checkShipStatus(attackBoard,3,cruiserCoord,cruiserDirect))
        {
            checkChange += "t";
        } else checkChange += "f";
        if (ai.checkShipStatus(attackBoard,3,submarineCoord,submarineDirect))
        {
            checkChange += "t";
        } else checkChange += "f";
        if (ai.checkShipStatus(attackBoard,2,destroyerCoord,destroyerDirect))
        {
            checkChange += "t";
        } else checkChange += "f";

        if (checkChange.equals("fffff"))
        {
            sinkStatus = false;
        }

        return sinkStatus;
    }

    /**
     * The getDisplayResult method is an accessor method which returns the displayResult instance variable.
     * The displayResult instance variable represents the result of the most recent AI turn.
     *
     * @return returns the displayResult instance variable, which represents the result of the most recent AI turn.
     */
    public String getDisplayResult()
    {
        return displayResult;
    }

    /**
     * The getDisplayCoord method is an accessor method which returns the displayCoord instance variable.
     * THe displayCoord instance variable represents the coordinate of the most recent shot.
     *
     * @return returns the displayCoord instance variable, which represents the coordinate of the most recent shot.
     */
    public String getDisplayCoord()
    {
        return displayCoord;
    }

}