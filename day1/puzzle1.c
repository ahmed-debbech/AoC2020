#include <stdio.h>
#include <stdlib.h>

int main(){
    FILE * f = fopen("input.txt", "r");
    int arr[200];
    if(f != NULL){
        char str[100];
        int i=0;
        while (fgets(str, 100, f) != NULL){
            arr[i] = atoi(str);
            i++;
        }
        fclose(f);
        for(i=0;i<=199;i++){
            printf("%d\n", arr[i]);
        }
        //processing
        for(i=0;i<=199;i++){
            int j=0;
            for(j=0; j<=199; j++){
                if(arr[i] + arr[j] == 2020){
                    printf("RESULT IS: %d\n", arr[i] * arr[j]);
                    printf("%d\n", arr[i]);
                    printf("%d\n",arr[j]);
                    return 0;
                }
            }
        }
    }
    return 0;
}