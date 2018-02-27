package com.wiscess.simpleutil.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuBo
 * 2018/2/27.
 */
public class FormatUtil {

    public static String formatDate2Str(Object date){
        return formatDate2Str(date,"yyyy-MM-dd");
    }

    public static String formatDate2Str(Object date,String format){
        if (date==null||!(date instanceof Date)) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    public static Date parseStr2Date(Object dateStr){
        return parseStr2Date(dateStr,"yyyy-MM-dd");
    }

    public static Date parseStr2Date(Object dateStr,String format){
        if (StringUtil.isEmpty(dateStr)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatNumber2MoneyStr(Object number){
        if (StringUtil.isEmpty(number)) return "";
        try {
            Double d = Double.valueOf(number.toString());
            BigDecimal df   = new BigDecimal(d);
            Double r = df.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            return r%1==0?(r.intValue()+""):(r+"");
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double formatNumber2Money(Object number){
        if (StringUtil.isEmpty(number)) return 0.0;
        return Double.valueOf(formatNumber2MoneyStr(number));
    }

    public static String formatMoneyThousandSeparator(Object number){
        DecimalFormat df = new DecimalFormat("#,##0.##");
        return df.format(formatNumber2Money(number));
    }

    public static void main(String[] args) {
        System.out.println(formatNumber2ChineseMoney(000.000));
    }

    public static String formatNumber2ChineseMoney(Object number){
        BigDecimal money = new BigDecimal(formatNumber2Money(number));
        String result = "";
        switch (money.signum()){
            case 0:
                result = "零元整";
                break;
            case 1:
                result = translateMoney(money);
                break;
            case -1:
                result = "负"+translateMoney(money);
                break;
        }
        return result;
    }

    private static final String[] upNumber = {"零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖"};
    private static final String[] upNumberUnit = {"分", "角", "元",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟" };
    private static final String numberFULL = "整";
    private static String  translateMoney(BigDecimal money){
        StringBuffer sb = new StringBuffer();
        long totalNumber = money.movePointRight(2).setScale(0,BigDecimal.ROUND_HALF_UP).abs().longValue();
        Long decimalFraction = totalNumber%100;
        int unit = 0;
        int idx = 0;
        boolean zero = false;
        if (!(decimalFraction > 0 )){
            idx =2;
            totalNumber = totalNumber/100;
            zero = true;
        }
        if ((decimalFraction > 0 )&&(!(decimalFraction % 10 > 0))){
            idx = 1;
            totalNumber = totalNumber/10;
            zero = true;
        }
        int zeroSize = 0;
        while (true){
            if (totalNumber<=0){
                break;
            }
            unit =(int)(totalNumber % 10);
            if (unit > 0){
                if ((idx == 9) && (zeroSize >= 3)) {
                    sb.insert(0, upNumberUnit[6]);
                }
                if ((idx == 13) && (zeroSize >= 3)) {
                    sb.insert(0, upNumberUnit[10]);
                }
                sb.insert(0, upNumberUnit[idx]);
                sb.insert(0, upNumber[unit]);
                zero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(zero)) {
                    sb.insert(0, upNumber[unit]);
                }
                if (idx == 2) {
                    if (totalNumber > 0) {
                        sb.insert(0, upNumberUnit[idx]);
                    }
                } else if (((idx - 2) % 4 == 0) && (totalNumber % 1000 > 0)) {
                    sb.insert(0, upNumberUnit[idx]);
                }
                zero = true;
            }
            // 让number每次都去掉最后一个数
            totalNumber = totalNumber / 10;
            ++idx;
        }
        if (!(decimalFraction > 0)) {
            sb.append(numberFULL);
        }

        return sb.toString();
    }



    public static String formatStartDate(Object date){
        if (StringUtil.isEmpty(date)) return "";
        String dateStr = date.toString();
        if (date instanceof Date){
            dateStr = formatDate2Str(date);
        }
        return dateStr+" 00:00:00";
    }

    public static String formatEndDate(Object date){
        if (StringUtil.isEmpty(date)) return "";
        String dateStr = date.toString();
        if (date instanceof Date){
            dateStr = formatDate2Str(date);
        }
        return dateStr+" 23:59:59";
    }



}
