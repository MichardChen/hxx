package com.framework.restful;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.framework.dto.ParamsDTO;
import com.framework.utils.ReturnData;

@Controller
@RequestMapping("wxrest")
public class WXController extends RestfulController{

	@RequestMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ParamsDTO dto = ParamsDTO.getInstance(request);
		renderJson(new ReturnData(), response);
	}
}
