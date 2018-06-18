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
import com.framework.entity.TCartParam2Entity;
import com.framework.entity.TCartParamsEntity;
import com.framework.model.ImportParamsModel;
import com.framework.service.FileService;
import com.framework.service.LocationCityService;
import com.framework.service.LocationProvinceService;
import com.framework.service.ScheduleJobService;
import com.framework.service.TCartParam2Service;
import com.framework.service.TCartParamsService;
import com.framework.utils.DateUtil;
import com.framework.utils.ImageTools;
import com.framework.utils.ImageZipUtil;
import com.framework.utils.R;
import com.framework.utils.ReturnData;
import com.framework.utils.StringUtil;

@Controller
@RequestMapping("common")
public class CommonController extends AbstractController {

	@Autowired
	private LocationProvinceService provinceService;
	@Autowired
	private LocationCityService cityService;
	@Autowired
	private TCartParam2Service param2Service;
	@Autowired
	private TCartParamsService paramService;

	@RequestMapping("/uploadImage")
	public String uploadImage() {
		System.out.println("============");
		return "tnews/tnews.html";
	}

	@ResponseBody
	@RequestMapping("/queryProvince")
	public R queryProvince() {
		// 查询列表数据
		List<LocationProvinceEntity> provinceList = provinceService.queryAllList();
		return R.ok().put("provinceList", provinceList);
	}

