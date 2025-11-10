public class CaesarCipher {
    private static final int ABC_MERET = 26;
    private static final char ALAP_BETU = 'A';
    private static final char UTOLSO_BETU = 'Z';

    public static String encode(String input, char offset) {
        // Bemenet átalakítása nagybetűssé (1. feladat követelménye)
        input = input.toUpperCase();

        // Eltolás mértékének kiszámítása [cite: 14]
        int shift = offset - ALAP_BETU;
        StringBuilder encoded = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c >= ALAP_BETU && c <= UTOLSO_BETU) {
                // Kiszámítjuk az új karakter kódját
                char shiftedChar = (char) (c + shift);

                // "Túlcsordulás" kezelése (pl. 'Z' után 'A' következik) [cite: 14]
                if (shiftedChar > UTOLSO_BETU) {
                    shiftedChar = (char) (shiftedChar - ABC_MERET);
                }
                encoded.append(shiftedChar);
            } else {
                // Ha nem az angol ABC nagybetűje, változatlanul hagyjuk
                encoded.append(c);
            }
        }
        return encoded.toString();
    }

    public static String decode(String input, char offset) {
        input = input.toUpperCase();

        int shift = offset - ALAP_BETU;
        StringBuilder decoded = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c >= ALAP_BETU && c <= UTOLSO_BETU) {
                // Kiszámítjuk az új karakter kódját (visszafelé)
                char shiftedChar = (char) (c - shift);

                // "Alulcsordulás" kezelése (pl. 'A' előtt 'Z' van)
                if (shiftedChar < ALAP_BETU) {
                    shiftedChar = (char) (shiftedChar + ABC_MERET);
                }
                decoded.append(shiftedChar);
            } else {
                decoded.append(c);
            }
        }
        return decoded.toString();
    }
}
