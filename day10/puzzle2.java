import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;

public class puzzle2{
    public static int[] getData(int size){
        File file = new File("input.txt"); 
        int numbers[] = new int[size];
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            int i=0;
            while ((st = br.readLine()) != null) {
                //System.out.println(st); 
                Scanner sn = new Scanner(st);
                int num = sn.nextInt();
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
    public static void show(int []l, int size){
        System.out.println("**********");
        for(int i=0; i<=size; i++){
            System.out.println(i + ": " +  l[i]);
        }
        System.out.println("**********");
    }
    public static int count(int adapt[], int size){
        int cur_jolts = 0; //the jolts from the outlet '0'
        int count_1_diff = 0;
        int count_2_diff = 0;
        int count_3_diff = 0;

        for(int i=0;i<=size; i++){
            if((adapt[i] - cur_jolts) == 1){
                count_1_diff ++;
                cur_jolts = adapt[i];
            }else{
                if((adapt[i] - cur_jolts) == 2){
                    count_2_diff++;
                    cur_jolts = adapt[i];
                }else{
                    if((adapt[i] - cur_jolts) == 3){
                        count_3_diff++;
                        cur_jolts = adapt[i];
                    }
                }
            }
        }
        count_3_diff++;
        System.out.println("count 3 " + (count_3_diff));
        System.out.println("count 1 "+ count_1_diff);
        int res = count_3_diff * count_1_diff;
        return res;
    }
    public static long combinate(int i, int[] numbers, int size, long memo[]){
        if(i == size-1) {
            return 1;
        }
        if(memo[i] != -1){
            return memo[i];
        }
        long total = 0;
        System.out.println();
        System.out.println("where : " + i);
        for(int j = i+1; j < size; j++) {
            if(numbers[j] - numbers[i] <= 3) {
                System.out.print(" | " + numbers[j]);
                total += combinate(j, numbers, size, memo);
                /*try{
                    Thread.sleep(1000);
                }catch(InterruptedException ex){
                    System.out.println("error");
                }*/
            }
        }
        memo[i] = total;
        System.out.println();
        System.out.println("total: " + total);
        return total;
    }
    public static void main(String args[]){
        int size = 94;
        int adapters[] = new int[size];
        adapters = getData(size);
        show(adapters, size-1);
        Arrays.sort(adapters);
        show(adapters, size-1);
        int res = count(adapters, size-1);
        //HashMap<Integer,Long> dp_storage = new HashMap<>();
        long memo[] = new long[size];
        for(int b=0; b<=size-1; b++){
            memo[b] = -1;
        }
        long r = combinate(0, adapters, size, memo);
        System.out.println("THE RESULT IS : " +r);
    }
}