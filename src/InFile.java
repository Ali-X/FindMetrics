import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class InFile {

	public static void WooHoo() throws FileNotFoundException, IOException {
        String searchWord = "�����";
        
        FileInputStream fis = new FileInputStream(new File("E:/1.txt"));
        byte[] content = new byte[fis.available()];
        fis.read(content);
        fis.close();
        String[] lines = new String(content, "Cp1251").split("\n");
        int i = 1;
        for (String line : lines) {
            String[] words = line.split(" ");
            int j = 1;
            for (String word : words) {
                if (word.equalsIgnoreCase(searchWord)) {
                    System.out.println("������� � " + i + "-� ������, " + j + "-� �����");
                }
                j++;
            }
            i++;
        }
    }
	
}
