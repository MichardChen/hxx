var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tBrandSeries:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tbrandseries/info/"+id, function(r){
                vm.tBrandSeries = r.tBrandSeries;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tBrandSeries.id == null ? "../tbrandseries/save" : "../tbrandseries/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tBrandSeries),
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