package com.framework.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.LocationCityDao;
import com.framework.dao.TCodemstDao;
import com.framework.dao.TQuestionAnswerDao;
import com.framework.dto.ParamsDTO;
import com.framework.entity.LocationCityEntity;
import com.framework.entity.TBrandEntity;
import com.framework.entity.TCarImportEntity;
import com.framework.entity.TCarLeaseEntity;
import com.framework.entity.TCarSecondhandEntity;
import com.framework.entity.TCarouselEntity;
import com.framework.entity.TQuestionAnswerEntity;
import com.framework.entity.TStoryEntity;
import com.framework.model.QuestionAnswerListModel;
import com.framework.model.QuestionAnswerModel;
import com.framework.restmodel.BrandModel;
import com.framework.restmodel.CarouselModel;
import com.framework.restmodel.ImportCarListModel;
import com.framework.restmodel.LeaseCarListModel;
import com.framework.restmodel.NewsListModel;
import com.framework.restmodel.PCImportCarListModel;
import com.framework.restmodel.PCLeaseCarListModel;
import com.framework.restmodel.SecondHandCarListModel;
import com.framework.service.TBrandService;
import com.framework.service.TCarImportService;
import com.framework.service.TCarLeaseService;
import com.framework.service.TCarSecondhandService;
import com.framework.service.TCarouselService;
import com.framework.service.TNewsService;
import com.framework.service.TStoryService;
import com.framework.utils.ReturnData;
import com.framework.utils.StringUtil;

@Controller
@RequestMapping("pcrest")
public class PCController extends RestfulController{

	@Autowired
	private TCarouselService carouselService;
	@Autowired
	private TBrandService brandService;
	@Autowired
	private TCarLeaseService leaseService;
	@Autowired
	private TCarImportService importService;
	@Autowired
	private TCarSecondhandService secondService;
	@Autowired
	private LocationCityDao cityDao;
	@Autowired
	private TStoryService storyService;
	@Autowired
	private TNewsService newsService;
	@Autowired
	private TCodemstDao codeMstDao;
	@Autowired
	private TQuestionAnswerDao questionAnswerDao;
	
