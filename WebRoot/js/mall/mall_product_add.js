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
			"detailLogo": null,
			"logos": null,
			"createTime": null,
			"updateTime": null
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
            	vm.mallProduct = resObj.mallProduct;
				$("#productDetail").summernote('code', resObj.mallProduct.productDetail);
				//产品详情主图片
				$("#detailLogoIcon").attr("href",resObj.mallProduct.detailLogo);
				// $("#detailLogoIcon").show();
				//产品logo
				$("#logosIcon").attr("href",resObj.mallProduct.logos);
				// $("#logosIcon").show();
    		});
		},
		saveOrUpdate: function (event) {
			var url = vm.mallProduct.id == null ? "../mall/save" : "../mall/update";
			vm.mallProduct.productDetail = $("#productDetail").val();
			var detailLogoObj = document.getElementById("detailLogo").files[0];
			var logosObj = document.getElementById("logos").files[0];
			var formFile = new FormData();
			// formFile.append("detailLogoFile", detailLogoObj);
			// formFile.append("logosFile", logosObj);
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

	// $('form').bootstrapValidator({
	// 	message: 'This value is not valid',
	// 	feedbackIcons: {
	// 		valid: 'glyphicon glyphicon-ok',
	// 		invalid: 'glyphicon glyphicon-remove',
	// 		validating: 'glyphicon glyphicon-refresh'
	// 	},
	// 	fields: {
	// 		productTitle: {
	// 			message: '用户名验证失败',
	// 			validators: {
	// 				notEmpty: {
	// 					message: '用户名不能为空'
	// 				}
	// 			}
	// 		}
	// 		// email: {
	// 		// 	validators: {
	// 		// 		notEmpty: {
	// 		// 			message: '邮箱不能为空'
	// 		// 		},
	// 		// 		emailAddress: {
	// 		// 			message: '邮箱地址格式有误'
	// 		// 		}
	// 		// 	}
	// 		// }
	// 	}
	// });
});
