package com.example.basiccalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.function.IntToDoubleFunction;


public class MainActivity extends Activity implements View.OnClickListener {
    //数字0-9
    private Button n_0;
    private Button n_1;
    private Button n_2;
    private Button n_3;
    private Button n_4;
    private Button n_5;
    private Button n_6;
    private Button n_7;
    private Button n_8;
    private Button n_9;

    //运算符
    private Button y_j;//+
    private Button y_jj;//-
    private Button y_c;//*
    private Button y_cc;//除
    private Button y_d;//=
    private Button y_x;//小数点
    private Button power;
    private Button rec;
    private Button morp;
    private Button lbra;
    private Button rbra;
    private Button mod;
    private Button backSpace;
    //清除
    private Button det;

    boolean clean;//清空标识
    boolean calculated=false;

    //结果显示集
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //数字0——9实例化
        n_0 = findViewById(R.id.button26);
        n_1 = findViewById(R.id.button21);
        n_2 = findViewById(R.id.button22);
        n_3 = findViewById(R.id.button23);
        n_4 = findViewById(R.id.button16);
        n_5 = findViewById(R.id.button17);
        n_6 = findViewById(R.id.button18);
        n_7 = findViewById(R.id.button13);
        n_8 = findViewById(R.id.button12);
        n_9 = findViewById(R.id.button11);

        //运算符实例化
        y_j = findViewById(R.id.button28);//加
        y_jj = findViewById(R.id.button24);//减
        y_c = findViewById(R.id.button19);//乘
        y_cc = findViewById(R.id.button14);//除
        y_d = findViewById(R.id.button25);//等
        y_x = findViewById(R.id.button27);//小数点
        det = findViewById(R.id.button8);//清除
        power = findViewById(R.id.button10);//平方
        rec = findViewById(R.id.button20);//倒数
        morp=findViewById(R.id.button9);
        lbra=findViewById(R.id.button50);
        rbra=findViewById(R.id.button51);
        mod=findViewById(R.id.button53);
        backSpace=findViewById(R.id.button52);
        //结果显示集
        editText = findViewById(R.id.ediText);

        //添加时间点击时间
        n_0.setOnClickListener(this);
        n_1.setOnClickListener(this);
        n_2.setOnClickListener(this);
        n_3.setOnClickListener(this);
        n_4.setOnClickListener(this);
        n_5.setOnClickListener(this);
        n_6.setOnClickListener(this);
        n_7.setOnClickListener(this);
        n_8.setOnClickListener(this);
        n_9.setOnClickListener(this);

