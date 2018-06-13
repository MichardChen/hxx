package com.framework.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.entity.LocationCityEntity;
import com.framework.entity.LocationProvinceEntity;
import com.framework.entity.TBrandEntity;
import com.framework.service.FileService;
import com.framework.service.LocationCityService;
import com.framework.service.LocationProvinceService;
import com.framework.service.ScheduleJobService;
import com.framework.utils.ImageTools;
import com.framework.utils.ImageZipUtil;
import com.framework.utils.R;
import com.framework.utils.ReturnData;

@Controller
@RequestMapping("common")
public class CommonController extends AbstractController {

	@Autowired
	private LocationProvinceService provinceService;
	@Autowired
	private LocationCityService cityService;
	
	@RequestMapping("/uploadImage")
	public String uploadImage() {
		System.out.println("============");
		return "tnews/tnews.html";
	}
	
	@ResponseBody
	@RequestMapping("/queryProvince")
	public R queryProvince(){
		//查询列表数据
		List<LocationProvinceEntity> provinceList = provinceService.queryAllList();
		return R.ok().put("provinceList", provinceList);
	}
	
	@ResponseBody
	@RequestMapping("/queryCity/{id}")
	public R queryCity(@PathVariable("id") Integer id){
		//查询列表数据
		List<LocationCityEntity> cityList = cityService.queryAllList(id);
		return R.ok().put("cityList", cityList);
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
			response.addHeader("Access-Control-Allow-Origin", "*");
			// 上传文件
			if (file != null) {
				String fileName = file.getName();
				String[] names = fileName.split("\\.");
				String uuid = UUID.randomUUID().toString();
				File t = new File(Constants.FILE_HOST.DOCUMENT + uuid + "." + names[1]);
				String url = Constants.HTTP_HOST.DOCUMENT + uuid + "." + names[1];
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
	
	@ResponseBody
	@RequestMapping("/uploadExcelFile")
	public R uploadExcelFile(MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws Exception{

		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	    //判断 request 是否有文件上传,即多部分请求  
	    if(multipartResolver.isMultipart(request)){  
	        //转换成多部分request    
	        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
	        //头像
	        MultipartFile recordFile = multiRequest.getFile("uploadFile");
	        InputStream is = recordFile.getInputStream();
	        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 循环工作表Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			  HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			  if (hssfSheet == null) {
			    continue;
			  }
			  // 循环行Row
			  for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			    for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
			      HSSFCell modelCell = hssfRow.getCell(i);
			      if (i == 0) {
			        System.out.println(getCellValue(modelCell));
			      } else if (i == 1) {
			    	System.out.println(getCellValue(modelCell));
			      } else if (i == 2) {
			    	System.out.println(getCellValue(modelCell));
			      } else if (i == 3) {
			    	System.out.println(getCellValue(modelCell));
			      } else if (i == 4) {
			    	System.out.println(getCellValue(modelCell));
			      } else if (i == 5) {
			    	System.out.println(getCellValue(modelCell));
			      } else if (i == 6) {
			    	System.out.println(getCellValue(modelCell));
			      } 
			  }
			}
		}
	    }
			
		return R.ok("上传成功");
	}
	
	public String getCellValue(Cell cell) {  
        String ret;  
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_BLANK:  
            ret = "";  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            ret = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case Cell.CELL_TYPE_ERROR:  
            ret = null;  
            break;  
        case Cell.CELL_TYPE_FORMULA:  
            Workbook wb = cell.getSheet().getWorkbook();  
            CreationHelper crateHelper = wb.getCreationHelper();  
            FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
            ret = getCellValue(evaluator.evaluateInCell(cell));  
            break;  
        case Cell.CELL_TYPE_NUMERIC:
        	double data = cell.getNumericCellValue();
        	java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");
        	ret = myformat.format(data);    
            break;
        case Cell.CELL_TYPE_STRING:  
            ret = cell.getRichStringCellValue().getString();  
            break;  
        default:  
            ret = null;  
        }  
          
        return ret; //有必要自行trim  
    }
	
	public String getCellValueNew(Cell cell) {  
        String ret;  
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_BLANK:  
            ret = "";  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            ret = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case Cell.CELL_TYPE_ERROR:  
            ret = null;  
            break;  
        case Cell.CELL_TYPE_FORMULA:  
            Workbook wb = cell.getSheet().getWorkbook();  
            CreationHelper crateHelper = wb.getCreationHelper();  
            FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
            ret = getCellValue(evaluator.evaluateInCell(cell));  
            break;  
        case Cell.CELL_TYPE_NUMERIC:
        	double data = cell.getNumericCellValue();
        	java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.0000");
        	ret = myformat.format(data);    
            break;
        case Cell.CELL_TYPE_STRING:  
            ret = cell.getRichStringCellValue().getString();  
            break;  
        default:  
            ret = null;  
        }  
          
        return ret; //有必要自行trim  
    }
}
