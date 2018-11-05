package com.xzst.relation.mp.dao;

import com.xzst.relation.mp.model.MessageDetail.SaveMessageDetailParams;
import com.xzst.relation.mp.model.MessageDetail.WarningDetailBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by LHT on 2018/7/6.
 */
@Component
public interface WarningDetailDao {

	List<Map<String,String>> getConfigInfoByConfigIdAndResultSetId(@Param("configId") String configId,@Param("resultSetId") String resultSetId);

	List<String> getAllconfigIdByResultSetId(@Param("resultSetId")String resultSetId);

	int insertMeassge2Database(SaveMessageDetailParams params);

	WarningDetailBean getWarningDetail(@Param("id") String id);
}
