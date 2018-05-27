package com.framework.restful;

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
import com.framework.dao.TCodemstDao;
import com.framework.dto.ParamsDTO;
import com.framework.entity.LocationCityEntity;
import com.framework.entity.TBrandEntity;
import com.framework.entity.TCarImportEntity;
import com.framework.entity.TCarLeaseEntity;
import com.framework.entity.TCarSecondhandEntity;
import com.framework.entity.TCarouselEntity;
import com.framework.entity.TCodemstEntity;
import com.framework.entity.TNewsEntity;
import com.framework.entity.TStoryEntity;
import com.framework.model.StoryListModel;
import com.framework.restmodel.BrandModel;
import com.framework.restmodel.CarouselModel;
import com.framework.restmodel.ImportCarListModel;
import com.framework.restmodel.LeaseCarListModel;
import com.framework.restmodel.NewsListModel;
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
@RequestMapping("wxrest")
public class WXController extends RestfulController{

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
	
	@RequestMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		//查询轮播图
		List<TCarouselEntity> list = carouselService.queryListByTypeCd(Constants.CAROUSEL_TYPE.WX_INDEX);
		List<CarouselModel> models = new ArrayList<>();
		CarouselModel model = null;
		for(TCarouselEntity e : list) {
			model = new CarouselModel();
			model.setImgUrl(e.getImgUrl());
			model.setRealUrl(e.getRealUrl());
			models.add(model);
		}
		JSONObject json = new JSONObject();
		json.put("carousel", models);
		//以租代购、二手车、平行进口车横幅
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
		//车的图标
		Map<String, Object> map = new HashMap<>();
		map.put("offset", 0);
		map.put("limit", 9);
		List<TBrandEntity> brandsList = brandService.queryList(map);
		List<BrandModel> brandModels = new ArrayList<>();
		BrandModel bm = null;
		for(TBrandEntity e : brandsList) {
			bm = new BrandModel();
			bm.setBrand(e.getBrand());
			bm.setId(e.getId());
			bm.setBrandIcon(e.getBrandIcon());
			brandModels.add(bm);
		}
		json.put("brandList", brandModels);
		//以租代购
		Map<String, Object> carListMap = new HashMap<>();
		carListMap.put("offset", 0);
		carListMap.put("limit", 6);
		
		List<TCarLeaseEntity> clList = leaseService.queryList(carListMap);
		List<TCarImportEntity> ciList = importService.queryList(carListMap);
		List<TCarSecondhandEntity> csList = secondService.queryList(carListMap);
		
		List<LeaseCarListModel> leaseList = new ArrayList<>();
		List<ImportCarListModel> importList = new ArrayList<>();
		List<SecondHandCarListModel> secondList = new ArrayList<>();
		
		LeaseCarListModel lcm = null;
		for(TCarLeaseEntity e : clList) {
			lcm = new LeaseCarListModel();
			lcm.setFirstPayment(StringUtil.toString(e.getFirstPayment()));
			lcm.setIcon(e.getIcon());
			lcm.setId(e.getId());
			lcm.setMonthPayment(StringUtil.toString(e.getMonthPayment()));
			lcm.setName(e.getCarName());
			lcm.setPeriod(StringUtil.toString(e.getPeriods()));
			leaseList.add(lcm);
		}
		
		ImportCarListModel icl = null;
		for(TCarImportEntity e : ciList) {
			icl = new ImportCarListModel();
			icl.setIcon(e.getIcon());
			icl.setId(e.getId());
			icl.setLabels(e.getLabels());
			icl.setName(e.getCarName());
			icl.setNowPrice(StringUtil.toString(e.getNowPrice()));
			icl.setPrimePrice(StringUtil.toString(e.getMarketPrice()));
			importList.add(icl);
		}
		
		SecondHandCarListModel scl = null;
		for(TCarSecondhandEntity e : csList) {
			scl = new SecondHandCarListModel();
			scl.setIcon(e.getIcon());
			scl.setName(e.getCarName());
			scl.setKilometers(StringUtil.toString(e.getKilomiters()));
			scl.setMonthPayment(StringUtil.toString(e.getMonthPayment()));
			scl.setId(e.getId());
			scl.setDate(e.getYear());
			LocationCityEntity city = cityDao.queryObject(e.getCityId());
			scl.setCity(city == null ? "":city.getName());
			secondList.add(scl);
		}
		
		json.put("leaseList", leaseList);
		json.put("importList", importList);
		json.put("secondList", secondList);
		
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
		//资讯
		List<TNewsEntity> snList = newsService.queryList(carListMap);
		List<NewsListModel> newList = new ArrayList<>();
		NewsListModel nlm = null;
		for(TNewsEntity e : snList) {
			nlm = new NewsListModel();
			nlm.setIcon(e.getNewsLogo());
			nlm.setId(e.getId());
			nlm.setTitle(e.getNewsTitle());
			nlm.setUrl(e.getContentUrl());
			TCodemstEntity mst = codeMstDao.queryByCode(e.getNewsTypeCd());
			if(mst != null) {
				nlm.setType(mst.getName());
			}else {
				nlm.setType("");
			}
			newList.add(nlm);
		}
		json.put("storyList", storyList);
		json.put("newList", newList);
		
		data.setData(json);
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
}
