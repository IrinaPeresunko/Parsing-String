import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;


public class ParsingString {
	public static int[] getPositionOfArithmeticOperations(char[] expression){
		int countOfOperations=1;
	      for(int i=0;i<expression.length;i++){
	    	  if(expression[i]=='+'|| expression[i]=='-'||expression[i]=='*'|| expression[i]=='/'){
	    		  countOfOperations++;
	    	  }
	      }
	      int[] operations = new int[countOfOperations];
	      for(int i=0,j=0;i<expression.length && j<countOfOperations;i++){
	    	  if(expression[i]=='+'|| expression[i]=='-'||expression[i]=='*'|| expression[i]=='/'){
	    		  operations[j]=i;
	    		  j++;
	    	  }
	    	  if(j==countOfOperations-1) operations[j]=expression.length;
	      }
		return operations;
	}
	public static int[] getPositionOfBrackets(char[] expression){
		int count=0;
	      for(int i=0;i<expression.length;i++){
	    	  if(expression[i]=='(' || expression[i]==')'){
	    		  count++;
	    	  }
	      }
	      int[] brackets = new int[count];
	      for(int i=0,j=0;i<expression.length && j<count;i++){
	    	  if(expression[i]=='(' || expression[i]==')'){
	    		  brackets[j]=i;
	    		  j++;
	    	  }
	      }
		return brackets ;
	}
	
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userInput = null;
		
	    System.out.println("Enter the arithmetic expression: ");
	    
