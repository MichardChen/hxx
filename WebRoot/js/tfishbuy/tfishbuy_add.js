var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tFishBuy:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tfishbuy/info/"+id, function(r){
                vm.tFishBuy = r.tFishBuy;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tFishBuy.id == null ? "../tfishbuy/save" : "../tfishbuy/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tFishBuy),
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