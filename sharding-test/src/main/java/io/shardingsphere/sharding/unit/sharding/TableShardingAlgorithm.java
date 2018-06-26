package io.shardingsphere.sharding.unit.sharding;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.sharding.unit.util.UnitException;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * 功能说明：实现数据表分片策略
 *
 * @author:weifeng.jiang
 * @DATE:2017/7/27 @TIME:18:15
 */
public class TableShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        for(String tableName:availableTargetNames){
            if(tableName.endsWith(new SimpleDateFormat("yyyyMM").format(shardingValue.getValue()))){
                return tableName;
            }
        }
        throw new UnitException("数据表分片异常，分片值=  "+new SimpleDateFormat("yyyyMM").format(shardingValue.getValue()));
    }
}
