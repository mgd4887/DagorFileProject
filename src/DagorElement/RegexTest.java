package DagorElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        String value = "  folder:t=\"content/hq_tex\"\n";
        value = value.strip();
        String regex = "(.+):(p4|p3|p2|c|i|r|t|m|b)=(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        System.out.println(matcher.matches());
    }
}
