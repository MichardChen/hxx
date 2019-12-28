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
		mallProduct:{}
	},
	created: function() {
		//加载完界面
		$.get("../sys/menu/perms", function(r){
			if(mallId != null){
				vm.title = "修改商品";
				vm.getMallProduct(mallId);
			} else {
				$("#mall_createTime").val(new Date().format("yyyy-MM-dd hh:mm:ss"));
				$("#mall_updateTime").val(new Date().format("yyyy-MM-dd hh:mm:ss"));
			}
		});
    },
	methods: {
		getMallProduct: function(mallId){
            $.get("../mall/info/"+mallId, function(res){
				var resObj = JSON.parse(res);
            	vm.mallProduct = resObj.mallProduct;
				$("#productDetail").summernote('code', resObj.mallProduct.productDetail);
    		});
		},
		saveOrUpdate: function (event) {
			var url = vm.mallProduct.id == null ? "../mall/save" : "../mall/update";
			vm.mallProduct.productDetail = $("#productDetail").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mallProduct),
				contentType: "application/json",
			    success: function(res){
					var resObj = JSON.parse(res);
			    	if(resObj.code === 0){
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
Date.prototype.format = function (fmt) {
	var o = {
		"M+": this.getMonth() + 1, //月份
		"d+": this.getDate(), //日
		"h+": this.getHours(), //小时
		"m+": this.getMinutes(), //分
		"s+": this.getSeconds(), //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
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
