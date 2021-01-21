import java.io.*;
import java.util.Arrays;
import java.util.ArrayList; 
import java.util.regex.*;  
import java.util.Scanner;

public class puzzle2{
    
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
        boolean countAdj = false;
        int k=i;
        int l=j;
        if((i-1) >= 0){
            //System.out.println(i + " "+ j + "up");
            k=i-1; l=j;
            countAdj = false;
            do{
                if(mat.get(k).charAt(l) == '.'){ //up
                    countAdj = false;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        countAdj = true;
                    }else{
                        return false;
                    }
                }
                k--;
            }while((k >= 0) && (countAdj == false));
            if(countAdj == false){
                countAdj = true;
            }
        }
        if((i+1) <= mat.size()-1){
            //System.out.println(i + " "+ j + "down");
            k=i+1; l=j;
            countAdj = false;
            do{
                if(mat.get(k).charAt(l) == '.'){ //down
                    countAdj = false;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        countAdj = true;
                    }else{
                        return false;
                    }
                }
                k++;
            }while((k <= mat.size()-1) && (countAdj == false));
            if(countAdj == false){
                countAdj = true;
            }
        }
        if((j-1) >= 0){
            //System.out.println(i + " "+ j + "left");
            k=i; l=j-1;
            countAdj = false;
            do{
                if(mat.get(k).charAt(l) == '.'){ //left
                    countAdj = false;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        countAdj = true;
                    }else{
                        return false;
                    }
                }
                l--;
            }while((l >= 0) && (countAdj == false));
            if(countAdj == false){
                countAdj = true;
            }
        }
        if((j+1) <= mat.get(i).length()-1){
            //System.out.println(i + " "+ j + "right");
            k=i; l=j+1;
            countAdj = false;
            do{
                if(mat.get(k).charAt(l) == '.'){ //right
                    countAdj = false;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        countAdj = true;
                    }else{
                        return false;
                    }
                }
                l++;
            }while((l <= mat.get(k).length()-1) && (countAdj == false));
            if(countAdj == false){
                countAdj = true;
            }
        }
        if(((j+1) <= mat.get(i).length()-1) && (i-1) >= 0){
            //System.out.println(i + " "+ j + "up-right");
            k = i-1; l=j+1;
            countAdj = false;
            do{
                if(mat.get(k).charAt(l) == '.'){ //up-right
                    countAdj = false;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        countAdj = true;
                    }else{
                        return false;
                    }
                }
                k--;
                l++;
            }while((k >= 0 ) && (l <= mat.get(k).length()-1) && (countAdj == false));
            if(countAdj == false){
                countAdj = true;
            }
        }
        if(((j+1) <= mat.get(i).length()-1) && (i+1) <= mat.size()-1){
            //System.out.println(i + " "+ j + "down-right");
            k=i+1; l=j+1;
            countAdj = false;
            do{
                if(mat.get(k).charAt(l) == '.'){ //down-right
                    countAdj = false;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        countAdj = true;
                    }else{
                        return false;
                    }
                }
                k++;
                l++;
            }while((k <= mat.size()-1 ) && (l <= mat.get(k).length()-1) && (countAdj == false));
            if(countAdj == false){
                countAdj = true;
            }
        }
        if(((j-1) >= 0) && ((i-1) >=0)){
            //System.out.println(i + " "+ j + "up-left");
            k=i-1; l=j-1;
            countAdj = false;
            do{
                if(mat.get(k).charAt(l) == '.'){ //up-left
                    countAdj = false;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        countAdj = true;
                    }else{
                        return false;
                    }
                }
                k--;
                l--;
            }while((k >= 0) && (l >= 0) && (countAdj == false));
            if(countAdj == false){
                countAdj = true;
            }
        }
        if(((j-1) >= 0) && (i+1) <= mat.size()-1){
            //System.out.println(i + " "+ j + "down-left");
            k=i+1; l=j-1;
            countAdj = false;
            do{
                if(mat.get(k).charAt(l) == '.'){ //down-left
                    countAdj = false;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        countAdj = true;
                    }else{
                        return false;
                    }
                }
                k++;
                l--;
            }while((k <= mat.size()-1) && (l >= 0) && (countAdj == false));
            if(countAdj == false){
                countAdj = true;
            }
        }
        return countAdj;
    }
    public static int search1(ArrayList<String> mat, int i, int j){
        int countAdj = 0;
        int k,l;
        boolean found = false;
        if((i-1) >= 0){
            //System.out.println(i + " "+ j + "up");
            k=i-1; l=j;
            found = false;
            do{
                if(mat.get(k).charAt(l) == '#'){ //up
                    countAdj++;
                    found = true;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        found = true;
                    }
                }
                k--;
            }while((k >= 0) && (found == false));
        }
        if((i+1) <= mat.size()-1){
            //System.out.println(i + " "+ j + "down");
            k=i+1; l=j;
            found = false;
            do{
                if(mat.get(k).charAt(l) == '#'){ //down
                    countAdj++;
                    found = true;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        found = true;
                    }
                }
                k++;
            }while((k <= mat.size()-1) && (found == false));
        }
        if((j-1) >= 0){
            //System.out.println(i + " "+ j + "left");
            k=i; l=j-1;
            found = false;
            do{
                if(mat.get(k).charAt(l) == '#'){ //left
                    countAdj++;
                    found = true;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        found = true;
                    }
                }
                l--;
            }while((l >= 0) && (found == false));
        }
        if((j+1) <= mat.get(i).length()-1){
            //System.out.println(i + " "+ j + "right");
            k=i; l=j+1;
            found = false;
            do{
                if(mat.get(k).charAt(l) == '#'){ //right
                    countAdj++;
                    found = true;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        found = true;
                    }
                }
                l++;
            }while((l <= mat.get(k).length()-1) && (found == false));
        }
        if(((j+1) <= mat.get(i).length()-1) && (i-1) >= 0){
            //System.out.println(i + " "+ j + "up-right");
            k=i-1; l=j+1;
            found = false;
            do{
                if(mat.get(k).charAt(l) == '#'){ //up-right
                    countAdj++;
                    found = true;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        found = true;
                    }
                }
                k--;
                l++;
            }while(((k >= 0) && (l<=mat.get(k).length()-1)) && (found == false));
        }
        if(((j+1) <= mat.get(i).length()-1) && (i+1) <= mat.size()-1){
            //System.out.println(i + " "+ j + "down-right");
            k=i+1; l=j+1;
            found = false;
            do{
                if(mat.get(k).charAt(l) == '#'){ //down-right
                    countAdj++;
                    found = true;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        found = true;
                    }
                }
                k++;
                l++;
            }while(((k <= mat.size()-1) && (l<=mat.get(k).length()-1)) && (found == false));
        }
        if(((j-1) >= 0) && ((i-1) >=0)){
            //System.out.println(i + " "+ j + "up-left");
            k=i-1; l=j-1;
            found = false;
            do{
                if(mat.get(k).charAt(l) == '#'){ //up-left
                    countAdj++;
                    found = true;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        found = true;
                    }
                }
                k--;
                l--;
            }while(((k >= 0) && (l>=0)) && (found == false));
        }
        if(((j-1) >= 0) && (i+1) <= mat.size()-1){
            //System.out.println(i + " "+ j + "down-left");
            k=i+1; l=j-1;
            found = false;
            do{
                if(mat.get(k).charAt(l) == '#'){ //down-left
                    countAdj++;
                    found = true;
                }else{
                    if(mat.get(k).charAt(l) == 'L'){
                        found = true;
                    }
                }
                k++;
                l--;
            }while(((l >=0 ) && (k <= mat.size()-1)) && (found == false));
        }
        return countAdj;
    }
    public static void applyForOccupied(ArrayList<String> original, ArrayList<String> mat, int i, int j){
        int countAdj = search1(original,i,j);
        if(countAdj >= 5){
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
        int j=0;
        do{
            matrix2 = new ArrayList<>(matrix);
            matrix = apply(matrix);
            show(matrix);
            //if(j == 4) break;
            j++;
        }while(areEqual(matrix,matrix2) == false);
        int count = count(matrix);
        System.out.println(count);
    }
}