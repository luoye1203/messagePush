package com.xzst.relation.mp.dao;

import com.xzst.relation.mp.model.MessageDetail.MessageModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by LHT on 2018/7/6.
 */
@Component
public interface MessageDao {



	int insertMeassge2Database(MessageModel params);

	List<MessageModel> messageList();
}
