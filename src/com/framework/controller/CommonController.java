package com.framework.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.UUIDGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.service.FileService;
import com.framework.utils.ImageTools;
import com.framework.utils.ImageZipUtil;
import com.framework.utils.ReturnData;

@Controller
@RequestMapping("common")
public class CommonController extends AbstractController {

	@RequestMapping("/uploadImage")
	public String uploadImage() {
		System.out.println("============");
		return "tnews/tnews.html";
	}

	@RequestMapping("/uploadFile")
	public void uploadFile(MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) {

		System.out.println(11);
		ReturnData data = new ReturnData();
		FileService fs = new FileService();

		////////////
		
		try {
			// 获取文件名
			String fileName1 = uploadFile.getOriginalFilename();
			// 获取文件后缀
			String prefix = fileName1.substring(fileName1.lastIndexOf("."));
			// 用uuid作为文件名，防止生成的临时文件重复
			// MultipartFile to File
			final File file = File.createTempFile(UUID.randomUUID().toString(), prefix);
			uploadFile.transferTo(file);

			// 上传文件
			if (file != null) {
				String fileName = file.getName();
				String[] names = fileName.split("\\.");
				String uuid = UUID.randomUUID().toString();
				File t = new File(Constants.FILE_HOST.DOCUMENT + uuid + "." + names[1]);
				String url = Constants.HOST.DOCUMENT + uuid + "." + names[1];
				t.createNewFile();

				fs.fileChannelCopy(file, t);
				ImageZipUtil.zipWidthHeightImageFile(file, t, ImageTools.getImgWidth(file),
						ImageTools.getImgHeight(file), 0.5f);
				file.delete();
				JSONObject map = new JSONObject();
				map.put("imgUrl", url);
				map.put("imgName", uuid + "." + names[1]);
				data.setCode(Constants.STATUS_CODE.SUCCESS);
				data.setData(map);
				data.setMessage("上传成功");
			    response.getWriter().print(data.toString());
			}else {
				data.setCode(Constants.STATUS_CODE.FAIL);
				data.setMessage("上传失败");
			    try {
					response.getWriter().print(data.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("上传失败");
		    try {
		    	response.addHeader("Access-Control-Allow-Origin", "*");
				response.getWriter().print(data.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
