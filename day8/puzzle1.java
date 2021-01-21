import java.io.*;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;

public class puzzle1{

    static class Instruction{
        public String opcode;
        public int arg;
        public boolean exec;
        public Instruction(String op, int arg){
            this.opcode = op;
            this.arg = arg;
            this.exec = false;
        }
    }
    public static ArrayList<Instruction> getData(){
        File file = new File("input.txt"); 
        ArrayList<Instruction> al = new ArrayList<Instruction>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            while ((st = br.readLine()) != null) {
                System.out.println(st); 
                Scanner sn = new Scanner(st);
                String op = sn.next();
                int arg = sn.nextInt();
                Instruction in = new Instruction(op, arg);
                al.add(in);
            } 
        }catch(FileNotFoundException exp){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error2");
        }
        return al;
    }
    public static int accumulate(ArrayList<Instruction> array){
        int sum = 0;
        System.out.println("size of array : "+ array.size());
        int i=0;
        while(true){
            if((array.get(i)).exec == false){
                switch(array.get(i).opcode){
                    case "jmp":
                    array.get(i).exec = true;
                    i = i + array.get(i).arg;
                    System.out.println("counter is at: "+ i);
                    break;
                    case "acc":
                    sum += array.get(i).arg;
                    array.get(i).exec = true;
                    System.out.println("sum is: "+ sum + " at: "+ i);
                    i++;
                    break;
                    case "nop": i++;
                    break;
                }
            }else{
                return sum;
            }
        }
    }
    public static void main(String args[]){
        System.out.println("hello");
        ArrayList<Instruction> array = getData();
        int sum = accumulate(array);
        System.out.println("THE RESULT IS: " + sum);
    }
}