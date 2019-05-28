package utilities;

import java.util.Comparator;

/**
 *
 * @author Adrian Vazquez
 */
public class StringComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {

        String bufferNumericalueOfS1;
        String bufferNumericalueOfS2;

        int i = 0;
        boolean equal = true;

        while (equal && i < s1.length() && i < s2.length()) {

            if (s1.charAt(i) != s2.charAt(i)) {

                if (isNumber(s1.charAt(i)) && isNumber(s2.charAt(i))) {

                    int j;

                    j = i;
                    bufferNumericalueOfS1 = "";
                    while (j < s1.length() && isNumber(s1.charAt(j))) {
                        
                        bufferNumericalueOfS1 += s1.charAt(j);

                        j++;
                    }

                    j = i;
                    bufferNumericalueOfS2 = "";
                    while (j < s2.length() && isNumber(s2.charAt(j))) {
                        bufferNumericalueOfS2 += s2.charAt(j);
                        j++;
                    }
                    
                    
                    return (int) (Long.parseLong(bufferNumericalueOfS1) - Long.parseLong(bufferNumericalueOfS2));

                } else if (isNumber(s1.charAt(i)) && !isNumber(s2.charAt(i))) {

                    return 1;

                } else if (!isNumber(s1.charAt(i)) && isNumber(s2.charAt(i))) {
                    return -1;
                } else {
                    return s1.charAt(i) - s2.charAt(i);
                }

            } else {
                i++;
            }
        }

        return 0;
    }

    private static boolean isNumber(char c) {

        return c >= '0' && c <= '9';
    }

}
