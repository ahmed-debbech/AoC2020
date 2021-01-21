#include<stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    int min;
    int max;
    char ch;
    char pass[100];
}data;

data * getData(){
    FILE* f = fopen("input.txt", "r");
    if(f == NULL){
        return NULL;
    } 
    data * arr = malloc(sizeof(data) * 1000);
    char str[100];
    int i=0;
    while (fgets(str, 100, f) != NULL){
        sscanf(str,"%d-%d %c: %s\n", &arr[i].min, &arr[i].max, &arr[i].ch, arr[i].pass);
        i++;
    }
    fclose(f);
    for(i=0; i<=999; i++){
        printf("%d-%d %c: %s\n", arr[i].min, arr[i].max, arr[i].ch, arr[i].pass);
    }
    return arr;
}
int valid(data d){
    int chcounter = 0;
    for(int j=0; j<=strlen(d.pass)-1; j++){
        if(d.pass[j] == d.ch){
            chcounter++;
        }
    }
    if((chcounter>=d.min) && (chcounter<=d.max)){
        return 1;
    }
    return 0;
}
int main(){
    data * array = getData();
    if(array != NULL){
        int count = 0;
        for(int i=0; i<=999; i++){
            if(valid(array[i]) == 1){
                count++;
            }
        }
        printf("result is: %d\n", count);
    }
    return 0;
}