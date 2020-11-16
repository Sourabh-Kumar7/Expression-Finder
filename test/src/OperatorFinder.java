import java.util.*;
import java.util.stream.*; 

public class OperatorFinder {
    Vector<Integer> num;
    Vector<oper> seq;

    enum oper
    {    
        add(0),
        sub(1),
        mult(2),
        divi(3);
        public final int num;
        oper(int num)
        {
            this.num=num;   
        }
    }

    public OperatorFinder()
    {
        initComponents();
    }

    private void initComponents(){
        this.num = null;
        this.seq = new Vector<oper>();
    }
    
    
    
    private int precedence(oper op)
    {
        switch(op)
        {
            case add: return 0;
            case sub: return 1;
            case mult: return 2;
            case divi: return 3;
        }
        
        return 0;
    }
    
    private void eval(Stack<Integer> vals, Stack<oper> ops)
    {
            int val2 = vals.peek();
            vals.pop();

            int val1 = vals.peek();
            vals.pop();

            oper op = ops.peek();
            ops.pop();

            int res = 0;

            switch(op)
            {
                case add: res = val1 + val2;
                    break;
                case sub: res = val1 - val2;
                    break;                
                case mult: res = val1 * val2;
                    break;
                case divi: res = val1 / val2;
                    break;
            }

            vals.add(res);
    }
    
    private String makeExpression ()
    {
        String arithmeticExpression = "";
        for(int i=0;i<seq.size();++i)
        {
           arithmeticExpression+=Integer.toString(num.elementAt(i)); 
           switch(seq.elementAt(i))
           {
                case add: arithmeticExpression+="+";
                     break;
                case sub: arithmeticExpression+="-";
                     break;
                case mult: arithmeticExpression+="*";
                     break;
                case divi: arithmeticExpression+="/";
                     break;
           }
        }
        arithmeticExpression+=Integer.toString(num.elementAt(num.size()-1));
        return arithmeticExpression;
    }
    
    public String getExpression (String inputNumbers, int desire)
    {
        initComponents();
        num = new Vector<Integer>(Stream.of(inputNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList()));

        boolean result = solve(desire);
        
        if(result)
        {
            return makeExpression();
        }
        else
        {
            num.set(0, -num.elementAt(0));
            result = solve(desire);
            
            if(result)
            {
                return makeExpression();
            }
        }
        return "NOT FOUND!!";
    }
    
    private int calc()
    {
        int sol = 0;
        Stack<Integer> vals;
        vals = new Stack<>();
        Stack<oper> ops;
        ops = new Stack<>();
        
        boolean numTime = true;
        
        for(int i=0,j=0;(i+j)<(num.size()+seq.size());)
        {
            if(numTime)
            {
                vals.push(num.elementAt(i));
                ++i;
            }
            else
            {
                while(!ops.empty() && precedence(ops.peek())>= precedence(seq.get(j)))
                {
                    eval(vals, ops);
                    
                }
                
                ops.add(seq.get(j));
                ++j;
            }
            
            numTime = !numTime;
        }
        
        while(!ops.empty())
        {
            eval(vals, ops);

        }
        
        return vals.peek();
    }
    
    private boolean solve(int desire)
    {
        if(seq.size() >= num.size()-1)
        {
            int cur = calc();
            return cur == desire;
        }
        
        for(int op=0; op<=3; ++op)
        {
            if(op==0)seq.add(oper.add);
            if(op==1)seq.add(oper.sub);
            if(op==2)seq.add(oper.mult);
            if(op==3)seq.add(oper.divi);

                        
            boolean res = solve(desire);
            if(res)
            {
                return true;
            }
            seq.remove(seq.size()-1);
        }
        return false;
    }
}