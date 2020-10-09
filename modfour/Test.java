import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args)
    {
        try {
			Scanner scanner = new Scanner(new File("./New.csv"));
			while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                //System.out.println(line);

                String[] lineArr = line.split("\t");

                System.out.println(lineArr[5]);

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}