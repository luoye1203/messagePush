package com.xzst.relation.mp.dao;

import com.xzst.relation.mp.model.VisitLogModel;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitLogDao {
    void insertVistLog(VisitLogModel visitLogModel);
}
