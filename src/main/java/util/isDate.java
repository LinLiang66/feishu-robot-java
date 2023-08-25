/**
 *
 * @ 获取 日期-->可以调用
 *
 *
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class isDate {
    public  String getCurrentYears(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }
}
