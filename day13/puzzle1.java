import java.io.*;
import java.util.Arrays;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;
import java.lang.Math;

public class puzzle1{
    
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
    public static void show(int [] mat){
        for(int i=0; i<=mat.length-1; i++){
            System.out.print(mat[i] + "|");
        }
        System.out.println();
        System.out.println("****************************************************************");
    }
    static class bus_path{
        int id;
        ArrayList<Integer> departs = new ArrayList<>();
        //bus_path(int id){this.id = id;}
    }
    public static bus_path[] run(int size, int []buses, int timestamp){
        bus_path [] bus_chron = new bus_path[size];
        for(int i=0; i<=buses.length-1; i++){
            int times=0; //initial time stamp
            bus_path x = new bus_path();
            x.id = buses[i];
            int k=times;
            int buff =0;
            x.departs.add(0);
            boolean stop = false;
            for(k=1; stop==false; k++){
                if((k-buff) == buses[i]){
                    buff=k;
                    x.departs.add(k);
                    if(k > timestamp){
                        stop = true;
                    }
                }else{
                    x.departs.add(-1);
                }
            }
            bus_chron[i] = x;
        }
        return bus_chron;
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
    public static int[] findClosest(bus_path [] chron, int size, int timestamp){
        int[] closest = new int[size];
        for(int i=0;i<=size-1;i++){
            int min=0;
            for(int j=timestamp; j<=chron[i].departs.size()-1; j++){
                min++;
            }
            closest[i] = min;
        }
        return closest;
    }
    public static void main(String args[]){
        int size =9; // the size of the array of all busses (change as your input)
        int []buses = new int[size];
        int timestamp = structureData(buses);
        System.out.println("my timestamp: "+ timestamp);
        show(buses);
        bus_path[] bus_chron= run(size, buses, timestamp);
        show(bus_chron);
        int[] closest = findClosest(bus_chron,size, timestamp);
        show(closest);
        int min = 0;
        int f=0;
        for(f=0; f<=closest.length-1; f++){
            if(closest[min] > closest[f]){
                min = f;
            }
        }
        int res = buses[min] * (closest[min]-1);
        System.out.println("THE RESULT IS: "+ res );
    }
}