import java.io.*;
import java.util.*;
import java.util.regex.*;  
import java.util.Scanner;
import java.lang.Math;
import java.math.BigInteger;

public class puzzle2{
    
    public static int structureData(int [] array){
        File file = new File("input.txt"); 
        boolean i=true;
        int time = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            while ((st = br.readLine()) != null) {
                if(i == true){
                    time = Integer.parseInt(st);
                    i = false;
                }else{
                    String f="";
                    int r = 0;
                    for(int l =0; l<=st.length()-1; l++){
                        if((st.charAt(l) >= '0' ) && (st.charAt(l) <= '9')){
                            f = f.concat(String.valueOf(st.charAt(l)));
                        }else{
                            if(f != ""){
                                array[r] = Integer.parseInt(f);
                                r++;
                                f = "";
                            }
                        }   
                    }
                }
            } 
        }catch(FileNotFoundException exp){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error2");
        }
        return time;
    }
    public static ArrayList<Integer> getBuses(){
        File file = new File("input.txt"); 
        boolean i=true;
        ArrayList <Integer> ids = new ArrayList<>();
        int time=0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            st = br.readLine();
            st = br.readLine();
            String f="";
            for(int l =0; l<=st.length()-1; l++){
                if((st.charAt(l) >= '0' ) && (st.charAt(l) <= '9')){
                    f = f.concat(String.valueOf(st.charAt(l)));
                }else{
                    if(st.charAt(l) == 'x'){
                        ids.add(-1);
                        f="";
                    }else{
                        if(f != ""){
                            ids.add(Integer.parseInt(f));
                            f = "";
                        }
                    }
                }   
            }
        }catch(FileNotFoundException exp){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error2");
        }
        return ids;
    }
    public static void show(int [] mat){
        for(int i=0; i<=mat.length-1; i++){
            System.out.print(mat[i] + "|");
        }
        System.out.println();
        System.out.println("****************************************************************");
    }
    static class bus_path{
        int id;
        Map<Long, Character> departs = new HashMap<>();
        //bus_path(int id){this.id = id;}
    }
    public static void show(bus_path[] chron){
        for(int i=0; i<=chron.length-1; i++){
            System.out.println("BUS ID: "+ chron[i].id);
            for(int j=0; j<=chron[i].departs.size()-1; j++){
                if(chron[i].departs.get(j) != -1)
                    System.out.print(chron[i].departs.get(j) + "|");
            }
            System.out.println();
        }
    }
    public static List<Integer> createPattern(int [] busses){
        List<Integer> pattern = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        ids = getBuses();
        int h=1;
        int count =1;
        for(int i=1; i<=ids.size()-1; i++){
            if(ids.get(i) == busses[h]){
                h++;
                pattern.add(count);
                count=1;
                if(h <= busses.length-1){
                    i=0;
                }else{
                    break;
                }
            }else{
                count++;
            }
        }
        for(int l=0; l<=pattern.size()-1; l++){
            System.out.println(pattern.get(l));
        }
        return pattern;
    }
    public static long findStamp(int size, int [] buses, List<Integer> pattern){
        long step_size =buses[0];
        int index = 1; //we point to the second bus
        long i=0; // this is timestamps
        boolean stop =false;
        for(i=0; stop == false; i+=step_size){
            System.out.println("timestamp = " + i);
            if(((i+pattern.get(index-1)) % buses[index]) == 0){
                System.out.println(i+pattern.get(index-1));
                System.out.println("busses "+ step_size);
                step_size *= buses[index];
                index++;
                if(index == size){
                    stop = true;
                    return i;
                }
            }
        }
        return i;
    }
    public static void main(String args[]){
        int size =9; // the size of the array of all busses (change as your input)
        int []buses = new int[size];
        int timestamp = structureData(buses);
        List<Integer> pattern = createPattern(buses);
        long res = findStamp(size,buses, pattern);
        System.out.println("THE RESULT IS: "+ res );
    }
}