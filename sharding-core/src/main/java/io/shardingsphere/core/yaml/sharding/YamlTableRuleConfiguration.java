/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.yaml.sharding;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.keygen.KeyGeneratorFactory;
import io.shardingsphere.core.yaml.parser.DynamicType;
import io.shardingsphere.core.yaml.parser.TableRuleParser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Yaml table rule configuration.
 *
 * @author caohao
 * @author he.tian
 */
@Getter
@Setter
@Slf4j
public class YamlTableRuleConfiguration {
    
    private String logicTable;
    
    private String actualDataNodes;
    
    private YamlShardingStrategyConfiguration databaseStrategy;
    
    private YamlShardingStrategyConfiguration tableStrategy;
    
    private String keyGeneratorColumnName;
    
    private String keyGeneratorClassName;
    
    private String logicIndex;

    private boolean dynamic = false;

    private String dynamicType = DynamicType.MONTH.toString();

    private String tableShardingEnd;

    /**
     * Build table rule configuration.
     *
     * @return table rule configuration
     */
    public TableRuleConfiguration build() {
        Preconditions.checkNotNull(logicTable, "Logic table cannot be null.");
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable(logicTable);
        if(dynamic){
            //分片信息动态配置解析
            try {
                result.setActualDataNodes(TableRuleParser.getActualDataNodesParse(dynamicType,actualDataNodes,logicTable,tableShardingEnd));
                log.info("ss扩展 动态配置 actualDataNodes={}",actualDataNodes);
            } catch (Exception e) {
                log.error("ss扩展 动态配置 actualData解析异常",e);
                throw new IllegalArgumentException("ss扩展 actualData解析异常");
            }
        }else{
            result.setActualDataNodes(actualDataNodes);
        }
        if (null != databaseStrategy) {
            result.setDatabaseShardingStrategyConfig(databaseStrategy.build());
        }
        if (null != tableStrategy) {
            result.setTableShardingStrategyConfig(tableStrategy.build());
        }
        if (!Strings.isNullOrEmpty(keyGeneratorClassName)) {
            result.setKeyGenerator(KeyGeneratorFactory.newInstance(keyGeneratorClassName));
        }
        result.setKeyGeneratorColumnName(keyGeneratorColumnName);
        result.setLogicIndex(logicIndex);
        return result;
    }
}
