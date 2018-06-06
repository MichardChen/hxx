var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tNews:{newsTypeCd:"030001",hotFlg:1,topFlg:0}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tnews/info/"+id, function(r){
                var json = eval('('+r+')');
				$("#newsTitle").val(json.tNews.newsTitle);
				$("#newsId").val(json.tNews.id);
				$("#newsTypeCd").val(json.tNews.newsTypeCd);
				$("#hotFlg").val(json.tNews.hotFlg);
				$("#topFlg").val(json.tNews.topFlg);
				$("#content").summernote('code', json.tNews.content);
				$("#icon").attr("href",json.tNews.newsLogo);
				$("#icon").show();
            });
		},
		saveOrUpdate: function (event) {
			
			var newsId=$("#newsId").val();
			if(!newsId){
				if($("#newsTitle").val()==""){
					alert("请输入资讯标题");
					return;
				}
				if(!$("#uFile").val()){
					alert("请选择封面图片");
					return;
				}
				if(!$("#newsTypeCd").val()){
					alert("请选择资讯类型");
					return;
				}
				if(!$("#content").summernote('code')){
					alert("请编辑资讯内容");
					return;
				}
				var url = "../tnews/save";
				var fileObj = document.getElementById("uFile").files[0];
				vm.tNews.content = $("#content").summernote('code');
				var formFile = new FormData();
				formFile.append("uFile", fileObj); 
				formFile.append("tNews", JSON.stringify(vm.tNews));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    processData: false,
				    contentType: false,
				    success: function(r){
				    	var rr = eval('('+r+')');
				    	if(rr.code === 0){
							alert('操作成功', function(index){
								vm.back();
							});
						}else{
							alert(rr.msg);
						}
				    	/*if(r.code === 0){
							alert('操作成功', function(index){
								vm.back();
							});
						}else{
							alert(r.msg);
						}*/
					}
				});
			}else{
				if(!$("#newsTitle").val()){
					alert("请输入资讯标题");
					return;
				}
				if(!$("#newsTypeCd").val()){
					alert("请选择资讯类型");
					return;
				}
				if(!$("#content").summernote('code')){
					alert("请编辑资讯内容");
					return;
				}
				var url = "../tnews/update";
				var fileObj = document.getElementById("uFile").files[0];
				vm.tNews.content = $("#content").summernote('code');
				vm.tNews.id=newsId;
				var formFile = new FormData();
				formFile.append("uFile", fileObj); 
				formFile.append("tNews", JSON.stringify(vm.tNews));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    processData: false,
				    contentType: false,
				    success: function(r){
				    	var rr = eval('('+r+')');
				    	if(rr.code === 0){
							alert('操作成功', function(index){
								vm.back();
							});
						}else{
							alert(rr.msg);
						}
				    	/*if(r.code === 0){
							alert('操作成功', function(index){
								vm.back();
							});
						}else{
							alert(r.msg);
						}*/
					}
				});
			}
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
