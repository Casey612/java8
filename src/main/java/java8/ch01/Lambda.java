package java8.ch01;

import org.springframework.util.Assert;

import java.io.File;
import java.util.Arrays;

/**
 * @author yuzhe
 * @since 1/17/18
 */
public class Lambda {

    public static void main(String[] args) {

    }

    public static void lambdaListFileCompareByPath() {
        File home = new File("/home/yuzhe");
        File[] files = home.listFiles();


        Arrays.sort(files, (file1, file2) -> {
            if (file1.isDirectory() && file2.isDirectory()) {
                return file1.getName().compareTo(file2.getName());
            } else {
                return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
            }
        });

    }

    public static void lambdaListDics() {
        File home = new File("/home/yuzhe");
        File[] files1 = home.listFiles(dir -> dir.isDirectory());
        File[] files2 = home.listFiles(File::isDirectory);
        Assert.isTrue(files1.length == files2.length);
    }


    /**
     * lambda 表达式的线程和主线程相同
     */
    public static void sameThread() {
        String[] array = new String[]{"123", "234", "abc", "bcd"};

        System.out.println("Main:" + Thread.currentThread().toString());

        Arrays.sort(array, (o1, o2) -> {
            System.out.println(Thread.currentThread().toString());
            return o1.compareTo(o2);
        });
    }

}
