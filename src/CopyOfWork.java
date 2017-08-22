import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyOfWork {

    private final int FILES = 0;
    private final int DIRECTORIES = 1;
    private final int ALL = 2;
    public String Parent;
    public String path;
    public ArrayList<String> par = new ArrayList<String>();
    String Child = "extends";
    boolean isExist = false;
    int numPar = 0;
    int numChild = 0;
    int N = 1;
    int NOC;
    int numInt = 0;
    private Pattern p = null;
    private Matcher m = null;
    private long totalLength = 0;
    private long filesNumber = 0;
    private long directoriesNumber = 0;

    public void getPath(String path) {
        this.path = path;
    }

    public void getNOC(long filesNumber2) {
        this.NOC = (int) filesNumber2;
    }

    public void getInt(int numInt) {
        this.numInt = numInt;
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
            if (list[i].isDirectory()) {
                if (objectType != FILES && accept(list[i].getName())) {
                    directoriesNumber++;
                    res.add(list[i]);
                    isExist = true;
                }
                search(list[i], res, objectType);
            } else {
                if (objectType != DIRECTORIES && accept(list[i].getName())) {
                    if (par.isEmpty()) {
                        par.add(list[i].getName());
                    } else if (!par.contains(list[i].getName())) {

                        par.add(list[i].getName());
                        filesNumber++;
                        totalLength += list[i].length();
                        res.add(list[i]);
                    }
                    numPar = par.size();
                    numChild++;
                }
            }
        }
    }

    public double findNDD() {
        double ans = 0;
        ans = (double) numChild / (double) (NOC - numInt);
        return ans;
    }


    public void PrintNDD() {
        System.out.println("Number of parent classes: " + numPar);
        System.out.println("Number of child classes: " + numChild);
        System.out.println("Number of interface classes: " + numInt);
        System.out.printf("NDD: " + "%.2f", findNDD());
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
                int j = 1;
                for (String word : words) {
                    if (word.equalsIgnoreCase(Child)) {
                        Parent = words[j];
                        String mask = "(" + Parent + ")" + ".(java)";
                        findAll(path, mask);
                    }
                    j++;
                }
            }
        } catch (Exception e) {
            System.out.println("Exc at class CopyOfWork: " + e);
        }

    }

}
