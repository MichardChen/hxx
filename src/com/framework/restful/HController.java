package com.framework.restful;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.framework.constants.Constants;
import com.framework.dto.ParamsDTO;
import com.framework.utils.ReturnData;

@Controller
@RequestMapping("hrest")
public class HController extends RestfulController{

	@RequestMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		ReturnData data = new ReturnData();
		data.setCode(Constants.STATUS_CODE.SUCCESS);
		data.setMessage("查询成功");
		renderJson(data, response);
	}
}
