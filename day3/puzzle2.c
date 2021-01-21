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
int countTrees(char ** mat, int incJ, int incI){
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
        }else{
        }
        j += incJ;
        i +=incI;
    }while(i <= 322);
    return numTrees;
}
int main(){
    char ** mat = getData();
    //dimensions are 323  *  31
    int res = countTrees(mat,1,1);
    int res1 = countTrees(mat,3,1);
    int res2 = countTrees(mat,5,1);
    int res3 = countTrees(mat,7,1);
    int res4 = countTrees(mat,1,2); 
    // for(int i=0; i<=322; i++){
    //     for(int j =0; j<=31; j++){
    //         printf("%c", mat[i][j]);
    //     }
    //     printf("\n");
    // }
    printf("%d , %d , %d , %d , %d\n", res,res1,res2,res3,res4);
    long long int ree = res*res1*res2*res3*res4;
    printf("the result is: %lld\n", ree);
    return 0;
}