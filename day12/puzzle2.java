import java.io.*;
import java.util.Arrays;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;
import java.lang.Math;

public class puzzle2{
    
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
        class coord{
            int x;
            int y;
            char dir;
            coord(char d){
                x=0;
                y=0;
                dir=d;
            }
        }
        char bosl[]= {'W','N','E','S'};
        int g,f;
        int aux1,aux2; //aux
        int X = 10; //east or west
        int Y = 1; //south or north
        coord Xboat = new coord('E');
        coord Yboat = new coord('N');
        char direction = 'E';
        char direction2 ='N';
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
                    for(int d=arr.get(i).units; d>0; d -= 90){
                        int aux = X;
                        X = Y;
                        Y = aux;
                        X *= -1;
                    }
                break;
                case 'R':
                    for(int d=arr.get(i).units; d>0; d -= 90){
                        int aux = X;
                        X = Y;
                        Y = aux;
                        Y *= -1;
                    }
                break;
                case 'F':
                    Xboat.x += (X*arr.get(i).units);
                    Yboat.y += (Y*arr.get(i).units);
                break;
            }
        System.out.println("inst " + arr.get(i).dir + " " + arr.get(i).units);
        System.out.println("facing dir: " + direction + " sec dir: "+ direction2);
        System.out.println("X : "+ X + " Y: " + Y); 
        System.out.println("Xboat : "+ Xboat.x + " dir: " +Xboat.dir+ " Yboat: " + Yboat.y + " dir: "+ Yboat.dir);
        System.out.println("abs( X: " + Math.abs(Xboat.x) + " Y: "+ Math.abs(Yboat.y) + ")");
        int res = Math.abs(Xboat.x) + Math.abs(Yboat.y);
        System.out.println("THE RESULT IS : " +  res);
        System.out.println("***************");
        }
    }
    public static void main(String args[]){
        ArrayList<Inst> array = structureData();
        show(array);
        parse(array);
    }
}