//
// Created by ljyst on 2019/9/23.
//



int scan(int flag, char filted[], char token[], int& p) {
	//超前搜索算法函数，词法分析核心程序，由实验一内容略作调整得到
	int i, count = 0;
	char ch;
	ch = filted[p];
	int fixed = 0;
	for (i = 0; i < 20; i++)
		token[i] = '\0';
	while (filted[p] == ' ')
		p++;
	else if (isDigit(filted[p])) {
		token[count] = filted[p];
		p++;
		count++;
		while (isDigit(filted[p]) || filted[p] == '.') {
			if (filted[p] == '.')fixed = 1;
			token[count] = filted[p];
			count++;
			p++;
		}
		if (isLetter(filted[p])) {
			p++;
			cout << "ERROR" << endl;
			return 0;
		}
		else {
			token[count] = '\0';
			if (fixed == 1)
				flag = 9999;
			else
				flag = 999;
			return flag;
		}
	}
	else if (ch == ';' || ch == '(' || ch == ')' || ch == '^'
		|| ch == ',' || ch == '\"' || ch == '\'' || ch == '~' || ch == '#' || ch == '['
		|| ch == ']' || ch == '{' || ch == '}' || ch == '\\' || ch == '.' || ch == '\?' || ch == ':')
	{
		token[0] = filted[p];
		token[1] = '\0';
		if (ch == ' ')flag = 57;
		for (i = 0; i < 41; i++)
			if (strcmp(token, operatorOrDelimiter[i]) == 0) {
				flag = 33 + i;
				break;
			}
		p++;
		return flag;
	}
	else if (filted[p] == '=') {
		p++;
		if (filted[p] == '=') {
			flag = 42;
			token[0] = '=';
			token[1] = '=';
			token[2] = '\0';
		}
		else {
			p--;
			token[0] = filted[p];
			token[1] = '\0';
			flag = 41;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '>') {
		p++;
		if (filted[p] == '=') {
			flag = 40;
			token[0] = '>';
			token[1] = '=';
			token[2] = '\0';
		}
		else if (filted[p] == '>')
			flag = 59;
		else {
			p--;
			token[0] = filted[p];
			token[1] = '\0';
			flag = 39;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '<') {
		p++;
		if (filted[p] == '=') {
			flag = 38;
			token[0] = '<';
			token[1] = '=';
			token[2] = '\0';
		}
		else if (filted[p] == '<')
			flag = 58;
		else {
			p--;
			token[0] = filted[p];
			token[1] = '\0';
			flag = 37;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '!') {
		p++;
		if (filted[p] == '=')
			flag = 43;
		else {
			p--;
			flag = 68;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '&') {
		p++;
		if (filted[p] == '&')
			flag = 53;
		else {
			p--;
			flag = 52;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '|') {
		p++;
		if (filted[p] == '|')
			flag = 55;
		else {
			p--;
			flag = 54;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '+') {
		p++;
		if (filted[p] == '=')
			flag = 69;
		else {
			p--;
			token[0] = filted[p];
			token[1] = '\0';
			flag = 33;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '-') {
		p++;
		if (filted[p] == '=')
			flag = 70;
		else {
			p--;
			token[0] = filted[p];
			token[1] = '\0';
			flag = 34;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '*') {
		p++;

			p--;
			token[0] = filted[p];
			token[1] = '\0';
			flag = 35;
		}
		p++;
		return flag;
	}
	else if (filted[p] == '/') {
		p++;
		return flag;

}

void getNext(){
	//检查待分析源文件需要下一个符号，调用该函数获取下一个单词
	flag = scan(flag, filted, token, p);
	if (flag == 0 && p == strlen(filted) + 1)		//读取字符为文档的结束标志时退出
	{
		symbol = "END";
		return;
	}
	//判断输入字符的类型
	else if (flag == 99)								//标识符：99；
		symbol = "ID";
	else if (flag == 999)								//整数： 999；
		symbol = "INTC";
	else if (flag == 9999)								//小数：9999；
		symbol = "DECI";
	else if (flag == 33)								//运算符及分隔符
		symbol = "+";
	else if (flag == 34)
		symbol = "-";
	else if (flag == 35)
		symbol = "*";
	else if (flag == 36)
		symbol = "/";
}


void Exp() {
	//该函数根据Exp→ Term {'+'|'-' Term} 进行分析
	Term();
	while (1) {
		getNext();
		if (symbol == "+" || symbol == "-") {
			Term();
			if (symbol == "ERROR")
				return;
		}
		else if (symbol == ")")
			return;
		else {
			p -= strlen(token);
			break;
		}
	}
}

void Term() {
	//该函数根据Term→ Factor {'*'|'/' Factor} 进行分析
	Factor();
	if (symbol == "ERROR")
		return;
	while (1) {
		getNext();
		if (symbol == "*" || symbol == "/") {
			Factor();
			if (symbol == "ERROR")
				return;
		}
		else {
			p -= strlen(token);
			break;
		}
	}
}

void Factor() {
	//该函数根据Factor→ ID | INTC | DECI | '(' Exp ')' 进行分析
	getNext();
	if (symbol == "ID" || symbol == "INTC" || symbol == "DECI")
		return;
	else if (symbol == "(") {
		Exp();
		if (symbol == "ERROR")
			return;
		if (symbol == ")")
			return;
	}
	else {
		ERRORpos = 17;				//ERROR17:四则表达式中缺少变量
		symbol = "ERROR";
	}
}