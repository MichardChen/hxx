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
		
		//queryAllBrand();
		$.ajaxSetup({ 
		    async : false 
		});
		
    },
	methods: {
		getInfo: function(id){
			$.get("../tcarlease/info/"+id, function(json){
				console.log(1);
				var r = eval('('+json+')');
                $("#cartId").val(r.tCarLease.id);
                $("#carName").val(r.tCarLease.carName);
                $("#brand").val(r.tCarLease.brand);
                $("#icon").val(r.tCarLease.icon);
                $("#year").val(r.tCarLease.year);
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
				$("#pcIcon").attr("href",r.tCarLease.pcIcon);
				$("#pcIcon").show();
				$("#content").summernote('code', r.tCarLease.content);
				$("#salecount").val(r.tCarLease.salecount);
				$("#flg").val(r.tCarLease.flg);
				
				$.get("../tbrandseries/queryBrandSeries/"+r.tCarLease.brand, function(rr){
		            var dr =eval('('+rr+')');
		            var list = dr.tBrandSeries;
			    	if(dr.code === 0){
			    		var html = "";
			    		for(var b in list){
			    			//向品牌下拉框添加品牌
			    			if(list[b].id == r.tCarLease.series){
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
		saveOrUpdate: function (event) {
			
			var cartId = $("#cartId").val();
			vm.tCarLease.carName = $("#carName").val();
			vm.tCarLease.brand = $("#brand").val();
			vm.tCarLease.carSeriesId = $("#series").val();
			vm.tCarLease.carCost = $("#carCost").val();
			vm.tCarLease.firmCost = $("#firmCost").val();
			vm.tCarLease.carColor = $("#carColor").val();
			vm.tCarLease.carTypeInfo = $("#carTypeInfo").val();
			vm.tCarLease.year = $("#year").val();
			vm.tCarLease.titleLabel = $("#titleLabel").val();
			vm.tCarLease.labels = $("#labels").val();
			vm.tCarLease.firstPayment = $("#firstPayment").val();
			vm.tCarLease.monthPayment = $("#monthPayment").val();
			vm.tCarLease.mark = $("#mark").val();
			vm.tCarLease.firstPayment1 = $("#firstPayment1").val();
			vm.tCarLease.monthPayment1 = $("#monthPayment1").val();
			vm.tCarLease.mark1 = $("#mark1").val();
			vm.tCarLease.tfirstYearFirstPay = $("#tfirstYearFirstPay").val();
			vm.tCarLease.tfirstYearMonthPayment = $("#tfirstYearMonthPayment").val();
			vm.tCarLease.tperiods = $("#tperiods").val();
			vm.tCarLease.tmonthPayment = $("#tmonthPayment").val();
			vm.tCarLease.finalPayment = $("#finalPayment").val();
			vm.tCarLease.realFirstPayment = $("#realFirstPayment").val();
			vm.tCarLease.serviceFee = $("#serviceFee").val();
			vm.tCarLease.salecount = $("#salecount").val();
			vm.tCarLease.flg = $("#flg").val();
			if(cartId == 0 || cartId == "" || cartId == null){
				
				//新增
				var fileObj = document.getElementById("uFile").files[0];
				var fileObj1 = document.getElementById("uFile1").files[0];
				var formFile = new FormData();
				if(!$("#carName").val()){
					alert("请输入汽车名称");
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
				if(!fileObj1){
					alert("请选择汽车图片");
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
				if(!$("#salecount").val()){
					alert("请输入销量");
					return;
				}
				
				var url = "../tcarlease/save";
				formFile.append("uFile", fileObj); 
				formFile.append("uFile1",fileObj1);
				vm.tCarLease.content = $("#content").summernote('code');
				formFile.append("tCarLease", JSON.stringify(vm.tCarLease));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    contentType: "application/json",
				    processData: false,
				    contentType: false,
				    success: function(rr){
				    	var r = eval('('+rr+')');
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
				var fileObj = document.getElementById("uFile").files[0];
				var fileObj1 = document.getElementById("uFile1").files[0];
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
				if(!$("#salecount").val()){
					alert("请输入销量");
					return;
				}
				vm.tCarLease.salecount = $("#salecount").val();
				
				var formFile = new FormData();
				var url = "../tcarlease/update";
				formFile.append("uFile", fileObj); 
				formFile.append("uFile1", fileObj1); 
				vm.tCarLease.id=$("#cartId").val();
				vm.tCarLease.content = $("#content").summernote('code');
				formFile.append("tCarLease", JSON.stringify(vm.tCarLease));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    contentType: "application/json",
				    processData: false,
				    contentType: false,
				    success: function(rr){
				    	var r = eval('('+rr+')');
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
	console.log(0);
	queryAllBrand();
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
function queryAllBrand(){
	console.log(3);
	//查询所有品牌
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
}