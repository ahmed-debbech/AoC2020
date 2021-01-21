#include<stdio.h>
#include <stdlib.h>
#include <string.h>

char ** getData(){
    FILE* f = fopen("input.txt", "r");
    if(f == NULL){
        return NULL;
    } 
    char str[50];
    int i=0;
    char ** mat = malloc(sizeof(char*)*323);
    while (fgets(str, 50, f) != NULL){
        printf("%ld\n",strlen(str));
        char * arr = malloc(sizeof(char)* strlen(str));
        for(int j=0; j<=strlen(str)-2; j++){
            arr[j]=str[j];
        }
        mat[i] = arr;
        i++;
    }
    fclose(f);
    
    return mat;
    
}
int countTrees(char ** mat){
    //bottom is line 322
    //each time you reach colomn 31 shift back to next line

    int numTrees = 0;
    int i =0;
    int j=0;
    do{
        if(j > 30){
            j = j - 30;
            j--;
        }
        if(mat[i][j] == '#'){
            numTrees++;
            mat[i][j] = 'X';
        }else{
            mat[i][j] = 'O';
        }
        j += 3;
        i +=1;
    }while(i <= 322);
    return numTrees;
}
int main(){
    char ** mat = getData();
    //dimensions are 323  *  31
    int res = countTrees(mat);
    for(int i=0; i<=322; i++){
        for(int j =0; j<=31; j++){
            printf("%c", mat[i][j]);
        }
        printf("\n");
    }
    printf("the result is: %d\n", res);
    return 0;
}