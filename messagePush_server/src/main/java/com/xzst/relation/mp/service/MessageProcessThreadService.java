package com.xzst.relation.mp.service;


import com.alibaba.fastjson.JSON;
import com.xzst.relation.mp.model.MessageDetail.SaveMessageDetailParams;
import com.xzst.relation.mp.model.MessageDetail.WarningDetailBean;
import com.xzst.relation.mp.model.webSocket.ConsumerMessageBean;
import com.xzst.relation.mp.util.DateUtils;
import com.xzst.relation.mp.util.JSONTools;
import com.xzst.relation.mp.util.Str2DateFormatUtils;
import com.xzst.relation.mp.webSocket.WebSocket;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LHT on 2018/7/5.
 */
@Service
public class MessageProcessThreadService {
    private static Logger LOG = Logger.getLogger(MessageProcessThreadService.class);

    @Autowired
    private WarningDetailService warningDetailService;


    //采用异步多线程
    @Async
    public void processData(ConsumerMessageBean consumerMessageBean) {

        /**
         * 异步线程是否启用测试代码
         * 测试通过注释掉
         */
//		try {
//			int random=((int)(Math.random()*10))*1000;
//			LOG.info(random);
//			Thread.sleep(random);
//		} catch (InterruptedException e) {
//			LOG.error(e.getMessage());
//		}
//		LOG.info(consumerMessageBean);
        //消息处理 获取配置的字段信息
        //先获取配置的报警字段

        List<Map<String, String>> congfigColInfoListMap = null;
        StringBuffer processedMessageBuffer = new StringBuffer("");
        String warningAddress = null;
        String lat = null;
        String lon = null;
        String warningTime = null;
        String targetId = null;
        try {
            //获取所有的关于此消息结果集id的配置ID
            List<String> configIds = warningDetailService.getAllconfigIdByResultSetId(consumerMessageBean.getResultSetId());
            //每个配置生成一条消息(只能取到一个configId 根据后来的业务逻辑)
            if (configIds != null && configIds.size() > 0) {
                for (int i = 0; i < configIds.size(); i++) {
                    String configId = configIds.get(i);
                    congfigColInfoListMap = warningDetailService.getConfigInfoByConfigIdAndResultSetId(configId, consumerMessageBean.getResultSetId());
                    Map<String, String> processedMessageMap = new LinkedHashMap<>();


                    if (congfigColInfoListMap != null && congfigColInfoListMap.size() > 0) {
                        for (Map<String, String> colMap : congfigColInfoListMap) {
                            //获取配置的字段英文名
                            String eColName = colMap.get("ENAME");
                            //获取配置的字段中文名
                            String cColName = colMap.get("CNAME");
                            //获取列的页面英文别名 中文别名 关注 地址 纬度 经度 三个字段
                            String pageByCName = colMap.get("PAGEBYNAME");
                            String pageByEName = colMap.get("PAGEBYENAME");
                            targetId = colMap.get("TARGETID");


                            if (StringUtils.isNoneBlank(eColName) && StringUtils.isNoneBlank(cColName)) {
                                //获取推送过来的对应的字段值
                                String colValue = consumerMessageBean.getDataMap().get(eColName);
                                processedMessageMap.put(pageByCName, colValue);


                                processedMessageBuffer.append(pageByCName + ":" + colValue + ",");
                                //地址 经纬度 处理
                                if (pageByEName.equals("address")) {
                                    warningAddress = colValue;
                                }
                                if (pageByEName.equals("lat")) {
                                    lat = colValue;
                                }
                                if (pageByEName.equals("lon")) {
                                    lon = colValue;
                                }
                                if (pageByEName.equals("time")) {
                                    warningTime = Str2DateFormatUtils.FormatDate(colValue, "yyyyMMddHHmmssSSS");
                                    if (null == warningTime || "".equals(warningTime)) {
                                        throw new Exception("原消息为:时间格式不正确,请确认....");
                                    }
                                    LOG.info(warningTime);
                                }

                            }

                        }

                    } else {
                        throw new Exception("原消息为:" + consumerMessageBean + " 查不到配置信息,请查证. ..");
                    }

                    if (processedMessageBuffer.toString().length() < 1) {
                        throw new Exception("原消息为:" + consumerMessageBean + " 通过配置信息处理后为空,请查证...");
                    }


                    String processedMessage = processedMessageBuffer.substring(0, processedMessageBuffer.length() - 1).toString();
                    String processedMeassgeMapStr = JSON.toJSONString(processedMessageMap);

                    String messageId = UUID.randomUUID().toString().replace("-", "");
                    SaveMessageDetailParams params = new SaveMessageDetailParams();
                    params.setId(messageId);
                    params.setCofnigId(configId);
                    params.setResultId(consumerMessageBean.getResultSetId());
                    params.setOriginalMessage(JSONTools.javabean2Json(consumerMessageBean));
                    params.setProcessedMessage(processedMessage);
                    params.setProcessedMessageMap(processedMeassgeMapStr);
                    params.setWarningAddress(warningAddress);
                    params.setLat(lat);
                    params.setLon(lon);
                    params.setWarningTime(warningTime);
                    params.setTargetId(targetId);

                    /**
                     *
                     * 正式环境注释掉
                     */
//					Date oDate=DateUtils.randomDate("2018-01-01","2018-08-01");
//					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					String dateString=formatter.format(oDate);
//					params.setCreate_time(formatter.parse(dateString));


                    try {
                        warningDetailService.messageDetail2Database(params);
                    } catch (Exception e) {
                        throw new Exception("原消息为:" + consumerMessageBean + "消息入库出错: " + e.getMessage());
                    }
                    //入库后获取此条消息
                    WarningDetailBean warningDetailBean = null;
                    warningDetailBean = warningDetailService.getWarningDetail(messageId);
                    if (null == warningDetailBean) {
                        throw new Exception("原消息为:" + consumerMessageBean + "消息入库后没获取到: ");
                    }
                    processedMeassgeMapStr =warningDetailBean.getProcessedMessageMapStr();
                    warningDetailBean.setProcessedMessageMapStr("归零");
                    warningDetailBean.setProcessedMessageMap(JSONTools.json2Map(processedMeassgeMapStr));
                    //消息入库，然后获取推送给前台
                    WebSocket.sendInfoToAllSession(JSONTools.javabean2Json(warningDetailBean));
                }
            } else {
                LOG.info("原消息:" + consumerMessageBean + " 没有对应的报警配置信息,摒弃....");
            }

        } catch (Exception e) {
//			LOG.error("处理消息出错,请查证...");
            LOG.error(e.getMessage());
        }

    }

}
