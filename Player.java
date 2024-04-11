public class Player
{
    private int score;
    private String name;
    private int guess;
    private boolean outOfRange;

    public Player()
    {
        score = 0;
        name = "Unknown";
        guess = 0;
        outOfRange = false;
    }

    public Player(int score , int guess , boolean outOfRange)
    {
        this.score = score;
        setName();
        this.guess = guess;
        this.outOfRange = outOfRange;
    }

    public Player(int score , String name , int guess , boolean outOfRange)
    {
        this.score = score;
        this.name = name;
        this.guess = guess;
        this.outOfRange = outOfRange;
    }

    public String display()
    {
        return name + " has guessed " + getGuess();
    }

    public int getGuess()
    {
        return guess;
    }

    public String getName()
    {
        return name;
    }

    public boolean getOutOfRange()
    {
        return outOfRange;
    }

    public int getScore()
    {
        return score;
    }

    public void setGuess(int value , String name)
    {
        Validation validate = new Validation();
        Input input = new Input();
        if(name == "Computer")
        {
            this.guess = value;
        }
        else
        {
            boolean flag = true;
            while(flag)
            {
                String playerGuess = input.acceptOnlyString("Enter your Guess");
                if(validate.onlyInt(playerGuess))
                {
                    this.guess = Integer.parseInt(playerGuess);
                    flag = false;
                }
                else
                {
                    System.out.println("Enter integer value only");
                }
            }
        }

    }

    public void setName()
    {
        Validation validate = new Validation();
        Input input = new Input();
        boolean flag = true;
        while(flag)
        {
            String playerName = input.acceptOnlyString("Enter your name");
            if(validate.onlyString(playerName) && validate.stringInRange(playerName , 9 , 0))
            {
                this.name = playerName;
                flag = false;
            }
            else
            {
                System.out.println("Enter a value of length 8 and only Characters,"
                        + " Try again!!!");
            }
        }


    }

    public void setOutOfScore(boolean val)
    {
        outOfRange = val;
    }

    public void setScore(int score)
    {
        this.score = this.score + score;
    }
}