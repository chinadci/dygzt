package software.dygzt;

import software.dygzt.util.DateUtil;

import java.sql.Date;

/**
 * Created by Pzy on 12/8/16.
 */
public class Demo {

    public static void main(String[] args){
        String strDate = "2016-12-11";
        Date date = Date.valueOf(strDate);
        System.out.println(date);



        strDate = DateUtil.format(DateUtil.addYears((DateUtil.parse(strDate,DateUtil.webFormat)),-11),DateUtil.webFormat);
//      DateUtil.addYears((DateUtil.parse(strDate,DateUtil.webFormat)),1);


        System.out.println(strDate);



    }
}
