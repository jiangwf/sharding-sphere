package io.shardingsphere.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * date Utility class
 *
 * @author: he.tian
 */
@Slf4j
public abstract class DateUtil {

    /**
     * a list of months between the specified time and the current time
     * @param startMonth start month
     * @param endMonth end month
     * @return a list of months between the specified time and the current time
     */
    public static List<String> getMonthListFromStartToNow(String startMonth,String endMonth){

        List<String> monthList = new ArrayList<>();

        if(StringUtils.isEmpty(startMonth) || StringUtils.isEmpty(endMonth)){
            log.info("ss扩展 分片规则开始月份为空");
            return monthList;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
            Date startMonthDate = dateFormat.parse(startMonth);
            Date endMonthDate = dateFormat.parse(endMonth);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startMonthDate);
            for (;calendar.getTime().before(endMonthDate);){
                monthList.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.MONTH,1);
            }
            monthList.add(dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            log.error("ss扩展 分片规则开始月份格式化异常");
        }
        return monthList;
    }
}
