package com.framework.controller;

import com.alibaba.fastjson.JSON;
import com.framework.constants.Constants;
import com.framework.entity.MallProduct;
import com.framework.service.FileService;
import com.framework.service.MallProductService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 商城商品
 */
@RestController
@RequestMapping("/mall")
public class MallProductController extends AbstractController {

	@Autowired
	private MallProductService mallProductService;

	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("mall:list")
	public R list(Integer page, Integer limit,String productTitle,String price, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("productTitle", productTitle);
		map.put("price", StringUtils.isNotBlank(price)? new BigDecimal(price):null);
		map.put("status", status);

		// 查询列表数据
		List<MallProduct> menuList = mallProductService.queryList(map);
		int total = mallProductService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(menuList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 商品信息
	 */
	@RequestMapping("/info/{productId}")
	@RequiresPermissions("mall:info")
	public R info(@PathVariable("productId") Long productId) {
		MallProduct MallProduct = mallProductService.queryObject(productId);
		return R.ok().put("mallProduct", MallProduct);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("mall:save")
	public R save(@RequestParam("mallProduct")String mallProduct) {
		MallProduct model = new MallProduct();
		model = JSON.parseObject(mallProduct, MallProduct.class);
		//处理图片logo及生成详情html
		try{
			dealHtml(model);
		} catch(Exception ex) {
			ex.printStackTrace();
			return R.error(ex.getMessage());
		}
		mallProductService.save(model);
		return R.ok();
	}

	/**
	 * 处理生成详情html
	 * @param mallProduct
	 */
	public void dealHtml(MallProduct mallProduct) throws Exception{
		//生成html文件
		String uuid = UUID.randomUUID().toString();
		String htmlContent = StringUtil.formatHTML(mallProduct.getProductTitle(), mallProduct.getProductDetail());
//		String host = "F:\\var\\www\\html\\file\\mallProduct";
//		PrintWriter pw = new PrintWriter(new OutputStreamWriter(
//				new FileOutputStream(host + uuid + ".html"),"utf-8"),true);
//		pw.println(htmlContent);
//		pw.close();
//		String contentUrl = host + uuid + ".html";

		PrintWriter pw = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(Constants.HTTPS_FILE_HOST.MALL_PRODUCT + uuid + ".html"),"utf-8"),true);
		pw.println(htmlContent);
		pw.close();
		String contentUrl = Constants.HTTPS_HOST.MALL_PRODUCT + uuid + ".html";
		mallProduct.setProductDetailUrl(contentUrl);
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("mall:update")
	public R update(@RequestParam("mallProduct")String mallProduct) {
		MallProduct model = new MallProduct();
		model = JSON.parseObject(mallProduct, MallProduct.class);
		//处理图片logo及生成详情html
		try{
			dealHtml(model);
		} catch(Exception ex) {
			ex.printStackTrace();
			return R.error(ex.getMessage());
		}
		mallProductService.update(model);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("mall:delete")
	public R delete(@RequestBody Long[] mallProductIds) {
		if(mallProductIds == null || mallProductIds.length<1){
			return R.error("ID不能为空");
		}
		mallProductService.deleteBatch(mallProductIds);
		return R.ok();
	}

	/**
	 * 下架
	 */
	@RequestMapping("/off")
	@RequiresPermissions("mall:off:loading")
	public R offLoading(@RequestBody String productId) {
		if(StringUtils.isBlank(productId)){
			return R.error("ID不能为空");
		}
		mallProductService.offLoading(Integer.parseInt(productId));
		return R.ok();
	}
}
