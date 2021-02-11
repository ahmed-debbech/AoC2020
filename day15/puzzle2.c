#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int * get_data(int * m){
    FILE * f = fopen("input.txt", "r");
    int * arr = malloc(sizeof(int) * ((*m)+1));
    if(f != NULL){
        char str[100];
        int i=0;
        *m=0;
        fgets(str, 100, f);
        printf("%s\n", str);
        char buf[4] = "";
        for(i=0; i<=strlen(str); i++){
            if((str[i] == ',') || (str[i] == '\0')){
                arr[*m] = atoi(buf);
                strcpy(buf, "");
                (*m)++;
                arr = realloc(arr, sizeof(int) * ((*m)+1));
            }else{
                strncat(buf, &str[i], 1);
            }
        }
        fclose(f);
    }
    return arr;
}
int guess_number(int * arr, int size){
    //for(int h=0; h<=size-1; h++){printf("arr: %d\n", arr[h]);}
    int index = size-1;
    int number = 0;
    int i;
    printf("size: %d\n", size);
    for(i=size-2; i>=0; i--){
        if(arr[i] == arr[index]){
            number = index - i;
            return number;
        }
    }
    return 0;
}
int main(){
    int size = 0;
    int * arr = NULL;
    arr = get_data(&size);
    for(int h=0; h<=size-1; h++) printf("arr: %d\n", arr[h]);
    printf("sii : %d\n", size);
    for(int i=0; i<=30000000; i++){
        printf("size: %d\n", size);
        int num = guess_number(arr, size);
        size++;
        arr = realloc(arr, sizeof(int) * size);
        arr[size-1] = num;
    }
    for(int h=0; h<=30000000; h++){printf("count (%d) = arr: %d\n", h+1, arr[h]);}
    printf("THE RESULT IS: %d\n", arr[2019]);
    return 0;
}