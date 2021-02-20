#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void get_data(long * m, long *size){
    FILE * f = fopen("input.txt", "r");
    if(f != NULL){
        char str[100];
        int i=0;
        fgets(str, 100, f);
        printf("%s\n", str);
        char buf[4] = "";
        for(i=0; i<=strlen(str); i++){
            if((str[i] == ',') || (str[i] == '\0')){
                m[atoi(buf)] = (*size);
                strcpy(buf, "");
                (*size)++;
            }else{
                strncat(buf, &str[i], 1);
            }
        }
        fclose(f);
    }
}
long guess_number(long size){
    FILE * f = fopen("input.txt", "r");
    long mm[50];
    int o=0;
    if(f != NULL){
        char str[100];
        fgets(str, 100, f);
        char buf[4] = "";
        for(int i=0; i<=strlen(str); i++){
            if((str[i] == ',') || (str[i] == '\0')){
                mm[o] = atoi(buf);
                o++;
                strcpy(buf, "");
            }else{
                strncat(buf, &str[i], 1);
            }
        }
        fclose(f);
    }
    for(long i=0; i<=4; i++){printf("[array] turn: %ld",mm[i]);}
    long m[size];
    for(long i=0; i<=size; i++){
        m[i] = -1;
    }
    long h=0;
    long last;
    int b = 1;
    long curr;
    int ff = o;
    for(int g=0; g<=o; g++){
        m[mm[o]] = g;

    }
    for(h=0; h<=size; h++){
        if(h <= o){
            m[mm[h]] = h;
            if(h == o){
                m[mm[h]] = -1; 
                last = mm[h];
            }
        }else{
            curr = -1;
            if(m[last] != -1){
                printf("ewdfij %ld\n", m[last]);
                curr = (h-1) - m[last];
                m[last] = curr; 
            }else{
                printf("epep\n");
                m[last] = h-1;
                curr = 0;
            }
            if(curr == size){
                //last = m[curr];
                printf("RESS ======== %ld\n", m[curr]);
            }
        }
        last = curr;
    }
    for(long i=0; i<=size; i++){printf("[array] turn: %ld - spoken: %ld\n", i, m[i]);}
    return last;
}
int main(){
    long size = 0;
    const long max = 6;
    long * arr = malloc(sizeof(long) * max);
    for(long i=0; i<=max; i++){
        arr[i] = -1;
    }
    printf("THE RESULT IS: \n");
    //get_data(arr, &size);
    long res = guess_number(max);
    //for(long h=0; h<=size-1; h++){printf("count (%ld) = arr: %ld\n", h+1, arr[h]);}
    printf("THE RESULT IS: %ld\n", res);
    return 0;
}