import java.util.Stack;
/**
 * Java implementation to convert infix expression to postfix expression
 * Note that here we use Stack class for Stack operations
 */

public class Transform {
    /**
     * A utility function to return precedence of a given operator
     * Higher returned value means higher precedence
     */
    static int Prec(char ch){
        switch (ch){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    /**
     * The main method that converts given infix expression to postfix expression
     */
    static String transform(String expression){
        // initialize empty String for result;
        String result = "";

        // initialize empty stack
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);

            // If the scanned character is an operand, add it to output
            if(Character.isLetterOrDigit(c))
                result += c;
            // If the scanned character is an '(', push it to the stack
            else if(c == '(')
                stack.push(c);
            // If the scanned character is an ')', pop and output from the stack
            // until an '(' is encountered
            else if(c == ')'){
                while(!stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();
                if(!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression";
                else
                    stack.pop();
            }else{
                // an operator is encountered
                while(!stack.isEmpty() && Prec(c) <= Prec(stack.peek()))
                    result += stack.pop();
                stack.push(c);
            }
        }

        // pop all the operators from the stack
        while(!stack.isEmpty())
            result += stack.pop();

        return result;
    }

    public static void main(String[] args){
        System.out.println(transform("1+2+5*6"));
        System.out.println(transform("a+b*(c^d-e)^(f+g*h)-i"));
    }
}