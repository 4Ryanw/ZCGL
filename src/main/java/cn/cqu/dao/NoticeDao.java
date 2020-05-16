package cn.cqu.dao;

import cn.cqu.pojo.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 信息公告Dao类
 */
@Repository
public interface NoticeDao {

 /**
  * 查询所有公告
  * @return
  */
 @Select("select * from t_notice")
  List<Notice> listAllNotice();

 /**
  * 按id查询信息公告
  * @param noticeId
  * @return
  */
 @Select("select * from t_notice where notice_id = #{noticeId}")
 Notice getNoticeById(String noticeId);

 /**
  * 按id删除信息公告
  * @param noticeId
  * @return
  */
 @Delete("delete from t_notice where notice_id = #{noticeId}")
 int deleteNoticeById(String noticeId);


 /**
  * 插入信息公告
  * @param notice
  * @return
  */
 @Insert("insert into t_notice values (UUID(),#{noticeTitle},#{noticeContent},#{noticeFile},#{noticeDate})")
 int insertNotice(Notice notice);
}
