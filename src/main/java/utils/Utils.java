package utils;

import org.jsoup.Jsoup;

public class Utils {

    /** This util will remove any HTML tag from a given HTML string  */
    public static String htmlToText(String html) {
        return Jsoup.parse(html).text();
    }
}
