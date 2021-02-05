import java.io.*;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;
import java.lang.Math;

public class puzzle1{

    static class Location{
        public long addr; //the memory address
        public long value; //the decimal value content
        public String valuebin; //the binary representation of that value
        public String mask; //the mask
        public long newval; // the new value after applying the mask
        public String valmask; //the value after applying mask in binary
        
        Location(){
            newval = 0;
            value = 0;
            addr = 0;
            valmask="";
            mask="";
            valuebin="";
        }
        @Override
        public String toString(){
            return "addr: " + addr + " value: " + value + " valuebin: " + valuebin + " mask: " + mask + " newval: " + newval + " valmask: " + valmask;  
        }
    }
    public static String toBinary(long value){
        String end = Long.toBinaryString(value);
        if(end.length() > 36){
            end = end.substring(end.length()-36, end.length()-1);
        }else{
            if(end.length() < 36){
                String zeros = "";
                for(int h=0; h<=36 - end.length()-1; h++){
                    zeros += '0';
                }
                end = zeros.concat(end);
            }
        }
        return end;
    }
    public static ArrayList<Location> structureData(){
        File file = new File("input.txt"); 
        ArrayList<Location> memory = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            String mask="";
            while ((st = br.readLine()) != null) {
                if((st.charAt(0) == 'm') && (st.charAt(1)=='a')){
                    //here we parse the mask
                    int s = st.indexOf('=');
                    mask = "";
                    for(int i=s+2; i<=st.length()-1; i++){
                        mask += st.charAt(i);
                    }
                }else{
                    //here we parse the data
                    Location l = new Location();
                    //read the address
                    int f = st.indexOf('[');
                    String add = "";
                    for(int i=f+1; i<st.indexOf(']'); i++){
                       add += st.charAt(i); 
                    }
                    l.addr = Long.parseLong(add);
                    //read the value in that address
                    f = st.indexOf('=');
                    String val = "";
                    for(int i=f+2; i<=st.length()-1; i++){
                        val += st.charAt(i);
                    }
                    l.value = Long.parseLong(val);
                    l.mask = mask;
                    l.valuebin = toBinary(l.value);
                    memory.add(l);
                }
            } 
        }catch(FileNotFoundException exp){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error2");
        }
        return memory;
    }

    public static void applyMask(ArrayList<Location> memory){
        for(Location loc : memory){
            for(int i=0; i<=loc.valuebin.length()-1; i++){
                if(loc.mask.charAt(i) != 'X'){
                    StringBuilder string = new StringBuilder(loc.valuebin); 
                    string.setCharAt(i, loc.mask.charAt(i)); 
                    loc.valuebin = string.toString();
                }
            }
            loc.newval = Long.parseLong(loc.valuebin, 2);
        }
    }
    public static long countVal(TreeMap<Long,Long> mem){
        long r = 0;
        for(Map.Entry<Long,Long> l : mem.entrySet()){
            r += l.getValue();
        }
        return r;
    }
    public static TreeMap<Long,Long> convertToMap(ArrayList<Location> mem){
        TreeMap<Long,Long> hm = new TreeMap<Long,Long>();
        for(Location l : mem){
            hm.put(new Long(l.addr),new Long(l.newval));
        }
        return hm;
    }   
    public static void main(String args[]){
        ArrayList<Location> memory = structureData();
        for(Location l : memory){
            System.out.println(l.toString());
        }
        applyMask(memory);
        for(Location l : memory){
            System.out.println(l.toString());
        }
        TreeMap<Long,Long> mem = convertToMap(memory);
        long res = countVal(mem);
        System.out.println("THE RESULT IS: *" + res +"*");
    }
}