var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCarImport:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
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
    },
	methods: {
		getInfo: function(id){
			$.get("../tcarimport/info/"+id, function(json){
				var r = eval('('+json+')');
                $("#carName").val(r.tCarImport.carName);
                $("#brand").val(r.tCarImport.brand);
                $("#nowPrice").val(r.tCarImport.nowPrice);
                $("#marketPrice").val(r.tCarImport.marketPrice);
                $("#maxSave").val(r.tCarImport.maxSave);
                $("#mark").val(r.tCarImport.mark);
                $("#carColor").val(r.tCarImport.carColor);
                $("#favour").val(r.tCarImport.favour);
                $("#servicePiror").val(r.tCarImport.servicePiror);
                $("#hot").val(r.tCarImport.hot);
                $("#labels").val(r.tCarImport.labels);
                $("#titleLabel").val(r.tCarImport.titleLabel);
                $("#carLevelCd").val(r.tCarImport.carLevelCd);
                $("#carClassCd").val(r.tCarImport.carClassCd);
                $("#series").val(r.tCarImport.series);
                $("#cartId").val(r.tCarImport.id);
                $("#icon").attr("href",r.tCarImport.icon);
				$("#icon").show();
				$("#pcIcon").attr("href",r.tCarImport.pcIcon);
				$("#pcIcon").show();
				$("#content").summernote('code', r.tCarImport.content);
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
			var fileObj = document.getElementById("uFile").files[0];
			var fileObj1 = document.getElementById("uFile1").files[0];
			var cartId = $("#cartId").val();
			if(cartId == 0 || cartId == "" || cartId == null){
				if(!$("#carName").val()){
					alert("请输入车名");
					return;
				}
				if(!$("#brand").val()){
					alert("请选择汽车品牌");
					return;
				}
				if(!fileObj){
					alert("请选择车辆图片");
					return;
				}
				if(!$("#nowPrice").val()){
					alert("请输入参考价");
					return;
				}
				if(!$("#marketPrice").val()){
					alert("请输入市场价");
					return;
				}
				if(!$("#maxSave").val()){
					alert("请输入最大节省金额");
					return;
				}
				if(!$("#mark").val()){
					alert("请输入备注");
					return;
				}
				if(!$("#carColor").val()){
					alert("请输入颜色编号");
					return;
				}
				if(!$("#favour").val()){
					alert("请输入优势条件");
					return;
				}
				if(!$("#servicePiror").val()){
					alert("请输入服务优势");
					return;
				}
				if(!$("#hot").val()){
					alert("请输入热度");
					return;
				}
				if(!$("#labels").val()){
					alert("请输入标签");
					return;
				}
				if(!$("#titleLabel").val()){
					alert("请输入主标签");
					return;
				}
				if(!$("#carLevelCd").val()){
					alert("请选择车辆级别");
					return;
				}
				if(!$("#carClassCd").val()){
					alert("请选择车辆品类");
					return;
				}
				
				if(!$("#series").val()){
					alert("请选择车系");
					return;
				}
				
				var formFile = new FormData();
				var url = "../tcarimport/save";
				formFile.append("uFile", fileObj); 
				formFile.append("uFile1", fileObj1); 
				vm.tCarImport.content = $("#content").summernote('code');
				formFile.append("tCarImport", JSON.stringify(vm.tCarImport));
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
				if(!$("#carName").val()){
					alert("请输入车名");
					return;
				}
				if(!$("#brand").val()){
					alert("请选择汽车品牌");
					return;
				}
			
				if(!$("#nowPrice").val()){
					alert("请输入参考价");
					return;
				}
				if(!$("#marketPrice").val()){
					alert("请输入市场价");
					return;
				}
				if(!$("#maxSave").val()){
					alert("请输入最大节省金额");
					return;
				}
				if(!$("#mark").val()){
					alert("请输入备注");
					return;
				}
				if(!$("#carColor").val()){
					alert("请输入颜色编号");
					return;
				}
				if(!$("#favour").val()){
					alert("请输入优势条件");
					return;
				}
				if(!$("#servicePiror").val()){
					alert("请输入服务优势");
					return;
				}
				if(!$("#hot").val()){
					alert("请输入热度");
					return;
				}
				if(!$("#labels").val()){
					alert("请输入标签");
					return;
				}
				if(!$("#titleLabel").val()){
					alert("请输入主标签");
					return;
				}
				if(!$("#carLevelCd").val()){
					alert("请选择车辆级别");
					return;
				}
				if(!$("#carClassCd").val()){
					alert("请选择车辆品类");
					return;
				}
				
				if(!$("#series").val()){
					alert("请选择车系");
					return;
				}
				var fileObj = document.getElementById("uFile").files[0];
				var fileObj1 = document.getElementById("uFile1").files[0];
				var formFile = new FormData();
				var url = "../tcarimport/update";
				formFile.append("uFile", fileObj); 
				formFile.append("uFile1", fileObj1); 
				vm.tCarImport.id=cartId;
				vm.tCarImport.content = $("#content").summernote('code');
				formFile.append("tCarImport", JSON.stringify(vm.tCarImport));
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
