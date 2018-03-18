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
     * 回车符ASCII码
     */
    private static final short ENTER_ASCII = 13;

    /**
     * 空格符ASCII码
     */
    private static final short SPACE_ASCII = 32;

    /**
     * 水平制表符ASCII码
     */
    private static final short TABULATION_ASCII = 9;

    public static int counter=0;
    public static int beginPosi=0;
    public static int endPosi=0;
    public static int beginArray[];
    public static int endArray[];
    public static String htmlTextArray[];
    public static boolean tblExist=false;

    public static final String inputFile="F:\\在线考试系统\\test2.doc";
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
     * 读取每个文字样式
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

        // 取得文档中字符的总数
        int length = doc.characterLength();

        //存放每一行的内容
        List<String> rows = new ArrayList<String>();

        // 创建临时字符串,好加以判断一串字符是否存在相同格式

        int cur=0;

        String tempString = "";
        for (int i = 0; i < length - 1; i++) {
            // 整篇文章的字符通过一个个字符的来判断,range为得到文档的范围
            Range range = new Range(i, i + 1, doc);

            CharacterRun cr = range.getCharacterRun(0);


            Range range2 = new Range(i + 1, i + 2, doc);
            // 第二个字符
            CharacterRun cr2 = range2.getCharacterRun(0);
            char c = cr.text().charAt(0);

            // 判断是否为空格符
            if (c == SPACE_ASCII)
                tempString += " ";
                // 判断是否为水平制表符
            else if (c == TABULATION_ASCII)
                tempString += "\t";
            // 比较前后2个字符是否具有相同的格式
            boolean flag = compareCharStyle(cr, cr2);
            if (flag&&c !=ENTER_ASCII)
                tempString += cr.text();
            else {
                //当c是换行符即“回车符”时，证明一行读取完毕，tempString中保存的既是一行内容
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

    /*** 字体颜色模块start ********/
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
    /** 字体颜色模块end ******/

    //获取大题-题目数量以及题目总计分数
    public static String numScore(String delHtml){

        String regEx="[^0-9+，|,+^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(delHtml);
        String s=m.replaceAll("").trim();
        if(StringUtils.isNotBlank(s)){
            if(s.contains(",")){
                return s;
            }else if(s.contains("，")){
                return s.replace("，", ",");
            }else{
                return "0,0";
            }
        }else{
            return "0,0";
        }

    }
    //获取每小题分数
    public static int itemNum(String delHtml){
        Pattern pattern = Pattern.compile("（(.*?)）"); //中文括号
        Matcher matcher = pattern.matcher(delHtml);
        if (matcher.find()&&isNumeric(matcher.group(1))){
            return Integer.parseInt(matcher.group(1));
        }else {
            return 0;
        }
    }
    //判断Str是否是 数字
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    //判断Str是否存在小标题号
    public static boolean isSmallTitle(String str){
        Pattern pattern = Pattern.compile("^([\\d]+[-\\、].*)");
        return pattern.matcher(str).matches();
    }
    //判断Str是否是选择题选择项
    public static boolean isSelecteTitele(String str){
        Pattern pattern = Pattern.compile("^([a-zA-Z]+[-\\：].*)");
        return pattern.matcher(str).matches();
    }
    //判断Str是否是大标题
    public static boolean isBigTilete(String str){
        boolean iso= false ;
        if(str.contains("一、")){
            iso=true;
        }else if(str.contains("二、")){
            iso=true;
        }else if(str.contains("三、")){
            iso=true;
        }else if(str.contains("四、")){
            iso=true;
        }else if(str.contains("五、")){
            iso=true;
        }else if(str.contains("六、")){
            iso=true;
        }else if(str.contains("七、")){
            iso=true;
        }else if(str.contains("八、")){
            iso=true;
        }
        return iso;
    }
}
