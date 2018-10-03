/**
 * William Milligan 
 * n00617078
 * COP 3551 - Data Structures
 * Homework - 6
 * InfixToPostfixConverter
 */
package infixtopostfixconverter;

/**
 * This class is designed to host two methods that take in strings and provide either the postfix expression from the provided string
 * or they evaluate a posfix expression to determine the resultant double value of the string.
 * 
 */
public class InFixToPostfixConverter {
   /**
    * main method executes the flow of control for the program. Can be used to call static methods infix2Postfix and postfixEvaluator
    * or create static classes stack1 stack2 
    * @param args takes in nothing from the command line arguments
    */
    public static void main(String[] args) throws Exception {
        String text = "20 + 10 /  15  +  5 - 5 * 3";
        System.out.println(infix2Postfix(text));
        System.out.println(postfixEvaluator(infix2Postfix(text)));
    }
    
    
    public static String infix2Postfix(String infixExpression) throws Exception{
        Stack stack = new Stack();
        String newExpression = "";
        char cur;
        for(int x = 0 ; x < infixExpression.length();x++)
        {
            cur=infixExpression.charAt(x);
             
            //For if a number 0-9 is scanned
            if(Character.isDigit(cur)==true)
            {
                newExpression += (cur); 
                while((x < infixExpression.length() - 1) && (Character.isDigit(infixExpression.charAt(x + 1))== true))
                {
                    x++;
                    newExpression += infixExpression.charAt(x);
                }
                newExpression += " ";
            }//For if a space is encountered
            else if(cur == ' ')
            {
            
            }//For when an operator is encountered
            else
            {  
                if(stack.isEmpty())
                {//If an operator is encountered and nothing is on the stack
                    stack.push(cur);
                }
                else if(((stack.top() == '+' || stack.top() == '-') && (cur == '+'||cur == '-'))||
                        ((stack.top() == '*' || stack.top() == '/') && (cur == '*'||cur == '/')))
                {//If the scanned operator (cur) has the precisdence as the top of the stack.
                    newExpression += (stack.pop() + " ");
                    stack.push(cur);
                }
                else if((stack.top() == '+' || stack.top() == '-') && (cur == '*'||cur == '/'))
                {//If the scanned operator (cur) has greater pecidence than the top of the stack
                    stack.push(cur);
                }
                else
                {//If the scanned operator has lower precidence than the top of the stack.
                    while(!stack.isEmpty())
                    {
                        newExpression += (stack.pop() + " ");
                    }
                    stack.push(cur);
                }//end default else
                
            }//end nested else if. These are the ones nested within the orginal else if's    
            if(x == infixExpression.length()-1 && stack.isEmpty() ==false )
            {
                while(stack.isEmpty()== false)
                {
                    
                    newExpression += stack.pop();
                    newExpression += " ";
                }
            }
        }//end for loop
      return newExpression;
    }//end InfixToPostfix

    public static double postfixEvaluator(String postFixExpression) throws Exception
    {  
        double right;
        double left;
        Stack2 stack = new Stack2();
        char cur;
        
        //For loop to iterate through each character of the passed in string
        for(int x  = 0 ; x < postFixExpression.length();x++)
        {
            
            cur = postFixExpression.charAt(x);
            if(cur== ' ')
            {
            }
            else if(Character.isDigit(cur))
            {
                stack.push((double)Character.getNumericValue(cur));
                
                 while((x < postFixExpression.length() - 1) && (Character.isDigit(postFixExpression.charAt(x + 1))== true))
                {
                    x++;
                    double temp = stack.pop() * 10 + (double)Character.getNumericValue(postFixExpression.charAt(x));
                    stack.push(temp);
                }
            }
            else
            {
                right = stack.pop();
                left = stack.pop();
                switch(cur)
                {
                    case '+':
                    {
                        stack.push(left + right);
                        break;
                    }
                    case '-':
                    {
                        stack.push(left - right);
                        break;
                    }
                    case '*':
                    {
                        stack.push(left * right);
                        break;
                    }
                    case'/':
                    {
                        stack.push(left/right);
                        break;
                    }         
                }
            }
        }
        return stack.pop();
    }

    
    public static class Stack{
        
        int CAPACITY = 3;
        char[] stack = new char[CAPACITY];
        int top = -1;
        
        
        public int size(){
            return top+1;
        }
        
        public char top(){
            return stack[top];
        }
        
        public char pop() throws Exception{
            if(isEmpty())
            {
                throw new Exception();
            }
            char x = stack[top];
            top--;
            return x;
        }
        
        public void push(char x){
            top++;
            stack[top]=x;
        }
         public boolean isEmpty(){
            return top==-1;
            
        }
        
    }
    
     public static class Stack2{
        
        int CAPACITY = 3;
        double[] stack = new double[CAPACITY];
        int top = -1;
        
        
        public int size(){
            return top+1;
        }
        
        public double top(){
            return stack[top];
        }
        
        public double pop() throws Exception{
             if(isEmpty())
            {
                throw new Exception();
            }
            double x = stack[top];
            top--;
            return x;
        }
        
        public void push(double x){
            top++;
            stack[top]=x;
        }
        
        public boolean isEmpty(){
            return top==-1;
        }
        
    }
}

