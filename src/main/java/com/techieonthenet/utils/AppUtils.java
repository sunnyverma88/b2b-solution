package com.techieonthenet.utils;

/**
 * The type App utils.
 */
public class AppUtils {

    private AppUtils() {
    }

    /**
     * Gets alpha numeric string.
     *
     * @param n the n
     * @return the alpha numeric string
     */
    public static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of alphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to alphaNumericString variable length
            int index
                    = (int) (alphaNumericString.length()
                    * Math.random());
            // add Character one by one in end of sb
            sb.append(alphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public static String toCamelCase(final String init) {
        if (init == null)
            return null;

        final StringBuilder ret = new StringBuilder(init.length());

        for (final String word : init.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(Character.toUpperCase(word.charAt(0)));
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length() == init.length()))
                ret.append(" ");
        }

        return ret.toString();
    }
}
