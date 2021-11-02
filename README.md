# Expression-Tree

This project entails a class called ExpressionTree that implements the ExpressionTreeInterface interface. The ExpressionTree constructor takes a string that contains a postfix expression, where the operands are integers and the operands are +, -, *, or /. For example: 20 4 - 3 * is equivalent to (20-4)*3. The ExpressionTree class uses ExpressionNodes as the nodes for the tree, which contain the individual operands and operators.

The constructor has methods as specified below:

public int eval() - returns the integer result of evaluating the expression tree when called on an ExpressionTree object.

public String postfix() - returns a String that contains the corresponding postfix expression when called on an ExpressionTree object.

public String prefix() - returns a String that contains the corresponding prefix expression when called on an ExpressionTree object. 

public String infix() - returns a String that contains the corresponding infix expression when called on an ExpressionTree object. 

public ExpressionTree(String expression) - constructor of the ExpressionTree object; it takes a string that stores a postfix expression.
