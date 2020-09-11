package converter;

import java.util.Scanner;

public class Main {
    private static String convertFractionToDecimal(String input, int sourceRadix, int targetRadix) {
        double fractionalDec = 0;
        char[] fractionalArr = input.toCharArray();
        for (int i = 0; i < fractionalArr.length; i++) {
            char ch = fractionalArr[i];
            double val = Character.getNumericValue(ch);
            fractionalDec += val/Math.pow(sourceRadix, i+1);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            fractionalDec = fractionalDec * targetRadix;
            int integer = (int) fractionalDec;
            result.append(Character.forDigit(integer, targetRadix));
            fractionalDec = fractionalDec % integer;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sourceRadix = 0;
        String input = null;
        int radix = 0;
        try {
             sourceRadix = scanner.nextInt();
             input = scanner.next();
             radix = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("error");
            System.exit(0);
        }


        if (radix < 1 || radix > 36 || sourceRadix < 1 || sourceRadix > 36) {
            System.out.println("error");
            System.exit(0);
        }

        try {
            String[] realInput = input.split("\\.");
            String fraction;
            if (realInput.length != 1) {
                fraction = convertFractionToDecimal(realInput[1], sourceRadix, radix);
            } else {
                fraction = null;
            }

            String converted;

            if (radix == sourceRadix) {
                converted = realInput[0];
            } else if (sourceRadix == 1) {
                int convertNumber = 0;
                for (int i = 0; i < input.length(); i++) {
                    convertNumber++;
                }
                converted = Integer.toString(Integer.parseInt(
                        Integer.toString(convertNumber), 10), radix);
            } else if (radix == 1) {
                int decimal = Integer.parseInt(realInput[0], sourceRadix);
                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < decimal; i++) {
                    builder.append("1");
                }

                converted = builder.toString();
            } else {
                converted = Integer.toString(Integer.parseInt(realInput[0], sourceRadix), radix);
            }


            if (fraction != null) {
                System.out.println(converted + "." + fraction);
            } else {
                System.out.println(converted);
            }
        } catch (Exception e) {
            System.out.println("error");
        }



    }
}
