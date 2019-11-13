import java.util.ArrayList;
import java.util.Collections;

public class bnet {
	public static int counter=0,k=0;
	public static int [][] ttArray; //n dimnsional array to store truth table values
	public static int [][] in_ttArray; //n dimnsional array to store truth table values for input without given 
	public static int [][] num_ttArray; //n dimnsional array to store truth table values for numerator  
	public static int [][] den_ttArray; //n dimnsional array to store truth table values for denominator 
	public static ArrayList<String> unKnown_num=new ArrayList<String>();
	
	//Probability values for different conditions
	static double Bt = 0.001;
	static double Et = 0.002;
	static double ABtEt = 0.95;
	static double ABtEf = 0.94;
	static double ABfEt = 0.29;
	static double ABfEf = 0.001;
	static double JAt = 0.90;
	static double JAf = 0.05;
	static double MAt = 0.70;
	static double MAf = 0.01;
	static double probA = 0;
	static double probB = 0;
	static double probE = 0;
	static double probJ = 0;
	static double probM = 0;
	static double prob_Total;
	static double prob_In_total;
	static double prob_Num_total;
	static double prob_Den_Total;
	static double prob_Final_Total;
	
	public static void main(String[] args) 
	{

		//Array lists for storing different values
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> input_miss = new ArrayList<String>();	
		ArrayList<String> input_upd = new ArrayList<String>();		
		ArrayList<String> before = new ArrayList<String>();
		ArrayList<String> after = new ArrayList<String>();
		ArrayList<String> numerator = new ArrayList<String>();
		ArrayList<String> numerator_miss = new ArrayList<String>();
		ArrayList<String> numerator_upd = new ArrayList<String>();
		ArrayList<String> denominator_miss = new ArrayList<String>();
		ArrayList<String> denominator_upd = new ArrayList<String>();
		
		if (args.length == 0 || args.length > 6 )
		{
		  System.out.println("The number of arguments is not in the range 1 to 6"); //Checks if input arguments are in the range 1 to 6
		  System.exit(0);
		}
		
		for( int i = 0; i < args.length ; i++)
		{
			input.add(args[i]);
		}
		
		StringBuilder in_exp = new StringBuilder();
		for (String s : input)
		{
			in_exp.append(s);

		}
		in_exp.toString();
		String str1 = in_exp.toString();
		String in_str = str1.replaceAll("given", "|"); 
		System.out.println("The input expression = " + str1);  //Shows input expression without given 
		
		// Displays the symbols before and after given 
		if(input.contains("given"))
		{
		String str2 = in_str;
		String beforegiven = str2.replaceAll("\\|.*", "");
		String aftergiven  = in_str.substring(in_str.lastIndexOf("|") + 1);
		System.out.println("The expression before given = " + beforegiven); //Displays the values before given 
		System.out.println("The expression after given = "  + aftergiven); //Displays the values after given 
		}
		
		if(input.contains("given")) //Performs calculations based on input with given condition
		{
			for (int i1 = 0;i1 <  input.indexOf("given");i1 ++)
			{
				before.add(args[i1]);
			}
			
			for (int i1 = (input.indexOf("given")+1);i1 <  input.size();i1 ++)
			{
				after.add(args[i1]);
			}
			
			if(before.size() > 5)
			{
				System.out.println("The input arguments before given are greater than 5 ");
				System.exit(0);
				
			}
			
			if(after.size() > 4)
			{
				System.out.println("The input arguments after given are greater than 4 ");
				System.exit(0);
				
			}
			
			numerator.addAll(before); //Adds all values to numerator
			numerator.addAll(after);
			
			
			numerator_miss = missing_val(numerator); //Stores all missing values from given input for numerator
			numerator_upd.addAll(numerator);
			ArrayList<String> temp= new ArrayList<String>();
			temp.addAll(numerator_upd); //Temp value to store numerator values
			numerator_upd.addAll(numerator_miss); //Used to store updated numerator values

			unKnown_num.clear(); //Used to know unknown values
			num_ttArray = generateTT(numerator_upd); //Stores all possible combination values from truth table
			for(int i=0;i<num_ttArray.length;i++) //Shows all possible combinations and performs all the calculations for numerator
			{
				ArrayList<String> arrayValAppended=new ArrayList<String>();
				for(int j=0;j<num_ttArray[i].length;j++)
				{
					
					arrayValAppended.add(unKnown_num.get(j)+num_ttArray[i][j]);
				}
				temp.addAll(arrayValAppended);
				Collections.sort(temp);
				prob_Num_total+=computeProbability(temp.get(0),temp.get(1) , temp.get(2), temp.get(3), temp.get(4));
				
				temp.removeAll(arrayValAppended);
			}
			
			temp.clear();
			
			//Denominator calculations start here 
			
			denominator_miss = missing_val(after);//Stores all missing values from given input for denominator
			denominator_upd.addAll(after);
			temp.addAll(denominator_upd); //Temp value to store denominator values
			denominator_upd.addAll(denominator_miss); //Used to store updated denominator values
			
			unKnown_num.clear(); //Used to know unknown values
			den_ttArray = generateTT(denominator_upd); //Stores all possible combination values from truth table
			
			for(int i=0;i<den_ttArray.length;i++) //Shows all possible combinations and performs all the calculations for denominator
			{
				ArrayList<String> arrayValAppended=new ArrayList<String>();
				for(int j=0;j<den_ttArray[i].length;j++) 
				{
					
					arrayValAppended.add(unKnown_num.get(j)+den_ttArray[i][j]);
				}
				temp.addAll(arrayValAppended);
				Collections.sort(temp);
				prob_Den_Total+=computeProbability(temp.get(0),temp.get(1) , temp.get(2), temp.get(3), temp.get(4));
				
				temp.removeAll(arrayValAppended);
				
			}
			prob_Final_Total = prob_Num_total/prob_Den_Total; //Final probability calculation 
			System.out.println("Computed Probability = "+prob_Final_Total);					
		}
		else //Calculations for input without given 
		{
			input_miss = missing_val(input); //Stores all missing values from given input 
			input_upd.addAll(input);
			ArrayList<String> temp= new ArrayList<String>();
			temp.addAll(input_upd); //Temp value to store input values
			
			input_upd.addAll(input_miss);
			
			unKnown_num.clear(); //Used to know unknown values
			in_ttArray = generateTT(input_upd); //Stores all possible combination values from truth table
		
			for(int i=0;i<in_ttArray.length;i++) //Shows all possible combinations and performs all the calculations for input
			{
				ArrayList<String> arrayValAppended=new ArrayList<String>();
				for(int j=0;j<in_ttArray[i].length;j++) 
				{
					
					arrayValAppended.add(unKnown_num.get(j)+in_ttArray[i][j]);
				}
				temp.addAll(arrayValAppended);
				Collections.sort(temp);
				prob_In_total+=computeProbability(temp.get(0),temp.get(1) , temp.get(2), temp.get(3), temp.get(4));
				
				temp.removeAll(arrayValAppended);
			}
			System.out.println("Computed Probability = "+prob_In_total); //Displays final probability
			
		}

	}
	
