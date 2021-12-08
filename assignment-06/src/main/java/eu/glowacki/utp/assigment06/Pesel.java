package eu.glowacki.utp.assigment06;

import java.time.LocalDate;

public class Pesel {

    private final String number;

    public Pesel(LocalDate birthDate) {
        this.number = generate(birthDate);
    }

    private String generate(LocalDate birthDate) {
        String pesel = "";
        pesel += String.valueOf(birthDate.getYear()).substring(2);

        int mm = birthDate.getMonthValue();
        int year = birthDate.getYear();

        if (year >= 2000 && year <= 2099) {
            mm += 20;
        }

        else if (year >= 2100 && year <= 2199) {
            mm += 40;
        }

        else if (year >= 2200 && year <= 2299) {
            mm += 60;
        }

        else if (year >= 1800 && year <= 1899) {
            mm += 80;
        }

        pesel += mm < 10 ? "0" + mm : String.valueOf(mm);
        int dd = birthDate.getDayOfMonth();
        pesel += dd < 10 ? "0" + dd : String.valueOf(dd);
        pesel += String.valueOf(getRandomNumber(1000, 9999));

        int sum = 0;

        for (int i = 0; i < pesel.length(); i++) {

            int multiplier = 0;

            switch ((i + 1) % 4) {
                case 1: multiplier = 1; break;
                case 2: multiplier = 3; break;
                case 3: multiplier = 7; break;
                case 0: multiplier = 9; break;
            }

            sum += Character.getNumericValue(pesel.charAt(i)) * multiplier;
        }
        int modulo = sum % 10;
        pesel += String.valueOf(modulo == 0 ? 0 : 10 - modulo);

        return pesel;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number;
    }

    public static boolean validate(String pesel) {

        if (pesel == null || pesel.length() != 11) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < pesel.length() - 1; i++) {

            int multiplier = 0;

            switch ((i + 1) % 4) {
                case 1: multiplier = 1; break;
                case 2: multiplier = 3; break;
                case 3: multiplier = 7; break;
                case 0: multiplier = 9; break;
            }

            sum += Character.getNumericValue(pesel.charAt(i)) * multiplier;
        }

        int modulo = sum % 10;
        int lastDigit = Character.getNumericValue(pesel.charAt(pesel.length() - 1));

        return (modulo == 0) && lastDigit == 0 || lastDigit == 10 - modulo;
    }

    public static boolean validate(Pesel pesel) {
        return Pesel.validate(pesel.getNumber());
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }



}
