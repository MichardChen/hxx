var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCarousel:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tcarousel/info/"+id, function(json){
               // var json = eval('('+r+')');
				$("#carouselId").val(json.tCarousel.id);
				$("#realUrl").val(json.tCarousel.realUrl);
				$("#typeCd").val(json.tCarousel.typeCd);
				$("#mark").val(json.tCarousel.mark);
				$("#icon").attr("href",json.tCarousel.imgUrl);
				$("#icon").show();
				
            });
		},
		saveOrUpdate: function (event) {
			var carouselId = $("#carouselId").val();
			
			if(carouselId==null || carouselId == "" || carouselId ==0){
				//新增
				var fileObj = document.getElementById("uFile").files[0];
				if(fileObj == null){
					alert("请选择上传图片");
					return;
				}
				/*var realUrl = $("#realUrl").val();
				if(realUrl == ""){
					alert("请输入跳转地址");
					return;
				}*/
				var typeCd = $("#typeCd").val();
				if(typeCd == null){
					alert("请选择图片类型");
					return;
				}
				var formFile = new FormData();
				var url = "../tcarousel/save";
				formFile.append("uFile", fileObj); 
				vm.tCarousel.realUrl="#";
				formFile.append("tCarousel", JSON.stringify(vm.tCarousel));
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
				//更新
				/*var realUrl = $("#realUrl").val();
				if(realUrl == ""){
					alert("请输入跳转地址");
					return;
				}*/
				var typeCd = $("#typeCd").val();
				if(typeCd == ""){
					alert("请选择图片类型");
					return;
				}
				var fileObj = document.getElementById("uFile").files[0];
				var formFile = new FormData();
				var url = "../tcarousel/update";
				formFile.append("uFile", fileObj); 
				vm.tCarousel.id=carouselId;
				formFile.append("tCarousel", JSON.stringify(vm.tCarousel));
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