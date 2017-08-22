import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Work {

	private Pattern p = null;
	private Matcher m = null;

	private long totalLength = 0;
	private long filesNumber = 0;
	private long directoriesNumber = 0;

	private final int FILES = 0;
	private final int DIRECTORIES = 1;
	private final int ALL = 2;

	int NDD;
	int sizearray = 1;
	String[] worlds = new String[sizearray];

	public static String path = "";
	boolean isExist = false;

	CopyOfWork NDD2 = new CopyOfWork();
	FindInterface fi = new FindInterface();

	public List findAll(String startPath) throws Exception {
		return find(startPath, "", ALL);
	}

	public List findAll(String startPath, String mask) throws Exception {
		path = startPath;
		return find(startPath, mask, ALL);
	}

	public long getDirectorySize() {
		return totalLength;
	}

	public long getFilesNumber() {
		return filesNumber;
	}

	public long getDirectoriesNumber() {
		return directoriesNumber;
	}

	private boolean accept(String name) {
		if (p == null) {
			return true;
		}
		m = p.matcher(name);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	private List find(String startPath, String mask, int objectType)
			throws Exception {
		if (startPath == null || mask == null) {
			throw new Exception("������: �� ������ ��������� ������");
		}
		File topDirectory = new File(startPath);
		if (!topDirectory.exists()) {
			throw new Exception("������: ��������� ���� �� ����������");
		}
		if (!mask.equals("")) {
			p = Pattern.compile(mask, Pattern.CASE_INSENSITIVE);
		}

		filesNumber = 0;
		directoriesNumber = 0;
		totalLength = 0;

		ArrayList res = new ArrayList(100);


		search(topDirectory, res, objectType);
		p = null;
		return res;
	}

	private void search(File topDirectory, List res, int objectType)
			throws Exception {

		File[] list = topDirectory.listFiles();
		for (int i = 0; i < list.length; i++) {
			System.out.println("Check file: " + list[i].getName());
			if (list[i].isDirectory()) {
				if (objectType != FILES && accept(list[i].getName())) {
					directoriesNumber++;
					res.add(list[i]);
					isExist = true;
				}
				search(list[i], res, objectType);
			}
			else {
				if (objectType != DIRECTORIES && accept(list[i].getName())) {
					String mask = "(" + list[i].getName() + ")";
					NDD2.getPath(path);
					fi.getPath(path);
					NDD2.getNOC(filesNumber);
					fi.aaa(topDirectory, list[i].getName());
					NDD2.getInt(fi.setint());
					NDD2.aaa(topDirectory, list[i].getName());
					filesNumber++;
					totalLength += list[i].length();
					res.add(list[i]);
				}
			}
		}
	}

	public void PrintNOC() {
		System.out.println("NOC:" + filesNumber);
	}

	public void PrintNDD() {
		NDD2.PrintNDD();
	}

}
