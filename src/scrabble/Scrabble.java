import scrabble.ReadingFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

// Java program to print all valid words that
// are possible using character of array



public class Scrabble {

    static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static int  values_scrabble[] = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    static int primes[]={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    static HashMap<Long, String> file;

    Scrabble() throws IOException {

    }

    static int summing(String letter)
    {
        int sum = 0;
        for(int i =0;i<letter.length();i++)
        {
            sum= sum + values_scrabble[letters.indexOf(letter.charAt(i))];
        }
        return sum;
    }

    static long product (String letter)
    {
        long product = 1;
        for(int i=0;i<letter.length();i++)
        {
            product = product * primes[letters.indexOf(letter.charAt(i))];
        }
        return product;
    }

    static long printSubsets(char set[]) throws IOException {
        ReadingFile r;
        r = new ReadingFile();
        file=r.map;
        int n = set.length;
        int maxsum=0;
        int sum=0;
        long prod=0;
        long goodprod=-1;
        String s="";

        // Run a loop for printing all 2^n
        // subsets one by obe
        for (int i = 0; i < (1<<n); i++)
        {
            s="";

            // Print current subset
            for (int j = 0; j < n; j++) {
                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0) {
                    s = s + (set[j]);
                    prod=r.findValue(s);
                    if(found(prod)){
                        sum=summing(s);
                    }
                    if(sum>maxsum){
                        maxsum=sum;
                        goodprod=prod;
                    }

                }

            }

        }
        return goodprod;
    }


    static boolean found(long product)
    {
        boolean value;
        try {

            if (file.containsKey(product)) {
                return true;
            }
        }catch(Exception e){System.out.println(e);}
        return false;
    }
    // Alphabet size

    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the input letters(7 letters only): ");
        String letters = in.next();
        letters=letters.toUpperCase();
        System.out.println();
        int maxsum=0;
        long prod=1;
        ReadingFile r;
        r = new ReadingFile();
        file=r.map;
        int maxx=0;
        String ans="ss";

        System.out.println("First subdivision: ");
        System.out.println(file.get(printSubsets(letters.toCharArray())));
        System.out.println("Value: "+summing(file.get(printSubsets(letters.toCharArray()))));
        System.out.println();

        for(int i=0;i<26;i++) {

            prod= (printSubsets((letters + (char) ('A' + i)).toCharArray()));
            String ss= file.get(prod);
            maxsum=summing(ss);
            if(maxsum>maxx)
            {
                maxx=maxsum;
                ans=ss;

            }
        }

        System.out.println("Second Subdivision: ");
        System.out.println(ans);
        System.out.println("Value: "+ summing(ans));






    }
}
