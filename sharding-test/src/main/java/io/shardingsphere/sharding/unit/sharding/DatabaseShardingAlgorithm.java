package io.shardingsphere.sharding.unit.sharding;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.sharding.unit.util.UnitException;

import java.util.Collection;

/**
 * 功能说明：实现数据库分片策略
 *
 * @author:weifeng.jiang
 * @DATE:2017/7/27 @TIME:18:15
 */
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        for(String databaseName:availableTargetNames){
            if(databaseName.endsWith(shardingValue.getValue().replace("-","_"))){
                return databaseName;
            }
        }
        throw new UnitException("数据库分片异常，分片字段值= "+shardingValue.getValue());
    }
}
