import java.io.File;
import java.io.FileInputStream;

public class FindInterface {

	public int filesNumber;
	String Child = "interface";
	public String path;

	public void getPath(String path){
		this.path = path;
	}
	public void aaa(File dir, String name) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(new File(dir + "\\"
					+ name));
			byte[] content = new byte[fis.available()];
			fis.read(content);

			fis.close();
			String[] lines = new String(content, "UTF-8").split("\n");
			for (String line : lines) {
				String[] words = line.split(" ");
				for (String word : words) {
					if (word.equalsIgnoreCase(Child)) {
						filesNumber++;					
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exc at class FindInt: " + e);
		}
	}
	
	public int setint(){
		return filesNumber;
	}

}
