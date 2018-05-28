package com.framework.restful;

import java.util.ArrayList;
import java.util.List;

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
import com.framework.entity.TBrandEntity;
import com.framework.entity.TCarouselEntity;
import com.framework.restmodel.BrandModel;
import com.framework.restmodel.CarouselModel;
import com.framework.service.TBrandService;
import com.framework.service.TCarImportService;
import com.framework.service.TCarLeaseService;
import com.framework.service.TCarSecondhandService;
import com.framework.service.TCarouselService;
import com.framework.service.TNewsService;
import com.framework.service.TStoryService;
import com.framework.utils.ReturnData;

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
		List<TBrandEntity> brands = brandService.queryAllList();
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
		renderJson(new ReturnData(), response);
	}
}
