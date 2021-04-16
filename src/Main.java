import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main( String[] args ) {
        Stack<Double> stackNumb = new Stack<>();
        Stack<String> stackSigne = new Stack<>();

        HashMap<String, Integer> signH = new HashMap<>();

        signH.put("(", 3);
        signH.put(")", 4);
        signH.put("*", 2);
        signH.put("/", 2);
        signH.put("+", 1);
        signH.put("-", 1);

        Integer priority;

        while (true){
            Scanner in = new Scanner(System.in);
            System.out.print("Input a number: ");
            String value = in.nextLine();

            priority = signH.get(value);

            if (priority != null){
                boolean flag = true;
                while (flag){
                    if (stackSigne.size() == 0){
                        stackSigne.push(value);
                        flag = false;
                    }
                    else if (priority > signH.get(stackSigne.peek()) & priority!= 4){

                        stackSigne.push(value);
                        flag = false;
                    }
                    else if(priority != 4 & signH.get(stackSigne.peek()) == 3){
                        stackSigne.push(value);
                        flag = false;
                    }
                    else if(priority == 4 & signH.get(stackSigne.peek()) !=3){
                        raschet(priority, signH.get(stackSigne.peek()), stackNumb, stackSigne);
                    }
                    else if(priority == 4 & signH.get(stackSigne.peek()) == 3){
                        String delete = stackSigne.pop();
                        flag = false;
                    }
                    else{
                        raschet(priority, signH.get(stackSigne.peek()), stackNumb, stackSigne);
                    }
                }

            }
            else{
                stackNumb.push(Double.parseDouble(value));
            }

            System.out.println("Стек чисел");
            Iterator iterator = stackNumb.iterator();
            while(iterator.hasNext()){
                Object value3 = iterator.next();
                System.out.print(value3 + " ");
            }
            System.out.println("");
            System.out.println("Стек знаков");
            Iterator iterator2 = stackSigne.iterator();
            while(iterator2.hasNext()){
                Object value2 = iterator2.next();
                System.out.print(value2 + " ");
            }

            System.out.println("");

        }
    }

    public static void raschet(Integer priority, Integer priorityLastSign, Stack<Double> stackNumb, Stack<String> stackSigne){

        double secondValue = stackNumb.pop();
        System.out.println("sv " + secondValue);
        double firstValue = stackNumb.pop();
        System.out.println("fv " + firstValue);
        String znak = stackSigne.pop();
        if(znak.equals("*")){
            double thirdValue = firstValue * secondValue;
            stackNumb.push(thirdValue);
        }
        if(znak.equals("/")){
            double thirdValue = firstValue / secondValue;
            stackNumb.push(thirdValue);
        }
        if(znak.equals("+")){
            double thirdValue = firstValue + secondValue;
            stackNumb.push(thirdValue);
        }
        if(znak.equals("-")){
            double thirdValue = firstValue - secondValue;
            stackNumb.push(thirdValue);
        }
    }

}
