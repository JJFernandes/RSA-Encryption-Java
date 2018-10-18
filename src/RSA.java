import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.util.Scanner;

public final class RSA {

    private RSA() {}

    /**
     * chose any two prime numbers, preferable large one, but for this we'll use smaller ones
     *
     * p = 61, q = 83
     * modulus = n = p*q = 5063
     * phi(n) = (p-1)(q-1) = (60)(82) = 4920
     * public key exponent to be given to others = e = any coprime to phi(n) = i chose 17
     * private key exponent to be kept to decrypt = d
     * d*e congruent to 1
     * just watch this video https://www.youtube.com/watch?v=Z8M2BTscoD4
     *
     * ENCRYPTION
     * m = password converted into ASCII
     * https://ascii.cl/
     * c = ciphertext or encrypted password
     * c = (m^e) mod n = (m^17) mod 5063
     *
     * This is why I had to use BigInteger because it gives big fucking numbers
     * and BigInteger has method called modPow, so its really convenient
     *
     * DECRYPTION
     * m = (c^d) mod n = (c^3473) mod 5063
     * then convert this m back from ASCII
     *
     */


    public static void writeFile(File f, String encryptPass) throws IOException{
        FileWriter fw = new FileWriter(f, true);
        Scanner sc = new Scanner(f);

        if (sc.hasNextLine()) {
            fw.write("\n");
            fw.write(encryptPass);
        }else {fw.write(encryptPass);}

        fw.close();
    }

    public static String readFile(File f, int lineNum) throws IOException{

        return Files.readAllLines(Paths.get(f.getName())).get(lineNum);
    }

    public static String turnPassIntoASCII(String pass) {
        String value = "";
        for (int i = 0; i < pass.length(); i++) {
            //char c = pass.charAt(i);
            int ascii = (int) pass.charAt(i);

            String formatted;
            if (ascii < 100) {
                formatted = "0" + ascii;
            }
            else {
                formatted = "" + ascii;
            }
            //System.out.println("The letter " + c + " is " + ascii + " in ASCII");
            value += formatted;
        }

        return value;
    }

    public static String encrypt(String ascii) {
        String result;
        String resultTotal = "";

        for (int i = 0; i < ascii.length(); i +=3) {

            String temp = ascii.substring(i, i + 3);

            BigInteger subASCII = new BigInteger(temp);

            BigInteger e = new BigInteger("17");
            BigInteger p1p2 = new BigInteger("5063");

            BigInteger encryptedPass = subASCII.modPow(e, p1p2);

            result = encryptedPass.toString();
            if (result.length() < 4) {
                result = "0" + result;

                if (result.length() < 4) {
                    result = "0" + result;
                }
            }
            resultTotal += result;

        }
        return resultTotal;
    }

    public static String decrypt(String encrypted) {
        String result = "";
        int z;

        BigInteger d = new BigInteger("3473");
        BigInteger p1p2 = new BigInteger("5063");

        for (int i = 0; i < encrypted.length(); i+=4) {
            String temp = encrypted.substring(i, i+4);

            BigInteger subEncrypted = new BigInteger(temp);

            BigInteger decryptedPass = subEncrypted.modPow(d, p1p2);

            z = decryptedPass.intValue();

            char a = (char)z;

            result += String.valueOf(a);

        }

        return result;
    }

}
