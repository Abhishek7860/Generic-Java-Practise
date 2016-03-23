/**
 * @authors: Sahithya Rangaraju (sxr142930) and Shravani Ramisetty (sxr143631)
 *
 * This class performs all the operations given in Level1 of the project
 **/

import java.util.LinkedList;
import java.util.ListIterator;

public class Operation {
	final int B = 15236; //base
	LinkedList<Long> finalList = new LinkedList<Long>(); //used to store result
	//empty constructor
	public Operation() {

	}

	/**
	 * constructor takes the string as input. Converts the given string to base
	 * B and stores in the list
	 * 
	 * @param s
	 * 			: String - string of numbers
	 **/
	public Operation(String s) {
		long num;
		int count = 0;
		int convertBase = B;
		
		LinkedList<Long> temp10 = new LinkedList<Long>();
		LinkedList<Long> digit = new LinkedList<Long>();
		LinkedList<Long> tem = new LinkedList<Long>();
		LinkedList<Long> multiplyList = new LinkedList<Long>();
		
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			num = Character.getNumericValue(c); //gives a digit from char
			convertToBase(digit, num,convertBase); //converts the number to base B
			
			convertTo10(temp10, count, 10, convertBase); //converts 10 and powers of 10 to base B
			tem = (LinkedList<Long>) temp10.clone();
			//System.out.println("Slist: " + temp10);
			multiply(tem, digit, convertBase); //multiplies digit and 10
			multiplyList = (LinkedList<Long>) digit.clone();
			addList(finalList, multiplyList, convertBase); //adds all the multiplied numbers
			count = count + 1;
			digit = new LinkedList<Long>();
		}
		//System.out.println("String list: " + finalList);
	}
	
	/**
	 * constructor takes Long as input. Converts the given long number to base
	 * B and stores in the list
	 * 
	 * @param num
	 * 				: Long - stores the long number to be converted to base B
	 * */
	public Operation(Long num) {
		int convertBase = B;
		LinkedList longList = new LinkedList();
		convertToBase(longList, num,convertBase);
		//System.out.println(longList);
		finalList = (LinkedList<Long>)longList.clone();
	}

	
	/**
	 * method to convert the class object into its equivalent string (in decimal)
	 * 
	 * @param n
	 * 			: LinkedList - contains result from any operation
	 * */
	public String toString(LinkedList<Long> n) {
		int tempsize = n.size();
		int convertBase = 10;
		int count = 0;
		LinkedList<Long> temp3 = new LinkedList<Long>();
		LinkedList<Long> tem = new LinkedList<Long>();
		LinkedList<Long> digit = new LinkedList<Long>();
		LinkedList<Long> multiplyList = new LinkedList<Long>();
		LinkedList<Long> finalList = new LinkedList<Long>();
		ListIterator<Long> temp10iter = n.listIterator(tempsize);
		
		while (tempsize > 0) {
			long tempDigit = temp10iter.previous().intValue();
			convertTo10(temp3, count, B, convertBase);
			tem = (LinkedList<Long>) temp3.clone();
			digit.add(tempDigit);
			multiply(tem, digit, convertBase);
			multiplyList = (LinkedList<Long>) digit.clone();
			addList(finalList, multiplyList, convertBase);
			digit = new LinkedList<Long>();
			tempsize--;
			count++;
		}
		//System.out.println(finalList);
		int tize = finalList.size();
		ListIterator<Long> titer = finalList.listIterator(0);
		String s1 = "";
		while(tize>0)
		{
			long tDigit = titer.next().intValue();
			
			s1 = s1+String.valueOf(tDigit);
			tize--;
		}
		return s1;
	}

	/**
	 * sum of two numbers stored as objects for the class
	 * 
	 * @param op
	 * 			: Operation - op is an object for class Operation which is first operand
	 * 
	 * @param op1
	 * 			: Operation - op1 is an object for class Operation which is second operand
	 * */
	public void add(Operation op, Operation op1) {
		int convertBase = B;
		LinkedList<Long> List1 = op.finalList;
		LinkedList<Long> List2 = op1.finalList;
		this.finalList = addtionList(List1, List2, convertBase);
		System.out.println("printList for addition: ");
		printList();
		System.out.println("toString for addition: ");
		System.out.println(toString(this.finalList));
	}

	/**
	 * method has two parameters op and op1 such as numbers n1 and n2 repectively, 
	 * returns the corresponding to n1-n2. 
	 * having implemented negative numbers, return the actual answer. 
	 * If not, then if the result is negative, it returns 0
	 * 
	 * @param op
	 * 			: Operation - op is an object for class Operation which is first operand
	 * 
	 * @param op1
	 * 			: Operation - op1 is an object for class Operation which is second operand
	 * */
	public void subtract(Operation op, Operation op1) {
		int convertBase = B;
		LinkedList<Long> List1 = op.finalList;
		LinkedList<Long> List2 = op1.finalList;
		this.finalList = substractionList(List1, List2, convertBase);
		System.out.println("printList for subtract: ");
		printList();
		System.out.println("toString for subtract: ");
		System.out.println(toString(this.finalList));
	}

	/**
	 * product of two numbers
	 * 
	 * @param op
	 * 			: Operation - op is an object for class Operation which is first operand
	 * 
	 * @param op1
	 * 			: Operation - op1 is an object for class Operation which is second operand
	 * */
	public void product(Operation op, Operation op1) {
		int convertBase = B;
		LinkedList<Long> List1 = op.finalList;
		LinkedList<Long> List2 = op1.finalList;
		LinkedList<Long> List1temp;
		LinkedList<Long> List2temp;
		List1temp = (LinkedList<Long>) List1.clone();
		List2temp = (LinkedList<Long>) List2.clone();
		this.finalList = MultiplyList(List1, List2, convertBase);
		this.finalList = (LinkedList<Long>) List2.clone();
		op.finalList = (LinkedList<Long>) List1temp.clone();
		op1.finalList = (LinkedList<Long>) List2temp.clone();
		int tempsize = this.finalList.size();
		ListIterator<Long> temp10iter = this.finalList.listIterator(tempsize);
		long div = 0;
		while (tempsize > 0) {
			long tempDigit = temp10iter.previous().intValue();
			tempDigit = tempDigit + div;
			div = tempDigit / convertBase;
			tempDigit = tempDigit % convertBase;
			this.finalList.set(tempsize - 1, tempDigit);
			tempsize--;
		}
		if (div > 0)
			this.finalList.addFirst(div);
		System.out.println("printList for product: ");
		printList();
		System.out.println("toString for product: ");
		System.out.println(toString(this.finalList));
	}

	
	/**
	 * calculates power of given object
	 * */
	public void power(Operation op1, long num) {
		int convertBase = B;
		LinkedList<Long> oper = op1.finalList;
		LinkedList<Long> oper1 = (LinkedList<Long>) oper.clone();
		this.finalList = tempPower(oper, num);
		op1.finalList.clear();
		op1.finalList = (LinkedList<Long>) oper1.clone();
		int tempsize = this.finalList.size();
		ListIterator<Long> temp10iter = this.finalList.listIterator(tempsize);
		long div = 0;
		while (tempsize > 0) {
			long tempDigit = temp10iter.previous().intValue();
			tempDigit = tempDigit + div;
			div = tempDigit / convertBase;
			tempDigit = tempDigit % convertBase;
			this.finalList.set(tempsize - 1, tempDigit);
			tempsize--;
		}
		if (div > 0)
			this.finalList.addFirst(div);
		System.out.println("printList for power: ");
		printList();
		System.out.println("toString for power: ");
		System.out.println(toString(this.finalList));
	}

	/**
	 * method prints the base + ":" + elements of the list, separated by spaces*/
	public void printList() {
		int len = this.finalList.size();
		ListIterator<Long> temp10iter = this.finalList.listIterator(0);
		System.out.print("Base " + B + " : ");
		int teml = 0;
		while (teml < len) {
			long tempDigit = temp10iter.next().longValue();
			System.out.print(tempDigit + " ");
			teml++;
		}
		System.out.println();
	}

	/**
	 * This method converts the digit to base
	 * B and stores in the list
	 * 
	 * @param digit1
	 * 				: LinkedList - stores the base B converted number
	 * 
	 * @param num
	 * 				: long - number to be converted to base B
	 **/
	public void convertToBase(LinkedList<Long> digit1, long num, int convertBase) {

		long temp = num % convertBase;
		long check = num;
		num = num / convertBase;
		digit1.addFirst(temp);

		while (num > 0) {
			temp = num % convertBase;
			digit1.addFirst(temp);
			num = num / convertBase;

		}
		if (check == 0) {
			digit1.addFirst(0L);
		}

	}

	/**
	 * This method converts 10 and powers of 10 to base
	 * B and stores in the list
	 * 
	 * @param temp10
	 * 				: LinkedList - stores the base B converted number of powers of 10
	 * 
	 * @param cont
	 * 				: int - length of the string-1 
	 **/
	public void convertTo10(LinkedList<Long> temp10, int cont, int base, int convertBase) {
		int val;
		if (cont == 0) {
			val = 0;
		} else
			val = base;
		long temp;
		LinkedList<Long> str_10 = new LinkedList<Long>(); //list to store base B value of 10
		str_10.clear();
		int num = val;
		while (num > 0) {
			temp = num % convertBase;
			str_10.addFirst(temp);
			num = num / convertBase;
		}
		if (val == 0) {
			str_10.addFirst(1L);
			temp10.addFirst(1L);
		}
		//System.out.println("Trial_Str"+str_10);
		multiply(str_10, temp10, convertBase); //multiplies both lists
		//System.out.println("Trial"+temp10);
		
		int tempsize = temp10.size();
		ListIterator<Long> temp10iter = temp10.listIterator(tempsize);
		long div = 0;
		while (tempsize > 0) {
			long tempDigit = temp10iter.previous().intValue();
			tempDigit = tempDigit + div;
			div = tempDigit / convertBase;
			tempDigit = tempDigit % convertBase;
			temp10.set(tempsize - 1, tempDigit);
			tempsize--;
		}
		if (div > 0)
			this.finalList.addFirst(div);
	}

	
	/**
	 * this method performs multiplication of two lists which are converted in base B
	 **/
	public void multiply(LinkedList<Long> operand1, LinkedList<Long> operand2, int convertBase) {
		// Edge case yields no change
		if (operand1 == null || operand2 == null) {
			return;
		}
		int operand1Len = operand1.size();
		int operand2Len = operand2.size();
		int bothOperandsLen = operand1Len + operand2Len;
		
		// Move digits from operand 2 to the end of operand 1
		while (!operand2.isEmpty()) {
			operand1.addLast(operand2.removeFirst());
		}
		
		// Rename linked lists for clarity
		LinkedList<Long> bothOperands = operand1;
		LinkedList<Long> answer = operand2;
		//System.out.println("Trial_Combined"+bothOperands);
		// Initialize answer to 0
		answer.add(0L);
		ListIterator<Long> operand2Iter = bothOperands.listIterator(bothOperandsLen);
		for (int i = 0; i < operand2Len; i++) {
			int operand2Digit = operand2Iter.previous().intValue();
			ListIterator<Long> operand1Iter = bothOperands.listIterator(operand1Len);
			for (int j = 0; j < operand1Len; j++) {
				int operand1Digit = operand1Iter.previous().intValue();
				int product = operand1Digit * operand2Digit;
				int shiftAmount = i + j;
				addMul(answer, product, shiftAmount, convertBase); //adds the lists
			}
		}
	}

	/**
	 * this methods shifts the digits and performs additions
	 * 
	 * @param result
	 * 				: LinkedList - stores values obtained from multiplication
	 * 
	 * @param product
	 * 				: int - product of two digits from lists
	 * 
	 * @param shiftAmount
	 * 				: int - the value by which digits shift
	 * */
	private void addMul(LinkedList<Long> result, int product, int shiftAmount, int convertBase) {
		int resultLen = result.size();
		for (int insertPositionFromRight = shiftAmount; product != 0; insertPositionFromRight++) {
			int digit = product % convertBase;
			product /= convertBase;
			int numNodesNeeded = insertPositionFromRight - resultLen + 1;
			for (int i = 0; i < numNodesNeeded; i++) {
				result.addFirst(0L);
			}
			resultLen = result.size();
			int resultIndex = resultLen - insertPositionFromRight - 1;
			long curVal = result.get(resultIndex).intValue();
			long newVal = curVal + digit;
			if (newVal > convertBase) {
				newVal -= convertBase;
				product++;
			}
			result.set(resultIndex, newVal);
		}
	}

	/**
	 * Method to add two lists obtained from multiplication
	 * 
	 * @param finalList
	 * 					: LinkedList - to store the resultant values
	 * 
	 * @param multiplyList
	 * 					: LinkedList - has digit multiplied by decimal converted to base B
	 * */
	public void addList(LinkedList<Long> finalList, LinkedList<Long> multiplyList, int convertBase) {
		int operand1Len = finalList.size();
		int operand2Len = multiplyList.size();
		long carry = 0, total, remind;
		int check = 0;
		ListIterator<Long> finalIter = finalList.listIterator(operand1Len);
		ListIterator<Long> multiplyIter = multiplyList.listIterator(operand2Len);
		long operand1Digit;
		long operand2Digit;
		
		//checks length of two operands to perform addition
		while (operand1Len > 0 && operand2Len > 0) {
			operand1Digit = finalIter.previous().intValue();
			operand2Digit = multiplyIter.previous().intValue();
			total = operand1Digit + operand2Digit + carry;
			remind = total % convertBase;
			finalList.set(operand1Len - 1, remind);
			carry = total / convertBase;
			operand1Len--;
			operand2Len--;
			check = 0;
		}
		
		//checks for condition where there is no operand2 or length of operand2 is less than operand1
		while (operand1Len > 0) {
			operand1Digit = finalIter.previous().intValue();
			total = operand1Digit + carry;
			remind = total % convertBase;
			finalList.set(operand1Len - 1, remind);
			// trial.add(remind);
			carry = total / convertBase;
			operand1Len--;
			check = 1;
		}

		//checks for condition where there is no operand1 or length of operand1 is less than operand2
		while (operand2Len > 0) {
			operand2Digit = multiplyIter.previous().intValue();
			total = operand2Digit + carry;
			remind = total % convertBase;
			finalList.addFirst(remind);
			// trial.add(remind);
			carry = total / convertBase;
			operand2Len--;
			check = 1;
		}
		if (carry != 0 && check == 0) {
			finalList.addFirst(carry);
		}
		if (carry != 0 && check == 1) {
			finalList.addFirst(carry);
		}
	}

	
	
	/**
	 * method to add two lists
	 * 
	 * @param finalList
	 * 					: LinkedList - to store final result after addition
	 * 
	 * @param multiplyList
	 * 					: LinkedList - second operand
	 * */
	public LinkedList<Long> addtionList(LinkedList<Long> finalList, LinkedList<Long> multiplyList, int convertBase) {
		LinkedList<Long> trial = new LinkedList<Long>();
		int operand1Len = finalList.size();
		int operand2Len = multiplyList.size();
		long carry = 0, total, remind;
		int check = 0;
		ListIterator<Long> finalIter = finalList.listIterator(operand1Len);
		ListIterator<Long> multiplyIter = multiplyList.listIterator(operand2Len);
		long operand1Digit;// = finalIter.previous().intValue();
		long operand2Digit;// = multiplyIter.previous().intValue();
		trial = (LinkedList<Long>) finalList.clone();
		
		//checks length of two operands to perform addition
		while (operand1Len > 0 && operand2Len > 0) {
			operand1Digit = finalIter.previous().intValue();
			operand2Digit = multiplyIter.previous().intValue();

			total = operand1Digit + operand2Digit + carry;
			remind = total % convertBase;
			trial.set(operand1Len - 1, remind);
			// finalList.add(remind);
			carry = total / convertBase;
			operand1Len--;
			operand2Len--;
			check = 0;
		}
		
		//checks for condition where there is no operand2 or length of operand2 is less than operand1
		while (operand1Len > 0) {
			operand1Digit = finalIter.previous().intValue();
			total = operand1Digit + carry;
			remind = total % convertBase;
			trial.set(operand1Len - 1, remind);
			// trial.add(remind);
			carry = total / convertBase;
			operand1Len--;
			check = 1;
		}

		//checks for condition where there is no operand2 or length of operand2 is less than operand1
		while (operand2Len > 0) {
			operand2Digit = multiplyIter.previous().intValue();
			total = operand2Digit + carry;
			remind = total % convertBase;
			trial.addFirst(remind);
			// trial.add(remind);
			carry = total / convertBase;
			operand2Len--;
			check = 1;
		}
		if (carry != 0 && check == 0) {
			trial.addFirst(carry);
		}
		if (carry != 0 && check == 1) {
			trial.addFirst(carry);
		}
		return trial;
	}

	/**
	 * method to subtract two lists
	 * 
	 * @param finalList
	 * 					: LinkedList - to store final result after addition
	 * 
	 * @param multiplyList
	 * 					: LinkedList - second operand
	 * */
	public LinkedList<Long> substractionList(LinkedList<Long> finalList, LinkedList<Long> multiplyList, int convertBase) {
		LinkedList<Long> trial = new LinkedList<Long>();
		int operand1Len = finalList.size();
		int operand2Len = multiplyList.size();
		int check = 0;
		ListIterator<Long> finalIter = finalList.listIterator(operand1Len);
		ListIterator<Long> multiplyIter = multiplyList.listIterator(operand2Len);
		long operand1Digit;// = finalIter.previous().intValue();
		long operand2Digit;// = multiplyIter.previous().intValue();
		long total;
		if (operand1Len < operand2Len) {
			trial.add(0L);
		} 
		else {
			trial = (LinkedList<Long>) finalList.clone();
			while (operand1Len > 0 && operand2Len > 0) {
				operand1Digit = finalIter.previous().intValue();
				operand2Digit = multiplyIter.previous().intValue();
				total = operand1Digit - operand2Digit + check;
				if (total < 0) {
					check = -1;
					total = total + convertBase;
				} else
					check = 0;
				trial.set(operand1Len - 1, total);
				operand1Len--;
				operand2Len--;
			}
			while (operand1Len > 0) {
				operand1Digit = finalIter.previous().intValue();
				total = operand1Digit + check;
				if (total < 0) {
					check = -1;
					total = total + convertBase;
				} else
					check = 0;
				trial.set(operand1Len - 1, total);
				operand1Len--;
			}
			if (check == -1) {
				trial = new LinkedList<Long>();
				trial.addFirst(0L);
			}
		}
		return trial;
	}

	
	/**
	 * method performs multiplication and returns a LinkedList
	 * 
	 * @param operand1
	 * 					: LinkedList - first operand
	 * 
	 * @param operand2
	 * 					: LinkedList - second operand
	 * */
	public LinkedList<Long> MultiplyList(LinkedList<Long> operand1, LinkedList<Long> operand2, int convertBase) {
		if (operand1 == null || operand2 == null) {
			return null;
		}
		int operand1Len = operand1.size();
		int operand2Len = operand2.size();
		int bothOperandsLen = operand1Len + operand2Len;
		while (!operand2.isEmpty()) {
			operand1.addLast(operand2.removeFirst());
		}
		LinkedList<Long> bothOperands = operand1;
		LinkedList<Long> answer = operand2;
		answer.add(0L);
		ListIterator<Long> operand2Iter = bothOperands.listIterator(bothOperandsLen);
		for (int i = 0; i < operand2Len; i++) {
			int operand2Digit = operand2Iter.previous().intValue();
			ListIterator<Long> operand1Iter = bothOperands.listIterator(operand1Len);
			for (int j = 0; j < operand1Len; j++) {
				int operand1Digit = operand1Iter.previous().intValue();
				int product = operand1Digit * operand2Digit;
				int shiftAmount = i + j;
				addtemp(answer, product, shiftAmount, convertBase);
			}
		}
		return operand2;
	}

	/**
	 * this methods shifts the digits and performs additions
	 * 
	 * @param result
	 * 				: LinkedList - stores values obtained from multiplication
	 * 
	 * @param product
	 * 				: int - product of two digits from lists
	 * 
	 * @param shiftAmount
	 * 				: int - the value by which digits shift
	 * */
	private void addtemp(LinkedList<Long> result, int product, int shiftAmount, int convertBase) {
		int resultLen = result.size();
		for (int insertPositionFromRight = shiftAmount; product != 0; insertPositionFromRight++) {
			int digit = product % convertBase;
			product /= convertBase;
			int numNodesNeeded = insertPositionFromRight - resultLen + 1;
			for (int i = 0; i < numNodesNeeded; i++) {
				result.addFirst(0L);
			}
			resultLen = result.size();
			int resultIndex = resultLen - insertPositionFromRight - 1;
			long curVal = result.get(resultIndex).intValue();
			long newVal = curVal + digit;
			if (newVal > convertBase) {
				newVal -= convertBase;
				product++;
			}
			result.set(resultIndex, newVal);
		}
	}

		/**
	 * method calculates power of a given value in a LinkedList
	 * 
	 * @param temp
	 * 				: LinkedList - base B converted list which is to be raised to power num
	 * 
	 * @param num
	 * 				: Long - the power which list has to be raised
	 * */
	public LinkedList<Long> tempPower(LinkedList<Long> temp, long num)
	{
		LinkedList<Long> trial = new LinkedList<Long>();
		LinkedList<Long> intermediate;// = new LinkedList<Long>();
		LinkedList<Long> hold;
		hold = (LinkedList<Long>) temp.clone();
		int convertBase = B;
		if (num == 0) {
			trial.add(1L);
			return trial;
		}
		trial = tempPower(temp, num / 2);
		if (num % 2 == 0) {
			intermediate = new LinkedList<Long>();
			intermediate = (LinkedList<Long>) trial.clone();
			trial = MultiplyList(intermediate, trial, convertBase);
			intermediate = (LinkedList<Long>) trial.clone();
			return trial;
			}
		else {
			intermediate = new LinkedList<Long>();
			intermediate = (LinkedList<Long>) trial.clone();
			trial = MultiplyList(intermediate, trial, convertBase);
			intermediate = (LinkedList<Long>) trial.clone();
			trial = MultiplyList(hold, intermediate, convertBase);
			hold.clear();
			hold = (LinkedList<Long>) temp.clone();
			return trial;
			}
	}
}