package cn.cqu.service;

import cn.cqu.pojo.Notice;

import java.util.List;

/**
 * 信息公告service接口
 */
public interface NoticeService {

    /**
     * 查询所有公告接口
     * @return
     */
    List<Notice> listAll();

    /**
     * 根据id查询公告
     * @param id
     * @return
     */
    Notice getNoticeById(String id);

    /**
     * 新增公告
     * @param notice
     * @return
     */
    int addNotice(Notice notice);

    /**
     * 按id删除公告
     * @param id
     * @return
     */
    int deleteNoticeById(String id);

}
