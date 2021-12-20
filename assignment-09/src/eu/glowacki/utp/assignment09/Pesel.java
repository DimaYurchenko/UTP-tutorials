package eu.glowacki.utp.assignment09;

import java.util.Date;
import java.util.GregorianCalendar;

public class Pesel {

    private final String pesel;

    public Pesel(String pesel) {
        this.pesel   = pesel;
    }

    public enum Sex {
        MALE,
        FEMALE
    }

    private boolean isValid() {
        if (pesel == null || pesel.length() != 11) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < pesel.length() - 1; i++) {

            int multiplier = switch ((i + 1) % 4) {
                case 1 -> 1;
                case 2 -> 3;
                case 3 -> 7;
                case 0 -> 9;
                default -> 0;
            };

            sum += Character.getNumericValue(pesel.charAt(i)) * multiplier;
        }

        int modulo = sum % 10;
        int lastDigit = Character.getNumericValue(pesel.charAt(pesel.length() - 1));

        return (modulo == 0) && lastDigit == 0 || lastDigit == 10 - modulo;
    }

    private Date getBirthDate() {
        int yy = Integer.parseInt(pesel.substring(0,2));
        int mm = Integer.parseInt(pesel.substring(2,4));
        int dd = Integer.parseInt(pesel.substring(4,6));

        if (mm > 80) {
            yy = yy + 1800;
            mm = mm - 80;
        }
        else if (mm > 60) {
            yy = yy + 2200;
            mm = mm - 60;
        }
        else if (mm > 40) {
            yy = yy + 2100;
            mm = mm - 40;
        }
        else if (mm > 20) {
            yy = yy + 2000;
            mm = mm - 20;
        }
        else
        {
            yy += 1900;
        }

        return new GregorianCalendar(yy, mm - 1, dd).getTime();
    }

    private Sex getSex() {
        return Character.getNumericValue(pesel.charAt(9)) % 2 == 0
                ? Sex.FEMALE : Sex.MALE;
    }
}
