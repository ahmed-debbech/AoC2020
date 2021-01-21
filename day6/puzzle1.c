#include<stdio.h>
#include <stdlib.h>
#include <string.h>

int exists(char * quests,char d, int size){
    for(int i=0; i<=size-1; i++){
        if(quests[i] == d){
            return 1;
        }
    }
    return 0;
}
char * collect(char str[], char * quests, int * size){
    for(int i=0; i<=strlen(str)-2; i++){
        if(exists(quests, str[i], *size) == 0){
            quests[*size] = str[i];
            printf("quests[%d] = %c\n", *size, str[i]);
            (*size)++;
            quests = (char*) realloc(quests, sizeof(char) * ((*size)+1));
        }
    }
    return quests;
}
int main(){
    FILE* f = fopen("input.txt", "r");
    if(f == NULL){
        return 0;
    } 
    char str[254];
    int i=0;
    char * quests = malloc(sizeof(char));
    int size=0;
    int counts[1000];
    while (fgets(str, 254, f) != NULL){
        if(strcmp(str, "\n") !=0){
            printf("string read: %s\n", str);
            quests = collect(str, quests, &size);
        }else{
            counts[i] = size;
            printf("counts[%d]: %d\n", i, counts[i]);
            free(quests);
            size =0;
            quests = malloc(sizeof(char));
            i++;
        }
    }
     int sum = 0;
    for(int j=0; j<=i-1; j++){
        printf("%d | ", counts[j]);
        sum = sum + counts[j];
    }
    printf("THE RESULT IS: %d\n", sum);
    fclose(f);
    return 0;
}