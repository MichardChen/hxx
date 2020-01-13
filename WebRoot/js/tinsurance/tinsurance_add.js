var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tInsurance:{status:'1'}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tinsurance/info/"+id, function(r){
                var json = eval('('+r+')');
                $("#name").val(json.tInsurance.name);
                $("#lowRate").val(json.tInsurance.lowRate);
                $("#lowRefund").val(json.tInsurance.lowRefund);
                $("#timeDistance").val(json.tInsurance.timeDistance);
                //$("#standard").val(json.tInsurance.standard);
                $("#insuranceId").val(json.tInsurance.id);
                $("#status").val(json.tInsurance.status);
                $("#typeCd").val(json.tInsurance.typeCd);
                $("#labels").val(json.tInsurance.labels);
                $("#content").summernote('code', json.tInsurance.content);
				$("#icon").attr("href",json.tInsurance.icon);
				$("#icon").show();
            });
		},
		saveOrUpdate: function (event) {
			var insuranceId = $("#insuranceId").val();
			if(insuranceId == null || insuranceId == ""){
				//保存
				var url = "../tinsurance/save";
				var fileObj = document.getElementById("uFile").files[0];
				var formFile = new FormData();
                if(!$("#name").val()){
                    alert("请输入产品名称");
                    return;
                }
                if(!$("#labels").val()){
                    alert("请输入产品标签");
                    return;
                }
                if(!$("#uFile").val()){
                    alert("请选择封面图片");
                    return;
                }
                if(!$("#lowRate").val()){
                    alert("请输入费率");
                    return;
                }
                if(!$("#lowRefund").val()){
                    alert("请输入投保金额");
                    return;
                }
                if(!$("#timeDistance").val()){
                    alert("请输入投保期限");
                    return;
                }
                if(!$("#content").summernote('code')){
                    alert("请编辑方案内容");
                    return;
                }
				/*if(!vm.tInsurance.standard){
					alert("请输入准入标准");
					return;
				}*/
				vm.tInsurance.name=$("#name").val();
				vm.tInsurance.lowRate=$("#lowRate").val();
				vm.tInsurance.lowRefund=$("#lowRefund").val();
				vm.tInsurance.timeDistance=$("#timeDistance").val();
				//vm.tInsurance.standard=$("#standard").val();
				vm.tInsurance.status=$("#status").val();
				vm.tInsurance.typeCd=$("#typeCd").val();
                vm.tInsurance.labels=$("#labels").val();
                vm.tInsurance.content=$("#content").summernote('code');
				formFile.append("uFile", fileObj); 
				formFile.append("tInsurance", JSON.stringify(vm.tInsurance));
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
				var url = "../tinsurance/update";
				var fileObj = document.getElementById("uFile").files[0];
				var formFile = new FormData();
				if(!$("#name").val()){
					alert("请输入产品名称");
					return;
				}
                if(!$("#labels").val()){
                    alert("请输入产品标签");
                    return;
                }
                /*if(!$("#uFile").val()){
                    alert("请选择封面图片");
                    return;
                }*/
				if(!$("#lowRate").val()){
					alert("请输入费率");
					return;
				}
				if(!$("#lowRefund").val()){
					alert("请输入贷款额度");
					return;
				}
				if(!$("#timeDistance").val()){
					alert("请输入贷款期限");
					return;
				}
                if(!$("#content").summernote('code')){
                    alert("请编辑方案内容");
                    return;
                }
				/*if(!$("#standard").val()){
					alert("请输入准入标准");
					return;
				}*/
				formFile.append("uFile", fileObj); 
				vm.tInsurance.id=insuranceId;
				vm.tInsurance.name=$("#name").val();
				vm.tInsurance.lowRate=$("#lowRate").val();
				vm.tInsurance.lowRefund=$("#lowRefund").val();
				vm.tInsurance.timeDistance=$("#timeDistance").val();
				//vm.tInsurance.standard=$("#standard").val();
				vm.tInsurance.status=$("#status").val();
                vm.tInsurance.typeCd=$("#typeCd").val();
                vm.tInsurance.labels=$("#labels").val();
                vm.tInsurance.content=$("#content").summernote('code');
				formFile.append("tInsurance", JSON.stringify(vm.tInsurance));
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