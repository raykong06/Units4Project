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

    public AI(String attackBoard, String carrierCoord, String battleshipCoord, String cruiserCoord, String submarineCoord, String destroyerCoord, String carrierDirect, String battleshipDirect, String cruiserDirect, String submarineDirect, String destroyerDirect)
    {
        this.attackBoard = attackBoard;
        sinkStatus = false;
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
                    if ((ai.checkHit(attackBoard,displayCoord).equals("miss")))
                    {
                        direction += 2;
                    }
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
                }
            }

            attackBoard = ai.takeShot(attackBoard,coord);

            if (ai.checkHit(attackBoard,coord).equals("hit"))
            {
                continueCoord = coord;
                continueShot = true;
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

    public String getAttackBoard()
    {
        return attackBoard;
    }

    public boolean checkSinkStatus(String board)
    {
        if ((ai.checkShipStatus(board,5,carrierCoord,carrierDirect)) && (ai.checkShipStatus(board,4,battleshipCoord,battleshipDirect))
            && (ai.checkShipStatus(board,3,cruiserCoord,cruiserDirect)) && (ai.checkShipStatus(board,3,submarineCoord,submarineDirect))
            && (ai.checkShipStatus(board,2,destroyerCoord,destroyerDirect)))
        {
            sinkStatus = true;
        }

        return sinkStatus;
    }

    public String getDisplayResult()
    {
        return displayResult;
    }

    public String getDisplayCoord()
    {
        return displayCoord;
    }

}
