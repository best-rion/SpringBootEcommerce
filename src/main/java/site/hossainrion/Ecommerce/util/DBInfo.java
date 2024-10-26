package site.hossainrion.Ecommerce.util;


public class DBInfo
{
    public static final String url = System.getenv("DATABASE");
    public static final String user = System.getenv("USERNAME");
    public static final String password = System.getenv("PASSWORD");

    private DBInfo(){}
}