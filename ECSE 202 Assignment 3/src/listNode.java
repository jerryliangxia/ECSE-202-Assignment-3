
//The following class contains the template for the node
//the node is used to implement queues and stacks as linked lists
//is a crucial basis for the In2pJ program
//@author frank ferrie

public class listNode {
	String value;			//Contains value stored in node
	listNode next;			//listNode next points to next element in linked list
	
	//Constructor assigns String variable token to Node
	public listNode(String token)
	{
		value = token;

	}
}
