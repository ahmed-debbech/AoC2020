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
vector<vector<int>> getValidOnes(vector<rule> rules, vector<vector<int>> tickets){
    vector<vector<int>> v;
    v.push_back(tickets[0]); // my ticket
    for(int i=1; i<= tickets.size()-1; i++){
        int valid = 0;
        for(int j=0; (j<=tickets[i].size()-1); j++){  
            bool vv = false;
            for(int k=0; (k<=rules.size()-1) && (vv == false); k++){
                if(((tickets[i][j] >= stoi(rules[k].frange.min)) && (tickets[i][j] <= stoi(rules[k].frange.max))) 
                || ((tickets[i][j] >= stoi(rules[k].srange.min)) && (tickets[i][j] <= stoi(rules[k].srange.max)))){
                    vv = true;
                }
            }
            if(vv == true){
                valid++;
            }
        }
        if(valid == rules.size()){
            v.push_back(tickets[i]);
        }
    }
    return v;
}
bool isUsed(vector<string> rightp, string field_name){
    for(int i=0; i<=rightp.size()-1; i++){
        if(rightp[i] == field_name){
            return true;
        }
    }
    return false;
}
vector<string> getRightPlaces(vector<rule> rules, vector<vector<int>> tickets){
    vector<string> rightp(tickets[0].size());
    int k=0;
    for(int i=0; i<=rules.size()-1; i++){
        cout << "int i " << i  << " rules is " << rules[i].field_name << endl;;
        bool used = isUsed(rightp, rules[i].field_name);
        if(used == false){
            bool valid = true;
            for(int j=1; (j<=tickets.size()-1) && (valid == true); j++){
                if(((tickets[j][k] >= stoi(rules[i].frange.min)) && (tickets[j][k] <= stoi(rules[i].frange.max))) 
                || ((tickets[j][k] >= stoi(rules[i].srange.min)) && (tickets[j][k] <= stoi(rules[i].srange.max)))){
                    valid = true;
                }else{
                    valid = false;
                }
                cout << "tickets number " << tickets[j][k] << " is " << valid << endl;
            }
            if(valid == true){
                rightp[k] = rules[i].field_name;
                cout << "rightp[ " << k << "] "<< rightp[k] << endl;;
                i=-1;
                k= k+1;
            }else{
                rightp[k] = "nothing";
            }
        }else{
            cout << rules[i].field_name << " is used " << endl;
        }
    }
    return rightp;
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
    cout << "size of first   " << tickets.size() << endl;;
    tickets = getValidOnes(rules, tickets);
    cout << "=========================" << endl;
    for(int i=0; i<=tickets.size()-1; i++){
        for(int j=0; j<=tickets[i].size()-1; j++){
            cout << tickets[i][j] << ",";
        }
        cout << endl;
    }
    cout << "size of second :  " << tickets.size() << endl;
    vector<string> rightp= getRightPlaces(rules, tickets);
    for(int j=0; j<=rightp.size()-1; j++){
            cout << rightp[j] << ",";
        }
        cout << endl;
    //cout << "THE RESULT IS: " << res << endl;
    return 0;   
}