        y_j.setOnClickListener(this);
        y_jj.setOnClickListener(this);
        y_c.setOnClickListener(this);
        y_cc.setOnClickListener(this);
        y_x.setOnClickListener(this);
        y_d.setOnClickListener(this);
        det.setOnClickListener(this);
        power.setOnClickListener(this);
        rec.setOnClickListener(this);
        morp.setOnClickListener(this);
        lbra.setOnClickListener(this);
        rbra.setOnClickListener(this);
        mod.setOnClickListener(this);
        backSpace.setOnClickListener(this);
    }

    //读取每个按钮内容
    public void onClick(View view) {
        //获取文本内容
        if(clean==true)
            editText.setText("");
        String input = editText.getText().toString();
        switch (view.getId()) {
            case R.id.button26:
            case R.id.button21:
            case R.id.button22:
            case R.id.button23:
            case R.id.button16:
            case R.id.button17:
            case R.id.button18:
            case R.id.button13:
            case R.id.button12:
            case R.id.button11:
                if(editText.getText().toString().endsWith(")")||editText.getText().toString().endsWith("²"))
                    break;
                if(calculated&&editText.getText().toString().endsWith("0")||
                        calculated&&editText.getText().toString().endsWith("1")||
                        calculated&&editText.getText().toString().endsWith("2")||
                        calculated&&editText.getText().toString().endsWith("3")||
                        calculated&&editText.getText().toString().endsWith("4")||
                        calculated&&editText.getText().toString().endsWith("5")||
                        calculated&&editText.getText().toString().endsWith("6")||
                        calculated&&editText.getText().toString().endsWith("7")||
                        calculated&&editText.getText().toString().endsWith("8")||
                        calculated&&editText.getText().toString().endsWith("9")
                ){
                    editText.setText(((Button) view).getText() + "");
                    calculated=false;
                }
                else
                    editText.setText(input + ((Button) view).getText() + "");//结果集就是本身
                break;

            case R.id.button27:
                if(editText.getText().toString().endsWith(")")||editText.getText().toString().endsWith("²")||editText.getText().toString().endsWith(".")
                        ||editText.getText().toString().endsWith("+")||editText.getText().toString().endsWith("-")
                        ||editText.getText().toString().endsWith("*")||editText.getText().toString().endsWith("/"))
                    break;
                if(editText.getText().toString().equals(""))
                    editText.setText(  "0" + ((Button) view).getText() + "");
                else
                    editText.setText(input + ((Button) view).getText() + "");//结果集就是本身
                break;

            case R.id.button28:
            case R.id.button24:
            case R.id.button19:
            case R.id.button14://除
            case R.id.button53:
                    calculated=false;
                if(editText.getText().toString().endsWith("+")||editText.getText().toString().endsWith("-")||editText.getText().toString().endsWith("*")||
                    editText.getText().toString().endsWith("/")||editText.getText().toString().endsWith("%")||editText.getText().toString().endsWith("."))
                     break;
                if(editText.getText().toString().equals(""))
                    editText.setText(  "0" + ((Button) view).getText() + "");
                else
                    editText.setText(input + "" + ((Button) view).getText() + "");
                break;

            case R.id.button50:
                if(editText.getText().toString().endsWith("1")||editText.getText().toString().endsWith("2")||editText.getText().toString().endsWith("3")||
                        editText.getText().toString().endsWith("4")||
                        editText.getText().toString().endsWith("5")||
                        editText.getText().toString().endsWith("6")||
                        editText.getText().toString().endsWith("7")||
                        editText.getText().toString().endsWith("8")||
                        editText.getText().toString().endsWith("9")||
                        editText.getText().toString().endsWith("0")||
                        editText.getText().toString().endsWith(")")||
                        editText.getText().toString().endsWith("²")||
                        editText.getText().toString().endsWith("."))
                    break;
                editText.setText(input + "" + ((Button) view).getText() + "");
                break;

            case R.id.button51:
                if(editText.getText().toString().endsWith("(")||
                        editText.getText().toString().endsWith("²")||
                        editText.getText().toString().endsWith("."))
                    break;
                if(editText.getText().toString().contains("(")){
                    String temp=editText.getText().toString();
                    int num1=0,num2=0;
                    for (int i =0;i<temp.length();i++){
                        if(temp.charAt(i)=='(')
                            num1++;
                        else if(temp.charAt(i)==')')
                            num2++;
                        else continue;
                    }
                    if(num2<num1)
                        editText.setText(input + "" + ((Button) view).getText() + "");
                    else
                        break;
                }
                break;

            case R.id.button10:
                if(editText.getText().toString().endsWith("+")||editText.getText().toString().endsWith("-")||editText.getText().toString().endsWith("*")||
                        editText.getText().toString().endsWith("/")||editText.getText().toString().equals("")||editText.getText().toString().endsWith("."))
                    break;
                editText.setText(input + "²" + "");
                break;

            case R.id.button20:
                if(editText.getText().toString().endsWith("+")||editText.getText().toString().endsWith("-")||editText.getText().toString().endsWith("*")||
                        editText.getText().toString().endsWith("/")||editText.getText().toString().equals("")||editText.getText().toString().endsWith("."))
                    break;

                editText.setText("1/("+ input + ")" + "");
                break;

            case R.id.button9:
                input=editText.getText().toString();
                if(input.equals("")){
                    editText.setText("-");
                    break;}
                char temp=editText.getText().toString().charAt(0);
                if(temp>=48&&temp<=57)
                    editText.setText("-"+input);
                else
                    editText.setText(input.substring(1));
                break;
            case R.id.button52:
                input=editText.getText().toString();
                if(input.equals(""))
                    break;
                else
                    editText.setText(input.substring(0,input.length()-1));
                break;
            case R.id.button8://清除
                 if (input != null || !input.equals("")) {
                    //如果获取内容为空
                    editText.setText("");//结果集为空
                     calculated=false;
                    break;
                }
                break;
            case R.id.button25://运算结果=
                calculated=true;
                getResult();//调用处理结果方法
                break;
        }
    }

    //运算结果方法
   public void getResult() {
        String exp =editText.getText().toString();//获取文本框内容
       if(editText.getText().toString().endsWith("+")||editText.getText().toString().endsWith("-")||editText.getText().toString().endsWith("*")||
               editText.getText().toString().endsWith("/"))
           return;
        if(exp==null||exp.equals(""))
            return;
        if(!exp.contains(""))
            return;
        calculate calculate = new calculate();
        calculate.filted=exp.toCharArray();
        double exp1 = calculate.Exp();
        if(exp1 * 10 % 10 == 0) {
             java.text.DecimalFormat myformat=new java.text.DecimalFormat("#");
            String str = myformat.format(exp1);
            editText.setText(str + "");
        }
        else {
            String str = String.valueOf(exp1);
            while(true){
                if(str.endsWith("0")){
                    str=str.substring(0,str.length()-1);
                }
                else
                    break;
            }
            editText.setText(str);
        }
    }
}