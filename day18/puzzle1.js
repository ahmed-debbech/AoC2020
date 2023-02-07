const fs = require('fs')

let file;
let f = fs.readFileSync('input', 'utf8');
file = f.split('\n')
let arr_ops = []
for(let i=0; i<=file.length-1; i++){
  arr_ops.push(file[i])
}
//console.log(arr_ops);
let tot_sum = 0;
for(let i=0; i<=arr_ops[i].length-1; i++){
    let op = arr_ops[i];
    for(let j=0; j<=op.length-1; j++){
        var re = new RegExp("^[+*()1-9]$");
        if(re.test(op[j])){
            
        }
    }
}