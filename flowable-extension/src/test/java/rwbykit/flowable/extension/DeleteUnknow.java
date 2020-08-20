package rwbykit.flowable.extension;

import java.io.File;
import java.util.Arrays;

public class DeleteUnknow {

    final static String unknown = "unknown";

    public static void main(String[] args) {

        String startFile = "D:\\maven\\Library\\Maven-Repo";

        //unknown

        delete(startFile);

    }


    static void delete(String path) {
        File file = new File(path);
        if (file.isFile()) {
            return;
        }

        if (needDelete(file)) {
            boolean result = deleteFile(file);
            System.out.println(file.getAbsolutePath() + "  --->  "+ result);
            return;
        }

        File[] files = file.listFiles();

        Arrays.asList(files).stream().map(File::getAbsolutePath).forEach(DeleteUnknow::delete);

    }

    static boolean needDelete(File file) {
        return unknown.equals(file.getName()) || file.getName().startsWith("\\$");
    }

    static boolean deleteFile(File file) {
        File[] files = file.listFiles();
        boolean r = Arrays.stream(files).map(File::delete).anyMatch(result -> !result);
        boolean s = file.delete();
        return !r || s;
    }

}
