#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    long num;
    long last_seen;
}entry;

long * get_data(long * m, entry ** uni, long *size1){
    FILE * f = fopen("input.txt", "r");
    long * arr = malloc(sizeof(long) * ((*m)+1));
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
                arr = realloc(arr, sizeof(long) * ((*m)+1));
            }else{
                strncat(buf, &str[i], 1);
            }
        }
        for(i=0; i<=(*m)-1; i++){
            (*uni)[*size1].num = arr[i];
            (*uni)[*size1].last_seen = i;
            (*size1)++;
            *uni = realloc(*uni, sizeof(entry) * ((*size1)+1));
        }
        fclose(f);
    }
    return arr;
}
long say_age_or_0(long index, long number_look, entry uni[], long size){
    for(long k =0; k<=size-1; k++){
        if(number_look == uni[k].num){
            if((index - uni[k].last_seen) == 0){
                return 0;
            }else{
                return index - uni[k].last_seen;
            }
        }
    }
    return -1; //it is a new number should be added to UNI
}
int guess_number(long * arr, long size, entry ** uni, long * size1){
    long index = size;
    long h = say_age_or_0(index-1, arr[index-1] ,*uni, *size1);
    if(h == -1){
        (*size1)++;
        *uni = realloc(*uni, sizeof(entry) * (*size1));
        (*uni)[(*size1)-1].num = arr[index-1];
        (*uni)[(*size1-1)].last_seen = index-1;
        return 0;
    }
    if(h == 0){
        return 0;
    }else{
        for(int i=0; i<=((*size1)-1); i++){
            if(arr[index-1] == (*uni)[i].num){
                (*uni)[i].last_seen = index-1;
            }
        }
        return h;
    }
    return 0;
}
int main(){
    //line 24 go see
    long size = 0;
    long * arr = NULL;
    long size_sec=0;
    entry * unique = malloc(sizeof(entry)*1);
    arr = get_data(&size, &unique, &size_sec);
    //for(long h=0; h<=10; h++){printf("@count (%ld) = arr: %ld + occ: %ld\n", h+1, unique[h].num, unique[h].last_seen);}

    for(long i=size; i<=30000000; i++){
        printf("counter: %ld\n", i);
        long num = guess_number(arr, size, &unique, &size_sec);
        size++;
        arr = realloc(arr, sizeof(long) * size);
        arr[size-1] = num;
    }
    for(long h=0; h<=size_sec-1; h++){printf("count (%ld) = arr: %ld + occ: %ld\n", h+1, unique[h].num, unique[h].last_seen);}
    printf("--------\n");
    for(long h=0; h<=size-1; h++){printf("count (%ld) = arr: %ld\n", h+1, arr[h]);}
    printf("THE RESULT IS: %ld\n", arr[2019]);
    return 0;
}