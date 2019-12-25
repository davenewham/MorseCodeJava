package am.newh.callsign;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        System.out.println(convertToMorse(getRandomComposition()));
    }

    private static String getRandomComposition() {
        /*
        PNS, PPNS, PNSS, PNSSS, PPNSS, PPNSSS - most common, should be weighted 80%
        PPNSSSS, PPPNSS, PPPNSSS

        P - can be letter or numeral
        N - numeral from 0 to 9
        S -Suffix usually only letters

        */


        String[] compositionList = {"PNS", "PPNS", "PNSS", "PNSSS", "PPNSS", "PPNSSS", "PPNSSSS", "PPPNSS", "PPPNSSS"};
        Random r = new Random(); // hash new random every time
        int randomNumber = r.nextInt(compositionList.length);
        return convertToCallsign(compositionList[randomNumber]);
    }

    private static String convertToCallsign(String compsition) {
        Random r = new Random(); // hash new random again
        StringBuilder callsign = new StringBuilder();
        for (int i = 0; i < compsition.length(); i++) {
            switch (compsition.charAt(i)) {
                case 'P': // fallthrough
                case 'S':
                    callsign.append((char) (r.nextInt(26) + 'A'));
                    break;
                case 'N':
                    callsign.append((int) (Math.random() * ((9) + 1)));
                    break;
            }
        }
        return callsign.toString();
    }

    private static String convertToMorse(String composition){
        String[] morseAlphabet = {".-","-...","-.-.","-..",".","..-.","--.",
        "....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.",
                "...","-","..-","...-",".--","-..-","-.--", "--.."};
        String[] morseNumeric = {"-----",".----","..---","...--","....-","......",
        "-...", "--...", "---..", "----."};

        System.out.println(composition);
        composition = composition.toLowerCase();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < composition.length(); i++) {
            int current = composition.charAt(i);
            if (composition.charAt(i) <= 57 && composition.charAt(i) >=48){
                // is an integer
                current = current - 48;
                s.append(" ").append(morseNumeric[current]);
            }
            if (composition.charAt(i) <= 122 && composition.charAt(i) >= 97){
                current = current - 97;
                s.append(" ").append(morseAlphabet[current]);
            }
        }
        return s.toString();

    }
}