	@RequestMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		//获取店招图片
		TCarouselEntity storyImage = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.STORY_ZHAO);
		if(storyImage != null) {
			json.put("storeImage", storyImage.getImgUrl());
		}else {
			json.put("storeImage", "");
		}
		//获取轮播图
		List<TCarouselEntity> list = carouselService.queryListByTypeCd(Constants.CAROUSEL_TYPE.PC_INDEX);
		List<CarouselModel> carouselModels = new ArrayList<>();
		CarouselModel model = null;
		for(TCarouselEntity e : list) {
			model = new CarouselModel();
			model.setImgUrl(e.getImgUrl());
			model.setRealUrl(e.getRealUrl());
			carouselModels.add(model);
		}
		json.put("carouselModels", carouselModels);
		//获取商标
		List<TBrandEntity> brands = brandService.queryAllList(1);
		List<BrandModel> brandModels = new ArrayList<>();
		BrandModel bmodel = null;
		for(TBrandEntity e : brands) {
			bmodel = new BrandModel();
			bmodel.setBrand(e.getBrand());
			bmodel.setBrandIcon(e.getBrandIcon());
			bmodel.setId(e.getId());
			brandModels.add(bmodel);
		}
		json.put("brandModels", brandModels);
		//获取横幅
		TCarouselEntity entity = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.CAR_LEASE_IMAGE);
		if(entity!=null) {
			CarouselModel m1 = new CarouselModel();
			m1.setImgUrl(entity.getImgUrl());
			m1.setRealUrl(entity.getRealUrl());
			json.put("leaseCarImage", m1);
		}else {
			json.put("leaseCarImage", null);
		}
		TCarouselEntity entity1 = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.CAR_IMPORT_IMAGE);
		if(entity1!=null) {
			CarouselModel m1 = new CarouselModel();
			m1.setImgUrl(entity1.getImgUrl());
			m1.setRealUrl(entity1.getRealUrl());
			json.put("importCarImage", m1);
		}else {
			json.put("importCarImage", null);
		}
		TCarouselEntity entity2 = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.CAR_SECONDHAND_IMAGE);
		if(entity2!=null) {
			CarouselModel m1 = new CarouselModel();
			m1.setImgUrl(entity2.getImgUrl());
			m1.setRealUrl(entity2.getRealUrl());
			json.put("secondHandCarImage",m1);
		}else {
			json.put("secondHandCarImage",null);
		}
		
		Map<String, Object> carListMap = new HashMap<>();
		carListMap.put("offset", 0);
		carListMap.put("limit", 6);
		
		//车主故事
		List<TStoryEntity> stList = storyService.queryList(carListMap);
		List<NewsListModel> storyList = new ArrayList<>();
		NewsListModel slm = null;
		for(TStoryEntity e : stList) {
			slm = new NewsListModel();
			slm.setIcon(e.getStoryIcon());
			slm.setId(e.getId());
			slm.setTitle(e.getStoryTitle());
			slm.setType("");
			slm.setUrl(e.getDescUrl());
			storyList.add(slm);
		}
		json.put("storyList", storyList);
		//惠群问答
		List<TQuestionAnswerEntity> questionList = questionAnswerDao.queryList(carListMap);
		List<QuestionAnswerModel> models = new ArrayList<>();
		QuestionAnswerModel anl = null;
		for(TQuestionAnswerEntity e : questionList){
			anl = new QuestionAnswerModel();
			anl.setAnswer(e.getAnswer());
			anl.setQuestion(e.getQuestion());
			anl.setId(e.getId());
			models.add(anl);
		}
		json.put("questionAnswerList", models);
		//汽车列表
		Map<String, Object> listMap = new HashMap<>();
		listMap.put("offset", 0);
		listMap.put("limit", 8);
		
		List<TCarLeaseEntity> clList = leaseService.queryList(listMap);
		List<TCarImportEntity> ciList = importService.queryList(listMap);
		List<TCarSecondhandEntity> csList = secondService.queryList(listMap);
		
		List<PCLeaseCarListModel> leaseList = new ArrayList<>();
		List<PCImportCarListModel> importList = new ArrayList<>();
		List<SecondHandCarListModel> secondList = new ArrayList<>();
		
		//以租代购
		PCLeaseCarListModel lcm = null;
		for(TCarLeaseEntity e : clList) {
			lcm = new PCLeaseCarListModel();
			lcm.setFirstPayment(StringUtil.toString(e.getFirstPayment()));
			lcm.setIcon(e.getIcon());
			lcm.setId(e.getId());
			lcm.setMonthPayment(StringUtil.toString(e.getMonthPayment()));
			lcm.setName(e.getCarName());
			lcm.setLabel(e.getTitleLabel());
			leaseList.add(lcm);
		}
		
		//平行进口车
		PCImportCarListModel icl = null;
		for(TCarImportEntity e : ciList) {
			icl = new PCImportCarListModel();
			icl.setIcon(e.getIcon());
			icl.setId(e.getId());
			icl.setLabels(e.getLabels());
			icl.setName(e.getCarName());
			icl.setNowPrice(StringUtil.toString(e.getNowPrice()));
			icl.setPrimePrice(StringUtil.toString(e.getMarketPrice()));
			icl.setTitleLabels(e.getTitleLabel());
			importList.add(icl);
		}
		
		//二手车
		SecondHandCarListModel scl = null;
		for(TCarSecondhandEntity e : csList) {
			scl = new SecondHandCarListModel();
			scl.setIcon(e.getIcon());
			scl.setName(e.getCarName());
			scl.setKilometers(StringUtil.toString(e.getKilomiters()));
			scl.setMonthPayment(StringUtil.toString(e.getMonthPayment()));
			scl.setFirstPayment(StringUtil.toString(e.getFirstPayment()));
			scl.setId(e.getId());
			scl.setDate(e.getYear());
			LocationCityEntity city = cityDao.queryObject(e.getCityId());
			scl.setCity(city == null ? "":city.getName());
			secondList.add(scl);
		}
		json.put("leaseList", leaseList);
		json.put("importList", importList);
		json.put("secondList", secondList);
		
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
}
