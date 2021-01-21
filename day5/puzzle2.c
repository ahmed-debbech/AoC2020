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
void sort(int array[], int n)
{
    int i, j, position, swap;
    for (i = 0; i < (n - 1); i++)
    {
        position = i;
        for (j = i + 1; j < n; j++)
        {
            if (array[position] > array[j])
                position = j;
        }
        if (position != i)
        {
            swap = array[i];
            array[i] = array[position];
            array[position] = swap;
        }
    }
}
int checkMine(int array[], int size){
    int seats = 12;
    for(int i=0; i<=size-1; i++ ){
        if(seats != array[i]){
            return seats;
        }   
        seats++;
        printf("array %d\n", array[i]);
    }
    return -1;
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
        //printf("%d: %d\n",i, array[i]);
        //fprintf(f1, "%d: %d\n",i, array[i]);
        i++;
    }
    fclose(f1);
    sort(array,i);
    for(int j=0; j<=i-1; j++){
        printf("%d: %d\n", j, array[j]);
    }
    //printf("THE RESULT IS: %d \n", max);
    printf("THE RESULT IS: %d\n", checkMine(array, i));
    fclose(f);
    return 0;
}