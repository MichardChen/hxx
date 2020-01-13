var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tFishSupply:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tfishsupply/info/"+id, function(r){
                vm.tFishSupply = r.tFishSupply;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tFishSupply.id == null ? "../tfishsupply/save" : "../tfishsupply/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tFishSupply),
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