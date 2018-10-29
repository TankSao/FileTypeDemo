package com.example.administrator.filetypedemo;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/10/29.
 */

public class FileType {
    public static final String PICTURE = "PICTURE";//图片
    public static final String AUDIO = "AUDIO";//音频
    public static final String VIDEO = "VIDEO";//视频
    public static final String TXT = "TXT";//文本
    public static final String WORD = "WORD";
    public static final String PPT = "PPT";
    public static final String PDF = "PDF";
    public static final String EXCEL = "EXCEL";
    public static final String DEFAULTTYPE = "DEFAULT";
    //根据文件名判断文件类型
    public static String getFileType(String file) {
        String type = DEFAULTTYPE;
        String suffix = getSuffix(file).toUpperCase();
        switch (suffix){
            case "PNG":
            case "JPG":
            case "BMP":
            case "JPEG":
            case "GIF":
                type = PICTURE;
                break;
            case "MP3":
            case "WMA":
                type = AUDIO;
                break;
            case "WMV":
            case "AVI":
            case "FLV":
            case "MP4":
                type = VIDEO;
                break;
            case "TXT":
                type = TXT;
                break;
            case "DOC":
            case "DOCX":
                type = WORD;
                break;
            case "PPT":
            case "PPTX":
                type = PPT;
                break;
            case "PDF":
                type = PDF;
                break;
            case "XLS":
            case "XLSX":
                type = EXCEL;
                break;
            default:
                type = DEFAULTTYPE;
                break;
        }
        return type;
    }
    //获取文件后缀名
    private static String getSuffix(String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }
    //文件大小格式化
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return (int)size+"bit";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
