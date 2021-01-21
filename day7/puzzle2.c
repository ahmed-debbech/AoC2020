#include<stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    int count;
    char bag[64];
}inside;
typedef struct{
    char color[64];
    int count;
    inside in[10];
}bag;
void split(char str[], char split1[], char split2[]){
    int i =0;
    do{
        split1[i] = str[i];
        i++;
    }while(((str[i] < '0') || (str[i]>'9')) && (str[i] != '\n'));
    if(str[i] != '\n'){
        int j=0;
        do{
            split2[j] = str[i];
            i++;
            j++;
        }while(str[i] != '\n');
        split2[j] = '\0';
    }
}
void fillData(bag * b, char s1[], char s2[]){
    char color[20], c[20];
    sscanf(s1, "%s %s bags", color, c);
    char r [50] = "";
    strcat(r, color);strcat(r, " ");strcat(r, c);
    strcpy(b->color, r);
    printf("color: *%s*\n", b->color);
    if(strcmp(s2, "") == 0){
        b->count = 0;
    }else{
        b->count=0;
        char buff[50] = "";
        int g=0;
        for(int i=0; i<=strlen(s2)-1; i++){
            if((s2[i] != ',') && (s2[i] != '.')){
                buff[g] = s2[i];
                g++;
            }else{
                buff[g] = '\0';
                printf("bag inside: *%s*\n", buff);
                int count;
                strcpy(r, "");
                sscanf(buff, "%d %s %s bag",&count, color, c);
                strcat(r, color);strcat(r, " ");strcat(r, c);
                strcpy(b->in[b->count].bag, r);
                b->in[b->count].count = count;
                printf("color inside *%s*\n", b->in[b->count].bag);
                printf("count inside %d\n", b->in[b->count].count);
                b->count++;
                g=0; strcpy(buff, "");
            }
        }
    }
}
bag * getData(int * size){
    FILE* f = fopen("input.txt", "r");
    if(f == NULL){
        return NULL;
    } 
    bag * bg = malloc(sizeof(bag));
    char str[254];
    while (fgets(str, 254, f) != NULL){
        printf("**************************************\n");
        printf("read string: %s\n", str);
        char split1[254]; strcpy(split1, "");
        char split2[254]; strcpy(split2, "");
        split(str,split1, split2);
        printf("split1 = %s\n", split1);
        printf("split2 = %s \n", split2);
        fillData(&bg[*size], split1, split2);
        printf("size of table = %d\n", *size);
        (*size)++;
        bg = (bag*) realloc(bg , sizeof(bag)* ((*size) +1));
        if(bg == NULL){
            perror("couldnt realloc\n");
        }
    }
    fclose(f);
    return bg;
}
void show(bag *bg, int size){
    for(int i=0; i<=size-1; i++){
        printf("====================\n");
        printf("bags %d color : %s: contain %d bugs which are:\n", i, bg[i].color, bg[i].count);
        for(int j=0; j<=bg[i].count-1; j++){
            printf("        bag %d with color %s and takes %d\n", j, bg[i].in[j].bag, bg[i].in[j].count);
        }
    }
}
int valid(bag * table, int size, char bag[]){
    int i=0, h=0;
    int count =0;
    for(h=0; h<= size-1;h++){
        if(strcmp(table[h].color, bag) == 0){
            printf("bag : %s\n", bag);
            for(int k=0; k<=table[h].count-1; k++){
                count += table[h].in[k].count;
                printf("count : %d\n", table[h].in[k].count);
                count += (table[h].in[k].count * valid(table, size, table[h].in[k].bag));
                printf("%d + ( %d * %d) +\n",count , table[h].in[k].count, valid(table, size, table[h].in[k].bag));
                printf("total count : %d\n", count);
            }
            printf("count end %d\n", count);
            return count;
        }
    }
    return count;
}
int main(){
    int size=0;
    bag * bg = getData(&size);
    if(bg == NULL){
        return 0;
    }
    show(bg, size);
    int count;
    count = valid(bg, size ,"shiny gold");
    printf("THE RESULT IS %d \n", count);
    free(bg);
    return 0;
}