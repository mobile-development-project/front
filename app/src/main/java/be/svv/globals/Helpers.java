package be.svv.globals;

public class Helpers
{

    public static String toPlurar (String word, int size)
    {
        return size > 1 ? " " + word + "s" : " " + word;
    }

}
