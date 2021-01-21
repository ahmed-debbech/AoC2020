#include<stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    char byr[50];
    char iyr[50];
    char eyr[50];
    char hgt[50];
    char hcl[50];
    char ecl[50];
    char pid[50];
    char cid[50];
}data;

void addToStruct(data * d, char key[], char value[]){
    printf("THIS IN ADDTOSTRUCT: key %s\n", key);
    printf("THIS IN ADDTOSTRUCT: value %s\n", value);
    if(strcmp(key, "byr") == 0){
        strcpy(d->byr, value);
    }else{
        if(strcmp(key, "iyr") == 0){
            strcpy(d->iyr, value);
        }else{
            if(strcmp(key, "eyr") == 0){
                strcpy(d->eyr, value);
            }else{
                if(strcmp(key, "hgt") == 0){
                    strcpy(d->hgt, value);
                }else{
                    if(strcmp(key, "hcl") == 0){
                        strcpy(d->hcl, value);
                    }else{
                        if(strcmp(key, "ecl") == 0){
                            strcpy(d->ecl, value);
                        }else{
                            if(strcmp(key, "pid") == 0){
                                strcpy(d->pid, value);
                            }else{
                                if(strcmp(key, "cid") == 0){
                                    strcpy(d->cid, value);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
void fillstruct(data *d, char str[]){
    printf("string: %s\n", str);
    char key[4];
    char value[50];
    int reached = 0;
     strcpy(key,"");
      strcpy(value,"");
    char buff[100];
    for(int i=0; i<=strlen(str)-1; i++){
        if((str[i] != ':') &&(str[i] != ' ')&& (reached == 0)){
            strncat(key,&str[i], 1);
            printf("key %s\n", key);
        }else{
            reached =1 ;
            if(str[i] != ':'){
                reached = 1;
                if(str[i] != ' ' && str[i] != '\n'){
                    strncat(value,&str[i], 1);
                    printf("value %s\n", value);
                }else{
                    printf("erfg");
                    if((str[i] == ' ') || (str[i] == '\n')){
                        reached = 0;
                        printf("sefg\n");
                        addToStruct(d,key,value);
                        strcpy(key,"");
                        strcpy(value,"");
                    }
                }
            }
        }
    }
}
void init(data *d){
    strcpy(d->byr, "");
    strcpy(d->iyr, "");
    strcpy(d->eyr, "");
    strcpy(d->hgt, "");
    strcpy(d->hcl, "");
    strcpy(d->ecl, "");
    strcpy(d->pid, "");
    strcpy(d->cid, "");
}
data * getData(){
    FILE* f = fopen("input.txt", "r");
    if(f == NULL){
        return NULL;
    } 
    char str[254];
    int i=0;
    data * d = malloc(sizeof(data)*(i+1));
    init(d);
    while (fgets(str, 254, f) != NULL){
        printf("%ld\n",strlen(str));
        if(strcmp(str, "\n") != 0){
            fillstruct(d+i, str);
        }else{
            i++;
            d = realloc(d, sizeof(data)*(i+1));
            init(d+i);
        }
    }
    printf("TABLE COUNT %d",i);
    fclose(f);
    return d;
}
int valid(data d){
    if(strcmp(d.byr, "") != 0 && strcmp(d.ecl, "") != 0 && strcmp(d.eyr, "") != 0
    && strcmp(d.hcl, "") != 0 && strcmp(d.hgt, "") != 0 && strcmp(d.iyr, "") != 0 && strcmp(d.pid, "") != 0){
        return 1;
    }
    return 0;
}
int main(){
    data * array = getData();
    printf("********************\n");
    for(int i=0; i<=264; i++){
        printf("========\n");
        printf("byr: %s\n", array[i].byr);
        printf("iyr: %s\n", array[i].iyr);
        printf("eyr: %s\n", array[i].eyr);
        printf("hgt: %s\n", array[i].hgt);
        printf("hcl: %s\n", array[i].hcl);
        printf("ecl: %s\n", array[i].ecl);
        printf("pid: %s\n", array[i].pid);
        printf("cid: %s\n", array[i].cid);
        printf("=======\n");
    }
    int count =0;
    for(int i=0; i<=264; i++){
        if(valid(array[i]) == 1){
            count++;
        }
    }
    printf("THE RESULT IS: %d\n", count);
    return 0;
}