	      try {
			userInput = (String) br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      char[] expression=new char[userInput.length()];
	      
	      for(int i=0;i<userInput.length();i++){
	    	  expression[i]=userInput.charAt(i);
	      }
	      //System.out.println(Arrays.toString(expression));
	     
	     int[] positionOfBrackets=getPositionOfBrackets(expression);
	     
	     char[] expressionInBrackets=new char[3*(positionOfBrackets.length/2)];
	      for(int i=0,j=0;i<positionOfBrackets.length;i+=2){
	    	  int k=positionOfBrackets[i]+1;
	    	  while(k!=positionOfBrackets[i+1]){
	    		  expressionInBrackets[j]=userInput.charAt(k);
	    		  k++;j++;
	    	  }
	      }
	     // System.out.println(Arrays.toString(expressionInBrackets));
	
	      int countOfOperationsInBrackets=expressionInBrackets.length/3+1;
	      int[] operationsInBrackets = new int[countOfOperationsInBrackets];
	      for(int i=0,j=0;i<expressionInBrackets.length && j<countOfOperationsInBrackets;i++){
	    	  if(expressionInBrackets[i]=='+'|| expressionInBrackets[i]=='-'|| 
	    			  expressionInBrackets[i]=='*'|| expressionInBrackets[i]=='/'){
	    		  operationsInBrackets[j]=i;
	    		  j++;
	    	  }
	    	  if(j==countOfOperationsInBrackets-1) operationsInBrackets[j]=expressionInBrackets.length;
	      }
	      //System.out.println(Arrays.toString(operationsInBrackets));
	     
	     
	      double[] numbersInBrackets=new double[expressionInBrackets.length-(expressionInBrackets.length/3)];
	      for(int i=0,j=0;i<numbersInBrackets.length-1;i+=2){
	    	  numbersInBrackets[i]=expressionInBrackets[operationsInBrackets[j]-1]-'0';
	    	  numbersInBrackets[i+1]=expressionInBrackets[operationsInBrackets[j]+1]-'0';
	    	  if(j<operationsInBrackets.length-1) j++;
	    	  }
	      //System.out.println(Arrays.toString(numbersInBrackets));
	      
	      double[] resultsInBrackets=new double[numbersInBrackets.length/2];
	      for(int i=0,j=0,k=0;i<operationsInBrackets.length-1;i++){
	    	  if(expressionInBrackets[operationsInBrackets[i]]=='+') resultsInBrackets[j]=numbersInBrackets[k]+numbersInBrackets[k+1];
	    	  if(expressionInBrackets[operationsInBrackets[i]]=='-') resultsInBrackets[j]=numbersInBrackets[k]-numbersInBrackets[k+1];
	    	  if(expressionInBrackets[operationsInBrackets[i]]=='*') resultsInBrackets[j]=numbersInBrackets[k]*numbersInBrackets[k+1];
	    	  if(expressionInBrackets[operationsInBrackets[i]]=='/') resultsInBrackets[j]=(numbersInBrackets[k]/numbersInBrackets[k+1]);
	    	  if(j<resultsInBrackets.length-1) j++;
	    	  k+=2;
	    	  }
	      //System.out.println(Arrays.toString(resultsInBrackets));
	      
	      for(int i=0;i<positionOfBrackets.length-1;i+=2){
	    	  int k=positionOfBrackets[i];
	    	  while(k!=positionOfBrackets[i+1]){
	    		  expression[k]='!';
	    		  k++;
	    		  }
	    	  }
	      //System.out.println(Arrays.toString(expression));
	   
	     char[] correctExpression=new char[expression.length-((positionOfBrackets.length/2)*4)];
	      for(int i=0,j=0;i<expression.length;i++){
	    	  if(expression[i]!='!'){
	    		  correctExpression[j]=expression[i];
	    		  j++;
	    	  }
	      }
	      //System.out.println(Arrays.toString(correctExpression));
	      
	      int[] positionOfOperations=getPositionOfArithmeticOperations(correctExpression);
		     //System.out.println(Arrays.toString(positionOfOperations));
	      
	      double[] allNumbers=new double[positionOfOperations.length];
	      for(int i=0,j=0,k=0;i<correctExpression.length;i+=2){
	    	  if(correctExpression[i]==')'){
	    		  allNumbers[j]=resultsInBrackets[k];
	    		  if(k<resultsInBrackets.length-1) k++;
	    		  if(j<allNumbers.length-1) j++;    		  
	    	  }
	    	  else{
	    		  allNumbers[j]=correctExpression[i]-'0';	    		 
	    		  if(j<allNumbers.length-1) j++;	    		  	    		  
	    	  }	    		
	          }
	     //System.out.println(Arrays.toString(allNumbers));
	    
	      double result=0;
	      for(int i=1,j=positionOfOperations[0];i<allNumbers.length;i++){
	    	  if(correctExpression[j]=='*'){
	    		  result=(allNumbers[i-1])*(allNumbers[i]);
	    		  allNumbers[i]=result;
	    		  allNumbers[i-1]=0;
	    		  }
	    	  if(correctExpression[j]=='/'){
	    		  result=(allNumbers[i-1])/(allNumbers[i]);
	    		  allNumbers[i]=result;
	    		  allNumbers[i-1]=0;
	    		  }
	    	  j=positionOfOperations[i];
	      }
	      
	      int count=0;
	      for(int i=0;i<allNumbers.length;i++){
	    	  if(allNumbers[i]!=0) count++;
	      }
	      
	      double[] numbersAfterCalculation = new double[count];
	      for(int i=0,k=0;i<allNumbers.length;i++){
	    	  if(allNumbers[i]!=0){
	    		  numbersAfterCalculation[k]=allNumbers[i];
	    		  k++;
	    	  }
	      }
	      //System.out.println(Arrays.toString(numbersAfterCalculation));
	      
	      boolean flag=false;
	      for(int i=0;i<correctExpression.length;i++){
	    	  if(correctExpression[i]=='+'|| correctExpression[i]=='-'){
	    		  flag=true;
	    		  break;
	    	  }
	      }
	      if(flag==true){
	    	  result=numbersAfterCalculation[0];
	    	  for(int i=1,j=0;j<positionOfOperations.length-1;j++){
	    		  if(correctExpression[positionOfOperations[j]]=='+'){
	    			  if(i<numbersAfterCalculation.length){
	    				  result+=numbersAfterCalculation[i];	    			
	    			   i++;
	    			  }
	    		  }
	    		  if(correctExpression[positionOfOperations[j]]=='-'){
	    			  if(i<numbersAfterCalculation.length){
	    			  result-=numbersAfterCalculation[i];
	    			   i++;
	    			  }
	    		  }
	    		  }
	    	  }
	      System.out.println("result="+result);
	}
}