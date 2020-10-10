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

                    //System.out.println(lineArr[4]);

                    try
                    {
                        if(Test.neighborhood(lineArr[4]))
                        {
                            
                        }
                        else
                        {
                            System.out.println("Error " + lineArr[4]);
                        }

                    }
                    catch(Exception e)
                    {
                        System.out.println(line);
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
            n != "\"Brooklyn\"" ||
            n != "\"Manhattan\"" || 
            n != "\"Staten Island\"" ||
            n != "\"Queens\"" ||
            n != "\"Bronx\"")
        {
            return true;
        }

        return false;
    }
}