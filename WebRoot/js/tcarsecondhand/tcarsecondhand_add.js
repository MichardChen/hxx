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
		$.ajaxSetup({ 
		    async : false 
		});
		//获取所有品牌
		$.get("../tbrand/queryAllBrand", function(rr){
			var r =eval('('+rr+')');
            var list = r.tBrandList;
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
		$.get("../common/queryProvince", function(rr){
            var r =eval('('+rr+')');
            var list = r.provinceList;
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
			$.get("../tcarsecondhand/info/"+id, function(json){
				var r = eval('('+json+')');
                $("#carName").val(r.tCarSecondhand.carName);
                $("#brand").val(r.tCarSecondhand.brand);
                $("#carTaxCost").val(r.tCarSecondhand.carTaxCost);
                $("#periods").val(r.tCarSecondhand.periods);
                $("#labels").val(r.tCarSecondhand.labels);
                $("#finalPayment").val(r.tCarSecondhand.finalPayment);
                $("#cartId").val(r.tCarSecondhand.id);
                $("#icon").attr("href",r.tCarSecondhand.icon);
				$("#icon").show();
				$("#pcIcon").attr("href",r.tCarSecondhand.pcIcon);
				$("#pcIcon").show();
				$("#content").summernote('code', r.tCarSecondhand.content);
				  $("#titleLabel").val(r.tCarSecondhand.titleLabel);
	                $("#provinceId").val(r.tCarSecondhand.provinceId);
	                $("#kilomiters").val(r.tCarSecondhand.kilomiters);
	                $("#year").val(r.tCarSecondhand.year);
	                $("#firstPayment").val(r.tCarSecondhand.firstPayment);
	                $("#monthPayment").val(r.tCarSecondhand.monthPayment);
	                $("#age").val(r.tCarSecondhand.age);
	                $("#carLevelCd").val(r.tCarSecondhand.carLevelCd);
	                $("#carColor").val(r.tCarSecondhand.carColor);
	                $("#carCost").val(r.tCarSecondhand.carCost);
	                $("#flg").val(r.tCarSecondhand.flg);
	                //0724新增
	                $("#mark3").val(r.tCarSecondhand.mark3);
	                $("#firstPayment1").val(r.tCarSecondhand.firstPayment1);
	                $("#monthPayment1").val(r.tCarSecondhand.monthPayment1);
	                $("#periods1").val(r.tCarSecondhand.periods1);
	                $("#mark1").val(r.tCarSecondhand.mark1);
	                $("#tfirstYearFirstPay").val(r.tCarSecondhand.tfirstYearFirstPay);
	                $("#tfirstYearMonthPayment").val(r.tCarSecondhand.tfirstYearMonthPayment);
	                $("#tperiods").val(r.tCarSecondhand.tperiods);
	                $("#tmonthPayment").val(r.tCarSecondhand.tmonthPayment);
	                $("#realFirstPayment").val(r.tCarSecondhand.realFirstPayment);
	                $("#serviceFee").val(r.tCarSecondhand.serviceFee);
	                $("#showflg").val(r.tCarSecondhand.showFlg);
	                
	                $.get("../common/queryCity/"+r.tCarSecondhand.provinceId, function(rr){
						var dr = eval('('+rr+')');
			            var list = dr.cityList;
				    	if(dr.code === 0){
				    		var html = "";
				    		for(var b in list){
				    			//向品牌下拉框添加品牌
				    			if(list[b].id == r.tCarSecondhand.cityId){
				    				html += "<option value='" + list[b].id + "' selected='selected'>" +list[b].name + "</option>";
				    			}else{
				    				html += "<option value='" + list[b].id + "'>" +list[b].name + "</option>";
				    			}
				    		}
				    		$("#cityId").empty();
				    		 $("#cityId").append(html);
			        }});
	                
                $.get("../tbrandseries/queryBrandSeries/"+r.tCarSecondhand.brand, function(rr){
					var dr =eval('('+rr+')');
			        var list =dr.tBrandSeries;
			    	if(dr.code === 0){
			    		var html = "";
			    		for(var b in list){
			    			//向品牌下拉框添加品牌
			    			if(list[b].id == r.tCarSecondhand.series){
			    				html += "<option value='" + list[b].id + "' selected='selected'>" +list[b].carSerial + "</option>";
			    			}else{
			    				html += "<option value='" + list[b].id + "'>" +list[b].carSerial + "</option>";
			    			}
			    		}
			    		$("#series").empty();
			    		 $("#series").append(html);
		        }});
            });
		},
		selectBrand:function(){
			var id=$("#brand").val();
			if(id==0){
				$("#series").empty();
				$("#series").append("<option value=\"0\">请选择车系</option>");
			}else{
				$.get("../tbrandseries/queryBrandSeries/"+id, function(rr){
					var r =eval('('+rr+')');
			        var list = r.tBrandSeries;
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
					$.get("../common/queryCity/"+id, function(rr){
						var r = eval('('+rr+')');
			            var list = r.cityList;
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
			
			var cartId = $("#cartId").val();
			
			vm.tCarSecondhand.carName = $("#carName").val();
			vm.tCarSecondhand.brand = $("#brand").val();
			vm.tCarSecondhand.provinceId = $("#provinceId").val();
			vm.tCarSecondhand.cityId = $("#cityId").val();
			vm.tCarSecondhand.kilomiters = $("#kilomiters").val();
			vm.tCarSecondhand.year = $("#year").val();
			vm.tCarSecondhand.firstPayment = $("#firstPayment").val();
			vm.tCarSecondhand.monthPayment = $("#monthPayment").val();
			vm.tCarSecondhand.age = $("#age").val();
			vm.tCarSecondhand.carLevelCd = $("#carLevelCd").val();
			vm.tCarSecondhand.carColor = $("#carColor").val();
			vm.tCarSecondhand.carCost = $("#carCost").val();
			vm.tCarSecondhand.titleLabel = $("#titleLabel").val();
			vm.tCarSecondhand.carSeriesId = $("#series").val();
			vm.tCarSecondhand.carTaxCost = $("#carTaxCost").val();
			vm.tCarSecondhand.periods = $("#periods").val();
			vm.tCarSecondhand.labels = $("#labels").val();
			vm.tCarSecondhand.finalPayment = $("#finalPayment").val();
			vm.tCarSecondhand.flg = $("#flg").val();
			//0724新增
			vm.tCarSecondhand.mark3 =  $("#mark3").val();
			vm.tCarSecondhand.firstPayment1 =  $("#firstPayment1").val();
			vm.tCarSecondhand.monthPayment1 =  $("#monthPayment1").val();
			vm.tCarSecondhand.periods1 =  $("#periods1").val();
			vm.tCarSecondhand.mark1 =  $("#mark1").val();
			vm.tCarSecondhand.tfirstYearFirstPay =  $("#tfirstYearFirstPay").val();
			vm.tCarSecondhand.tfirstYearMonthPayment =  $("#tfirstYearMonthPayment").val();
			vm.tCarSecondhand.tperiods =  $("#tperiods").val();
			vm.tCarSecondhand.tmonthPayment =  $("#tmonthPayment").val();
			vm.tCarSecondhand.realFirstPayment =  $("#realFirstPayment").val();
			vm.tCarSecondhand.serviceFee =  $("#serviceFee").val();
			vm.tCarSecondhand.showFlg =  $("#showflg").val();
			
			if(cartId == "" || cartId == null){
				//新增
				var fileObj = document.getElementById("uFile").files[0];
				var fileObj1 = document.getElementById("uFile1").files;
				var formFile = new FormData();
				
				if(!$("#carName").val()){
					alert("请输入车名");
					return;
				}
				if(!$("#brand").val()){
					alert("请选择汽车品牌");
					return;
				}
				if(!fileObj){
					alert("请选择汽车图片");
					return;
				}
				if(!$("#titleLabel").val()){
					alert("请输入封面标签");
					return;
				}
				if(!$("#provinceId").val()){
					alert("请选择省份");
					return;
				}
				if(!$("#cityId").val()){
					alert("请选择城市");
					return;
				}
				if(!$("#kilomiters").val()){
					alert("请输入公里数");
					return;
				}
				if($("#year").val()==""){
					alert("请选择汽车年限");
					return;
				}
				if(!$("#realFirstPayment").val()){
					alert("请输入购车支付费用");
					return;
				}
				if(!$("#serviceFee").val()){
					alert("请输入交车服务费用");
					return;
				}
				
				/*if(!$("#firstPayment").val()){
					alert("请输入首付");
					return;
				}
				if(!$("#monthPayment").val()){
					alert("请输入月供");
					return;
				}*/
				if(!$("#age").val()){
					alert("请输入车龄");
					return;
				}
				if(!$("#carLevelCd").val()){
					alert("请选择车型");
					return;
				}
				/*if(!$("#carColor").val()){
					alert("请输入颜色编号");
					return;
				}*/
				if(!$("#carCost").val()){
					alert("请输入车价");
					return;
				}
				if(!$("#series").val()){
					alert("请选择车系");
					return;
				}
				if(!$("#carTaxCost").val()){
					alert("请输入新车含税价");
					return;
				}
				if(!$("#periods").val()){
					alert("请输入已使用年数");
					return;
				}
				if(!$("#labels").val()){
					alert("请输入标签");
					return;
				}
				/*if(!$("#finalPayment").val()){
					alert("请输入尾款");
					return;
				}*/
				var url = "../tcarsecondhand/save";
				formFile.append("uFile", fileObj); 
				var fileLength = fileObj1.length;
				formFile.append("fileLength",fileLength);
				for(i=0;i<fileObj1.length;i++){    
					formFile.append("file"+i, fileObj1[i]); //++++++++++  
               } 
				vm.tCarSecondhand.content = $("#content").summernote('code');
				formFile.append("tCarSecondhand", JSON.stringify(vm.tCarSecondhand));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    contentType: "application/json",
				    processData: false,
				    contentType: false,
				    success: function(json){
				    	var r = eval('('+json+')');
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
					alert("请输入车名");
					return;
				}
				if(!$("#brand").val()){
					alert("请选择汽车品牌");
					return;
				}
				
				if(!$("#titleLabel").val()){
					alert("请输入封面标签");
					return;
				}
				if(!$("#provinceId").val()){
					alert("请选择省份");
					return;
				}
				if(!$("#cityId").val()){
					alert("请选择城市");
					return;
				}
				if(!$("#kilomiters").val()){
					alert("请输入公里数");
					return;
				}
				if($("#year").val()==""){
					alert("请选择汽车年限");
					return;
				}
				
				if(!$("#realFirstPayment").val()){
					alert("请输入购车支付费用");
					return;
				}
				if(!$("#serviceFee").val()){
					alert("请输入交车服务费用");
					return;
				}
				/*if(!$("#firstPayment").val()){
					alert("请输入首付");
					return;
				}
				if(!$("#monthPayment").val()){
					alert("请输入月供");
					return;
				}*/
				if(!$("#age").val()){
					alert("请输入车龄");
					return;
				}
				if(!$("#carLevelCd").val()){
					alert("请选择车型");
					return;
				}
			/*	if(!$("#carColor").val()){
					alert("请输入颜色编号");
					return;
				}*/
				if(!$("#carCost").val()){
					alert("请输入车价");
					return;
				}
				if(!$("#series").val()){
					alert("请选择车系");
					return;
				}
				if(!$("#carTaxCost").val()){
					alert("请输入新车含税价");
					return;
				}
				if(!$("#periods").val()){
					alert("请输入已使用年数");
					return;
				}
				if(!$("#labels").val()){
					alert("请输入标签");
					return;
				}
				/*if(!$("#finalPayment").val()){
					alert("请输入尾款");
					return;
				}*/
				var fileObj = document.getElementById("uFile").files[0];
				var fileObj1 = document.getElementById("uFile1").files;
				var formFile = new FormData();
				var url = "../tcarsecondhand/update";
				formFile.append("uFile", fileObj); 
				
				var fileLength = fileObj1.length;
				formFile.append("fileLength",fileLength);
				for(i=0;i<fileObj1.length;i++){    
					formFile.append("file"+i, fileObj1[i]); //++++++++++  
				}  
				
				vm.tCarSecondhand.id=cartId;
				vm.tCarSecondhand.content = $("#content").summernote('code');
				formFile.append("tCarSecondhand", JSON.stringify(vm.tCarSecondhand));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    contentType: "application/json",
				    processData: false,
				    contentType: false,
				    success: function(json){
				    	var r = eval('('+json+')');
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
function sendFile(files, editor, $editable) {
    var $files = $(files);
    $files.each(function () {
        var file = this;
        // FormData，新的form表单封装，具体可百度，但其实用法很简单，如下
        var data = new FormData();
        // 将文件加入到file中，后端可获得到参数名为“file”
        data.append("uploadFile", file);
        console.log(URL);
        // ajax上传
        $.ajax({
            async: false, // 设置同步
            data: data,
            type: "POST",
            url: URL+"/common/uploadFile",//图片上传的url（指定action），返回的是图片上传后的路径，http格式
            cache: false,
            contentType: false,
            processData: false,
            // 成功时调用方法，后端返回json数据
            success: function (data) {
            	var temp = eval('('+data+')');
                $('.summernote').summernote('insertImage',temp.data.imgUrl);
            },
            // ajax请求失败时处理
            error: function () {
                alert("上传失败");
            }
        });
    });
}

$(function () {
	
    $('.summernote').summernote({
        height: 400,
        toolbar: [
                  ['style', ['style']],
                  ['fontsize',['fontsize']],
                  ['font', ['bold', 'underline', 'clear']],
                  ['fontname', ['fontname']],
                  ['color', ['color']],
                  ['para', ['ul', 'ol', 'paragraph']],
                  ['table', ['table']],
                  ['insert', ['link', 'picture', 'video']],
                  ['view', ['fullscreen', 'codeview', 'help']]
                ],
        tabsize: 2,
        lang: 'zh-CN',
        codemirror: {
            theme: 'monokai'
        },
        focus: true,
        callbacks: {
            onImageUpload: function (files, editor, $editable) {
                sendFile(files);
            }
        }
    });
});