	@ResponseBody
	@RequestMapping("/queryCity/{id}")
	public R queryCity(@PathVariable("id") Integer id) {
		// 查询列表数据
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
			} else {
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
	public R uploadExcelFile(MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 头像
			MultipartFile recordFile = multiRequest.getFile("uploadFile");
			InputStream is = recordFile.getInputStream();
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 循环工作表Sheet
			List<ImportParamsModel> models = new ArrayList<>();
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					ImportParamsModel model = null;
					model = new ImportParamsModel();
					TCartParamsEntity params = new TCartParamsEntity();
					TCartParam2Entity param2 = new TCartParam2Entity();
					for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
						HSSFCell modelCell = hssfRow.getCell(i);
						if(i == 0) {
							params.setCartId(StringUtil.toInteger(getCellValue(modelCell)));
							param2.setCartId(StringUtil.toInteger(getCellValue(modelCell)));
						} else if (i == 1) {
							params.setBiaopei(getCellValue(modelCell));
						} else if (i == 2) {
							params.setLength(getCellValue(modelCell));
						} else if (i == 3) {
							params.setWidth(getCellValue(modelCell));
						} else if (i == 4) {
							params.setHeight(getCellValue(modelCell));
						} else if (i == 5) {
							params.setZhouju(getCellValue(modelCell));
						} else if (i == 6) {
							params.setQianlunju(getCellValue(modelCell));
						} else if (i == 7) {
							params.setHoulunju(getCellValue(modelCell));
						}else if (i == 8) {
							params.setLidijianxi(getCellValue(modelCell));
						}else if (i == 9) {
							params.setZhiliang(getCellValue(modelCell));
						}else if (i == 10) {
							params.setCheshenjiegou(getCellValue(modelCell));
						}else if (i == 11) {
							params.setChemenshu(getCellValue(modelCell));
						}else if (i == 12) {
							params.setZuoweishu(getCellValue(modelCell));
						}else if (i == 13) {
							params.setYouxiangrongji(getCellValue(modelCell));
						}else if (i == 14) {
							params.setXinglirongji(getCellValue(modelCell));
						}else if (i == 15) {
							params.setFadongjixinghao(getCellValue(modelCell));
						}else if (i == 16) {
							params.setPailiang(getCellValue(modelCell));
						}else if (i == 17) {
							params.setJinqixingshi(getCellValue(modelCell));
						}else if (i == 18) {
							params.setQgpxxingshi(getCellValue(modelCell));
						}else if (i == 19) {
							params.setQigangshu(getCellValue(modelCell));
						}else if (i == 20) {
							params.setQimenshu(getCellValue(modelCell));
						}else if (i == 21) {
							params.setGangjing(getCellValue(modelCell));
						}else if (i == 22) {
							params.setXingcheng(getCellValue(modelCell));
						}else if (i == 23) {
							params.setMaxmali(getCellValue(modelCell));
						}else if (i == 24) {
							params.setMaxgonglv(getCellValue(modelCell));
						}else if (i == 25) {
							params.setMaxgonglvzhuansu(getCellValue(modelCell));
						}else if (i == 26) {
							params.setMaxniuju(getCellValue(modelCell));
						}else if (i == 27) {
							params.setMaxnjzhuansu(getCellValue(modelCell));
						}else if (i == 28) {
							params.setFadongjityjs(getCellValue(modelCell));
						}else if (i == 29) {
							params.setRanliaotype(getCellValue(modelCell));
						}else if (i == 30) {
							params.setRanliaocode(getCellValue(modelCell));
						}else if (i == 31) {
							params.setGongyoutype(getCellValue(modelCell));
						}else if (i == 32) {
							params.setGanggaicailiao(getCellValue(modelCell));
						}else if (i == 33) {
							params.setGangticailiao(getCellValue(modelCell));
						}else if (i == 34) {
							params.setHuanbobiaozhun(getCellValue(modelCell));
						}else if (i == 35) {
							params.setDangweigeshu(getCellValue(modelCell));
						}else if (i == 36) {
							params.setBiansuxiangtype(getCellValue(modelCell));
						}else if (i == 37) {
							params.setQudongtype(getCellValue(modelCell));
						}else if (i == 38) {
							params.setSiqutype(getCellValue(modelCell));
						}else if (i == 39) {
							params.setChasuxiangjg(getCellValue(modelCell));
						}else if (i == 40) {
							params.setQianxuanliangtype(getCellValue(modelCell));
						}else if (i == 41) {
							params.setHouxuanliangtype(getCellValue(modelCell));
						}else if (i == 42) {
							params.setZhulitype(getCellValue(modelCell));
						}else if (i == 43) {
							params.setChetijiegou(getCellValue(modelCell));
						}else if (i == 44) {
							params.setQianzhidongqitype(getCellValue(modelCell));
						}else if (i == 45) {
							params.setHouzhidongqitype(getCellValue(modelCell));
						}else if (i == 46) {
							params.setZhuchezhidongtype(getCellValue(modelCell));
						}else if (i == 47) {
							params.setQianluntaiguige(getCellValue(modelCell));
						}else if (i == 48) {
							params.setHouluntaiguige(getCellValue(modelCell));
						}else if (i == 49) {
							params.setBeitaiguige(getCellValue(modelCell));
						}else if (i == 50) {
							params.setZhujiashiqinang(getCellValue(modelCell));
						}else if (i == 51) {
							params.setFujiashiqinang(getCellValue(modelCell));
						}else if (i == 52) {
							params.setQiancepaiqinang(getCellValue(modelCell));
						}else if (i == 53) {
							params.setHoucepaiqinang(getCellValue(modelCell));
						}else if (i == 54) {
							params.setQiantouqinang(getCellValue(modelCell));
						}else if (i == 55) {
							params.setHoutouqinang(getCellValue(modelCell));
						}else if (i == 56) {
							params.setQibuqinang(getCellValue(modelCell));
						}else if (i == 57) {
							params.setTaiyajianceqinang(getCellValue(modelCell));
						}else if (i == 58) {
							params.setLingtaiyajxxs(getCellValue(modelCell));
						}else if (i == 59) {
							params.setAnquandaiweixts(getCellValue(modelCell));
						}else if (i == 60) {
							params.setErtongzuoyijiekou(getCellValue(modelCell));
						}else if (i == 61) {
							params.setFadongjifangdao(getCellValue(modelCell));
						}else if (i == 62) {
							params.setCheneizhognkongsuo(getCellValue(modelCell));
						}else if (i == 63) {
							param2.setYaokongyaosi(getCellValue(modelCell));
						}else if (i == 64) {
							param2.setWuyaosiqidong(getCellValue(modelCell));
						}else if (i == 65) {
							param2.setWuyaosixitong(getCellValue(modelCell));
						}else if (i == 66) {
							param2.setAbsbaosi(getCellValue(modelCell));
						}else if (i == 67) {
							param2.setZhidonglifenpei(getCellValue(modelCell));
						} else if (i == 68) {
							param2.setShachefuzhu(getCellValue(modelCell));
						} else if (i == 69) {
							param2.setQianyinlikongzhi(getCellValue(modelCell));
						} else if (i == 70) {
							param2.setChenshenwending(getCellValue(modelCell));
						} else if (i == 71) {
							param2.setShangpofuzhu(getCellValue(modelCell));
						} else if (i == 72) {
							param2.setZidongzhuche(getCellValue(modelCell));
						}else if (i == 73) {
							param2.setDuopohuanjiang(getCellValue(modelCell));
						}else if (i == 74) {
							param2.setKebianxuanlinag(getCellValue(modelCell));
						}else if (i == 75) {
							param2.setKongqixuanliang(getCellValue(modelCell));
						}else if (i == 76) {
							param2.setDiandongtianchuang(getCellValue(modelCell));
						}else if (i == 77) {
							param2.setQuanjingtianchuan(getCellValue(modelCell));
						}else if (i == 78) {
							param2.setYundongwaiguantaojian(getCellValue(modelCell));
						}else if (i == 79) {
							param2.setLvjinjinlunquan(getCellValue(modelCell));
						}else if (i == 80) {
							param2.setDiandongxihemen(getCellValue(modelCell));
						}else if (i == 81) {
							param2.setCehuamen(getCellValue(modelCell));
						}else if (i == 82) {
							param2.setDiandonghoubeixiang(getCellValue(modelCell));
						}else if (i == 83) {
							param2.setGanyinghoubeixiang(getCellValue(modelCell));
						}else if (i == 84) {
							param2.setChedingxinglijia(getCellValue(modelCell));
						}else if (i == 85) {
							param2.setZhenpifangxiangpan(getCellValue(modelCell));
						}else if (i == 86) {
							param2.setFangxiangpantiaojie(getCellValue(modelCell));
						}else if (i == 87) {
							param2.setFangxiangpandiandong(getCellValue(modelCell));
						}else if (i == 88) {
							param2.setDuogongnengfangxiang(getCellValue(modelCell));
						}else if (i == 89) {
							param2.setFxphuandang(getCellValue(modelCell));
						}else if (i == 90) {
							param2.setFxpjiare(getCellValue(modelCell));
						}else if (i == 91) {
							param2.setDignweixunhang(getCellValue(modelCell));
						}else if (i == 92) {
							param2.setQianhouzhucheleida(getCellValue(modelCell));
						}else if (i == 93) {
							param2.setDaocheyingxiang(getCellValue(modelCell));
						}else if (i == 94) {
							param2.setXingchediannao(getCellValue(modelCell));
						}else if (i == 95) {
							param2.setQuanyejingyibiaopan(getCellValue(modelCell));
						}else if (i == 96) {
							param2.setZuoyicaizhi(getCellValue(modelCell));
						}else if (i == 97) {
							param2.setZuoyigaoditiaojie(getCellValue(modelCell));
						}else if (i == 98) {
							param2.setYaobuzhichengtiaojie(getCellValue(modelCell));
						}else if (i == 99) {
							param2.setJuanbuzhichengtiaojie(getCellValue(modelCell));
						}else if (i == 100) {
							param2.setZhufujiashidiandong(getCellValue(modelCell));
						}else if (i == 101) {
							param2.setHoupaizuoyidiandong(getCellValue(modelCell));
						}else if (i == 102) {
							param2.setQianhouzuoyijiare(getCellValue(modelCell));
						}else if (i == 103) {
							param2.setGpsdaohang(getCellValue(modelCell));
						}else if (i == 104) {
							param2.setZhongkongtaicaise(getCellValue(modelCell));
						}else if (i == 105) {
							param2.setLanyachezai(getCellValue(modelCell));
						}else if (i == 106) {
							param2.setWaijieyinyuan(getCellValue(modelCell));
						}else if (i == 107) {
							param2.setDuomeitixitong(getCellValue(modelCell));
						}else if (i == 108) {
							param2.setYangshengqishu(getCellValue(modelCell));
						}else if (i == 109) {
							param2.setJinguangdeng(getCellValue(modelCell));
						}else if (i == 110) {
							param2.setYuanguangdeng(getCellValue(modelCell));
						}else if (i == 111) {
							param2.setRijianxingchedeng(getCellValue(modelCell));
						}else if (i == 112) {
							param2.setZishiyingyuanjindeng(getCellValue(modelCell));
						}else if (i == 113) {
							param2.setZidongtoudeng(getCellValue(modelCell));
						}else if (i == 114) {
							param2.setZxfuzhudeng(getCellValue(modelCell));
						}else if (i == 115) {
							param2.setZxtoudeng(getCellValue(modelCell));
						}else if (i == 116) {
							param2.setQianwudeng(getCellValue(modelCell));
						}else if (i == 117) {
							param2.setDadenggaoduketiao(getCellValue(modelCell));
						}else if (i == 118) {
							param2.setDadengqixizhuangzhi(getCellValue(modelCell));
						}else if (i == 119) {
							param2.setQiandiandongchechuan(getCellValue(modelCell));
						}else if (i == 120) {
							param2.setHoudiandongchechuan(getCellValue(modelCell));
						}else if (i == 121) {
							param2.setChechuanfangjia(getCellValue(modelCell));
						}else if (i == 122) {
							param2.setHoushijingdiandong(getCellValue(modelCell));
						}else if (i == 123) {
							param2.setHoushijingjiare(getCellValue(modelCell));
						}else if (i == 124) {
							param2.setNeiwaihoushijing(getCellValue(modelCell));
						}else if (i == 125) {
							param2.setHoushijingdiandongzd(getCellValue(modelCell));
						}else if (i == 126) {
							param2.setZybhuazhuangjing(getCellValue(modelCell));
						}else if (i == 127) {
							param2.setHouyushua(getCellValue(modelCell));
						}else if (i == 128) {
							param2.setGanyingyushua(getCellValue(modelCell));
						}else if (i == 129) {
							param2.setKongtiaokongzhitype(getCellValue(modelCell));
						}else if (i == 130) {
							param2.setHoupaidulikongtiao(getCellValue(modelCell));
						}else if (i == 131) {
							param2.setHouzuochufengkou(getCellValue(modelCell));
						}else if (i == 132) {
							param2.setWendufenqu(getCellValue(modelCell));
						}else if (i == 133) {
							param2.setCheneikongqitj(getCellValue(modelCell));
						}else if (i == 134) {
							param2.setChezaibingxiang(getCellValue(modelCell));
						}else if (i == 135) {
							param2.setZidongbocheruwei(getCellValue(modelCell));
						}else if (i == 136) {
							param2.setFadongjiqidong(getCellValue(modelCell));
						}else if (i == 137) {
							param2.setBingxianfuzhu(getCellValue(modelCell));
						}else if (i == 138) {
							param2.setChedaopianli(getCellValue(modelCell));
						}else if (i == 139) {
							param2.setZhudongshache(getCellValue(modelCell));
						}else if (i == 140) {
							param2.setZhengtizhudong(getCellValue(modelCell));
						}else if (i == 141) {
							param2.setYeshixitong(getCellValue(modelCell));
						}else if (i == 142) {
							param2.setZhongkongyejing(getCellValue(modelCell));
						}else if (i == 143) {
							param2.setZishiyingxunhang(getCellValue(modelCell));
						}else if (i == 144) {
							param2.setQuanjingshexiangtou(getCellValue(modelCell));
						}
					}
					param2.setCartTypeCd(request.getParameter("typeCd"));
					params.setCartTypeCd(request.getParameter("typeCd"));
					
					param2.setCreateTime(DateUtil.getNowTimestamp());
					param2.setUpdateTime(DateUtil.getNowTimestamp());
					
					params.setCreateTime(DateUtil.getNowTimestamp());
					params.setUpdateTime(DateUtil.getNowTimestamp());
					model.setParams(params);
					model.setParams2(param2);
					models.add(model);
				}
			}
			
			
			//添加
			for(ImportParamsModel model : models){
				TCartParam2Entity param2Entity = model.getParams2();
				TCartParamsEntity paramsEntity = model.getParams();
				param2Service.save(param2Entity);
				paramService.save(paramsEntity);
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
			java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
			ret = myformat.format(data);
			break;
		case Cell.CELL_TYPE_STRING:
			ret = cell.getRichStringCellValue().getString();
			break;
		default:
			ret = null;
		}

		return ret; // 有必要自行trim
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
			java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.0000");
			ret = myformat.format(data);
			break;
		case Cell.CELL_TYPE_STRING:
			ret = cell.getRichStringCellValue().getString();
			break;
		default:
			ret = null;
		}

		return ret; // 有必要自行trim
	}
}
