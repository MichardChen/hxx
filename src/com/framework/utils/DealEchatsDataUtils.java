package com.framework.utils;

import com.alibaba.fastjson.JSONArray;
import com.framework.vo.report.EchartSeries;
import com.framework.vo.report.OrderReportVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理数据返回给前端Echarts显示
 * @author Chenwx
 * @date
 */
public class DealEchatsDataUtils {

    /**
     * 处理查询的报表数据返回给前端 - 处理只有一条线（类型）的数据
     * @param result 最终结果map
     * @param key 前端报表的取值key
     * @param queryList 查询到的结果集
     */
    public static void dealUniqueLineReportResult(Map<String, Object> result, String key, List<OrderReportVo> queryList){
        result.put(key, null);
        if(queryList != null && queryList.size()>0){
            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<String> valueList = new ArrayList<>();
            for(OrderReportVo item: queryList){
                nameList.add(StringUtil.isBlank(item.getName())?item.getCode():item.getName());
                valueList.add(StringUtil.isBlank(item.getValue())?null:item.getValue());
            }
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("xAxis", nameList);
            queryMap.put("series", valueList);
            result.put(key, queryMap);
        }
    }

    /**
     * 处理查询的报表数据返回给前端 - 处理返回结果中有多条线（多种类型）的数据
     * @param result 最终结果map
     * @param key 前端报表的取值key
     * @param queryList 查询到的结果集
     */
    public static void dealManyLinesReportResult(Map<String, Object> result, String key, List<OrderReportVo> queryList){
        result.put(key, null);
        if(queryList != null && queryList.size()>0){
            ArrayList<String> nameList = new ArrayList<>();//订单类型名称集合
            ArrayList<String> timeList = new ArrayList<>();//订单时间集合
            Map<String, List<String>> valueMap = new HashMap<>();//订单名称与时间点下的数量
            for(OrderReportVo item: queryList){
                //时间
                if(!timeList.contains(item.getTime())){
                    timeList.add(item.getTime());
                }
                //订单类型
                if(!nameList.contains(item.getName())){
                    nameList.add(item.getName());
                }
                //收集每个类别的数量
                if(valueMap.containsKey(item.getName())){
                    List<String> valueTemp = valueMap.get(item.getName());
                    valueTemp.add(item.getValue());
                    valueMap.put(item.getName(), valueTemp);
                } else {
                    List<String> valueTemp = new ArrayList<>();
                    valueTemp.add(item.getValue());
                    valueMap.put(item.getName(), valueTemp);
                }
            }
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("xAxis", timeList);
            queryMap.put("yAxis", nameList);
            List<EchartSeries> series = new ArrayList<>();
            for(String keySet: valueMap.keySet()){
                List<String> tempList = valueMap.get(keySet);
                String[] strings = new String[tempList.size()];
                tempList.toArray(strings);
                EchartSeries item = new EchartSeries(keySet, "line", strings);
                series.add(item);
            }
            queryMap.put("series", JSONArray.toJSON(series));
            result.put(key, queryMap);
        }
    }
}
