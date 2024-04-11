public class Validation
{
    public Validation()
    {

    }

    public boolean isBlank(String val)
    {
        if(val.trim().length() > 0 )
        {
            return false;
        }
        return true;
    }

    public boolean stringInRange(String val , int max , int min)
    {
        if(val.length() < min || val.length() > max)
        {
            return false;
        }
        return true;
    }

    public boolean intInRange(int val , int max , int min)
    {
        if(val < min || val > max)
        {
            return false;
        }
        return true;
    }

    public boolean onlyString(String val)
    {
        for(int i = 0 ; i < val.length() ; i++)
        {
            if(Character.isDigit(val.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean onlyInt(String val)
    {
        for(int i = 0 ; i < val.length() ; i++)
        {
            if(!Character.isDigit(val.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}