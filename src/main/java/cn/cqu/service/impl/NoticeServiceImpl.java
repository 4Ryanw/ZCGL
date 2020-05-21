package cn.cqu.service.impl;

import cn.cqu.dao.NoticeDao;
import cn.cqu.pojo.Notice;
import cn.cqu.service.NoticeService;
import cn.cqu.util.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeDao noticeDao ;

    /**
     * 查询所有公告接口
     *
     * @return
     */
    @Override
    public List<Notice> listAll() {
        return noticeDao.listAllNotice();
    }

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    @Override
    public Notice getNoticeById(String id) {
        return noticeDao.getNoticeById(id);
    }

    /**
     * 新增公告
     *
     * @param notice
     * @return
     */
    @Override
    @MyLog(actionName = "新增公告")
    public int addNotice(Notice notice) {
        notice.setNoticeDate(new Date());
        return noticeDao.insertNotice(notice);
    }

    /**
     * 按id删除公告
     *
     * @param id
     * @return
     */
    @Override
    @MyLog(actionName = "删除公告")
    public int deleteNoticeById(String id) {
        return noticeDao.deleteNoticeById(id);
    }
}
