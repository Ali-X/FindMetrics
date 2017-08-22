public class Main {

	 public static String path = "D:\\���\\Java\\Empirical Software Eng\\src\\fractalzoomer";
	 public static String file = ".+\\.(java)";
		
	 public static void main(String[] args) throws Exception{
		 Work find = new Work();
		 
		 find.findAll(path, file);
		 find.PrintNOC();
		 find.PrintNDD();

	 }
}
