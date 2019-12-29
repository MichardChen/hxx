var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "mallId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};

//商品ID
var mallId = T.p("mallId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增商品",
		mallProduct:{
			"productTitle": null,
			"detailLogo": null,
			"logos": null,
			"createTime": null,
			"updateTime": null
		},
		validField: {
			productTitle: '用户名不能为空',
			detailLogo: '产品详情主图片不能为空',
			logos: '产品logo不能为空',
			price: '价格不能为空',
			quality: '库存不能为空',
			needPoints: '所需兑换积分不能为空',
			status: '状态不能为空',
			productTypeCd: '产品类型不能为空',
			userTypeCd: '用户类型不能为空',
			productDetail: '产品详情不能为空'
		}
	},
	created: function() {
		//加载完界面
		$.get("../sys/menu/perms", function(r){
			if(mallId && mallId != null){
				vm.title = "修改商品";
				vm.getMallProduct(mallId);
			} else {
				vm.mallProduct.createTime = new Date().format("yyyy-MM-dd hh:mm:ss");
				vm.mallProduct.updateTime = new Date().format("yyyy-MM-dd hh:mm:ss");
			}
		});
    },
	methods: {
		getMallProduct: function(mallId){
            $.get("../mall/info/"+mallId, function(res){
				var resObj;
				if(typeof res.mallProduct === 'undefined'){
					resObj = JSON.parse(res);
				} else {
					resObj = res.mallProduct;
				}
            	vm.mallProduct = resObj;
				if(typeof resObj.productDetail !=='undefined' && resObj.productDetail){
					$("#productDetail").summernote('code', resObj.productDetail);
				}
				//产品详情主图片
				if(typeof resObj.detailLogo !=='undefined' && resObj.detailLogo){
					$("#detailLogoIcon").attr("href",resObj.detailLogo);
				}
				//产品logo
				if(typeof resObj.logos !=='undefined' && resObj.logos){
					$("#logosIcon").attr("href",resObj.logos);
				}
    		});
		},
		saveOrUpdate: function (event) {
			// $('#form_mall_product').data('bootstrapValidator').validate();
			// var flag = $("#form_mall_product").data("bootstrapValidator").isValid();
			if(!validNotEmpty()){
				return false;
			}
			var url = vm.mallProduct.id == null ? "../mall/save" : "../mall/update";
			vm.mallProduct.productDetail = $("#productDetail").val();
			var detailLogoObj = document.getElementById("detailLogoInput").files[0];
			var logosObj = document.getElementById("logos").files[0];
			var formFile = new FormData();
			formFile.append("mallProduct", JSON.stringify(vm.mallProduct));
			$.ajax({
				type: "POST",
			    url: url,
				data: formFile,
				processData: false,
				contentType: false,
			    success: function(res){
					var resObj;
					if(typeof res.code === 'undefined'){
						resObj = JSON.parse(res);
					} else {
						resObj = res;
					}
					if(resObj.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(resObj.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});

function validNotEmpty(){
	for(let field in vm.validField){
		if(vm.mallProduct[field] == null || vm.mallProduct[field] == ""){
			alert(vm.validField[field]);
			return false;
		}
	}
	return true;
}

//选择图片时把图片上传到服务器再读取服务器指定的存储位置显示在富文本区域内
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
				if(typeof editor!=undefined && editor){
					vm.mallProduct[editor] = temp.data.imgUrl;
				} else {
					$('.summernote').summernote('insertImage',temp.data.imgUrl);
				}
			},
			// ajax请求失败时处理
			error: function () {
				alert("上传失败");
			}
		});
	});
}

function uploadDetailLogo(obj){
	var files = obj.files;
	sendFile(files, "detailLogo")
}

function uploadLogos(obj){
	var files = obj.files;
	sendFile(files, "logos")
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
	// $('#form_mall_product').bootstrapValidator({
	// 	message: 'This value is not valid',
	// 	feedbackIcons: {
	// 		valid: 'glyphicon glyphicon-ok',
	// 		invalid: 'glyphicon glyphicon-remove',
	// 		validating: 'glyphicon glyphicon-refresh'
	// 	},
	// 	fields: {
	//
	// 	}
	// });
});
