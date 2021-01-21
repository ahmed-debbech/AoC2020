import java.io.*;
import java.util.Arrays;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;

public class puzzle1{
    
    public static ArrayList<String> structureData(){
        File file = new File("input.txt"); 
        ArrayList<String> matrix = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            String st; 
            while ((st = br.readLine()) != null) {
                //System.out.println(st); 
                Scanner sn = new Scanner(st);
                String line = sn.next();
                matrix.add(line);
            } 
        }catch(FileNotFoundException exp){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error2");
        }
        return matrix;
    }
    public static void show(ArrayList<String> mat){
        for(int i=0; i<=mat.size()-1; i++){
            for(int j=0; j<=((mat.get(i)).length()-1); j++){
                System.out.print(mat.get(i).charAt(j));
            }
            System.out.println();
        }
        System.out.println("****************************************************************");
    }
    public static boolean search(ArrayList<String> mat, int i, int j){
        boolean countAdj = true;
        if((i-1) >= 0){
            if((mat.get(i-1).charAt(j) == 'L') || (mat.get(i-1).charAt(j) == '.')){ //up
                countAdj = true;
            }else{
                return false;
            }
        }
        if((i+1) <= mat.size()-1){
            if((mat.get(i+1).charAt(j) == 'L') || (mat.get(i+1).charAt(j) == '.')){ //down
                countAdj = true;
            }else{
                return false;
            }
        }
        if((j-1) >= 0){
            if((mat.get(i).charAt(j-1) == 'L') || (mat.get(i).charAt(j-1) == '.')){ //left
                countAdj = true;
            }else{
                return false;
            }
        }
        if((j+1) <= mat.get(i).length()-1){
            if((mat.get(i).charAt(j+1) == 'L') || (mat.get(i).charAt(j+1) == '.')){ //right
                countAdj = true;
            }else{
                return false;
            }
        }
        if(((j+1) <= mat.get(i).length()-1) && (i-1) >= 0){
            if((mat.get(i-1).charAt(j+1) == 'L') || (mat.get(i-1).charAt(j+1) == '.')){ // up-right
                countAdj = true;
            }else{
                return false;
            }
        }
        if(((j+1) <= mat.get(i).length()-1) && (i+1) <= mat.size()-1){
            if((mat.get(i+1).charAt(j+1) == 'L') || (mat.get(i+1).charAt(j+1) == '.')){ //down-right
                countAdj = true;
            }else{
                return false;
            }
        }
        if(((j-1) >= 0) && ((i-1) >=0)){
            if((mat.get(i-1).charAt(j-1) == 'L') || (mat.get(i-1).charAt(j-1) == '.')){ //up-left
                countAdj = true;
            }else{
                return false;
            }
        }
        if(((j-1) >= 0) && (i+1) <= mat.size()-1){
            if((mat.get(i+1).charAt(j-1) == 'L') || (mat.get(i+1).charAt(j-1) == '.')){ //down-left
                countAdj = true;
            }else{
                return false;
            }
        }
        return countAdj;
    }
    public static int search1(ArrayList<String> mat, int i, int j){
        int countAdj = 0;
        if((i-1) >= 0){
            if(mat.get(i-1).charAt(j) == '#'){ //up
                countAdj++;
            }
        }
        if((i+1) <= mat.size()-1){
            if(mat.get(i+1).charAt(j) == '#'){ //down
                countAdj++;
            }
        }
        if((j-1) >= 0){
            if(mat.get(i).charAt(j-1) == '#'){ //left
                countAdj++;
            }
        }
        if((j+1) <= mat.get(i).length()-1){
            if(mat.get(i).charAt(j+1) == '#'){ //right
                countAdj++;
            }
        }
        if(((j+1) <= mat.get(i).length()-1) && (i-1) >= 0){
            if(mat.get(i-1).charAt(j+1) == '#'){ // up-right
                countAdj++;
            }
        }
        if(((j+1) <= mat.get(i).length()-1) && (i+1) <= mat.size()-1){
            if(mat.get(i+1).charAt(j+1) == '#'){ //down-right
                countAdj++;
            }
        }
        if(((j-1) >= 0) && ((i-1) >=0)){
            if(mat.get(i-1).charAt(j-1) == '#'){ //up-left
                countAdj++;
            }
        }
        if(((j-1) >= 0) && (i+1) <= mat.size()-1){
            if(mat.get(i+1).charAt(j-1) == '#'){ //down-left
                countAdj++;
            }
        }
        return countAdj;
    }
    public static void applyForOccupied(ArrayList<String> original, ArrayList<String> mat, int i, int j){
        int countAdj = search1(original,i,j);
        if(countAdj >= 4){
            StringBuilder b = new StringBuilder(mat.get(i));
            b.setCharAt(j, 'L');
            mat.set(i,b.toString());
        }
    }
    public static void applyForEmpty(ArrayList<String> original, ArrayList<String> mat, int i, int j){
        boolean valid = search(original,i,j);
        if(valid == true){
            StringBuilder b = new StringBuilder(mat.get(i));
            b.setCharAt(j, '#');
            mat.set(i,b.toString());
        }
    }
    public static ArrayList<String> apply (ArrayList<String> mat){
        ArrayList<String> matcopy = new ArrayList<String>(mat);
        for(int i=0; i<=mat.size()-1; i++){
            for(int j=0; j<=mat.get(i).length()-1; j++){
                if(mat.get(i).charAt(j) == '#'){
                    applyForOccupied(mat,matcopy, i, j);
                }else{
                    if(mat.get(i).charAt(j) == 'L'){
                        applyForEmpty(mat,matcopy, i, j);
                    }
                }
            }
        }
        //matcopy = new ArrayList<String>(mat); //recreate the copy (For simultaniously)
        return matcopy;
    }
    public static boolean areEqual(ArrayList<String> matrix, ArrayList<String> matrix2){
        for(int i=0; i<=matrix.size()-1; i++){
            for(int j=0; j<=((matrix.get(i)).length()-1); j++){
               if(matrix.get(i).charAt(j) != matrix2.get(i).charAt(j)){
                    return false;
               }
            }
        }
        return true;
    }
    public static int count(ArrayList<String> matrix){
        int count = 0;
        for(int i=0; i<=matrix.size()-1; i++){
            for(int j=0; j<=((matrix.get(i)).length()-1); j++){
               if(matrix.get(i).charAt(j) == '#'){
                    count++;
               }
            }
        }
        return count;
    }
    public static void main(String args[]){
        ArrayList<String> matrix = structureData();
        ArrayList<String> matrix2 = new ArrayList<>();
        show(matrix);
        do{
            matrix2 = new ArrayList<>(matrix);
            matrix = apply(matrix);
            show(matrix);
        }while(areEqual(matrix,matrix2) == false);
        int count = count(matrix);
        System.out.println(count);
    }
}