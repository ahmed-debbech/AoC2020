import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.regex.*;  
import java.util.Scanner;
import java.lang.Math;

public class puzzle2{

    static class Location{
        public long addr; //the memory address
        public long value; //the decimal value content
        public String addrbin; //the binary representation of that address
        public String mask; //the mask
        public long newaddr; // the new address after applying the mask
        Location(int val){
            value = val;
        }
        Location(long addr , long value){
            this.value = value;
            this.addr = addr;
        }
        Location(long addr , long value , String addrbin, String mask, long newaddr){
            this.value = value;
            this.addr = addr;
            this.addrbin = addrbin;
            this.mask = mask;
            this.newaddr = newaddr;
        }
        Location(){
            newaddr = 0;
            value = 0;
            addr = 0;
            mask="";
            addrbin="";
        }
        @Override
        public String toString(){
            return "addr: " + addr + " value: " + value + " addrbin: " + addrbin + " mask: " + mask + " newaddr: " + newaddr;
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
        ArrayList<Location> memory = new ArrayList<Location>();
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
                    l.addrbin = toBinary(l.addr);
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
            for(int i=0; i<=loc.addrbin.length()-1; i++){
                if((loc.mask.charAt(i) == '1') || (loc.mask.charAt(i) == 'X')){
                    StringBuilder string = new StringBuilder(loc.addrbin); 
                    string.setCharAt(i, loc.mask.charAt(i)); 
                    loc.addrbin = string.toString();
                }
            }
        }
    }
    public static long countVal(ConcurrentSkipListMap<Long,Location> mem){
        long r = 0;
        for(Map.Entry<Long,Location> l : mem.entrySet()){
            r += l.getValue().value;
        }
        return r;
    }
    public static ArrayList<String> resolve(int size, char bit, int counter, String ent, ArrayList<String> p){
        //System.out.println("[STACK]" + " counter: " + counter + " ent: " + ent);
        if(counter < size){
            ent  = ent + Character.toString(bit);
        }else{
            p.add(ent);
            return p;
        }
        resolve(size, '0', counter+1, ent, p);
        resolve(size, '1', counter+1, ent, p);
        return p;
    }
    public static int getX(String addr){
        int count = 0;
        for(int i=0; i<= addr.length()-1; i++){
            if(addr.charAt(i) == 'X') count++;
        }
        return count;
    }
    public static void applyResolving(ArrayList<String> list,Location entry, ConcurrentSkipListMap<Long, Location> memory){
        Location l = entry;
        for(String g : list){
            String gg = entry.addrbin;
            int j=0;
            int count = -1;
            for(int i=0; i<=g.length()-1; i++){
                for(j=count+1; j<=gg.length()-1; j++){
                    if(gg.charAt(j) == 'X'){
                        count = j;
                        StringBuilder string = new StringBuilder(gg); 
                        string.setCharAt(j, g.charAt(i)); 
                        gg = string.toString();
                        break;
                    }
                }
            }
            long addr = Long.parseLong(gg, 2);
            Location k = new Location(addr, l.value, gg, l.mask, 0);
            memory.put(addr, k);
        }
    }
    public static void resolveFloats(ArrayList<Location> list, ConcurrentSkipListMap<Long, Location> memory1){
        ArrayList<String> p = new ArrayList<String>();
        for(Location d : list){
            System.out.println(d.addr);
            int c = getX(d.addrbin);
            System.out.println(c);
            String s = "";
            resolve(c, '0', 0, s, p); 
            resolve(c, '1', 0, s, p); 
            applyResolving(p, d, memory1);
            p.clear();
        }
    }
    public static void main(String args[]){
        ArrayList<Location> memory = structureData();
        applyMask(memory);
        ConcurrentSkipListMap<Long,Location> mem1 = new ConcurrentSkipListMap<>();
        resolveFloats(memory, mem1);
        long res = countVal(mem1);
        System.out.println("THE RESULT IS: *" + res +"*");
    }
}