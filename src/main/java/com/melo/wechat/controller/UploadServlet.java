package com.melo.wechat.controller;



import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/**
 * @Description: 文件上传Servlet工具类
 * @author: Jun
 * @date: 15:52 2021/5/24
 */
@WebServlet("/upload")
public class UploadServlet extends BaseServlet {

    /**
     * @Description: 上传图片
     * @param request
     * @param response
     * @date: 11:50 2021/5/25
     * @return: void
     */
    public void uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);

        //设置缓冲区大小与临时文件目录
        factory.setSizeThreshold(1024*1024*10);

        //设置单个文件大小限制
        upload.setFileSizeMax(1024*1024*10);
        //设置所有文件总和大小限制
        upload.setSizeMax(1024*1024*30);

        String path = request.getParameter("path");
        //上传到相应文件夹
        String uploadPath=request.getSession().getServletContext().getRealPath("/")+path;
        File file=new File(uploadPath);
        file.mkdirs();

        //若上传的文件夹不为临时文件夹,则在确定最终上传前先删除临时文件夹
            if(!path.contains("Temp")){
                File tempFile=new File(request.getSession().getServletContext().getRealPath("/")+path+"Temp");
                FileUtils.deleteDirectory(tempFile);
            }

        try {
            List<FileItem> list=upload.parseRequest(request);
            for (FileItem fileItem:list){
                if (!fileItem.isFormField()&&fileItem.getName()!=null&&!"".equals(fileItem.getName())){
                    String filName=fileItem.getName();
                    //利用UUID生成伪随机字符串，作为文件名避免重复
                    String uuid= UUID.randomUUID().toString();
                    //获取文件后缀名
                    String suffix=filName.substring(filName.lastIndexOf("."));
                    file=new File(uploadPath);
                    file.mkdirs();
                    //判断前台有没有要给文件命名,没有则使用uuid生成的文件名
                    String name=request.getParameter("name");
                    String newFileName= name==null?uuid+suffix:name;
                    //写入文件到磁盘，该行执行完毕后，若有该临时文件，将会自动删除
                    fileItem.write(new File(uploadPath,newFileName));
                    ServiceResult result = new ServiceResult();
                    result.setData(newFileName);
                    sendJsonObject(response,result);
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

}
