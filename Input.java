import java.util.Scanner;
public class Input
{

    public Input()
    {

    }

    public int acceptOnlyInt(String value)
    {
        System.out.println(value);
        Scanner console = new Scanner(System.in);
        int num = Integer.parseInt(console.nextLine());
        return num;
    }

    public String acceptOnlyString(String value)
    {
        System.out.println(value);
        Scanner console = new Scanner(System.in);
        String data = console.nextLine();
        return data;
    }

}