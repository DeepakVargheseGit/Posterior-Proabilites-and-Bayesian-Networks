import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class compute_a_posteriori

{

	public static void main(String[] args) throws IOException
	{
		FileWriter op_write = new FileWriter("results.txt"); //Used to write into file
		PrintWriter print_write = new PrintWriter(op_write);

		double pcherry[] = {1.00,0.75,0.50,0.25,0}; //probability values for cherry in bags
		double plime[] = {0,0.25,0.50,0.75,1.00}; //probability values for lime in bags
		double prior[] = {0.1,0.2,0.4,0.2,0.1}; //prior probability values for the bags
		double pnextC = 0, pnextL = 0;
		int i = 0;

		String in_seq = args[0].toString().toUpperCase(); //Converts to uppercase
		print_write.printf("Observation sequence Q : " + in_seq + "\n"); // Shows the input sequence
		int seq_length = in_seq.length();
		print_write.printf("Length of Q :" + seq_length + "\n" + "\n"); // Shows the length of input sequence

		for( i = 0; i<seq_length; i++ )
		{
			if(i == 0)
			{
				print_write.printf("After observation "+(i+1)+ " = "+ in_seq.charAt(i)+ "\n" + "\n" );	// Shows the observation number and type
			}
			if(in_seq.charAt(i) == 'C') // Checks if next is a cherry candy
			{
				for(int i1 = 0; i1< 5; i1++ )
				{
					pnextC = ( prior[i1]*pcherry[i1] ) + pnextC;
					pnextL = ( prior[i1]*plime[i1]   ) + pnextL;

					if(i1 == 4)
					{
						if(i!=0)
						{
							print_write.printf("Probability that the next candy we pick will be C, given Q: " + String.format("%.10f", pnextC)+ "\n"); //shows the probability values for next cherry candy
							print_write.printf("Probability that the next candy we pick will be L, given Q: " + String.format("%.10f", pnextL)+ "\n" + "\n"); //shows the probability values for next lime candy
							print_write.printf("After observation "+(i+1)+ " = "+ in_seq.charAt(i)+ "\n" + "\n"); // Shows the observation number and type
						}

						for(int i2 = 0; i2< 5; i2++ )
						{

							prior[i2] = (pcherry[i2] * prior[i2] )/ pnextC;
							print_write.printf("P(h" + (i2+1) + " | Q) = " + String.format("%.10f", prior[i2]) + "\n"); //Shows the probability for each bag
						}
						print_write.printf("\n");
						if(i == seq_length- 1)
						{
							pnextC = 0;
							pnextL = 0;
							for(int i3 = 0; i3< 5; i3++ )
							{

								pnextC = ( prior[i3]*pcherry[i3] ) + pnextC;
								pnextL = ( prior[i3]*plime[i3]   ) + pnextL;
							}
							print_write.printf("Probability that the next candy we pick will be C, given Q: " + String.format("%.10f", pnextC)+ "\n");//shows the probability values for next cherry candy
							print_write.printf("Probability that the next candy we pick will be L, given Q: " + String.format("%.10f", pnextL)+ "\n");//shows the probability values for next lime candy
						}

					}
				}
				pnextC = 0;
				pnextL = 0;
			}
			else if (in_seq.charAt(i) == 'L')
			{
				for(int i4 = 0; i4< 5; i4++ )
				{

					pnextC = ( prior[i4]*pcherry[i4] ) + pnextC;
					pnextL = ( prior[i4]*plime[i4]   ) + pnextL;
					if(i4 == 4)
					{
						if(i!=0)
						{
							print_write.printf("Probability that the next candy we pick will be C, given Q: " + String.format("%.10f", pnextC)+ "\n");//shows the probability values for next cherry candy
							print_write.printf("Probability that the next candy we pick will be L, given Q: " + String.format("%.10f", pnextL)+ "\n" + "\n");//shows the probability values for next lime candy
							print_write.printf("After observation "+(i+1)+ " = "+ in_seq.charAt(i)+ "\n" + "\n");// Shows the observation number and type
						}

						for(int i5 = 0; i5< 5; i5++ )
						{

							prior[i5] = ( plime[i5] * prior[i5] ) / pnextL;
							print_write.printf("P(h" + (i5+1) + " | Q) = " + String.format("%.10f", prior[i5])+ "\n"); //Shows the probability for each bag
						}
						print_write.printf("\n");
						if(i == seq_length - 1 )
						{
							pnextC = 0;
							pnextL = 0;
							for(int i6 = 0; i6< 5; i6++ )
							{

								pnextC = ( prior[i6]*pcherry[i6] ) + pnextC;
								pnextL = ( prior[i6]*plime[i6]   ) + pnextL;
							}
							print_write.printf("Probability that the next candy we pick will be C, given Q: " + String.format("%.10f", pnextC)+ "\n");//shows the probability values for next cherry candy
							print_write.printf("Probability that the next candy we pick will be L, given Q: " + String.format("%.10f", pnextL)+ "\n");//shows the probability values for next lime candy
						}

					}
				}
				pnextC = 0;
				pnextL = 0;
			}
			else
			{
				System.out.println("The input sequence has characters that are not C and L ");
			}


		}
		print_write.close();
	}

}
