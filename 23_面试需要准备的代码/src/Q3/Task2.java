package Q3;

import java.io.*;
import java.util.Arrays;

public class Task2 {
    private static final String NATURAL_ORDER = "natureOrder";
    private static final String INDEX_ORDER = "indexOrder";
    private static final String CHAR_ORDER = "charOrder";
    // private static final String CHAR_ORDER_DESC = "charOrderDESC";
    private static final int READ_BUFF_SIZE = 512;
    private static final int WRITE_BUFF_SIZE = 1024;
    private static final int ARRAY_SIZE = 6110;

    /**
     * 原小说Reader, 已封装为CharArrayReader
     */
    private static Reader templateReader;
    /**
     * 替换词组资源文件Reader, 已封装为CharArrayReader
     */
    private static Reader configReader;
    /**
     * 替换完成文件Writer, 已封装为FileWriter
     */
    private static Writer mergedWriter;

    static {
        try {
            templateReader = new BufferedReader(new FileReader("C:\\Users\\Shan\\Desktop\\attachments\\Question 3\\sdxl_template.txt"));
            configReader = new BufferedReader(new FileReader("C:\\Users\\Shan\\Desktop\\attachments\\Question 3\\sdxl_prop.txt"));
            mergedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Shan\\Desktop\\attachments\\Question 3\\sdxl.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自然顺序替换集
     */
    private static String[] naturalOrderRep = new String[ARRAY_SIZE];

    /**
     * 索引顺序词条集
     */
    private static String[] indexOrderRep = new String[ARRAY_SIZE];

    /**
     * 字符顺序词条集
     */
    private static String[] charOrderRep = new String[ARRAY_SIZE];

    Task2(Reader templateReader, Reader configReader, Writer mergedWriter) throws IOException {
        this.templateReader = templateReader;
        this.configReader = configReader;
        this.mergedWriter = mergedWriter;
    }

    public static void main(String[] args) {
        execute();
    }

    public static Long execute() {
        try {
            initOrderRep();
            // 开始替换数据
            BufferedReader templateBuffReader = new BufferedReader(templateReader, READ_BUFF_SIZE);
            BufferedWriter mergedBuffWriter = new BufferedWriter(mergedWriter, WRITE_BUFF_SIZE);
            String s = templateBuffReader.readLine();
            while (s != null) {
                mergedBuffWriter.write(replaceWord(s));
                s = templateBuffReader.readLine();
                if (s != null)
                    mergedBuffWriter.write("\n");
            }
            mergedBuffWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                templateReader.close();
                configReader.close();
                mergedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 替换单词方法, 此方法假定每一行只有一个需要替换的词组
     *
     * @param originalSentence 待替换的原始句子
     * @return 替换完成的句子
     * @throws IOException
     */
    private static String replaceWord(String originalSentence) throws IOException {
        int dollar = originalSentence.indexOf('$');

        // 存在可替换字串
        if (dollar != -1) {
            int leftParenthese = -1;
            int rightParenthese = -1;
            // 获取左右括弧坐标
            for (int i = dollar; i < originalSentence.length(); i++) {
                if (originalSentence.charAt(i) == '(') {
                    leftParenthese = i;
                } else if (originalSentence.charAt(i) == ')') {
                    rightParenthese = i;
                    break;
                }
            }

            int numStart = leftParenthese + 1; // 编号开始位置
            int numEnd = rightParenthese - 1; // 编号结束位置
            int typeStart = dollar + 1; // 替换类型开始位置
            int typeEnd = leftParenthese - 1; // 替换类型结束位置
            int num = Integer.valueOf(originalSentence.substring(numStart, numEnd + 1)); // 替换单词编号
            String type = originalSentence.substring(typeStart, typeEnd + 1); // 替换单词类型
            String target = originalSentence.substring(dollar, rightParenthese + 1); // 待替换字符串

            if (type.equals(NATURAL_ORDER)) {
                originalSentence = originalSentence.replace(target, naturalOrderRep[num]);
            } else if (type.equals(INDEX_ORDER)) {
                originalSentence = originalSentence.replace(target, indexOrderRep[num]);
            } else if (type.equals(CHAR_ORDER)) {
                originalSentence = originalSentence.replace(target, charOrderRep[num]);
            } else {
                originalSentence = originalSentence.replace(target, charOrderRep[charOrderRep.length - num - 1]);
            }
        }

        return originalSentence;
    }

    /**
     * 准备替换数据
     *
     * @throws IOException
     */
    private static void initOrderRep() throws IOException {
        BufferedReader configBuffReader = new BufferedReader(configReader, READ_BUFF_SIZE);
        String s;
        int index = 0;
        while ((s = configBuffReader.readLine()) != null) {
            int tabIndex = s.indexOf('\t');
            String word = s.substring(tabIndex + 1, s.length());
            Integer wordIndex = Integer.parseInt(s.substring(0, tabIndex));
            naturalOrderRep[index] = word;
            indexOrderRep[wordIndex] = word;
            index++;
        }
        System.arraycopy(naturalOrderRep, 0, charOrderRep, 0, naturalOrderRep.length);
        Arrays.sort(charOrderRep);
    }
}