var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tStory:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tstory/info/"+id, function(r){
				var json = eval('('+r+')');
				$("#title").val(json.tStory.title);
				$("#storyId").val(json.tStory.id);
				$("#content").summernote('code', json.tStory.content);
				console.log(json.tStory.icon);
				$("#icon").attr("href",json.tStory.icon);
				$("#icon").show();
            });
		},
		saveOrUpdate: function (event) {
			var storyId = $("#storyId").val();
			if(storyId == null || storyId==""){
				//新增
				var fileObj = document.getElementById("uFile").files[0];
				vm.tStory.content = $("#content").summernote('code');
				var formFile = new FormData();
				if(!vm.tStory.title){
					alert("请输入故事标题");
					return;
				}
				if(!fileObj){
					alert("请选择封面图片");
					return;
				}
				if(!$("#content").val()){
					alert("请输入故事内容");
					return;
				}
				formFile.append("uFile", fileObj); 
				formFile.append("tStory", JSON.stringify(vm.tStory));
				var url = "../tstory/save";
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
					}
				});
			}else{
				//更新
				var fileObj = document.getElementById("uFile").files[0];
				vm.tStory.content = $("#content").summernote('code');
				vm.tStory.id=storyId;
				var formFile = new FormData();
				if(!$("#title").val()){
					alert("请输入故事标题");
					return;
				}
				if(!$("#content").val()){
					alert("请输入故事内容");
					return;
				}
				formFile.append("uFile", fileObj); 
				formFile.append("tStory", JSON.stringify(vm.tStory));
				var url = "../tstory/update";
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    contentType: "application/json",
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
					}
				});
			}
		},
		back: function (event) {
			history.go(-1);
		}
	}
});

// 选择图片时把图片上传到服务器再读取服务器指定的存储位置显示在富文本区域内
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