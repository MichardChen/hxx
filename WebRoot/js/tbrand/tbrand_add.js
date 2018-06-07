var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tBrand:{showflg:0}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tbrand/info/"+id, function(r){
                var json = eval('('+r+')');
				$("#brand").val(json.tBrand.brand);
				$("#brandId").val(json.tBrand.id);
				$("#word").val(json.tBrand.word);
				$("#showflg").val(json.tBrand.showflg);
                $("#icon").attr("href",json.tBrand.brandIcon);
				$("#icon").show();
            });
		},
		saveOrUpdate: function (event) {
			
			var brandId = $("#brandId").val();
			if(!$("#brand").val()){
				alert("请输入品牌");
				return;
			}
			if(!$("#word").val()){
				alert("请输入首字母");
				return;
			}
			if(brandId == null || brandId==""){
				var fileObj = document.getElementById("uFile").files[0];
				if(!fileObj){
					alert("请选择车标");
					return;
				}
				var formFile = new FormData();
				var url = "../tbrand/save";
				formFile.append("uFile", fileObj); 
				formFile.append("tBrand", JSON.stringify(vm.tBrand));
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
				var fileObj = document.getElementById("uFile").files[0];
				var formFile = new FormData();
				vm.tBrand.id = brandId;
				var url = "../tbrand/update";
				formFile.append("uFile", fileObj); 
				formFile.append("tBrand", JSON.stringify(vm.tBrand));
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