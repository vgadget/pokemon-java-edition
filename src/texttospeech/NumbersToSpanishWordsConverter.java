package texttospeech;

public class NumbersToSpanishWordsConverter {

    public static String substituteNumbers(String input) {
        String output = "";
        String buffer;

        for (int i = 0; i < input.length(); i++) {

            if (isNumber(input.charAt(i))) {
                
                int j = i;
                buffer = "";
                while(j < input.length() && isNumber(input.charAt(j))){
                    buffer += input.charAt(j);
                    j++;
                }
                
                output += toSpanish((int) Long.parseLong(buffer));
                i = j-1;
                
            } else {
                output += input.charAt(i);
            }

        }

        return output;
    }

    private static boolean isNumber(char c) {

        return c >= '0' && c <= '9';
    }

    private static String toSpanish(int n) {

        if (Math.abs(n) > 999999999) {
            return "nan";
        }

        String res = "";

        if (n < 0) {
            res += "menos ";
            n = n * (-1);
        } else if (n == 0) {
            return "cero";
        }

        return res + toSpanishMillonesimas(n);
    }

    private static String toSpanishUnidades(int n) {

        int unidad = n % 10;
        String res = "";

        switch (unidad) {

            case 1:
                res = "uno";
                break;
            case 2:
                res = "dos";
                break;
            case 3:
                res = "tres";
                break;
            case 4:
                res = "cuatro";
                break;
            case 5:
                res = "cinco";
                break;
            case 6:
                res = "seis";
                break;
            case 7:
                res = "siete";
                break;
            case 8:
                res = "ocho";
                break;
            case 9:
                res = "nueve";
                break;
        }

        return res;
    }

    private static String toSpanishDecenas(int n) {
        int unidad = n % 10;
        int decima = (n % 100) / 10;
        String res = "";

        switch (decima) {

            case 0:
                res = toSpanishUnidades(n);
                break;
            case 1:

                switch (unidad) {
                    case 0:
                        res = "diez";
                        break;
                    case 1:
                        res = "once";
                        break;
                    case 2:
                        res = "doce";
                        break;
                    case 3:
                        res = "trece";
                        break;
                    case 4:
                        res = "catorce";
                        break;
                    case 5:
                        res = "quince";
                        break;
                    default:
                        res = "dieci" + toSpanishUnidades(n);
                        break;
                }

                break;
            case 2:
                if (unidad == 0) {
                    res = "veinte";
                } else {
                    res = "veinti" + toSpanishUnidades(n);
                }
                break;
            case 3:
                if (unidad == 0) {
                    res = "treinta";
                } else {
                    res = "treinta y " + toSpanishUnidades(n);
                }
                break;
            case 4:
                if (unidad == 0) {
                    res = "cuarenta";
                } else {
                    res = "cuarenta y " + toSpanishUnidades(n);
                }
                break;
            case 5:
                if (unidad == 0) {
                    res = "cincuenta";
                } else {
                    res = "cincuenta y " + toSpanishUnidades(n);
                }
                break;
            case 6:
                if (unidad == 0) {
                    res = "sesenta";
                } else {
                    res = "sesenta y " + toSpanishUnidades(n);
                }
                break;
            case 7:
                if (unidad == 0) {
                    res = "setenta";
                } else {
                    res = "setenta y " + toSpanishUnidades(n);
                }
                break;
            case 8:
                if (unidad == 0) {
                    res = "ochenta";
                } else {
                    res = "ochenta y " + toSpanishUnidades(n);
                }
                break;
            case 9:
                if (unidad == 0) {
                    res = "noventa";
                } else {
                    res = "noventa y " + toSpanishUnidades(n);
                }
                break;
        }
        return res;
    }

    private static String toSpanishCentenas(int n) {
        String res = "";
        int centena = (n % 1000) / 100;

        switch (centena) {
            case 0:
                res = toSpanishDecenas(n);
                break;

            case 1:
                if (n == 100) {
                    res = "cien";
                } else {
                    res = "ciento " + toSpanishDecenas(n);
                }
                break;
            case 5:
                if (n == 500) {
                    res = "quinientos";
                } else {
                    res = "quinientos " + toSpanishDecenas(n);
                }
                break;
            case 7:
                if (n == 700) {
                    res = "setecientos";
                } else {
                    res = "setecientos " + toSpanishDecenas(n);
                }
                break;
            case 9:
                if (n == 900) {
                    res = "novecientos";
                } else {
                    res = "novecientos " + toSpanishDecenas(n);
                }
                break;
            case 2:
            case 3:
            case 4:
            case 6:
            case 8:
                res = toSpanishUnidades(centena) + "cientos " + toSpanishDecenas(n);
                break;
        }
        return res;
    }

    private static String toSpanishMilesimas(int n) {

        int milesimaU = (n % 10000) / 1000;
        int milesimaD = (n % 100000) / 10000;
        int milesimaC = (n % 1000000) / 100000;
        String res = "";

        if (milesimaU == 0 && milesimaD == 0 && milesimaC == 0) {
            res = toSpanishCentenas(n);
        } else {
            int num = (100 * milesimaC) + (10 * milesimaD) + (milesimaU);
            res = toSpanishCentenas(num) + " mil " + toSpanishCentenas(n);
        }

        return res;
    }

    private static String toSpanishMillonesimas(int n) {

        int millonesimaU = (n % 10000000) / 1000000;
        int millonesimaD = (n % 100000000) / 10000000;
        int millonesimaC = (n % 1000000000) / 100000000;

        String res = "";

        if (millonesimaU == 0 && millonesimaD == 0 && millonesimaC == 0) {
            res = toSpanishMilesimas(n);
        } else if (millonesimaU == 1 && millonesimaD == 0 && millonesimaC == 0) {
            res = "un millón " + toSpanishMilesimas(n);

        } else {
            int num = (100 * millonesimaC) + (10 * millonesimaD) + (millonesimaU);
            res = toSpanishMilesimas(num) + " millones " + toSpanishMilesimas(n);
        }

        return res;
    }

}
