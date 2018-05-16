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
                vm.tBrand = r.tBrand;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tBrand.id == null ? "../tbrand/save" : "../tbrand/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tBrand),
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