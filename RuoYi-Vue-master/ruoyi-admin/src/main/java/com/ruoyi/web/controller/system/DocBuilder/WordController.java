package com.ruoyi.web.controller.system.DocBuilder;

import com.deepoove.poi.data.PictureRenderData;
import com.ruoyi.common.utils.poi.WordUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth/exportWord/")
@RestController
public class WordController {

    /**
     * 用户信息导出word --- poi-tl
     * @throws IOException
     */
    @RequestMapping("/exportUserWord")
    public void exportUserWord(HttpServletRequest request, HttpServletResponse response) throws IOException{

//		response.setContentType("application/vnd.ms-excel");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" +new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".docx");
        Map<String, Object> params = new HashMap<>();
        // 渲染文本
        params.put("name", "张三");
        params.put("position", "开发工程师");
        params.put("entry_time", "2020-07-30");
        params.put("province", "江苏省");
        params.put("city", "南京市");
        // 渲染图片
        params.put("picture", new PictureRenderData(120, 120, "D:\\cssTest\\square.png"));
        // TODO 渲染其他类型的数据请参考官方文档

        //word模板地址放在src/main/webapp/下
        //表示到项目的根目录（webapp）下，要是想到目录下的子文件夹，修改"/"即可
        String path = request.getSession().getServletContext().getRealPath("/");
        String templatePath = path+"template/user1.docx";
        WordUtil.downloadWord(response.getOutputStream(), templatePath, params);
    }

}
