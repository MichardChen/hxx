var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tFinance:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tfinance/info/"+id, function(r){
                vm.tFinance = r.tFinance;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tFinance.id == null ? "../tfinance/save" : "../tfinance/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tFinance),
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