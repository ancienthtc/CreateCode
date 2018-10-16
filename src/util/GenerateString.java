package util;
import java.util.Random;
import java.util.UUID;

public class GenerateString {


    public String getRandomString(int length)
    {
        //String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public String getRandomString_Num(int length)
    {
        //String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-","");
    }


}