package sphiinx.api.game.pricechecking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceChecking {

    /**
     * Gets the price of the item id from the RuneScape website.
     *
     * @param id The id of the item.
     * @return The price of the item; 0 otherwise.
     */
    public static int getRSPrice(int id) {
        try (BufferedReader READER = new BufferedReader(new InputStreamReader(new URL("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + id).openStream()))) {
            final Matcher MATCHER = Pattern.compile(".*\"price\":\"?(\\d+\\,?\\.?\\d*)([k|m]?)\"?},\"today\".*").matcher(READER.readLine());
            READER.close();

            if (MATCHER.matches()) {
                final double PRICE = Double.parseDouble(MATCHER.group(1).replace(",", ""));
                final String SUFFIX = MATCHER.group(2);
                return (int) (SUFFIX.isEmpty() ? PRICE : PRICE * (SUFFIX.charAt(0) == 'k' ? 1000 : 1000000));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Gets the price of the item id from the OSBuddy website.
     *
     * @param id The id of the item.
     * @return The price of the item; 0 otherwise.
     */
    public static int getOSBuddyPrice(int id) {
        try (BufferedReader READER = new BufferedReader(new InputStreamReader(new URL("https://api.rsbuddy.com/grandExchange?a=guidePrice&i=" + id).openStream()))) {
            final String LINE = READER.readLine();
            READER.close();
            return LINE == null ? -1 : Integer.parseInt(LINE.substring(11, LINE.indexOf(',')));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
