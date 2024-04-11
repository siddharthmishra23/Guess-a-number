public class Game
{
    private Player player1;
    private Player player2;
    private static int hiddenNumber;
    private static int rounds = 1;
    private static int attempts = 0;
    private static int abandonNumber = 15;
    private static int[] points = {18 , 12 , 8 , 5 , 3 , 2};
    private static int humanScore = 0;
    private static int compScore = 0;

    public Game()
    {
        player1 : new Player();
        player2 : new Player();
    }

    public Game(Player player1 , Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;
    }

    public boolean compare(int num , NumberGenerator numGen , Player human)
    {
        if(num > hiddenNumber && num < 101 && !human.getOutOfRange())
        {
            System.out.println("Guessed higher!!!");
            numGen.setMax(num - 1);
            return false;
        }
        else if(num == hiddenNumber && !human.getOutOfRange())
        {
            System.out.println("You guessed it right!!!");
            return true;
        }
        else if(num > 0 && num < hiddenNumber && !human.getOutOfRange())
        {
            System.out.println("Guessed lower!!!");
            numGen.setMin(num + 1);
            return false;
        }
        return false;
    }

    public int computerGuess(NumberGenerator numGenComp , Player comp)
    {
        int compGuess;
        compGuess = numGenComp.generateNumber(numGenComp.getMin() , numGenComp.getMax());
        comp.setGuess(compGuess , "Computer");
        System.out.println(comp.display());
        attempts++;
        return compGuess;
    }

    public boolean humanMoves(NumberGenerator numGenHuman , Player human)
    {
        boolean flag = true;
        Validation validate = new Validation();
        while(flag) {
            human.setGuess(0, "");
            if (!validate.intInRange(human.getGuess(), 100, 1)) {
                if (human.getGuess() == 999) {
                    return false;
                }
                System.out.println("You entered number not in range"
                        + " enter again!!!");
            }
            else
            {
                break;
            }
        }
        if(!validate.intInRange(human.getGuess() , numGenHuman.getMax() , numGenHuman.getMin())
                && validate.intInRange(human.getGuess() , 100 , 1)
        )
        {
            System.out.println("You have missed your turn as you have entered " +
                    "a number which is not in range");
            human.setOutOfScore(true);
        }
        attempts++;
        return true;
    }

    public boolean currentRound(Player firstMove , Player secondMove)
    {
        System.out.println("*****************************************************");
        System.out.println(firstMove.getName() + " is going to play first");
        System.out.println(secondMove.getName() + " is going to play second");
        System.out.println("*****************************************************");
        NumberGenerator numGenComp = new NumberGenerator();
        NumberGenerator numGenHuman = new NumberGenerator();
        int randomNumber = numGenComp.generateNumber(1,20);
        while(attempts < 6)
        {
            System.out.println(randomNumber + "random");
            System.out.println("Attempts " + attempts);
            System.out.println("*****************************************************");
            System.out.println("Computer a number between possible range "
                    + numGenComp.getMin() + " and "+ numGenComp.getMax());
            System.out.println("You enter a number between possible range "
                    + numGenHuman.getMin() + " and "+ numGenHuman.getMax());
            System.out.println("*****************************************************");
            if(firstMove.getName() == "Computer")
            {
                int compGuess = computerGuess(numGenComp , firstMove);

                if(randomNumber == abandonNumber)
                {
                    compScore = 0;
                    humanScore = points[attempts];
                    firstMove.setScore(compScore);
                    secondMove.setScore(humanScore);
                    break;
                }

                if(compare(compGuess , numGenComp , secondMove))
                {
                    compScore = points[attempts];
                    humanScore = 0;
                    firstMove.setScore(points[attempts]);
                    secondMove.setScore(0);
                    break;
                }

                if(!humanMoves(numGenHuman , secondMove))
                {
                    compScore = points[attempts];
                    humanScore = 0;
                    firstMove.setScore(compScore);
                    secondMove.setScore(humanScore);
                    roundScoreInfo();
                    return false;
                }

                if(compare(secondMove.getGuess() , numGenHuman , secondMove))
                {
                    humanScore = points[attempts];
                    compScore = 0;
                    firstMove.setScore(0);
                    secondMove.setScore(points[attempts]);
                    break;
                }
            }
            else
            {
                if(!humanMoves(numGenHuman , firstMove))
                {
                    compScore = points[attempts];
                    humanScore = 0;
                    firstMove.setScore(humanScore);
                    secondMove.setScore(compScore);
                    roundScoreInfo();
                    return false;
                }

                if(compare(firstMove.getGuess() , numGenHuman , firstMove))
                {
                    humanScore = points[attempts];
                    compScore = 0;
                    firstMove.setScore(points[attempts]);
                    secondMove.setScore(compScore);
                    break;
                }

                int compGuess = computerGuess(numGenComp , secondMove);
                if(randomNumber == abandonNumber)
                {
                    compScore = 0;
                    humanScore = points[attempts];
                    firstMove.setScore(points[attempts]);
                    secondMove.setScore(0);
                    break;
                }
                if(compare(compGuess , numGenComp , firstMove))
                {
                    compScore = points[attempts];
                    humanScore = 0;
                    firstMove.setScore(humanScore);
                    secondMove.setScore(points[attempts]);
                    break;
                }
            }
        }

        if(Math.abs(hiddenNumber - firstMove.getGuess()) > Math.abs(hiddenNumber - secondMove.getGuess()))
        {
            secondMove.setScore(1);
            firstMove.setScore(0);
        }
        else if(Math.abs(hiddenNumber - firstMove.getGuess()) < Math.abs(hiddenNumber - secondMove.getGuess()))
        {
            secondMove.setScore(0);
            firstMove.setScore(1);
        }
        else
        {
            secondMove.setScore(0);
            firstMove.setScore(0);
        }
        roundScoreInfo();
        attempts = 0;
        humanScore = 0;
        compScore = 0;
        return false;
    }

    public String display()
    {
        return "The current status\n" + player1.display()
                + player2.display();
    }

    public static void main(String[] args)
    {
        Game game = new Game();
        game.startGame();
    }

    public void startGame()
    {
        System.out.println("*****************************************************");
        System.out.println("*****************************************************");
        System.out.println("Welcome to the guessing game :)");
        System.out.println("You will be playing with Computer");
        System.out.println("*****************************************************");
        System.out.println("*****************************************************");
        NumberGenerator numGen = new NumberGenerator();
        hiddenNumber = numGen.generateNumber(1,100);
        Game game = new Game();
        game.player1 = new Player(0 , 0 , false);
        game.player2 = new Player(0 , "Computer" , 0 ,false);
        Player[] players = {game.player1 , game.player2};
        Player firstMove , secondMove ;
        while(rounds < 5)
        {
            System.out.println("*****************************************************");
            System.out.println("*****************************************************");
            System.out.println("Round " + rounds + " is going to start :P");
            System.out.println("*****************************************************");
            System.out.println("*****************************************************");
            int chance = numGen.generateNumber(0,1);
            if(chance == 0)
            {
                firstMove = players[chance];
                secondMove = players[chance+1];
            }
            else
            {
                firstMove = players[chance];
                secondMove = players[chance-1];
            }
            game.currentRound(firstMove , secondMove);
            game.totalScoreInfo(firstMove , secondMove);


            rounds++;
        }

    }

    public Player getPlayer1()
    {
        return player1;
    }

    public Player getPlayer2()
    {
        return player2;
    }



    public void roundScoreInfo()
    {
        System.out.println("Hidden number for this round is" + hiddenNumber);
        System.out.println("Current round score of computer " + compScore);
        System.out.println("Current round score of Human " + humanScore);
    }

    public void setPlayer1(Player player1)
    {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2)
    {
        this.player2 = player2;
    }

    public void totalScoreInfo(Player player1Info , Player player2Info)
    {
        System.out.println(player1Info.getName() + "'s" + "current total score " + player1Info.getScore());
        System.out.println(player2Info.getName() + "'s" + "current total score " + player2Info.getScore());
    }

}