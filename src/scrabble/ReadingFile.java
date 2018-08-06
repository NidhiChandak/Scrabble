package scrabble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class ReadingFile {
    public HashMap<Long, String> map = new HashMap<>();
    public ReadingFile() throws IOException {
        // TODO Auto-generated method stub

        map=getPrimeMultiplication();

    }
    public static long findValue(String word){
        long primeProduct=1;
        String alphabets= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int prime[]={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        for(int i = 0; i < word.length(); i++){
            //System.out.println(prime[alphabets.indexOf(word.charAt(i))]);
            primeProduct*=prime[alphabets.indexOf(word.charAt(i))];
            //System.out.println(primeProduct);
        }

        return primeProduct;
    }
    public static HashMap<Long, String> getPrimeMultiplication() throws IOException{
        //System.out.println(("getprime called"));
        HashMap<Long, String> map = new HashMap<>();

        File file = new File("./sowpods.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null)
        {
            if(st.length()<=7){
                map.put(findValue(st), st);
                //System.out.println(findValue(st)+" "+st);
            }
        }
        return map;
    }
}
