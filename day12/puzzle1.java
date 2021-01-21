import java.io.*;
import java.util.Arrays;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;
import java.lang.Math;

public class puzzle1{
    
    static class Inst{ 
        public Inst(char dir, int units){this.dir = dir; this.units = units;}
        char dir;
        int units;
    }
    public static ArrayList<Inst> structureData(){
        File file = new File("input.txt"); 
        ArrayList<Inst> matrix = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            while ((st = br.readLine()) != null) {
                Scanner sn = new Scanner(st);
                String str = sn.next();
                int k = Integer.parseInt(str.substring(1, str.length()));
                Inst i = new Inst(str.charAt(0), k);
                matrix.add(i);
            } 
        }catch(FileNotFoundException exp){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error2");
        }
        return matrix;
    }
    public static void show(ArrayList<Inst> mat){
        for(int i=0; i<=mat.size()-1; i++){
            System.out.print("dir: "+ mat.get(i).dir + " | ");
            System.out.print("units " + mat.get(i).units);
            System.out.println();
        }
        System.out.println("****************************************************************");
    }

    public static void parse(ArrayList<Inst> arr){
        char bosl[]= {'W','N','E','S'};
        int g,f;
        int X = 0; //east or west
        int Y = 0; //south or north
        char direction = 'E';
        for(int i=0; i<=arr.size()-1; i++){
            switch(arr.get(i).dir){
                case 'N': 
                    Y += arr.get(i).units;
                break;
                case 'S':
                    Y -= arr.get(i).units;
                break;
                case 'E':
                    X += arr.get(i).units;
                break;
                case 'W':
                    X -= arr.get(i).units;
                break;
                case 'L':
                    //get where the direction in bosl;
                     f=0;
                    for(f=0;f<=3; f++){
                        if(bosl[f] == direction){
                            break;
                        }
                    }
                    // start rotating left
                    g = arr.get(i).units;
                    while(g != 0){
                        g -= 90;
                        f--;
                        if(f == -1){
                            f = 3;
                        }
                    }
                    direction = bosl[f];
                break;
                case 'R':
                    //get where the direction in bosl;
                     f=0;
                    for(f=0;f<=3; f++){
                        if(bosl[f] == direction){
                            break;
                        }
                    }
                    // start rotating left
                    g = arr.get(i).units;
                    while(g != 0){
                        g -= 90;
                        f++;
                        if(f == 4){
                            f = 0;
                        }
                    }
                    direction = bosl[f];
                break;
                case 'F':
                    switch(direction){
                        case 'N': 
                            Y += arr.get(i).units;
                        break;
                        case 'S':
                            Y -= arr.get(i).units;
                        break;
                        case 'E':
                            X += arr.get(i).units;
                        break;
                        case 'W':
                            X -= arr.get(i).units;
                        break;
                    }
                break;
            }
        System.out.println("X : "+ X + " Y: " + Y); 
        System.out.println("abs( X: " + Math.abs(X) + " Y: "+ Math.abs(Y) + ")");
        int res = Math.abs(X) + Math.abs(Y);
        System.out.println("THE RESULT IS : " +  res);
        }
    }
    public static void main(String args[]){
        ArrayList<Inst> array = structureData();
        show(array);
        parse(array);
    }
}