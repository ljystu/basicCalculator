package com.example.basiccalculator;


public class calculate {
    String m;
    char filted[];
    double token;
    int p = 0,f=0;
    String symbol;
    double result;
    int scan() {
        //超前搜索算法函数，词法分析核心程序，由实验一内容略作调整得到
        int flag;
        int fixed = 0;
        String temp="";
        if (this.p == filted.length)
            return 0;
        if (Character.isDigit(filted[this.p])) {
             temp+= filted[this.p];
            this.p++;
            if (this.p != filted.length) {
                while (Character.isDigit(filted[this.p]) || filted[this.p] == '.') {
                    if (filted[this.p] == '.'){
                        fixed = 1;
                        temp+=filted[this.p];
                    }
                    else{
                        temp+=filted[this.p];
                    }
                    this.p++;
                    if(this.p==filted.length)
                        break;
                }
            }
            token=Double.parseDouble(temp);
            temp="";
            if (fixed == 1)
                flag = 9999;
            else
                flag = 999;
            return flag;
        } else if (filted[this.p] == '²') {
            this.p++;
            flag = 40;
            return flag;
        } else if (filted[this.p] == '%') {
            this.p++;
            flag = 42;
            return flag;
        } else if (filted[this.p] == '+') {
            token = filted[this.p];

            flag = 33;
            this.p++;
            return flag;
        } else if (filted[this.p] == '-') {
            token = filted[this.p];

            flag = 34;
            this.p++;
            return flag;
        } else if (filted[this.p] == '*') {
            token = filted[this.p];

            flag = 35;
            this.p++;
            return flag;
        } else if (filted[this.p] == '/') {
            this.p++;
            flag = 36;
            return flag;}
        else if (filted[this.p] == '(') {
                this.p++;
                flag = 50;
                return flag;
        }
        else if (filted[this.p] == ')') {
            this.p++;
            flag = 51;
            return flag;}
        else
            return 0;
    }

    void getNext() {
        //检查待分析源文件需要下一个符号，调用该函数获取下一个单词
        int flag = scan();
        if (flag == 0 && this.p == filted.length )        //读取字符为文档的结束标志时退出
        {
            symbol="END";
            return;
        }
        //判断输入字符的类型
        else if (flag == 999)                                //整数： 999；
            symbol = "INTC";
        else if (flag == 9999)                                //小数：9999；
            symbol = "DECI";
        else if (flag == 33)                                //运算符及分隔符
            symbol = "+";
        else if (flag == 34){
            if(this.p==1)
                f=1;
                symbol = "-";
        }
        else if (flag == 35)
            symbol = "*";
        else if (flag == 36)
            symbol = "/";
        else if (flag == 40)
            symbol = "²";
        else if (flag == 42)
            symbol = "%";
        else if (flag == 50)
            symbol = "(";
        else if (flag == 51)
            symbol = ")";
        else
            symbol="END";
    }

    double Exp() {
        //该函数根据Exp→ Term {'+'|'-' Term} 进行分析
        double r1 = Term();
        double r2;
        while (true) {
            getNext();
            if(symbol=="END")return r1;
            if (symbol == "+" || symbol == "-") {
               if (symbol == "+"){
                    r2 = Term();
                    r1 = r1 + r2; }
                else {
                     r2 = Term();
                    r1 = r1 - r2;
                }
            } else {
                this.p--;
                break;
            }
        }
        return r1;
    }

    double Term() {
        double r1 = Factor();
        double r2;
        while (true) {
            getNext();
            if(symbol=="END")return r1;
            if (symbol == "*" || symbol == "/"|| symbol=="%") {
                if (symbol == "*"){
                    r2 = Factor();
                    r1 = r1 * r2;
                }
                else if(symbol=="/"){
                    r2=Factor();
                    r1 = r1 / r2;
                }
                else{
                    r2=Factor();
                    r1=r1%r2;
                }
            }
            else if(symbol==")")
                return r1;
            else {
                this.p--;
                break;
            }
        }
        return r1;
    }

    double Factor() {
        //该函数根据Factor→ ID | INTC | DECI | '(' Exp ')' 进行分析
        boolean calculated=false;
        double temp=0;
        getNext();
        if(f==1)
            getNext();
        if(symbol=="END")return 0;
        else if (symbol == "INTC"||symbol == "DECI"||symbol=="("){
            if (symbol == "("){
                double r1=Exp();
                return r1;
            }
            double r1=token;
            if(f==1){
                r1=0-r1;
                f=0;
            }
            while(true){
                getNext();
                if(symbol=="²"){
                    calculated=true;
                    temp=token*token;
                    token=temp;
                }
                else{
                    this.p--;
                    break;
                }
            }
            if( calculated==true){
                token=temp;
                calculated=false;
                return token;}
            return r1;
        }
        else
            return 0;
    }
}
