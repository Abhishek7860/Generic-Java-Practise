/**
 
 *
 * This class performs all the operations from Operation.java
 **/

public class Driver {
	public static void main(String args[])
	{
		Operation op = new Operation ("441236547896328741023560005544");
		Operation op1 = new Operation (978532560L);
		Operation op3=new Operation();
		
		System.out.println("ADDITION:");
		op3.add(op,op1);
		System.out.println();
		
		System.out.println("SUBTRACTION:");
		op3.subtract(op,op1);
		System.out.println();
		
		System.out.println("PRODUCT: ");
		op3.product(op1,op);
		System.out.println();
		
		System.out.println("POWER: ");
		op3.power(op1,5);
		System.out.println();	
	}
}
