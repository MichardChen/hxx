package com.framework.restful;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.LocationCityDao;
import com.framework.dao.TBrandSeriesDao;
import com.framework.dao.TCodemstDao;
import com.framework.dao.TQuestionAnswerDao;
import com.framework.dto.ParamsDTO;
import com.framework.entity.LocationCityEntity;
import com.framework.entity.TBrandEntity;
import com.framework.entity.TBrandSeriesEntity;
import com.framework.entity.TCarImportEntity;
import com.framework.entity.TCarLeaseEntity;
import com.framework.entity.TCarSecondhandEntity;
import com.framework.entity.TCarouselEntity;
import com.framework.entity.TCartParam2Entity;
import com.framework.entity.TCartParamsEntity;
import com.framework.entity.TCodemstEntity;
import com.framework.entity.TDocumentEntity;
import com.framework.entity.TFinanceCommitEntity;
import com.framework.entity.TFinanceEntity;
import com.framework.entity.TNewsEntity;
import com.framework.entity.TQuestionAnswerEntity;
import com.framework.entity.TQuestionEntity;
import com.framework.entity.TStoryEntity;
import com.framework.entity.TVertifyCodeEntity;
import com.framework.model.DocumentListModel;
import com.framework.model.DocumentModel;
import com.framework.model.FinanceListModel;
import com.framework.model.QuestionAnswerModel;
import com.framework.pcmodel.ImportCarPCListModel;
import com.framework.pcmodel.LeaseCarPCListModel;
import com.framework.pcmodel.PCBrandModel;
import com.framework.pcmodel.PCBrandSeriesModel;
import com.framework.pcmodel.SecondhandCarPCDetailModel;
import com.framework.pcmodel.SecondhandCarPCListModel;
import com.framework.restmodel.BrandJsonModel;
import com.framework.restmodel.BrandModel;
import com.framework.restmodel.BrandSeriesMJsonModel;
import com.framework.restmodel.CarouselModel;
import com.framework.restmodel.NewsListModel;
import com.framework.restmodel.PCImportCarListModel;
import com.framework.restmodel.PCLeaseCarListModel;
import com.framework.restmodel.PCQuestionAnswerListModel;
import com.framework.restmodel.SecondHandCarListModel;
import com.framework.service.TBrandService;
import com.framework.service.TCarImportService;
import com.framework.service.TCarLeaseService;
import com.framework.service.TCarSecondhandService;
import com.framework.service.TCarouselService;
import com.framework.service.TCartParam2Service;
import com.framework.service.TCartParamsService;
import com.framework.service.TDocumentService;
import com.framework.service.TFinanceCommitService;
import com.framework.service.TFinanceService;
import com.framework.service.TNewsService;
import com.framework.service.TQuestionAnswerService;
import com.framework.service.TQuestionService;
import com.framework.service.TStoryService;
import com.framework.service.TVertifyCodeService;
import com.framework.utils.DateUtil;
import com.framework.utils.ReturnData;
import com.framework.utils.ShortMessageUtil;
import com.framework.utils.StringUtil;
import com.framework.utils.VertifyUtil;

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
	@Autowired
	private TBrandSeriesDao brandSeriesDao;
	@Autowired
	private TFinanceService financeService;
	@Autowired
	private TVertifyCodeService vertifyCodeService;
	@Autowired
	private TFinanceCommitService financeCommitService;
	@Autowired
	private TQuestionAnswerService qanswerService;
	@Autowired
	private TQuestionService qService;
	@Autowired
	private TCartParamsService paramsService;
	@Autowired
	private TCartParam2Service params2Service;
	@Autowired
	private TDocumentService documentService;
	
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
		TCarouselEntity entity = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_CAR_LEASE_IMAGE);
		if(entity!=null) {
			CarouselModel m1 = new CarouselModel();
			m1.setImgUrl(entity.getImgUrl());
			m1.setRealUrl(entity.getRealUrl());
			json.put("leaseCarImage", m1);
		}else {
			json.put("leaseCarImage", null);
		}
		TCarouselEntity entity1 = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_CAR_IMPORT_IMAGE);
		if(entity1!=null) {
			CarouselModel m1 = new CarouselModel();
			m1.setImgUrl(entity1.getImgUrl());
			m1.setRealUrl(entity1.getRealUrl());
			json.put("importCarImage", m1);
		}else {
			json.put("importCarImage", null);
		}
		TCarouselEntity entity2 = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_CAR_SECONDHAND_IMAGE);
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
		carListMap.put("flg", 1);
		
		//车主故事
		List<TStoryEntity> stList = storyService.queryListData(carListMap);
		List<NewsListModel> storyList = new ArrayList<>();
		NewsListModel slm = null;
		for(TStoryEntity e : stList) {
			slm = new NewsListModel();
			slm.setIcon(e.getStoryIcon());
			slm.setId(e.getId());
			slm.setTitle(e.getStoryTitle());
			slm.setType("");
			slm.setDate(DateUtil.formatCN(e.getCreateTime()));
			slm.setUrl(StringUtil.cutBodyHeader(e.getContent()));
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
		listMap.put("flg", 1);
		
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
			
			lcm.setTitleLabel(e.getTitleLabel());
			
			if(e.getShowFlg()==1){
				lcm.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(),0));
				lcm.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(),1));
			}
			if(e.getShowFlg()==2){
				lcm.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment1(),0));
				lcm.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment1(),1));
			}
			if(e.getShowFlg()==3){
				lcm.setFirstPayment(StringUtil.formatCarPrice(e.getTfirstYearFirstPay(),0));
				lcm.setMonthPayment(StringUtil.formatCarPrice(e.getTfirstYearMonthPayment(),1));
			}
			
			
			lcm.setIcon(e.getIcon());
			lcm.setId(e.getId());
			
			//lcm.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(),0));
			//lcm.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(),1));
			
			TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(e.getCarSeriesId());
			TBrandEntity brandEntity = brandService.queryObject(e.getBrand());
			lcm.setBrand(brandEntity.getBrand()+seriesEntity.getCarSerial());
			lcm.setName(e.getCarName());
			lcm.setLabel(e.getLabels());
			leaseList.add(lcm);
		}
		
		//平行进口车
		PCImportCarListModel icl = null;
		for(TCarImportEntity e : ciList) {
			icl = new PCImportCarListModel();
			icl.setIcon(e.getIcon());
			icl.setId(e.getId());
			icl.setLabels(e.getLabels());
			TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(e.getCarSeriesId());
			TBrandEntity brandEntity = brandService.queryObject(e.getBrand());
			icl.setName(brandEntity.getBrand()+seriesEntity.getCarSerial()+" "+e.getCarName());
			icl.setNowPrice(StringUtil.formatCarPrice(e.getNowPrice(),0));
			icl.setPrimePrice(StringUtil.formatCarPrice(e.getMarketPrice(),0));
			icl.setSavePrice(StringUtil.formatCarPrice(e.getMaxSave(), 0));
			icl.setTitleLabels(e.getTitleLabel());
			importList.add(icl);
		}
		
		//二手车
		SecondHandCarListModel scl = null;
		for(TCarSecondhandEntity e : csList) {
			scl = new SecondHandCarListModel();
			scl.setIcon(e.getIcon());
			String brand = "";
			TBrandEntity brandEntity = brandService.queryObject(e.getBrand());
			if(brandEntity != null) {
				brand = brandEntity.getBrand();
			}
			TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(e.getCarSeriesId());
			if(seriesEntity != null) {
				brand = brand + seriesEntity.getCarSerial();
			}
			scl.setBrand(brand);
			scl.setName(e.getCarName());
			scl.setKilometers(StringUtil.formatCarPrice(e.getKilomiters(),0)+"公里");
			
			if(e.getShowFlg()==1){
				scl.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(), 0));
				scl.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(),1));
			}
			if(e.getShowFlg()==2){
				scl.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment1(), 0));
				scl.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment1(),1));
			}
			if(e.getShowFlg()==3){
				scl.setFirstPayment(StringUtil.formatCarPrice(e.getTfirstYearFirstPay(), 0));
				scl.setMonthPayment(StringUtil.formatCarPrice(e.getTfirstYearMonthPayment(),1));
			}
			
			//scl.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(),1));
			//scl.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(),0));
			scl.setId(e.getId());
			scl.setLabel(e.getLabels());
			scl.setDate(DateUtil.formatCNYM(e.getYear()));
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
	
	//查询所有品牌
	@RequestMapping("/queryAllBrand")
	public void queryAllBrand(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		List<TBrandEntity> list = brandService.queryAllList(1);
		Map<String, List<PCBrandModel>> map = new HashMap<>();
		List<PCBrandSeriesModel> allCarSeries = new ArrayList<>();
		List<PCBrandModel> allList = new ArrayList<>();
		for(TBrandEntity e : list){
			String word = e.getWord();
			PCBrandModel bm = new PCBrandModel();
			bm.setBrand(e.getBrand());
			bm.setId(e.getId());
			bm.setBrandIcon(e.getBrandIcon());
			allList.add(bm);
			//获取品牌对应的车系
			List<PCBrandSeriesModel> model = new ArrayList<>();
			PCBrandSeriesModel pcs = null;
			List<TBrandSeriesEntity> sList = brandSeriesDao.queryCarSeriesList(e.getId());
			for(TBrandSeriesEntity b : sList) {
				pcs = new PCBrandSeriesModel();
				pcs.setId(b.getId());
				pcs.setSeriesName(b.getCarSerial());
				pcs.setWord(word);
				pcs.setPid(e.getId());
				model.add(pcs);
				allCarSeries.add(pcs);
			}
			bm.setSeriesList(model);
			if(map.containsKey(word)){
				//包含
				List<PCBrandModel> value = map.get(word);
				value.add(bm);
				map.put(word, value);
			}else{
				//不包含
				List<PCBrandModel> value = new ArrayList<>();
				value.add(bm);
				map.put(word, value);
			}
		}
		json.put("allBrand", allList);
		json.put("allBrandSeries", allCarSeries);
		json.put("brandList", map);
		
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//惠搜车列表
	@RequestMapping("/queryLeaseCarList")
	public void queryLeaseCarList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("offset", dto.getPageSize() * (dto.getPageNum() - 1));
		map.put("limit", dto.getPageSize());
		map.put("brandId", dto.getBrandId());
		map.put("brandSeriesId", dto.getBrandSeriesId());
		map.put("fromFirstPayment", dto.getFromFirstPayment());
		map.put("toFirstPayment", dto.getToFirstPayment());
		map.put("fromMonthPayment", dto.getFromMonthPayment());
		map.put("toMonthPayment", dto.getToMonthPayment());
		map.put("fromCarCost", dto.getFromCarCost());
		map.put("toCarCost", dto.getToCarCost());
		map.put("carLevelCd", dto.getCarLevelCd());
		System.out.println(dto.getFromCarCost()+"="+dto.getToCarCost());
		map.put("flg", 1);
		List<TCarLeaseEntity> list = leaseService.queryPCTerminalList(map);
		List<LeaseCarPCListModel> pcListModels = new ArrayList<>();
		LeaseCarPCListModel model = null;
		for(TCarLeaseEntity e : list){
			
			model = new LeaseCarPCListModel();
			model.setIcon(e.getIcon());
			model.setId(e.getId());
			
			if(e.getShowFlg()==1){
				model.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(),0));
				model.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(),1));
			}
			if(e.getShowFlg()==2){
				model.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment1(),0));
				model.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment1(),1));
			}
			if(e.getShowFlg()==3){
				model.setFirstPayment(StringUtil.formatCarPrice(e.getTfirstYearFirstPay(),0));
				model.setMonthPayment(StringUtil.formatCarPrice(e.getTfirstYearMonthPayment(),1));
			}
			
			//model.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(), 0));
			//model.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(), 1));
			model.setLabels(e.getLabels());
			model.setName(e.getCarName());
			model.setTitleLabel(e.getTitleLabel());
			TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(e.getCarSeriesId());
			TBrandEntity brandEntity = brandService.queryObject(e.getBrand());
			String brand = "";
			if(brandEntity != null){
				brand = brandEntity.getBrand();
				if(seriesEntity != null) {
					brand = brand + seriesEntity.getCarSerial();
					model.setBrand(brand);
				}
			}else{
				model.setBrand("");
			}
			if(seriesEntity != null) {
				model.setBrandSeries(seriesEntity.getCarSerial());
			}else {
				model.setBrandSeries("");
			}
			pcListModels.add(model);
		}
		//查询结果集总条数
		int count = leaseService.queryPCTerminalTotal(map);
		json.put("count", count);
		json.put("leaseCarList", pcListModels);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//会淘车，二手车
	@RequestMapping("/querySecondhandCarList")
	public void querySecondhandCarList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("offset", dto.getPageSize() * (dto.getPageNum() - 1));
		map.put("limit", dto.getPageSize());
		map.put("brandId", dto.getBrandId());
		map.put("brandSeriesId", dto.getBrandSeriesId());
		map.put("fromFirstPayment", dto.getFromFirstPayment());
		map.put("toFirstPayment", dto.getToFirstPayment());
		map.put("fromMonthPayment", dto.getFromMonthPayment());
		map.put("toMonthPayment", dto.getToMonthPayment());
		map.put("fromCarCost", dto.getFromCarCost());
		map.put("toCarCost", dto.getToCarCost());
		map.put("carLevelCd", dto.getCarLevelCd());
		map.put("fromAge", dto.getFromAge());
		map.put("toAge", dto.getToAge());
		map.put("fromKilomiter", dto.getFromKilomiter());
		map.put("toKilomiter", dto.getToKilomiter());
		map.put("flg", 1);
		List<TCarSecondhandEntity> list = secondService.queryPCTerminalList(map);
		List<SecondhandCarPCListModel> pcListModels = new ArrayList<>();
		SecondhandCarPCListModel model = null;
		for(TCarSecondhandEntity e : list){
			
			model = new SecondhandCarPCListModel();
			model.setIcon(e.getIcon());
			model.setId(e.getId());
			
			if(e.getShowFlg()==1){
				model.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(), 0));
				model.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(),1));
			}
			if(e.getShowFlg()==2){
				model.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment1(), 0));
				model.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment1(),1));
			}
			if(e.getShowFlg()==3){
				model.setFirstPayment(StringUtil.formatCarPrice(e.getTfirstYearFirstPay(), 0));
				model.setMonthPayment(StringUtil.formatCarPrice(e.getTfirstYearMonthPayment(),1));
			}
			//model.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(), 0));
			//model.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(), 1));
			model.setLabels(e.getTitleLabel());
			model.setName(e.getCarName());
			model.setKilomiters(StringUtil.toMoneyString(e.getKilomiters())+"公里");
			model.setDate(DateUtil.formatCNYM(e.getCreateTime()));
			TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(e.getCarSeriesId());
			TBrandEntity brandEntity = brandService.queryObject(e.getBrand());
			String brand = "";
			if(brandEntity != null){
				brand = brandEntity.getBrand();
				if(seriesEntity != null) {
					brand = brand + seriesEntity.getCarSerial();
				}
				model.setBrand(brand);
			}else{
				model.setBrand("");
			}
			if(seriesEntity != null) {
				model.setBrandSeries(seriesEntity.getCarSerial());
			}else {
				model.setBrandSeries("");
			}
			LocationCityEntity cityEntity = cityDao.queryObject(e.getCityId());
			if(cityEntity != null) {
				model.setCity(cityEntity.getName());
			}else {
				model.setCity("");
			}
			pcListModels.add(model);
		}
		//查询结果集总条数
		int count = secondService.queryPCTerminalTotal(map);
		json.put("count", count);
		json.put("secondhandCarList", pcListModels);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//平行进口车
	@RequestMapping("/queryImportCarList")
	public void queryImportCarList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("offset", dto.getPageSize() * (dto.getPageNum() - 1));
		map.put("limit", dto.getPageSize());
		map.put("brandId", dto.getBrandId());
		map.put("brandSeriesId", dto.getBrandSeriesId());
		map.put("fromPrice", dto.getFromPrice());
		map.put("toPrice", dto.getToPrice());
		map.put("carClassCd", dto.getCarClassCd());
		map.put("carLevelCd", dto.getCarLevelCd());
		map.put("flg", 1);
		List<TCarImportEntity> list = importService.queryPCTerminalList(map);
		List<ImportCarPCListModel> pcListModels = new ArrayList<>();
		ImportCarPCListModel model = null;
		for(TCarImportEntity e : list){
			
			model = new ImportCarPCListModel();
			model.setIcon(e.getIcon());
			model.setId(e.getId());
			model.setNowPrice((StringUtil.formatCarPrice(e.getNowPrice(), 0)));
			model.setMarketPrice(StringUtil.formatCarPrice(e.getMarketPrice(), 1));
			model.setLabels(e.getLabels());
			model.setSavePrice(StringUtil.formatCarPrice(e.getMaxSave(), 0));
			model.setTitleLabel(e.getTitleLabel());
			TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(e.getCarSeriesId());
			if(seriesEntity != null) {
				TBrandEntity brandEntity = brandService.queryObject(e.getBrand());
				if(brandEntity != null){
					model.setBrandSeries(brandEntity.getBrand()+" "+seriesEntity.getCarSerial());
					model.setName(brandEntity.getBrand()+seriesEntity.getCarSerial()+" "+e.getCarName());
				}else{
					model.setBrandSeries(seriesEntity.getCarSerial());
					model.setName(seriesEntity.getCarSerial()+" "+e.getCarName());
				}
			}else {
				model.setBrandSeries("");
				model.setName(e.getCarName());
			}
			
			pcListModels.add(model);
		}
		//查询结果集总条数
		int count = importService.queryPCTerminalTotal(map);
		json.put("count", count);
		json.put("importCarList", pcListModels);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	@RequestMapping("/queryNewsList")
	public void queryNewsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		Map<String, Object> carListMap = new HashMap<>();
		carListMap.put("offset", dto.getPageSize() * (dto.getPageNum() - 1));
		carListMap.put("limit", dto.getPageSize());
		List<TNewsEntity> snList = newsService.queryListData(carListMap);
		List<NewPCListModel> newList = new ArrayList<>();
		NewPCListModel nlm = null;
		for (TNewsEntity e : snList) {
			nlm = new NewPCListModel();
			nlm.setIcon(e.getNewsLogo());
			nlm.setId(e.getId());
			nlm.setTitle(e.getNewsTitle());
			nlm.setShortTitle(StringUtil.StripHT(e.getContent()));
			nlm.setUrl(StringUtil.cutBodyHeader(e.getContent()));
			nlm.setDate(DateUtil.formatCN(e.getCreateTime()));
			TCodemstEntity mst = codeMstDao.queryByCode(e.getNewsTypeCd());
			if (mst != null) {
				nlm.setType(mst.getName());
			} else {
				nlm.setType("");
			}
			newList.add(nlm);
		}
		json.put("newList", newList);
		int total = newsService.queryTotalData(null);
		json.put("count", total);
		
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}

	@RequestMapping("/queryStoryList")
	public void queryStoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		Map<String, Object> carListMap = new HashMap<>();
		carListMap.put("offset", dto.getPageSize() * (dto.getPageNum() - 1));
		carListMap.put("limit", dto.getPageSize());
		carListMap.put("flg", 1);

		List<TStoryEntity> stList = storyService.queryListData(carListMap);
		List<NewPCListModel> storyList = new ArrayList<>();
		NewPCListModel slm = null;
		for (TStoryEntity e : stList) {
			slm = new NewPCListModel();
			slm.setIcon(e.getStoryIcon());
			slm.setId(e.getId());
			slm.setTitle(e.getStoryTitle());
			slm.setType("");
			slm.setDate(DateUtil.formatCN(e.getCreateTime()));
			slm.setUrl(StringUtil.cutBodyHeader(e.getContent()));
			storyList.add(slm);
		}
		json.put("storyList", storyList);
		int total = storyService.queryTotalData(null);
		json.put("count", total);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//惠鑫贷
	@RequestMapping("/queryFinanceList")
	public void queryFinanceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("offset", dto.getPageSize() * (dto.getPageNum() - 1));
		map.put("limit", dto.getPageSize());
		map.put("status", 1);

		List<TFinanceEntity> stList = financeService.queryList(map);
		List<FinancePCListModel> models = new ArrayList<>();
		FinancePCListModel slm = null;
		for (TFinanceEntity e : stList) {
			slm = new FinancePCListModel();
			slm.setId(e.getId());
			slm.setFinanceName(e.getName());
			slm.setMoneys(e.getLowRefund());
			slm.setPeriod(e.getTimeDistance());
			slm.setIcon(e.getIcon());
			slm.setRate(e.getLowRate());
			slm.setStandard(e.getStandard());
			models.add(slm);
		}
		json.put("financeList", models);
		int total = financeService.queryTotal(null);
		json.put("count", total);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	@RequestMapping("/sendVertifyCode")
	public void sendVertifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		TVertifyCodeEntity vertifyCodeEntity = vertifyCodeService.queryObjectByMobile(dto.getMobile()
																					 ,Constants.SHORT_MESSAGE_TYPE.SUMBIT_QUESTION);
		String code = VertifyUtil.getVertifyCode();
		if(vertifyCodeEntity != null){
			
			vertifyCodeEntity.setCode(code);
			vertifyCodeEntity.setUpdateTime(DateUtil.getNowTimestamp());
			vertifyCodeEntity.setCodeTypeCd(Constants.SHORT_MESSAGE_TYPE.SUMBIT_QUESTION);
			vertifyCodeEntity.setExpireTime(DateUtil.getVertifyCodeExpireTime());
			int updateFlg = vertifyCodeService.update(vertifyCodeEntity);
			if(updateFlg != 0){
				data.setCode(Constants.STATUS_CODE.SUCCESS);
				data.setMessage("发送成功");
				String shortMsg = "您的验证码是：" + code + "，10分钟内有效，请不要把验证码泄露给其他人。";
				ShortMessageUtil.sendsms(dto.getMobile(), shortMsg);
				renderJson(data, response);
				return;
				
			}else{
				data.setCode(Constants.STATUS_CODE.FAIL);
				data.setMessage("获取验证码失败，请重试");
				renderJson(data, response);
				return;
			}
		}else{
			TVertifyCodeEntity e = new TVertifyCodeEntity();
			e.setCode(code);
			e.setCodeTypeCd(Constants.SHORT_MESSAGE_TYPE.SUMBIT_QUESTION);
			e.setCreateTime(DateUtil.getNowTimestamp());
			e.setUpdateTime(DateUtil.getNowTimestamp());
			e.setExpireTime(DateUtil.getVertifyCodeExpireTime());
			e.setMobile(dto.getMobile());
			e.setUserTypeCd(Constants.USER_TYPE.USER_TYPE_CLIENT);
			int ret = vertifyCodeService.save(e);
			if(ret != 0){
				data.setCode(Constants.STATUS_CODE.SUCCESS);
				data.setMessage("发送成功");
				String shortMsg = "您的验证码是：" + code + "，10分钟内有效，请不要把验证码泄露给其他人。";
				ShortMessageUtil.sendsms(dto.getMobile(), shortMsg);
				renderJson(data, response);
				return;
			}else{
				data.setCode(Constants.STATUS_CODE.FAIL);
				data.setMessage("获取验证码失败，请重试");
				renderJson(data, response);
				return;
			}
		}
	}
	
	@RequestMapping("/submitFinanceApply")
	public void submitFinanceApply(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		TFinanceCommitEntity entity = new TFinanceCommitEntity();
		entity.setFinanceId(dto.getFinanceId());
		entity.setAge(dto.getAge());
		entity.setBrandId(dto.getBrandId());
		entity.setBrandSeriesId(dto.getBrandSeriesId());
		entity.setProvinceId(dto.getProvinceId());
		entity.setCityId(dto.getCityId());
		entity.setName(dto.getName());
		entity.setIdcardNo(dto.getCardNo());
		entity.setSex(dto.getSex());
		entity.setMobile(dto.getMobile());
		entity.setMark(dto.getMark());
		entity.setCreateTime(DateUtil.getNowTimestamp());
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		entity.setUpdateBy(0);
		entity.setStatus(Constants.FEEDBACK_STATUS.STAY_HANDLE);
		
		int ret = financeCommitService.save(entity);
		ReturnData data = new ReturnData();
		if(ret != 0){
			data.setCode(Constants.STATUS_CODE.SUCCESS);
			data.setMessage("谢谢您的提交，我们会尽快审核");
			renderJson(data, response);
			return;
		}else{
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("提交失败，请重试");
			renderJson(data, response);
			return;
		}
	}
	
	@RequestMapping("/queryQuestionAnswerList")
	public void queryQuestionAnswerList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("typeCd", dto.getTypeCd());
		map.put("flg", 1);

		List<TQuestionAnswerEntity> stList = qanswerService.queryQAList(map);
		List<PCQuestionAnswerListModel> models = new ArrayList<>();
		PCQuestionAnswerListModel slm = null;
		for (TQuestionAnswerEntity e : stList) {
			slm = new PCQuestionAnswerListModel();
			slm.setAnswer(e.getAnswer());
			slm.setQuestion(e.getQuestion());
			models.add(slm);
		}
		int count = qanswerService.queryQATotal(map);
		json.put("count", count);
		json.put("questionAnswerList", models);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//在线咨询
	@RequestMapping("/submitQuestion")
	public void submitQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		TQuestionEntity entity = new TQuestionEntity();
		
		entity.setQuestion(dto.getMark());
		entity.setLinkMan(dto.getName());
		entity.setMobile(dto.getMobile());
		entity.setCreateBy(0);
		entity.setCreateTime(DateUtil.getNowTimestamp());
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		entity.setUpdateBy(0);
		entity.setStatus(Constants.FEEDBACK_STATUS.STAY_HANDLE);
		entity.setTypeCd(Constants.QUESTION_TYPE.PC);
		entity.setCartflg(0);
		entity.setCartId(0);
		entity.setEmployeeId(0);
		
		int ret = qService.save(entity);
		ReturnData data = new ReturnData();
		if(ret != 0){
			data.setCode(Constants.STATUS_CODE.SUCCESS);
			data.setMessage("谢谢您的提交，我们会尽快给您反馈");
			renderJson(data, response);
			return;
		}else{
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("提交失败，请重试");
			renderJson(data, response);
			return;
		}
	}
	
	//获取金融申请初始化的品牌和车系
	@RequestMapping("/queryAllBrandData")
	public void queryAllBrandData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		List<TBrandEntity> list = brandService.queryAllList(1);
		List<BrandJsonModel> brandList = new ArrayList<>();
		BrandJsonModel brandModel = null;
		for(TBrandEntity entity : list){
			brandModel = new BrandJsonModel();
			brandModel.setId(entity.getId());
			brandModel.setName(entity.getBrand());
			brandModel.setPid(0);
			
			
			BrandSeriesMJsonModel seriesModel = null;
			List<BrandSeriesMJsonModel> models = new ArrayList<>();
			List<TBrandSeriesEntity> sList = brandSeriesDao.queryCarSeriesList(entity.getId());
			for(TBrandSeriesEntity b : sList) {
				seriesModel = new BrandSeriesMJsonModel();
				seriesModel.setId(b.getId());
				seriesModel.setName(b.getCarSerial());
				models.add(seriesModel);
			}
			brandModel.setModels(models);
			brandList.add(brandModel);
		}
		json.put("brandList", brandList);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//汽车详情页面，对具体汽车的咨询
	@RequestMapping("/submitConsult")
	public void submitConsult(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		String code = dto.getCode();
		String mobile = dto.getMobile();
		TVertifyCodeEntity vCode = vertifyCodeService.queryObjectByMobile(mobile,Constants.SHORT_MESSAGE_TYPE.SUMBIT_QUESTION);
		Timestamp expireTime = vCode == null ? null : (Timestamp)vCode.getExpireTime();
		Timestamp now = DateUtil.getNowTimestamp();
		if(expireTime == null){
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("对不起，您还没有获取验证码");
			renderJson(data, response);
			return;
		}
		
		if((expireTime != null)&&(now.after(expireTime))){
			//true，就是过期了
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("验证码过期了，请重新获取");
			renderJson(data, response);
			return;
		}
		
		if((expireTime != null) && (expireTime.after(now))){
			//没有过期，获取数据库验证码
			String dcode = vCode.getCode();
			if(!StringUtil.equals(code, dcode)){
				//验证码错误
				data.setCode(Constants.STATUS_CODE.FAIL);
				data.setMessage("请输入正确的验证码");
				renderJson(data, response);
				return;
			}
		}
		
		TQuestionEntity entity = new TQuestionEntity();
		
		entity.setCartId(dto.getCartId());
		entity.setLinkMan(dto.getName());
		entity.setMobile(dto.getMobile());
		entity.setQuestion("官网提交咨询:"+dto.getMark());
		entity.setCreateBy(0);
		entity.setCreateTime(DateUtil.getNowTimestamp());
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		entity.setUpdateBy(0);
		entity.setStatus(Constants.FEEDBACK_STATUS.STAY_HANDLE);
		entity.setTypeCd(Constants.QUESTION_TYPE.PC);
		entity.setCartflg(dto.getCartFlg());
		
		int ret = qService.save(entity);
		if(ret != 0){
			//更新验证码过期
			vertifyCodeService.updateExpireCode(vCode.getId(), DateUtil.getNowTimestamp());
			data.setCode(Constants.STATUS_CODE.SUCCESS);
			data.setMessage("谢谢您的提交，我们会尽快给您反馈");
			renderJson(data, response);
			return;
		}else{
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("提交失败，请重试");
			renderJson(data, response);
			return;
		}
	}
	
	//平行进口车详情
	@RequestMapping("/queryImportCarDetail")
	public void queryImportCarDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		TCarImportEntity car = importService.queryObject(dto.getCartId());
		if(car == null){
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("您来晚啦，此车已下架");
			renderJson(data, response);
			return;
		}
		ImportCartDetailModel model = new ImportCartDetailModel();
		model.setFavourMoney(StringUtil.formatCarPrice(car.getFavourMoney(), 0));
		model.setCartId(car.getId());
		List<String> icons = new ArrayList<>();
		icons.add(car.getPcIcon());
		icons.add(car.getPcIcon());
		icons.add(car.getPcIcon());
		model.setIcons(icons);
		TBrandEntity brand = brandService.queryObject(car.getBrand());
		if(brand != null){
			TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObject(car.getCarSeriesId());
			if(brandSeriesEntity != null){
				model.setCarName(brand.getBrand()+brandSeriesEntity.getCarSerial()+" "+car.getCarName());
			}else{
				model.setCarName(car.getCarName());
			}
		}else{
			model.setCarName(car.getCarName());
		}
		TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(car.getCarSeriesId());
		if(seriesEntity != null){
			model.setCarSeriesName(seriesEntity.getCarSerial());
		}else{
			model.setCarSeriesName("");
		}
		
		model.setColors(car.getCarColor());
		model.setReferenPrice(StringUtil.formatCarPrice(car.getNowPrice(),0));
		model.setMarketPrice(StringUtil.formatCarPrice(car.getMarketPrice(), 0));
		model.setMaxSavePrice(StringUtil.formatCarPrice(car.getMaxSave(), 0));
		TCodemstEntity mst = codeMstDao.queryByCode(car.getCarClassCd());
		model.setHot(StringUtil.toString(car.getHot()));
		importService.updateHot(dto.getCartId());
		if(mst != null){
			model.setClassType(mst.getName());
		}
		TCodemstEntity level = codeMstDao.queryByCode(car.getCarLevelCd());
		if(level != null){
			model.setCartType(level.getName());
		}
		model.setDescUrl(StringUtil.cutBodyHeader(car.getContent()));
		TCartParamsEntity params = paramsService.queryObjectByCartId(car.getId(),Constants.CAR_SALE_TYPE.IMPORT);
		
		if(params != null){
			model.setCheshenjiegou(params.getCheshenjiegou());
			model.setFadongjixinghao(params.getFadongjixinghao());
			model.setQudongtype(params.getQudongtype());
			model.setHeight(params.getHeight());
			model.setWidth(params.getWidth());
			model.setLength(params.getLength());
			model.setBiansuxiangtype(params.getBiansuxiangtype());
			model.setRanliaotype(params.getRanliaotype());
			model.setZhuchezhidongtype(params.getZhuchezhidongtype());
			model.setPailiang(params.getPailiang()+"ml");
			model.setGongyoutype(params.getGongyoutype());
		}
		//获取常见问题图片
		TCarouselEntity oftenQuestionUrl = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_IMPORT_CAR_OFTENQUESTION);
		if(oftenQuestionUrl != null){
			model.setOftenQuestionUrl(oftenQuestionUrl.getImgUrl());
		}
		//客服电话
		TCodemstEntity kefuTel = codeMstDao.queryByCode(Constants.TEL_TYPE.KEFU);
		if(kefuTel != null){
			model.setCompanyMobile(kefuTel.getData2());
		}
		
		TCartParamsEntity params1 = paramsService.queryObjectByCartId(dto.getCartId(),Constants.CAR_SALE_TYPE.IMPORT);
		TCartParam2Entity params2 = params2Service.queryObjectByCartIdType(dto.getCartId(),Constants.CAR_SALE_TYPE.IMPORT);
		
		json.put("params1", params1);
		json.put("params2", params2);
		
		json.put("carDetail", model);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
		return;
	}
	
	//平行进出口车品牌
	@RequestMapping("/queryImportCarBrandList")
	public void queryImportCarBrandList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		List<TBrandEntity> list = brandService.queryShowBrandList(1);
		List<BrandModel> models = new ArrayList<>();
		BrandModel model = null;
		for(TBrandEntity entity : list){
			model = new BrandModel();
			model.setBrand(entity.getBrand());
			model.setId(entity.getId());
			model.setBrandIcon(entity.getBrandIcon());
			
			models.add(model);
		}
		json.put("brandList", models);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//会淘车详情
	@RequestMapping("/querySecondhandCarDetail")
	public void querySecondhandCarDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		TCarSecondhandEntity car = secondService.queryObject(dto.getCartId());
		if(car == null){
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("您来晚啦，此车已下架");
			renderJson(data, response);
			return;
		}
		SecondhandCarPCDetailModel model = new SecondhandCarPCDetailModel();
		model.setCartId(car.getId());
		model.setTitleLabel(car.getTitleLabel());
		TBrandEntity brand = brandService.queryObject(car.getBrand());
		if(brand != null){
			TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObject(car.getCarSeriesId());
			if(brandSeriesEntity != null){
				model.setCarName(brand.getBrand()+brandSeriesEntity.getCarSerial()+" "+car.getCarName());
			}else{
				model.setCarName(car.getCarName());
			}
		}else{
			model.setCarName(car.getCarName());
		}
		model.setPeriods(StringUtil.toString(car.getPeriods()));
		TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(car.getCarSeriesId());
		if(seriesEntity != null){
			model.setCarSeriesName(seriesEntity.getCarSerial());
		}else{
			model.setCarSeriesName("");
		}
		List<String> icons = new ArrayList<>();
		icons.add(car.getPcIcon());
		icons.add(car.getPcIcon());
		icons.add(car.getPcIcon());
		model.setIcons(icons);
		model.setLabels(car.getLabels());
		//36期首付和月供、备注、分期数
		model.setFirstPayment(StringUtil.formatCarPrice(car.getFinalPayment(),0));
		model.setMonthPayment(StringUtil.formatCarPrice(car.getMonthPayment(), 1));
		model.setContainTaxPrice(StringUtil.formatCarPrice(car.getCarTaxCost(), 0));
		model.setDescUrl(StringUtil.cutBodyHeader(car.getContent()));
		model.setYear(DateUtil.formatCNYM(car.getYear()));
		model.setKilomiter(StringUtil.formatCarPrice(car.getKilomiters(), 0)+"公里");
		LocationCityEntity city = cityDao.queryObject(car.getCityId());
		
		/////0724修改
		//48期首付和月供、备注、分期数
		model.setFirstPayment1(StringUtil.formatCarPrice(car.getFirstPayment1(),0));
		model.setMonthPayment1(StringUtil.formatCarPrice(car.getMonthPayment1(), 1));
		
		//1+3首年首付、首年月供、一年后分期数、一年后分期月供
		model.setTfirstYearFirstPay(StringUtil.formatCarPrice(car.getTfirstYearFirstPay(),0));
		model.setTfirstYearMonthPayment(StringUtil.formatCarPrice(car.getTfirstYearMonthPayment(),1));
		model.setTperiods(StringUtil.toString(car.getTperiods()));
		model.setTmonthPayment(StringUtil.formatCarPrice(car.getTmonthPayment(),1));
		model.setFinalPayment(StringUtil.formatCarPrice(car.getFinalPayment(), 0));
		
		model.setBuyPay(StringUtil.formatCarPrice(car.getRealFirstPayment(),0));
		model.setServiceFee(StringUtil.formatCarPrice(car.getServiceFee(),1));
		/////////////////
		if(car.getFirstPayment() != null){
			model.setFlg1(1);
		}else{
			model.setFlg1(0);
		}
		if(car.getFirstPayment1() != null){
			model.setFlg2(1);
		}else{
			model.setFlg2(0);
		}
		if(car.getTfirstYearFirstPay() != null){
			model.setFlg3(1);
		}else{
			model.setFlg3(0);
		}
		
		if(city != null){
			model.setCity(city.getName());
		}
		
		TCartParamsEntity params = paramsService.queryObjectByCartId(car.getId(),Constants.CAR_SALE_TYPE.SECONDHAND);
		
		if(params != null){
			model.setCheshenjiegou(params.getCheshenjiegou());
			model.setFadongjixinghao(params.getFadongjixinghao());
			model.setQudongtype(params.getQudongtype());
			model.setHeight(params.getHeight());
			model.setWidth(params.getWidth());
			model.setLength(params.getLength());
			model.setBiansuxiangtype(params.getBiansuxiangtype());
			model.setRanliaotype(params.getRanliaotype());
			model.setZhuchezhidongtype(params.getZhuchezhidongtype());
			model.setPailiang(params.getPailiang()+"ml");
			model.setGongyoutype(params.getGongyoutype());
		}
		//获取购买说明
		TCarouselEntity buyMark = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_SECONDHAND_CAR_BUYMARK);
		if(buyMark != null){
			model.setBuyMarkUrl(buyMark.getImgUrl());
		}
		//购买须知
		TCarouselEntity buyKnow = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_SECONDHAND_CAR_BUYKONW);
		if(buyKnow != null){
			model.setBuyKnowUrl(buyKnow.getImgUrl());
		}
		//客服电话
		TCodemstEntity kefuTel = codeMstDao.queryByCode(Constants.TEL_TYPE.KEFU);
		if(kefuTel != null){
			model.setCompanyMobile(kefuTel.getData2());
		}
		
		TCartParamsEntity params1 = paramsService.queryObjectByCartId(dto.getCartId(),Constants.CAR_SALE_TYPE.SECONDHAND);
		TCartParam2Entity params2 = params2Service.queryObjectByCartIdType(dto.getCartId(),Constants.CAR_SALE_TYPE.SECONDHAND);
		
		json.put("params1", params1);
		json.put("params2", params2);
		
		json.put("carDetail", model);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//惠搜车详情
	@RequestMapping("/queryLeaseCarDetail")
	public void queryLeaseCarDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		TCarLeaseEntity car = leaseService.queryObject(dto.getCartId());
		if(car == null){
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("您来晚啦，此车已下架");
			renderJson(data, response);
			return;
		}
		LeaseCarDetailModel model = new LeaseCarDetailModel();
		model.setCartId(car.getId());
		TBrandEntity brand = brandService.queryObject(car.getBrand());
		if(brand != null){
			TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObject(car.getCarSeriesId());
			if(brandSeriesEntity != null){
				model.setCarName(brand.getBrand()+brandSeriesEntity.getCarSerial()+" "+car.getCarName());
			}else{
				model.setCarName(car.getCarName());
			}
		}else{
			model.setCarName(car.getCarName());
		}
		TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(car.getCarSeriesId());
		if(seriesEntity != null){
			model.setCarSeriesName(seriesEntity.getCarSerial());
		}else{
			model.setCarSeriesName("");
		}
		
		ArrayList<String> logos = new ArrayList<>();
		logos.add(car.getPcIcon());
		logos.add(car.getPcIcon());
		logos.add(car.getPcIcon());
		model.setIcons(logos);
		model.setFirmPrice(StringUtil.formatCarPrice(car.getFirmCost(),0));
		model.setCarInfo(car.getCarTypeInfo());
		//48期首付和月供、备注、分期数
		model.setFirstPayment(StringUtil.formatCarPrice(car.getFirstPayment(),0));
		model.setMonthPayment(StringUtil.formatCarPrice(car.getMonthPayment(), 1));
		model.setMark(car.getMark());
		model.setPeriods(car.getPeriods());
		if(car.getFirstPayment() == null) {
			model.setFlg2(0);
		}else {
			model.setFlg2(1);
		}
		
		//36期首付、月供、备注、分期数
		model.setFirstPayment1(StringUtil.formatCarPrice(car.getFirstPayment1(),0));
		model.setMonthPayment1(StringUtil.formatCarPrice(car.getMonthPayment1(), 1));
		model.setMark1(car.getMark());
		model.setPeriods1(car.getPeriods());
		if(car.getFirstPayment1() == null) {
			model.setFlg1(0);
		}else {
			model.setFlg1(1);
		}
		//1+3首年首付、首年月供、一年后分期数、一年后分期月供
		model.setTfirstYearFirstPay(StringUtil.formatCarPrice(car.getTfirstYearFirstPay(),0));
		model.setTfirstYearMonthPayment(StringUtil.formatCarPrice(car.getTfirstYearMonthPayment(),1));
		model.setTperiods(car.getTperiods());
		model.setTmonthPayment(StringUtil.formatCarPrice(car.getTmonthPayment(),1));
		model.setFinalPayment(StringUtil.formatCarPrice(car.getFinalPayment(), 0));
		if(car.getTfirstYearFirstPay() == null) {
			model.setFlg3(0);
		}else {
			model.setFlg3(1);
		}
		model.setBuyPay(StringUtil.formatCarPrice(car.getRealFirstPayment(),0));
		model.setServiceFee(StringUtil.formatCarPrice(car.getServiceFee(),1));
		//客服电话
		TCodemstEntity kefuTel = codeMstDao.queryByCode(Constants.TEL_TYPE.KEFU);
		if(kefuTel != null){
			model.setCompanyMobile(kefuTel.getData2());
		}
				
		model.setLabels(car.getLabels());
		
		model.setDescUrl(StringUtil.cutBodyHeader(car.getContent()));
		
		TCartParamsEntity params = paramsService.queryObjectByCartId(car.getId(),Constants.CAR_SALE_TYPE.LEASE);
		
		if(params != null){
			model.setCheshenjiegou(params.getCheshenjiegou());
			model.setFadongjixinghao(params.getFadongjixinghao());
			model.setQudongtype(params.getQudongtype());
			model.setHeight(params.getHeight());
			model.setWidth(params.getWidth());
			model.setLength(params.getLength());
			model.setBiansuxiangtype(params.getBiansuxiangtype());
			model.setRanliaotype(params.getRanliaotype());
			model.setZhuchezhidongtype(params.getZhuchezhidongtype());
			model.setPailiang(params.getPailiang()+"ml");
			model.setGongyoutype(params.getGongyoutype());
		}
		//获取购买说明
		TCarouselEntity buyMark = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_LEASE_CAR_BUYMARK);
		if(buyMark != null){
			model.setBuyMarkUrl(buyMark.getImgUrl());
		}
		//购买须知
		TCarouselEntity buyKnow = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_LEASE_CAR_BUYKONW);
		if(buyKnow != null){
			model.setBuyKnowUrl(buyKnow.getImgUrl());
		}
		model.setSalecount(StringUtil.toString(car.getSalecount()));
		
		TCartParamsEntity params1 = paramsService.queryObjectByCartId(dto.getCartId(),Constants.CAR_SALE_TYPE.LEASE);
		TCartParam2Entity params2 = params2Service.queryObjectByCartIdType(dto.getCartId(),Constants.CAR_SALE_TYPE.LEASE);
		
		json.put("params1", params1);
		json.put("params2", params2);
		
		
		json.put("carDetail", model);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	@RequestMapping("/queryCarParams")
	public void queryCarParams(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		int cartId = dto.getCartId();
		if(cartId == 0){
			data.setCode(Constants.STATUS_CODE.FAIL);
			data.setMessage("您来晚啦，此车已下架");
			renderJson(data, response);
			return;
		}
		
		TCartParamsEntity params1 = paramsService.queryObjectByCartId(cartId,dto.getTypeCd());
		TCartParam2Entity params2 = params2Service.queryObjectByCartIdType(cartId,dto.getTypeCd());
		
		json.put("params1", params1);
		json.put("params2", params2);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	//查看关于我们的数据列表
	@RequestMapping("/queryDocumentList")
	public void queryDocumentList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		List<TCodemstEntity> mstList = codeMstDao.queryByCodeList(Constants.DOCUMENT_ABOUTUS.PCODE);
		String[] objects = new String[mstList.size()];
		for(int i=0;i<mstList.size();i++){
			TCodemstEntity mst = mstList.get(i);
			if(mst != null){
				objects[i]=mst.getCode();
			}
		}
		
		List<TDocumentEntity> documents = documentService.queryListByTypeCd(objects);
		List<DocumentModel> models = new ArrayList<>();
		DocumentModel model = null;
		for(TDocumentEntity entity : documents){
			model = new DocumentModel();
			model.setCode(entity.getTypeCd());
			model.setDocumentName(entity.getTitle());
			model.setDocumentUrl(StringUtil.cutBodyHeader(entity.getContent()));
			models.add(model);
		}
		
		json.put("documentList", models);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	@RequestMapping("/common")
	public void common(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		
		//获取店招图片
		TCarouselEntity storyImage = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.STORY_ZHAO);
		if(storyImage != null) {
			json.put("storeImage", storyImage.getImgUrl());
		}else {
			json.put("storeImage", "");
		}
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	@RequestMapping("/queryLeaseCarByBrandList")
	public void queryLeaseCarByBrandList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<>();
		map.put("offset", dto.getPageSize() * (dto.getPageNum() - 1));
		map.put("limit", dto.getPageSize());
		map.put("brand", dto.getBrand());
		
		List<TCarLeaseEntity> list = leaseService.queryPCTerminalByBrandList(map);
		List<LeaseCarPCListModel> pcListModels = new ArrayList<>();
		LeaseCarPCListModel model = null;
		for(TCarLeaseEntity e : list){
			
			model = new LeaseCarPCListModel();
			model.setIcon(e.getIcon());
			model.setId(e.getId());
			model.setFirstPayment(StringUtil.formatCarPrice(e.getFirstPayment(), 0));
			model.setMonthPayment(StringUtil.formatCarPrice(e.getMonthPayment(), 1));
			model.setLabels(e.getLabels());
			model.setName(e.getCarName());
			model.setTitleLabel(e.getTitleLabel());
			TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObject(e.getCarSeriesId());
			if(seriesEntity != null) {
				model.setBrandSeries(seriesEntity.getCarSerial());
			}else {
				model.setBrandSeries("");
			}
			
			pcListModels.add(model);
		}
		//查询结果集总条数
		int count = leaseService.queryPCTerminalByBrandTotal(map);
		json.put("count", count);
		json.put("leaseCarList", pcListModels);
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	@RequestMapping("/queryCustomerService")
	public void queryCustomerService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		json.put("address", "厦门市翔安区马巷镇巷西路611号");
		json.put("emailCode", "3610000");
		json.put("phone", "0592-6597999");
		json.put("longtitude", "118.24338197705003");
		json.put("latitude", "24.674669004378305");
		json.put("companyName", "厦门永翔群汽车贸易有限公司");
		TCarouselEntity serviceImg = carouselService.queryByTypeCd(Constants.CAROUSEL_TYPE.PC_AFTER_SALE);
		if(serviceImg != null) {
			json.put("customerServiceImg", serviceImg.getImgUrl());
		}else {
			json.put("customerServiceImg", "");
		}
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
	
	@RequestMapping("/queryStoreOrNews")
	public void queryStoreOrNews(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		JSONObject json = new JSONObject();
		String flg = dto.getTypeCd();
		if(StringUtil.equals(flg, "1")) {
			//车主故事
			TStoryEntity storyEntity = storyService.queryObject(dto.getKey());
			if(storyEntity!=null) {
				json.put("detail", StringUtil.cutBodyHeader(storyEntity.getContent()));
			}else {
				json.put("detail", "");
			}
		}else if(StringUtil.equals(flg, "2")){
			//资讯
			TNewsEntity newsEntity = newsService.queryObject(dto.getKey());
			if(newsEntity != null) {
				json.put("detail", StringUtil.cutBodyHeader(newsEntity.getContent()));
			}else {
				json.put("detail", "");
			}
		}else {
			json.put("detail", "");
		}
		
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
}
