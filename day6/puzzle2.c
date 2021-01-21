#include<stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    char quest[27];
    int size;
}person;
typedef struct{
    person * pers;
    int size;
}group;
void getQuest(person * per, char quests[], int size){
    strcpy(per->quest, quests);
    //printf("persone quest %d | %s\n", size, per->quest);
    per->size = strlen(per->quest);
    printf("size for word %d\n", per->size);
}
void getGroups(group * grp, char str[]){
    if(grp->size == 0){
        printf("mm\n");
        grp->pers = malloc(sizeof(person)* ((grp->size)+1));
        (grp->size)++;
    }else{
        printf("ree\n");
        (grp->size)++;
        grp->pers = (person * )realloc(grp->pers, sizeof(person)* ((grp->size)+1));
    }
    printf("person number: %d\n", grp->size);
    getQuest(&(grp->pers[grp->size-1]), str, grp->size);
    printf("person size_quest: %d | quests: %s\n", grp->pers[(grp->size)-1].size, grp->pers[(grp->size)-1].quest);
}
int exists(char * quests,char d, int size){
    for(int i=0; i<=size-1; i++){
        if(quests[i] == d){
            return 1;
        }
    }
    return 0;
}
int count(person * pers, int size){
    printf("group ******************\n");
    int count = 0;
    int countYes = 1;
    char que[1000];
    strcpy(que, "");
    for(int i=0; i<=pers[0].size-2; i++){
        for(int r=1; r<=size-1; r++){
            if(exists(pers[r].quest, pers[0].quest[i], pers[r].size) == 1){
                countYes++;
            }
        }
        printf("yes count %d | char %c\n", countYes, pers[0].quest[i]);
        if(countYes == size){
            que[count] = pers[0].quest[i];
            count++;
        }   
        countYes=1;
    }
    printf("anwered yes : %s\n", que);
    return count;
}
int main(){
    FILE* f = fopen("input.txt", "r");
    if(f == NULL){
        return 0;
    } 
    char str[254];
    group * grp = malloc(sizeof(group) * 1);
    int size=0;
    grp[size].size=0;
    int counts[1000];
    while (fgets(str, 254, f) != NULL){
        if(strcmp(str, "\n") !=0){
            printf("string read: %s\n", str);
            printf("new person---\n");
            getGroups(&(grp[size]), str);
            //printf("persone quest %d | %s\n", size, grp[size].pers[grp[size].size-1].quest);
        }else{
            printf("new group***************\n");
            size++;
            grp = realloc(grp, sizeof(group) * (size+1));
            grp[size].size=0;
            printf("new group size %d | size person %d\n", size, grp[size].size);
        }
    }
    for(int k=0; k<=size-1; k++){
        printf("group %d: person num %d\n",k ,grp[k].size);
        for(int r=0; r<=grp[k].size-1; r++){
            printf("       group %d: person quests %s\n", k,grp[k].pers[r].quest);
        }
    }
    int v=0;
    for(v=0; v<=size-1; v++){
        counts[v] = count(grp[v].pers, grp[v].size);
    }
    int sum = 0;
    for(int j=0; j<=v-1; j++){
        printf("%d | ", counts[j]);
        sum = sum + counts[j];
    }
    printf("\n");
        printf("THE RESULT IS: %d\n", sum);
    fclose(f);
    return 0;
}