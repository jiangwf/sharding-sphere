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
     * @return a list of months between the specified time and the current time
     */
    public static List<String> getMonthListFromStartToNow(String startMonth){

        List<String> monthList = new ArrayList<>();

        if(StringUtils.isEmpty(startMonth)){
            log.info("ss扩展 分片规则开始月份为空");
            return monthList;
        }
        try {
            Date startMonthDate = new SimpleDateFormat("yyyyMM").parse(startMonth);
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startMonthDate);
            for (;calendar.getTime().before(now);){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
                monthList.add(simpleDateFormat.format(calendar.getTime()));
                calendar.add(Calendar.MONTH,1);
            }
        } catch (ParseException e) {
            log.error("ss扩展 分片规则开始月份格式化异常");
        }
        return monthList;
    }
}
