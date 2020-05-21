package cn.cqu.dao;

import cn.cqu.pojo.SystemLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 系统日志Dao类
 */
@Repository
public interface SystemLogDao {

 /**
  * 根据时间查询日志
  * @return
  */
  List<SystemLog> listLogByDateStr(@Param("startStr") String startStr, @Param("endStr") String endStr);

 /**
  * 按id查询日志
  * @param LogId
  * @return
  */
 @Select("select * from t_log where logId = #{LogId}")
 SystemLog getLogById(String LogId);

 /**
  * 按id删除日志
  * @param LogId
  * @return
  */
 @Delete("delete from t_log where logId = #{LogId}")
 int deleteLogById(String LogId);


 /**
  * 插入日志
  * @param Log
  * @return
  */
 @Insert("insert into t_log values (UUID(),#{userNo},#{requestPath},#{remoteAddr},#{discription},#{startTime},#{executionTimeMillis},#{inputParam},#{outputParam})")
 int insertLog(SystemLog Log);
}
