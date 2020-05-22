package cn.cqu.controller;
/**
 * 信息公告控制器
 * */
import cn.cqu.pojo.Notice;
import cn.cqu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    /**
     * 查看公告详情
     * @param id
     * @param map
     * @return
     */
    @GetMapping("/{id}")
    public String readContent(@PathVariable("id") String id,ModelMap map){
        Notice notice = noticeService.getNoticeById(id);
        map.put("notice",notice);
        return "noticeContent";
    }

    /**
     * 查询所有公告
     * @param map
     * @return
     /*/
    @GetMapping("/list")
    public String listAllNotice(ModelMap map){
        List<Notice> noticeList = noticeService.listAll();
        map.addAttribute("noticeList",noticeList);
        return "notice::table-refresh";
    }


    /**
     * 新增公告
     * @param notice
     * @return
     */
    @PostMapping
    public String addNotice(Notice notice){
       noticeService.addNotice(notice);
        return "notice";
    }


    /**
     * 按id删除公告
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public int deleteNotice(@PathVariable("id")String id){
        return noticeService.deleteNoticeById(id);
    }

}
