import java.io.*;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;

public class puzzle2{

    static class Instruction{
        public String opcode;
        public int arg;
        public int count;
        public Instruction(String op, int arg){
            this.opcode = op;
            this.arg = arg;
            this.count = 0;
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
        int ind = 1;
        boolean forever = true;
        while((forever == true) && (array.get(i).count <= 50)){
            //System.out.println("count for "+ array.get(i).opcode +" " + array.get(i).arg + " is: "+ array.get(i).count);
            array.get(i).count++;
            switch(array.get(i).opcode){
                case "jmp":
                i = i + array.get(i).arg;
                break;
                case "acc":
                sum += array.get(i).arg;
                i++;
                break;
                case "nop": i++; 
                break;
            }
            if(i > array.size()-1){
                System.out.println("ended");
                forever = false;
            }
        }
        if(forever == true){
            return -1;
        }
        return sum;
    }
    public static int [] getCrashed(ArrayList<Instruction> array){
        int[] ind = new int[1000];
        int cc =0;
        for(int i=0; i<=array.size()-1; i++){
            if(array.get(i).count >= 50){
                if(array.get(i).opcode.equals("jmp") || array.get(i).opcode.equals("nop")){
                    ind[cc] = i;
                    cc++;
                }
            }
        }
        return ind;
    }
    public static void flip(Instruction in){
        if(in.opcode.equals("jmp")){
            in.opcode = "nop";
        }else{
            in.opcode = "jmp";
        }
    }
    public static void main(String args[]){
        ArrayList<Instruction> array = getData();
        int sum = 0;
        sum = accumulate(array);
        int size = 0;
        int indcies[] = getCrashed(array); //we get where the crash happens by examining the counter
        for(int k=0; k<=5; k++){
            System.out.println(indcies[k]);
        }
        int gg=0; // counter for indecies change array
        boolean first = true;
       do{
            sum = accumulate(array);
            for(int i=0; i<=array.size()-1; i++){
                System.out.println(array.get(i).opcode +" " + array.get(i).arg + " is: "+ array.get(i).count );
            }
            if(sum == -1){
                if(first  == false){
                   flip(array.get(indcies[gg-1]));
                }
                array = getData();
                flip(array.get(indcies[gg]));
                gg++;
                first = false;
            }
        }while(sum == -1 && indcies[gg-1] != 0);
        System.out.println("THE RESULT IS: " + sum);
    }
}