package com.ruiliang.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruiliang.management.pojo.UserVerifyLog;


@Mapper
public interface UserVerifyLogDAO {

	/**
	 * 保存短信日志
	 * @param userVerifyLog
	 * @return
	 */
	Long create(UserVerifyLog userVerifyLog);

	int updateUsedFlag(@Param("id") Long id, @Param("useFlag") int useFlag);

	UserVerifyLog findLastByDest(@Param("dest") String dest, @Param("type") int type,
			@Param("verifyType") int verifyType);

	List<UserVerifyLog> loadSmsSendLogByDate(@Param("date") String date,@Param("dest") String dest);
}
