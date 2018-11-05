package com.xzst.relation.mp.service;

import com.xzst.relation.mp.dao.WarningDetailDao;
import com.xzst.relation.mp.model.MessageDetail.SaveMessageDetailParams;
import com.xzst.relation.mp.model.MessageDetail.WarningDetailBean;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by LHT on 2018/7/6.
 */
@Service
public class WarningDetailService {
	private static  final Logger log=Logger.getLogger(WarningDetailService.class);

	@Autowired
	private WarningDetailDao warningDetailDao;


	public List<Map<String,String>> getConfigInfoByConfigIdAndResultSetId(String configId,String resultSetId){

		return warningDetailDao.getConfigInfoByConfigIdAndResultSetId(configId,resultSetId);
	}


	public List<String> getAllconfigIdByResultSetId(String resultSetId){


		return warningDetailDao.getAllconfigIdByResultSetId(resultSetId);
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,RuntimeException.class})
	public void messageDetail2Database(SaveMessageDetailParams params){
		warningDetailDao.insertMeassge2Database(params);
	}


	public WarningDetailBean getWarningDetail(String id){

		return warningDetailDao.getWarningDetail(id);
	}


}
