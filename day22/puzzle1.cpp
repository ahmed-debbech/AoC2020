#include <iostream>
#include <queue> 
#include <fstream>

using namespace std;

int main(){

    queue<int> pl1;
    queue<int> pl2;
    ifstream file;
    file.open("input.txt");
    if(!file){
        cout << "error" << endl;
        exit(1);
    }
    int num;
    int pass = 0;
    string ss;
    while(getline(file, ss)){
        if(ss == "Player 2:"){
            pass = 1;
        }  
    
        if(pass == 0){
            if((ss.substr(0,1) >= "0") && ss.substr(0,1) <= "9"){
                num = atoi(ss.c_str());
                pl1.push(num);
            }
        }else{
            if((ss.substr(0,1) >= "0") && ss.substr(0,1) <= "9"){
                num = atoi(ss.c_str());
                pl2.push(num);
            }
        }
    }
    file.close();
    // ============================================================
    int v=1;
    while((!pl2.empty()) && (!pl1.empty())){
        cout << "Round " << v << endl;
        int num1 = pl1.front();
        pl1.pop();
        int num2 = pl2.front(); 
        pl2.pop();
        if(num1 > num2){
            cout << "Player 1 wins"<< endl;
            // push to first player
            pl1.push(num1);
            pl1.push(num2);
        }else{
            if(num1 < num2){
                cout << "Player 2 wins"<< endl;
                // push to second player
                pl2.push(num2);
                pl2.push(num1);
            }
        }
        v++;
    }
    int score = 0;
    if(!pl1.empty()){
        int size = pl1.size();
        while(!pl1.empty()){
            score = score + (pl1.front() * size);
            pl1.pop();
            size--;
        }
    }else{
        int size = pl2.size();
        while(!pl2.empty()){
            score = score + (pl2.front() * size);
            pl2.pop();
            size--;
        }
    }
    cout << "THE RESULT IS: " << score << endl;
    return 0;
}