	public static int[][] generateTT(ArrayList<String> passVal) //Gets all the missing values from input 
	{
		counter =0;
		
		if(passVal.contains("B")){
			unKnown_num.add("B");
			counter++;
		}
		if(passVal.contains("E")){
			unKnown_num.add("E");
			counter++;
		}
		if(passVal.contains("A")){
			unKnown_num.add("A");
			counter++;
		}
		if(passVal.contains("M")){
			unKnown_num.add("M");
			counter++;
		}
		if(passVal.contains("J")){
			unKnown_num.add("J");
			counter++;
		}
		k=0;
		ttArray=new int[(int)Math.pow(2, counter)][counter];
		
				generateTable(0, counter, new int[counter]);
		
		return ttArray;
	}
	
	public static double computeProbability (String A, String B , String E, String J, String M ) //Function for actual probability calculation
	{
		
		if(B.equals("Bt")
		|| B.equals("B1"))
		{
			probB = Bt;
		}
		else
		{
			probB = 1 - Bt;
		}
		
		if(E.equals("Et")
		|| E.equals("E1"))
		{
			probE = Et;
		}
		else
		{
			probE = 1 - Et;
		}
		
		if(A.equals("At")|| A.equals("A1"))
		{
			if(  (B.equals("Bt")|| B.equals("B1")) && (E.equals("Et")|| E.equals("E1")) )
			{
				probA = ABtEt;
			}
			else if (  (B.equals("Bt")|| B.equals("B1")) && (E.equals("Ef")|| E.equals("E0")) )
			{
				probA = ABtEf;
			}
			else if (  (B.equals("Bf")|| B.equals("B0")) && (E.equals("Et")|| E.equals("E1")) )
			{
				probA = ABfEt;
			}
			else if (  (B.equals("Bf")|| B.equals("B0")) && (E.equals("Ef")|| E.equals("E0")) )
			{
				probA = ABfEf;
			}
			else
			{
				
			}
		}
		else
		{
			
			if(  (B.equals("Bt")|| B.equals("B1")) && (E.equals("Et")|| E.equals("E1")) )
			{
				probA =  1 - ABtEt;
			}
			else if (  (B.equals("Bt")|| B.equals("B1")) && (E.equals("Ef")|| E.equals("E0")) )
			{
				probA =  1 - ABtEf;
			}
			else if (  (B.equals("Bf")|| B.equals("B0")) && (E.equals("Et")|| E.equals("E1")) )
			{
				probA =  1 - ABfEt;
			}
			else if (  (B.equals("Bf")|| B.equals("B0")) && (E.equals("Ef")|| E.equals("E0")) )
			{
				probA =  1 - ABfEf;
			}
			else
			{
				
			}
		}
		
		if( J.equals("Jt")
		 || J.equals("J1"))
		{
			if(A.equals("At")
			|| A.equals("A1"))
			{
				probJ = JAt;
			}
			else
			{
				probJ = JAf;
			}
		}
		else
		{
			if(A.equals("At")
			|| A.equals("A1"))
			{
				probJ = 1 - JAt;
			}
			else
			{
				probJ = 1 - JAf;
			}
		}
		
		if( M.equals("Mt")
		 || M.equals("M1"))
		{
			if(A.equals("At")
			|| A.equals("A1"))
			{
				probM = MAt;
			}
			else
			{
				probM = MAf;
			}
		}
		else
		{
			if(A.equals("At")
			|| A.equals("A1"))
			{
				probM = 1 - MAt;
			}
			else
			{
				probM = 1 - MAf;
			}
		}
		
		
		prob_Total = probA*probB*probE*probJ*probM; 
		return prob_Total;
	}
	
