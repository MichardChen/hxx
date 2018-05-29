var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tBrand:{}
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
                $("#icon").attr("href",json.tBrand.brandIcon);
				$("#icon").show();
            });
		},
		saveOrUpdate: function (event) {
			var fileObj = document.getElementById("uFile").files[0];
			var formFile = new FormData();
			var url = vm.tBrand.id == null ? "../tbrand/save" : "../tbrand/update";
			formFile.append("uFile", fileObj); 
			formFile.append("tBrand", JSON.stringify(vm.tBrand));
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
		},
		back: function (event) {
			history.go(-1);
		}
	}
});