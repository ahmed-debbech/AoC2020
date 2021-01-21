import java.io.*;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;

public class puzzle1{
    public static long[] getData(){
        File file = new File("input.txt"); 
        long numbers[] = new long[1000];
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            int i=0;
            while ((st = br.readLine()) != null) {
                //System.out.println(st); 
                Scanner sn = new Scanner(st);
                long num = sn.nextLong();
                numbers[i] = num;
                i++;
            } 
        }catch(FileNotFoundException exp){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error2");
        }
        return numbers;
    }
    public static void show(long []l){
        System.out.println("**********");
        for(int i=0; i<=999; i++){
            System.out.println(l[i]);
        }
        System.out.println("**********");
    }
    public static long [] create_sub(long [] num, int i){
        long sub [] = new long[25];
        int r = 0;
        i--;
        for(int k=0; k<=24; k++){
            sub[k]=num[i];
            i--;
        }
        return sub;
    }
    public static boolean checker(long[] sub, long number){
        for(int i=0; i<=24; i++){
            for(int j=0; j<=24; j++){
                if(i!=j){
                    if(sub[i]+sub[j] == number){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static long check(long []numbers){
        for(int i=25; i<=999; i++){
            long sub [] = create_sub(numbers, i);
            if(checker(sub, numbers[i]) == false){
                return numbers[i];
            }
        }
        return 0;
    }
    public static void main(String args[]){
        long numbers[] = new long[1000];
        numbers = getData();
        show(numbers);
        long res = check(numbers);
        System.out.println("THE RESULT IS: "+ res);
    }
}