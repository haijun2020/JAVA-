import javax.swing.text.AbstractDocument;

/**
 * Calculator
 * <h5>描述</h5>
 **/
public class Calculator {
    public static void main(String[] args) {
        String expression = "1*2+4-3/1";
        //创建两个栈
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';

        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么
            if(operStack.isOper(ch)){ //如果是一个运算符
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //栈中不为空时
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){ //当优先级小于等于栈顶符号时
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //将结果直接放进数字栈
                        numStack.push(res);
                        //将当前符号放入符号栈
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }

                }else{
                    //如果栈为空
                    operStack.push(ch);
                }
            }else{ //当是数字时
                numStack.push(ch - 48); //注意这是‘1’ ，所以使用ASCIll码进行计算。减去48就对了
            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        while(true){
             //如果符号栈为空，说明已经完成运算
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s = %d",expression,numStack.pop());

    }


}
//先创建一个栈
class ArrayStack{
    private int maxSize;  //栈的大小
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

     //栈满
    public boolean  isFull(){
        return top == maxSize - 1 ;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw  new RuntimeException("栈空，没有数据....");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据....");
            return;
        }
        for(int i = top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //判断是一个运算符，还是数字
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //判断运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，则优先级就越大
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }

    //返回栈顶的元素
    public int peek(){
        return stack[top];
    }


}