	public static int [][] generateTable(int index, int size, int[] current) //Generates the truth table combinations for missing values
	{
		
        if (index == size) {
            for (int i = 0; i < size; i++) {
                 ttArray[k][i]=current[i];              
            }
            k++;
        } else {
            for (int i = 0; i < 2; i++) {
                current[index] = i;
                generateTable(index + 1, size, current);
            }
          
        }
        return ttArray;
    }
	
	public static ArrayList<String> missing_val(ArrayList<String> in_arr) //Finds all the missing values from the input
	{
		
		ArrayList<String> in_arr_upd = new ArrayList<String>();	
		  if(in_arr.contains("At"))
		  {

		  }
		  else if (in_arr.contains("Af"))
		  {
			  
		  }
		  else
		  {
			  in_arr_upd.add("A");
		  }

		  if(in_arr.contains("Bt"))
		  {

		  }
		  else if (in_arr.contains("Bf"))
		  {
			  
		  }
		  else
		  {
			  in_arr_upd.add("B");
		  }

		  if(in_arr.contains("Et"))
		  {

		  }
		  else if (in_arr.contains("Ef"))
		  {
			  
		  }
		  else
		  {
			  in_arr_upd.add("E");
		  }

		  if(in_arr.contains("Jt"))
		  {

		  }
		  else if (in_arr.contains("Jf"))
		  {
			  
		  }
		  else
		  {
			  in_arr_upd.add("J");
		  }
		  
		  if(in_arr.contains("Mt"))
		  {

		  }
		  else if (in_arr.contains("Mf"))
		  {
			  
		  }
		  else
		  {
			  in_arr_upd.add("M");
		  }
		  
		  return in_arr_upd;
	}
}
