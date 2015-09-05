import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * @author A. Tzani
**/


public class AskGreekAfm{
	
	/**
	 * Reads the database of GreekAfm site from
	 * the home page to the last and stores it 
	 * in csv file.
	 * The recordings are of the format "Company Name , Company AFM".
	 */

	public static void main(String[] args) throws IOException, InterruptedException{
	
	// creation and declaration of bufferdWriter as Null
	BufferedWriter bufferedWriter = null;

	//variable for counting the greekAfm current page
	int page=0;
	//variable to store the url
	String url;
	
	//variable for storing the desirable time delay in seconds
	int delay=30;
	
	try {
	      
	    //creation of bufferedWriter object
	   	bufferedWriter = new BufferedWriter(new FileWriter("GreekAfmDatabase.csv"));
		
	   	while (page<660)	{
	   		
	   		//add time delay every 10 repetitions
	   		if (page!=0 && page%10==0){
	   			System.out.print("page = "+page+" , apotelesma = "+page%10);
	   			new TimeDelay(delay);
	   		}
	   		
	   		if (page==0){
	   			//definition of the URL http://www.greekafm.com/δου for the home pages
	   			url="http://www.greekafm.com/%CE%B4%CE%BF%CF%85";
	   		}
	   		else	{
	   			//definition of the URL http://www.greekafm.com/δου for the rest pages
	   			url="http://www.greekafm.com/%CE%B4%CE%BF%CF%85?page="+page;
	   		}
	   				//get connected with the command Jsoup.connect at the 
	   				//Choosed url and all the source code get stored to doc1
	   				Document doc1=Jsoup.connect(url).userAgent("Internet Explorer").get();
			
	   				//we draw from the doc1 only the contents of 
	   				//class s with label cite
	   				Elements links=doc1.select(".field-content a[href]");
			
			
	   				for(Element link:links) {
				
	   					//char array to store the url tis of each company
	   					char[] aArray =link.attr("href").toCharArray();
			
	   					//string variable to store the name of each company
	   					String companyName = link.text();
			
	   					//we take only the section of char array which corresponds to the afm
	   					String companyAfm = new String(aArray, 29, 9);
				
	   					System.out.print(link.text());
	   					System.out.print("\n");
	   					System.out.print(companyAfm);
	   					System.out.print("\n");
	   					bufferedWriter.write(companyName + " , " + companyAfm);
	   					bufferedWriter.write("\n");
	   					System.out.print("\n");
	   				}
	   				//increase the page variable by one in each repeat
	   				page++;
	   	}
		
	} catch (FileNotFoundException e1) {
	e1.printStackTrace();
	}
	finally {
		//Closes the BufferedWriter
		try {
			if (bufferedWriter != null) {
				bufferedWriter.flush();
				bufferedWriter.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
}
