
//queue class for both enqueue and dequeue methods
//@author frank ferrie

public class Queue {
	
	//use of listNode class
	//front and back listNodes are initialized
	listNode front;
	listNode back;

	//the enqueue method adds elements onto the queue, takes in user input 'token'
	public void Enqueue(String token)
	{
		
		//default constructor for newlistNode
		listNode newlistNode = new listNode(token);
		
		//if queue is empty
		if(isEmpty())
		{
			//make the front and back are equal to the new listNode
			front = newlistNode;
			back = newlistNode;
		}
		//else wise
		else
		{
			//set the previous listNode of the back listNode to the new listNode...
			back.next = newlistNode;
			//and set the new back to the new listNode
			back = newlistNode;
		}
	}
	
	//isEmpty() checks if the queue front of queue is null
	public boolean isEmpty()
	{
		//if it is
		if(front == null)
			//return true
			return true;
		else
			//else wise return false
			return false;

	}

	//dequeue acts on the first item of the queue as queue is a FIFO data structure
	public String Dequeue()
	{
		//if there are no elements present in the queue
		if(isEmpty())
		{
			//state that the queue is empty
			System.out.println("ERROR: The queue is empty");
			//returns null
			return null;
		}
		
		//else wise, the output is the data in front
		else
		{
			//if a value exists in the queue, the element at the front is copied to string variable "frontValue"
			String frontValue = front.value;
			//the element at the front is now changed to the element that was before in second place
			front = front.next;
			return frontValue;
		}
	}
	//returns front listNode
	public listNode getFront () {
		return front;
	}
	//returns back listNode
	public listNode getback () {
		return back;
	}
}

