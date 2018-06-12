var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCarLease:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
		//获取所有品牌
		$.get("../tbrand/queryAllBrand", function(r){
            var list =eval(r.tBrandList);
	    	if(r.code === 0){
	    		var html = "";
	    		for(var b in list){
	    			//向品牌下拉框添加品牌
	    			html += "<option value='" + list[b].id + "'>" +list[b].brand + "</option>";
	    		}
	    		 $("#brand").append(html);
	    		 $("#series").append("<option value=\"0\">请选择车系</option>");
        }});
		
    },
	methods: {
		getInfo: function(id){
			$.get("../tcarlease/info/"+id, function(r){
                $("#cartId").val(r.tCarLease.id);
                $("#carName").val(r.tCarLease.carName);
                $("#brand").val(r.tCarLease.brand);
                $("#icon").val(r.tCarLease.icon);
                $("#year").val(r.tCarLease.year);
                $("#series").val(r.tCarLease.series);
                $("#carCost").val(r.tCarLease.carCost);
                $("#firmCost").val(r.tCarLease.firmCost);
                $("#carColor").val(r.tCarLease.carColor);
                $("#carTypeInfo").val(r.tCarLease.carTypeInfo);
                $("#titleLabel").val(r.tCarLease.titleLabel);
                $("#firstPayment1").val(r.tCarLease.firstPayment1);
                $("#monthPayment1").val(r.tCarLease.monthPayment1);
                $("#mark1").val(r.tCarLease.mark1);
                $("#labels").val(r.tCarLease.labels);
                $("#firstPayment").val(r.tCarLease.firstPayment);
                $("#monthPayment").val(r.tCarLease.monthPayment);
                $("#mark").val(r.tCarLease.mark);
                $("#tfirstYearFirstPay").val(r.tCarLease.tfirstYearFirstPay);
                $("#tfirstYearMonthPayment").val(r.tCarLease.tfirstYearMonthPayment);
                $("#tperiods").val(r.tCarLease.tperiods);
                $("#tmonthPayment").val(r.tCarLease.tmonthPayment);
                $("#finalPayment").val(r.tCarLease.finalPayment);
                $("#realFirstPayment").val(r.tCarLease.realFirstPayment);
                $("#serviceFee").val(r.tCarLease.serviceFee);
                $("#icon").attr("href",r.tCarLease.icon);
				$("#icon").show();
            });
		},
		selectBrand:function(){
			
			var id=$("#brand").val();
			if(id==0){
				$("#series").empty();
				$("#series").append("<option value=\"0\">请选择车系</option>");
			}else{
				$.get("../tbrandseries/queryBrandSeries/"+id, function(r){
		            var list =eval(r.tBrandSeries);
			    	if(r.code === 0){
			    		var html = "";
			    		for(var b in list){
			    			//向品牌下拉框添加品牌
			    			html += "<option value='" + list[b].id + "'>" +list[b].carSerial + "</option>";
			    		}
			    		$("#series").empty();
			    		 $("#series").append(html);
		        }});
			}
		},
		saveOrUpdate: function (event) {
			
			var cartId = $("#cartId").val();
			
			if(cartId == 0 || cartId == "" || cartId == null){
				
				//新增
				var fileObj = document.getElementById("uFile").files[0];
				var formFile = new FormData();
				if(!$("#carName").val()){
					alert("请输入汽车名称");
					return;
				}
				if(!$("#brand").val()){
					alert("请选择汽车品牌");
					return;
				}
			/*	if(!fileObj){
					alert("请选择汽车图片");
					return;
				}*/
				if(!$("#year").val()){
					alert("请输入先用年数");
					return;
				}
				if(!$("#series").val()){
					alert("请选择车系");
					return;
				}
				if(!$("#carCost").val()){
					alert("请输入车价");
					return;
				}
				if(!$("#firmCost").val()){
					alert("请输入厂商指导价");
					return;
				}
				if(!$("#carColor").val()){
					alert("请输入颜色编号");
					return;
				}
				if(!$("#carTypeInfo").val()){
					alert("请输入车辆信息");
					return;
				}
				if(!$("#titleLabel").val()){
					alert("请输入主标签");
					return;
				}
				if(!$("#labels").val()){
					alert("请输入标签");
					return;
				}
				if(!$("#firstPayment1").val()){
					alert("请输入36期首付");
					return;
				}
				if(!$("#monthPayment1").val()){
					alert("请输入36期月供");
					return;
				}
				if(!$("#mark1").val()){
					alert("请输入36期备注，如：包含首年保险，购置税，服务费");
					return;
				}
				if(!$("#firstPayment").val()){
					alert("请输入48期首付");
					return;
				}
				if(!$("#monthPayment").val()){
					alert("请输入48期月供");
					return;
				}
				if(!$("#mark").val()){
					alert("请输入48期备注，如：包含首年保险，购置税，服务费");
					return;
				}
				if(!$("#tfirstYearFirstPay").val()){
					alert("请输入1+3首年首付");
					return;
				}
				if(!$("#tfirstYearMonthPayment").val()){
					alert("请输入1+3月供");
					return;
				}
				if(!$("#tperiods").val()){
					alert("请输入1+3一年后分期数");
					return;
				}
				if(!$("#tmonthPayment").val()){
					alert("请输入1+3一年后分期月供");
					return;
				}
				if(!$("#finalPayment").val()){
					alert("请输入1+3尾款");
					return;
				}
				if(!$("#realFirstPayment").val()){
					alert("请输入1+3购车支付费用");
					return;
				}
				if(!$("#serviceFee").val()){
					alert("请输入1+3交车服务费用");
					return;
				}
				var url = "../tcarlease/save";
				formFile.append("uFile", fileObj); 
				formFile.append("tCarLease", JSON.stringify(vm.tCarLease));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    contentType: "application/json",
				    processData: false,
				    contentType: false,
				    success: function(r){
				    	if(r.code === 0){
							alert('操作成功', function(index){
								vm.back();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			}else{
				//更新
				if(!$("#carName").val()){
					alert("请输入汽车名称");
					return;
				}
				if(!$("#brand").val()){
					alert("请选择汽车品牌");
					return;
				}
				if(!$("#year").val()){
					alert("请输入先用年数");
					return;
				}
				if(!$("#series").val()){
					alert("请选择车系");
					return;
				}
				if(!$("#carCost").val()){
					alert("请输入车价");
					return;
				}
				if(!$("#firmCost").val()){
					alert("请输入厂商指导价");
					return;
				}
				if(!$("#carColor").val()){
					alert("请输入颜色编号");
					return;
				}
				if(!$("#carTypeInfo").val()){
					alert("请输入车辆信息");
					return;
				}
				if(!$("#titleLabel").val()){
					alert("请输入主标签");
					return;
				}
				if(!$("#labels").val()){
					alert("请输入标签");
					return;
				}
				if(!$("#firstPayment1").val()){
					alert("请输入36期首付");
					return;
				}
				if(!$("#monthPayment1").val()){
					alert("请输入36期月供");
					return;
				}
				if(!$("#mark1").val()){
					alert("请输入36期备注，如：包含首年保险，购置税，服务费");
					return;
				}
				if(!$("#firstPayment").val()){
					alert("请输入48期首付");
					return;
				}
				if(!$("#monthPayment").val()){
					alert("请输入48期月供");
					return;
				}
				if(!$("#mark").val()){
					alert("请输入48期备注，如：包含首年保险，购置税，服务费");
					return;
				}
				if(!$("#tfirstYearFirstPay").val()){
					alert("请输入1+3首年首付");
					return;
				}
				if(!$("#tfirstYearMonthPayment").val()){
					alert("请输入1+3月供");
					return;
				}
				if(!$("#tperiods").val()){
					alert("请输入1+3一年后分期数");
					return;
				}
				if(!$("#tmonthPayment").val()){
					alert("请输入1+3一年后分期月供");
					return;
				}
				if(!$("#finalPayment").val()){
					alert("请输入1+3尾款");
					return;
				}
				if(!$("#realFirstPayment").val()){
					alert("请输入1+3购车支付费用");
					return;
				}
				if(!$("#serviceFee").val()){
					alert("请输入1+3交车服务费用");
					return;
				}
				var fileObj = document.getElementById("uFile").files[0];
				var formFile = new FormData();
				var url = "../tcarlease/update";
				formFile.append("uFile", fileObj); 
				vm.tCarLease.id=$("#cartId").val();
				formFile.append("tCarLease", JSON.stringify(vm.tCarLease));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    contentType: "application/json",
				    processData: false,
				    contentType: false,
				    success: function(r){
				    	if(r.code === 0){
							alert('操作成功', function(index){
								vm.back();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			}
		},
		back: function (event) {
			history.go(-1);
		}
	}
});