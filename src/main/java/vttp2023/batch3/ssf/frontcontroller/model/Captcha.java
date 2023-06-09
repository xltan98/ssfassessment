package vttp2023.batch3.ssf.frontcontroller.model;

import java.util.Arrays;
import java.util.Random;

public class Captcha {

    private int firstNumber;
    private int secondNumber;
    private String operator;
    private int answer;


    public Captcha() {
        this.firstNumber=genFirstNumber();
        this.secondNumber=genSecondNumber();
        this.operator=genOperator();
        this.answer=genAnswer();
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

   

    public int genFirstNumber(){
        Random r = new Random();
        firstNumber = r.nextInt(50)+1;
        return firstNumber;
    }

    public int genSecondNumber(){
        Random r = new Random();
        secondNumber = r.nextInt(50)+1;
        return secondNumber;
    }

    public String genOperator(){
        String[] mathOperators = { "+", "-", "*", "/" };
        Random rand = new Random();
        int randIndex = rand.nextInt(4);
        return mathOperators[randIndex];

    }

    public int genAnswer(){
        int firstNumber= this.getFirstNumber();
        int secondNumber= this.getSecondNumber();

        switch (this.getOperator()) {
            case "+":
                answer = firstNumber + secondNumber;
                break;
            case "-":
                answer = firstNumber - secondNumber;
                break;
            case "*":
                answer = firstNumber * secondNumber;
                break;
            case "/":
                answer = firstNumber / secondNumber;
                break;
            default:
                answer = 0;
        }

        return answer;

    }
   

    @Override
    public String toString() {
        return "Captcha [firstNumber=" + firstNumber + ", secondNumber=" + secondNumber + ", operator=" + operator
                + ", answer=" + answer + "]";
    }


    







    // public Float getAns(){
    //     // secondNumber= random.nextInt(50)+1;
    //     // firstNumber=random.nextInt(50)+1;

    //     Random r = new Random();

    //     int operator= r.nextInt(4);

    //     switch(operator){
    //         case 0:operatorSwitch= "+";
    //             answer= firstNumber+ secondNumber;
    //             return answer;

    //         case 1:operatorSwitch= "-";
    //             answer= firstNumber-secondNumber;
    //             return answer;
               

    //         case 2:operatorSwitch= "*";
    //             answer= firstNumber*secondNumber;
    //             return answer;
                

    //         case 3: operatorSwitch= "/";
    //         answer= firstNumber/secondNumber;
    //         return answer;

    //         default: operatorSwitch= "";

    //     }
    //     return (float) 0;

    // }

    
    



    
}
