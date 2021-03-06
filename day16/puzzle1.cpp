#include <iostream>
#include <vector>
#include <fstream>
#include <cstdio>
#include <sstream>

using namespace std;

typedef struct{
    string min="";
    string max="";
}range;
class rule{
    public:
    string field_name="";
    range frange;
    range srange;
    void assign(string line){
        int i=0;
        int check=0;
        string buf("");
        while(i != line.size()){
            if(check == 0){
                if(line[i] == ':'){
                    check=1;
                }else{
                    field_name += line[i];
                }
            }else{
                if(check == 1){
                    if(line[i] == '-'){
                        check=2;
                    }else{
                        frange.min += line[i];
                    }
                }else{
                    if(check == 2){
                        if(line[i] == ' '){
                            check=3;
                        }else{
                            frange.max += line[i];
                        }
                    }else{
                        if(check == 3){
                            if((line[i] >= '0') && (line[i] <= '9')){
                                srange.min += line[i];
                            }else{
                                if(line[i] == '-'){
                                    check = 4;
                                }
                            }
                        }else{
                            if(check == 4){
                                if(line[i] == '\n'){
                                    check = -1;
                                }else{
                                    srange.max += line[i];
                                }
                            }
                        }
                    }
                }
            }
            i++;
        }
    }
};
vector<int> extractNum(string line){
    vector<int> v;
    string buff="";
    for(int i=0; i<=line.size(); i++){
        if(line[i] >= '0' && line[i] <= '9'){
            buff += line[i];
        }else{
            v.push_back(stoi(buff));
            buff = "";
        }
    }
    return v;
}
vector<rule> parse(vector<vector<int>> & tickets){
    vector<rule> rules;
    ifstream input("input.txt");
    string line;
    int parts = 1;
    while(getline (input, line)){
        //cout << line;
        if((line != "your tickets") && (parts == 1)){
            rule ru;
            if(line != "\0"){
                ru.assign(line);
                rules.push_back(ru);
            }else{
                parts = 2;
            }
        }else{
            if(parts == 2){
                parts=3;
                getline(input,line);
                //this is my card
                tickets.push_back(extractNum(line));
                getline(input, line);
                getline(input,line);
            }else{
                if(parts ==3){
                    tickets.push_back(extractNum(line));
                }
            }
        }
    }
    input.close();
    return rules;
}
int getError(vector<vector<int>> tickets, vector<rule> rules){
    int count =0;
    for(int i=1; i<= tickets.size()-1; i++){
        for(int j=0; j<=tickets[i].size()-1; j++){  
            bool valid = false;
            for(int k=0; (k<=rules.size()-1) && (valid==false); k++){
                if(((tickets[i][j] >= stoi(rules[k].frange.min)) && (tickets[i][j] <= stoi(rules[k].frange.max))) 
                || ((tickets[i][j] >= stoi(rules[k].srange.min)) && (tickets[i][j] <= stoi(rules[k].srange.max)))){
                    valid = true;
                }
            }
            if(valid == false){
                count += tickets[i][j];
            }
        }
    }
    return count;
}
int main(){
    vector<vector<int>> tickets;
    vector<rule> rules = parse(tickets);
    for(int i=0; i<=rules.size()-1; i++){
        cout << "[" << i << "] " << rules[i].field_name 
        << rules[i].frange.min << " - " << rules[i].frange.max 
        << " or " << rules[i].srange.min << " - " << rules[i].srange.max
        << endl;
    }
    for(int i=0; i<=tickets.size()-1; i++){
        for(int j=0; j<=tickets[i].size()-1; j++){
            cout << tickets[i][j] << ",";
        }
        cout << endl;
    }
    int res = getError(tickets, rules);
    cout << "THE RESULT IS: " << res << endl;
    return 0;   
}