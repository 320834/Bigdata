import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class NonMapRedWordCount
{

    //private HashMap<String, Integer> map = new HashMap<String, Integer>();

    public int hackathonCount = 0;
    public int decCount = 0;
    public int chicagoCount = 0;
    public int javaCount = 0;

    public static void main(String[] args)
    {
        NonMapRedWordCount a = new NonMapRedWordCount();

        try {
			Scanner scanner = new Scanner(new File("./data.txt"));
			while (scanner.hasNextLine()) {

                a.search(scanner.nextLine());

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        System.out.println("Chicago " + a.chicagoCount);
        System.out.println("Dec " + a.decCount);
        System.out.println("Java " + a.javaCount);
        System.out.println("Hackathon " + a.hackathonCount);
        
	}

    public void search(String line)
    {
        String lowercaseStr = line.toLowerCase();

        if(lowercaseStr.contains("hackathon"))
        {
            hackathonCount += 1;
        }

        if(lowercaseStr.contains("dec"))
        {
            decCount += 1;
        }

        if(lowercaseStr.contains("java"))
        {
            javaCount += 1;
        }

        if(lowercaseStr.contains("chicago"))
        {
            chicagoCount += 1;
        }
    }

    
}