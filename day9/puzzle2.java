import java.io.*;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;

public class puzzle2{
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
    public static void show(ArrayList<Long> l, int size){
        System.out.println("**********");
        for(int i=0; i<=size; i++){
            System.out.println(l.get(i));
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
    public static ArrayList<Long> find_num(long[] numbers, long invalid){
        ArrayList<Long> cont = new ArrayList<>();
        long count=0;
        for(int i=0; numbers[i] != invalid; i++){
            for(int j=i; numbers[j] != invalid; j++){
                if(count < invalid){
                    count += numbers[j];
                    cont.add(numbers[j]);
                }else{
                    if(count > invalid){
                        cont.removeAll(cont);
                        count=0;
                        break;
                    }else{
                        return cont;
                    }
                }
            }
        }
        return null;
    }
    public static long match(ArrayList<Long> numb){
        long max=numb.get(0), min=numb.get(0);
        for(int i=1; i<=numb.size()-1; i++){
            if(numb.get(i) > max){
                max = numb.get(i);
            }
            if(numb.get(i) < min){
                min = numb.get(i);
            }
        }
        return min + max;
    }
    public static void main(String args[]){
        long numbers[] = new long[1000];
        numbers = getData();
        long invalid = check(numbers);
        System.out.println("The invalid number is : "+ invalid);
        ArrayList<Long> cont = find_num(numbers, invalid);
        if(cont == null){
            System.out.println("null");
        }else{
            show(cont, cont.size()-1);
            long res = match(cont);
            System.out.println("THE RESULT IS: "+ res);   
        }
    }
}