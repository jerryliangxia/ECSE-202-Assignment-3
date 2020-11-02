import java.util.StringTokenizer;
import acm.program.*;

/* Name: Jerry Xia
 * ID: 260917329
 * 
 * This program converts from infix to post fix.
 * 
 * Basic summary:
 * get prompt from user
 * cycle through each part of the string using StringTokenizer (must be imported)
 * if an operator is seen, push the operator onto the operator stack
 * if an integer/anything other than an operator is seen, enqueue the integer onto the queue
 * if an operator is of greater precedence than the one on top of the operator stack, push the
 * operator onto the operator stack. otherwise, print out all operators, and enqueue the remaining
 * integers onto the queue until the next operator.
 * When this is done, the operator stack will contain the operator of lesser degree, and the process
 * continues.
 * 
 * The use of queues and stacks are important as they follow, respectively, FIFO and LILO systems.
 * as the program proceeds, everything (with exception of brackets) gets pushed onto the queue
 * 
 */

public class In2pJ extends ConsoleProgram {
	
	//sets up SENTINEL for termination of program
	public final String SENTINEL = "";
	
	
	public void run() {			//main method
		
		println("Infix to Postfix Converter");	//prints title

		//while loop is created to account for Sentinel. 
		while(true) {
			println();	//adds space
			
			//initializes operator stack, queue and gets input from user.
			//@param opStack - stack used for storing operators
			//@param output - queue used for storing and printing out final result
			//@param str - contains prompt which is read from user
			//
			
			Stack opStack = new Stack();

			Queue output = new Queue();

			String str = readLine("Enter string (blank line to exit): ");
			 
			if (str.equals(SENTINEL)) break;	//use of sentinel to exit program
			 
			//@param st - StringTokenizer which cycles through each token of the string
			StringTokenizer st = new StringTokenizer(str, "+-/*()", true);

			/*
			 * main while loop
			 * 
			 * continues while tokens are still available (taken from string str)
			 * @param token - this method allows one to cycle through the loop
			 * with the next respective token
			 * 
			 * this loop contains if statements that cycle through each token
			 */

			while (st.hasMoreTokens()) {	//while there are tokens to be read
				 String token = st.nextToken();	//explained above
				 
				 //if the token is an operator (this method is allocated below, second from last, which takes in a token)
				 if (isOperator(token)) {
						
					if(opStack.top != null) {	//rules applied if top of stack contains an operator already
							
						//if precedence of token is higher than precedence of top value on operator stack.
						//push the token onto the stack
						if (precedence(token) > precedence(opStack.top.value)) {
							opStack.push(token);
						}
						
						//else if precedence of token is less than or equal to precedence of value on top of stack,
						//the following applies
						else if (precedence(token) <= precedence(opStack.top.value)) {
							//while loop is created to go through
							while(true) {
								if(opStack.top != null) {	//if top of stack has an operator
									
									//if precedence of the token is higher than that of the value on top of opStack,
									//push all operators from opStack onto the queue
									
									if(precedence(token) > precedence(opStack.top.value)) break;
									
								} else break;				//otherwise push the token onto opStack
								output.Enqueue(opStack.pop());
							}
							opStack.push(token);					
						}
											
					}
					
					//if top of opStack is null, push token onto operator stack
					else if (opStack.top == null) {
						opStack.push(token);	//pushes token onto opStack
						
					}
				
				//if token is left parentheses
				} else if (token.equals("(")){	//push onto stack. Will be used later with right parentheses for output
					opStack.push(token);
					
				//if token is right parentheses
				} else if(token.equals(")"))
					//establishes while loop to push ALL operands onto the queue
					while(true) {
						//queues all operators onto output
						//applies only to operators, not left bracket
						while (opStack.top.value != null && !opStack.top.value.equals("(")) {
							output.Enqueue(opStack.pop());
						}
						//if left bracket is present on opStack, discard the bracket
						if (opStack.top.value.equals("(")) {
							opStack.pop();
							break;	//break from while loop
						}
					}
					
				else {	//ELSE if not an operand, "(" or ")" then enqueue the value onto the output queue
						//(in this assignment the output contains all numbers and operands and no variables such as a or b)
					output.Enqueue(token);
				}	
			}	//end of while loop
				
			while(opStack.top != null) {		//while top of opStack contains operators
				output.Enqueue(opStack.pop());	//push all final operators onto the output queue
			}
			String final_result = "";						//initializes string that is printed
			
			while(output.front != null) {			//dequeues from output
				final_result += " " + output.Dequeue();	//queue is FIFO, so string is set to += "" +output.Dequeue()
				
				//as well, the Dequeue(), Enqueue() as well as push() and pop() methods can be found in the queue and stack,
				//classes, respectively.
			}
			
			print("Postfix: " +final_result);				//prints final result
			println();								//adds space
		}
		
		print("Program terminated.");		//end of program if sentinel is entered.
	}
		
	boolean isOperator(String token)	//isOperator method used for specifying operators entered from user
										//returns boolean
	{
		
		if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("^") 
				|| token.equals("*"))	//or statements used for this method
			// if string is an operator, return true
			return true;
		else
		{
			// otherwise, return false
			return false;
		}
	}

	int precedence(String token)	//ranks precedence and takes in token from user
	{
		{
			if (token.equals("*") || token.equals("/"))			//higher precedence includes * and /
				return 2;
			if (token.equals("+") || token.equals("-"))			//second highest includes + and -
				return 1;
			if (token.equals("("))								//final state of precedence -
																//includes just left parentheses, used in popping off of
																//stack when needed
				return -1;
			else
				return 0;
		}
		
	}
}

