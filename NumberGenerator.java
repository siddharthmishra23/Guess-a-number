public class NumberGenerator
{
    private int min;
    private int max;

    public NumberGenerator()
    {
        min = 1;
        max = 100;
    }

    public NumberGenerator(int min , int max)
    {
        this.min = min;
        this.max = max;
    }

    public int getMin()
    {
        return min;
    }

    public int getMax()
    {
        return max;
    }

    public int generateNumber(int min , int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void setMin(int min)
    {
        this.min = min;
    }

    public void setMax(int max)
    {
        this.max = max;
    }
}