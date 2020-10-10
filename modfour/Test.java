import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args)
    {
        int max = 10;
        int i = 0;
        try {
			Scanner scanner = new Scanner(new File("./NEW_2019.csv"));
			while (scanner.hasNextLine()) {


                    String line = scanner.nextLine();

                    //System.out.println(line);

                    String[] lineArr = line.split("\",\"");

                    // System.out.println(lineArr[4]);
 
                    if(lineArr.length >= 5 && Test.neighborhood(lineArr[4].trim()))
                    {
                        //System.out.println(lineArr[4]);
                        System.out.println(lineArr[4] + " " + lineArr[5]);
                    }
                    else
                    {
                        //System.out.println(lineArr[4]);
                        //System.out.println("Error " + lineArr[4]);
                    }


                

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    public static boolean neighborhood(String n)
    {
        if(
            n.compareTo("Brooklyn") == 0 ||
            n.compareTo("Manhattan") == 0 || 
            n.compareTo("Staten Island") == 0 ||
            n.compareTo("Queens") == 0 ||
            n.compareTo("Bronx") == 0)
        {
            return true;
        }

        return false;
    }
}