package cn.orditech.tool;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/9.
 */
public class ReadWordDocUtils {
    /**
     * �س���ASCII��
     */
    private static final short ENTER_ASCII = 13;

    /**
     * �ո��ASCII��
     */
    private static final short SPACE_ASCII = 32;

    /**
     * ˮƽ�Ʊ��ASCII��
     */
    private static final short TABULATION_ASCII = 9;

    public static int counter=0;
    public static int beginPosi=0;
    public static int endPosi=0;
    public static int beginArray[];
    public static int endArray[];
    public static String htmlTextArray[];
    public static boolean tblExist=false;

    public static final String inputFile="F:\\���߿���ϵͳ\\test2.doc";
    public static final String htmlFile="F:/abc.html";

    public static void main(String argv[])
    {
        try {
            getWordRows(new File(inputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡÿ��������ʽ
     *
     * @param file
     * @throws Exception
     */


    public static List<String> getWordRows(File file) throws Exception {
        FileInputStream in = new FileInputStream(file);
        HWPFDocument doc = new HWPFDocument(in);

        int num=100;

        beginArray=new int[num];
        endArray=new int[num];
        htmlTextArray=new String[num];

        // ȡ���ĵ����ַ�������
        int length = doc.characterLength();

        //���ÿһ�е�����
        List<String> rows = new ArrayList<String>();

        // ������ʱ�ַ���,�ü����ж�һ���ַ��Ƿ������ͬ��ʽ

        int cur=0;

        String tempString = "";
        for (int i = 0; i < length - 1; i++) {
            // ��ƪ���µ��ַ�ͨ��һ�����ַ������ж�,rangeΪ�õ��ĵ��ķ�Χ
            Range range = new Range(i, i + 1, doc);

            CharacterRun cr = range.getCharacterRun(0);


            Range range2 = new Range(i + 1, i + 2, doc);
            // �ڶ����ַ�
            CharacterRun cr2 = range2.getCharacterRun(0);
            char c = cr.text().charAt(0);

            // �ж��Ƿ�Ϊ�ո��
            if (c == SPACE_ASCII)
                tempString += " ";
                // �ж��Ƿ�Ϊˮƽ�Ʊ��
            else if (c == TABULATION_ASCII)
                tempString += "\t";
            // �Ƚ�ǰ��2���ַ��Ƿ������ͬ�ĸ�ʽ
            boolean flag = compareCharStyle(cr, cr2);
            if (flag&&c !=ENTER_ASCII)
                tempString += cr.text();
            else {
                //��c�ǻ��з������س�����ʱ��֤��һ�ж�ȡ��ϣ�tempString�б���ļ���һ������
                if(tempString !="" && !tempString.equals("END")){
                    rows.add(tempString);
                }
                tempString = "";
            }

        }

        return rows;
    }

    public static boolean compareCharStyle(CharacterRun cr1, CharacterRun cr2)
    {
        boolean flag = false;
        if (cr1.isBold() == cr2.isBold() && cr1.isItalic() == cr2.isItalic() && cr1.getFontName().equals(cr2.getFontName())
                && cr1.getFontSize() == cr2.getFontSize()&& cr1.getColor() == cr2.getColor())
        {
            flag = true;
        }
        return flag;
    }

    /*** ������ɫģ��start ********/
    public static int red(int c) {
        return c & 0XFF;
    }

    public static int green(int c) {
        return (c >> 8) & 0XFF;
    }

    public static int blue(int c) {
        return (c >> 16) & 0XFF;
    }

    public static int rgb(int c) {
        return (red(c) << 16) | (green(c) << 8) | blue(c);
    }

    public static String rgbToSix(String rgb) {
        int length = 6 - rgb.length();
        String str = "";
        while (length > 0) {
            str += "0";
            length--;
        }
        return str + rgb;
    }


    public static String getHexColor(int color) {
        color = color == -1 ? 0 : color;
        int rgb = rgb(color);
        return "#" + rgbToSix(Integer.toHexString(rgb));
    }
    /** ������ɫģ��end ******/

    //��ȡ����-��Ŀ�����Լ���Ŀ�ܼƷ���
    public static String numScore(String delHtml){

        String regEx="[^0-9+��|,+^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(delHtml);
        String s=m.replaceAll("").trim();
        if(StringUtils.isNotBlank(s)){
            if(s.contains(",")){
                return s;
            }else if(s.contains("��")){
                return s.replace("��", ",");
            }else{
                return "0,0";
            }
        }else{
            return "0,0";
        }

    }
    //��ȡÿС�����
    public static int itemNum(String delHtml){
        Pattern pattern = Pattern.compile("��(.*?)��"); //��������
        Matcher matcher = pattern.matcher(delHtml);
        if (matcher.find()&&isNumeric(matcher.group(1))){
            return Integer.parseInt(matcher.group(1));
        }else {
            return 0;
        }
    }
    //�ж�Str�Ƿ��� ����
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    //�ж�Str�Ƿ����С�����
    public static boolean isSmallTitle(String str){
        Pattern pattern = Pattern.compile("^([\\d]+[-\\��].*)");
        return pattern.matcher(str).matches();
    }
    //�ж�Str�Ƿ���ѡ����ѡ����
    public static boolean isSelecteTitele(String str){
        Pattern pattern = Pattern.compile("^([a-zA-Z]+[-\\��].*)");
        return pattern.matcher(str).matches();
    }
    //�ж�Str�Ƿ��Ǵ����
    public static boolean isBigTilete(String str){
        boolean iso= false ;
        if(str.contains("һ��")){
            iso=true;
        }else if(str.contains("����")){
            iso=true;
        }else if(str.contains("����")){
            iso=true;
        }else if(str.contains("�ġ�")){
            iso=true;
        }else if(str.contains("�塢")){
            iso=true;
        }else if(str.contains("����")){
            iso=true;
        }else if(str.contains("�ߡ�")){
            iso=true;
        }else if(str.contains("�ˡ�")){
            iso=true;
        }
        return iso;
    }
}
