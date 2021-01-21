#include<stdio.h>
#include <stdlib.h>
#include <string.h>

int analayzeC(char str[]){
    int start = 0;
    int end = 8;
    for(int i=7; i<=9; i++){
        if(str[i] == 'L'){
            end = end - ((end-start) /2);
        }else{
            if(str[i] == 'R'){
                start = start + ((end-start) /2);
            }
        }
    }
    return end -1;
}
int analyzeR(char str[]){
    int start = 0;
    int end = 128;
    for(int i=0; i<=6; i++){
        if(str[i] == 'F'){
            end = end - ((end-start) /2);
        }else{
            if(str[i] == 'B'){
                start = start + ((end-start) /2);
            }
        }
    }
    return end -1;
}
int sort(int array[], int size){
    int max = array[0];
    for(int i=1; i<=size-1; i++){
        if(array[i] > max){
            max = array[i];
        }
    }
    return max;
}

int main(){
    FILE* f = fopen("input.txt", "r");
    if(f == NULL){
        return 0;
    } 
    char str[100];
    int i=0;
    int array[900];
    FILE * f1 = fopen("cat.txt","w");
    while (fgets(str, 100, f) != NULL){
        int row = analyzeR(str);
        int col = analayzeC(str);
        array[i] = row * 8 +col;
        printf("%d: %d\n",i, array[i]);
        fprintf(f1, "%d: %d\n",i, array[i]);

        i++;
    }
    fclose(f1);
    int max = sort(array,i);
    printf("THE RESULT IS: %d \n", max);
    fclose(f);
    return 0;
}