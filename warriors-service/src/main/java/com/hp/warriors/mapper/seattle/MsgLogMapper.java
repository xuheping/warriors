package com.hp.warriors.mapper.seattle;

import com.hp.warriors.entity.seattle.MsgLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MsgLogMapper {

    int insert(MsgLog msgLog);

    @Select("select * from tb_msg_log where status = 2")
    List<MsgLog> selectTimeoutMsg();

    @Update("update tb_msg_log set next_try_time = #{nextTryTime},try_count = try_count+1 where msg_id = #{msgId}")
    int updateTryCount(@Param("msgId") String msgId,@Param("nextTryTime") Date nextTryTime);

    @Update("update tb_msg_log set status = #{status} where msg_id = #{msgId}")
    int updateStatus(@Param("msgId")String msgId,@Param("status")Integer status);

}
