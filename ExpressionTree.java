import java.util.Stack;

public class ExpressionTree {
	ExpressionNode root;
    
	public ExpressionTree(String expression){
        Stack<ExpressionNode> tree = new Stack<ExpressionNode>();
		String[] expr = expression.split(" ");
		ExpressionNode t, leftNode, rightNode;

		for (int i = 0; i < expr.length; i++) { // iterates through the String array of operands & operators

			if (isNum(expr[i])) { // creates new node for operand and pushes to tree stack
				t = new ExpressionNode(expr[i]);
				tree.push(t);
			} 
			else if(isOp(expr[i]))
			{
                if(tree.size() < 2){ // construction of tree stopped if there are not enough operands for an operator
                    System.out.println("There are too few operands.");
                    root = null;
                    break;
                }
                 
                // creates new node for operator and pops top two operands, sets them as the two children of the operator
				t = new ExpressionNode(expr[i]);
				rightNode = tree.pop();	 
				leftNode = tree.pop();
				t.right = rightNode;
				t.left = leftNode;
				tree.push(t); // pushes operator node to stack
			}
            else{ // construction of tree stopped if symbols other than numbers and operators appear in the postfix expr
                System.out.println("The postfix expression contains unacceptable values.");
                root = null;
                break;
            }
		}
        // construction of tree stopped if there are values other than the root node on the stack at the end
        if(tree.size() > 1){
            System.out.println("There are too many operators.");
            root = null;
        }
        else 
            root = tree.pop();
	}
    
    // returns true if passed str is an operator
	private boolean isOp(String str) {
		if (str.equals("+") || str.equals("-") || str.equals("*")|| str.equals("/")) {
			return true;
		}
		return false;
	}
    
    // returns true if passed str is a numerical operand
    private boolean isNum(String str){
        if(str.matches("\\d+")){
            return true;
        }
        return false;
    }

	// calls private method with root
    public int eval(){
        return eval(root);
    }
    // recurses through the tree to evaluate the expression
    private int eval(ExpressionNode t){
        if(t == null){
            return 0;
        }
        if(t.left == null && t.right == null){ // leaf node, which is an operand
            return Integer.parseInt(t.data);
        }
        
        // gets the left and right subtrees' evaluated number
        int resultLeft = eval(t.left);
        int resultRight = eval(t.right);
        
        // t is an operator, so the correct one is used to evaluate the left and right numbers
        if(t.data.equals("+"))
            return (resultLeft + resultRight);
        else if(t.data.equals("-"))
            return (resultLeft - resultRight);
        else if(t.data.equals("*"))
            return (resultLeft * resultRight);
        else
            return (resultLeft / resultRight);             
    }
    
    // calls private method with root
    public String postfix(){
        return postfix(root).trim();
    }
    // recurses through tree to obtain expression values in postfix order (left node, right node, node)
    private String postfix(ExpressionNode t){
        if(t == null)
            return "";
        else
            return postfix(t.left) + postfix(t.right) + t.data + " ";
    }
    // calls private method with root
    public String prefix(){
		return prefix(root).trim();
    }
    // recurses through tree to obtain expression values in prefix order (node, left node, right node)
    private String prefix(ExpressionNode t){
        if(t == null)
            return "";
        else
            return t.data + " " + prefix(t.left) + prefix(t.right);       
    }
    
    // calls private method with root
	public String infix() {
		return inorder(root).trim();
	}
    // recurses through tree to obtain expression values in infix order (left node, node, right node) with parentheses
	private String infix(ExpressionNode t){
        if(t == null)
            return "";
        else
            if(isOp(t.data)){ // inserts parentheses around operands of current node's operator
                return "(" + inorder(t.left) + " " + t.data + " " + inorder(t.right)+ ")";
            }
            else
                return inorder(t.left) + t.data + inorder(t.right);
	}

	static class ExpressionNode {

		String data;
		ExpressionNode left;
        ExpressionNode right;
	
		ExpressionNode(String val) {
			data = val;
			left = null;
            right = null;
		}
	}
}

