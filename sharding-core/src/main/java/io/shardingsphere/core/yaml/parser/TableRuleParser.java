package io.shardingsphere.core.yaml.parser;

import com.google.common.collect.Sets;
import io.shardingsphere.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * parsers by month rule
 *
 * @author: he.tian
 */
@Slf4j
public class TableRuleParser {

    /**
     * the set of execution cell nodes after parsing
     * @param dynamicType dynamic type
     * @param actualDataNodes the actual execution of the cell node list before the rule is parsed
     * @return the set of execution cell nodes after parsing
     */
    public static String getActualDataNodesParse(String dynamicType,String actualDataNodes){
        if(DynamicType.MONTH.toString().equals(dynamicType)){
            if(StringUtils.isEmpty(actualDataNodes) || StringUtils.isEmpty(dynamicType)){
                throw new IllegalArgumentException("ss扩展 动态配置 表规则配置是否动态flag为空或actualDataNodes为空");
            }
        }

        Map<String,String> actualDataNodeKeyMap = new HashMap<>();
        Map<String,String> actualDataNodeValueMap  = new HashMap<>();

        String[] actualDataNodeArr = actualDataNodes.split(",");
        for (String actualDataNode : actualDataNodeArr) {
            String[] actualDataNodeBuildBeforeArr = actualDataNode.split("\\|");
            actualDataNodeKeyMap.put(actualDataNode,actualDataNodeBuildBeforeArr[0]);
            actualDataNodeValueMap.put(actualDataNode,actualDataNodeBuildBeforeArr[1]);
        }

        return getActualDataNodeBeforeList(actualDataNodeKeyMap,actualDataNodeValueMap);
    }

    /**
     * the set of execution cell nodes after parsing
     * @param actualDataNodeKeyMap actually execute the unit node key collection
     * @param actualDataNodeValueMap actually execute the unit node value collection
     * @return the set of execution cell nodes after parsing
     */
    private static String getActualDataNodeBeforeList(Map<String,String> actualDataNodeKeyMap, Map<String,String> actualDataNodeValueMap) {
        Map<String,List<String>> dataNodeKeyMap = new HashMap<>();
        Map<String,List<String>> dataNodeValueMap = new HashMap<>();

        for (String key : actualDataNodeKeyMap.keySet()) {
            dataNodeKeyMap.put(key, Arrays.asList(actualDataNodeKeyMap.get(key)));
        }

        for (String key : actualDataNodeValueMap.keySet()) {
            dataNodeValueMap.put(key, DateUtil.getMonthListFromStartToNow(actualDataNodeValueMap.get(key)));
        }
        return getActualDataNodeList(dataNodeKeyMap,dataNodeValueMap);
    }

    /**
     * the set of execution cell nodes after parsing
     * @param dataNodeKeyMap actually execute the unit node key collection
     * @param dataNodeValueMap actually execute the unit node value collection
     * @return the set of execution cell nodes after parsing
     */
    private static String getActualDataNodeList(Map<String, List<String>> dataNodeKeyMap, Map<String, List<String>> dataNodeValueMap) {
        StringBuilder builder = new StringBuilder();
        for (String key : dataNodeKeyMap.keySet()) {
            Set<List<String>> cartesianDataNodeList = Sets.cartesianProduct(Sets.newHashSet(dataNodeKeyMap.get(key)), Sets.newHashSet(dataNodeValueMap.get(key)));
            for (List<String> dataNodeList : cartesianDataNodeList) {
                builder.append(dataNodeList.get(0)).append(dataNodeList.get(1)).append(",");
            }
        }
        return builder.substring(0,builder.length()-1).toString();
    }

}
