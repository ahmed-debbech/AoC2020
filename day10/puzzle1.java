import java.io.*;
import java.util.Arrays;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;

public class puzzle1{
    public static int[] getData(){
        File file = new File("input.txt"); 
        int numbers[] = new int[92];
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
            System.out.println(i + " " +  l[i]);
        }
        System.out.println("**********");
    }
    public static int count(int adapt[]){
        int cur_jolts = 0; //the jolts from the outlet '0'
        int count_1_diff = 0;
        int count_2_diff = 0;
        int count_3_diff = 0;

        for(int i=0;i<=91; i++){
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
    public static void main(String args[]){
        int adapters[] = new int[92];
        adapters = getData();
        show(adapters, 91);
        Arrays.sort(adapters);
        show(adapters, 91);
        int res = count(adapters);
        System.out.println("THE RESULT IS : " +res);
    }
}