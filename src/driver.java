import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class driver {
    public static void main (String args[]) throws IOException{

        //These are final and represent the specific file line in userFile
        //Used for readFile method from RSA class
        final int CPHD20 = 0, INSTA = 1, ZEBAY = 2;

        //These are testers so we need to incorporate the methods to what we want to do

        //This is just to get a userName to create file to store encrypted passwords
        Scanner sc = new Scanner(System.in);

        System.out.print("What is your UserName? : ");
        String name = sc.nextLine();
        File userFile = new File(name+".txt");

        //Im assuming that the user will have 3 different passwords for each service
        //i would have made this cleaner but i made a mistake so i had to go through each one separately. it was a really stupid mistake.

        //Club Penguin HD Remastered 2.0
      String pass0= "adK1M?2*hc";
        //ENCRYPT
        String ascii0 = RSA.turnPassIntoASCII(pass0);
        String encrypted0 = RSA.encrypt(ascii0);
        //Write encryped password to a file
        RSA.writeFile(userFile, encrypted0);


        //MyInstaBook
        String pass1 = "ahSGo87td2HBcj?12";
        String ascii1 = RSA.turnPassIntoASCII(pass1);
        String encrypted1 = RSA.encrypt(ascii1);
        RSA.writeFile(userFile, encrypted1);

        //ZEBAY
        String pass2 = "jksdnc7";
        String ascii2 = RSA.turnPassIntoASCII(pass2);
        String encrypted2 = RSA.encrypt(ascii2);
        RSA.writeFile(userFile, encrypted2);

        //Read each line from Encrypted.txt and decrypt it
        String clubPengDecyrypt = RSA.decrypt(RSA.readFile(userFile, CPHD20));
        System.out.println(clubPengDecyrypt);

        String instaDecrypt = RSA.decrypt(RSA.readFile(userFile, INSTA));
        System.out.println(instaDecrypt);

        String zeBayDecrypt = RSA.decrypt(RSA.readFile(userFile, ZEBAY));
        System.out.println(zeBayDecrypt);

        //Then you have to check if decrypted message == original password

       if (clubPengDecyrypt.equals(pass0)) {
            System.out.println("club penguin passwords match.");
        }else {
            System.out.println("club penguin passwords do not match.");
        }

        if (instaDecrypt.equals(pass1)) {
            System.out.println("MyInstaBook passwords match.");
        }else {
            System.out.println("MyInstaBook passwords do not match.");
        }

        if (zeBayDecrypt.equals(pass2)) {
            System.out.println("ZeBay passwords match.");
        }else {
            System.out.println("Zebay passwords do not match.");
        }



    }
}
