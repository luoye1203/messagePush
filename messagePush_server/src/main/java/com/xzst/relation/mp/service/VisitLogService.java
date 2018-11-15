package com.xzst.relation.mp.service;

import com.xzst.relation.mp.dao.VisitLogDao;
import com.xzst.relation.mp.model.VisitLogModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitLogService {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private VisitLogDao visitLogDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertVistLog(VisitLogModel visitLogModel){
        visitLogDao.insertVistLog(visitLogModel);

//        throw new RuntimeException("测试");
    }

}
