var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCarSecondhand:{}
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
		//获取省市区
		$.get("../common/queryProvince", function(r){
            var list =eval(r.provinceList);
	    	if(r.code === 0){
	    		var html = "";
	    		for(var b in list){
	    			//向品牌下拉框添加品牌
	    			html += "<option value='" + list[b].id + "'>" +list[b].name + "</option>";
	    		}
	    		 $("#provinceId").append(html);
	    		 $("#cityId").append("<option value=\"0\">请选择城市</option>");
        }});
    },
	methods: {
		getInfo: function(id){
			$.get("../tcarsecondhand/info/"+id, function(r){
                vm.tCarSecondhand = r.tCarSecondhand;
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
		 selectCity:function(){
				var id=$("#provinceId").val();
				if(id==0){
					$("#cityId").empty();
					$("#cityId").append("<option value=\"0\">请选择城市</option>");
				}else{
					$.get("../common/queryCity/"+id, function(r){
			            var list =eval(r.cityList);
				    	if(r.code === 0){
				    		var html = "";
				    		for(var b in list){
				    			//向品牌下拉框添加品牌
				    			html += "<option value='" + list[b].id + "'>" +list[b].name + "</option>";
				    		}
				    		$("#cityId").empty();
				    		 $("#cityId").append(html);
			        }});
				}
			},
		saveOrUpdate: function (event) {
			var fileObj = document.getElementById("uFile").files[0];
			var formFile = new FormData();
			var url = vm.tCarSecondhand.id == null ? "../tcarsecondhand/save" : "../tcarsecondhand/update";
			formFile.append("uFile", fileObj); 
			formFile.append("tCarSecondhand", JSON.stringify(vm.tCarSecondhand));
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
		},
		back: function (event) {
			history.go(-1);
		}
	}
});