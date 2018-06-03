var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tFinanceCommit:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tfinancecommit/info/"+id, function(r){
                vm.tFinanceCommit = r.tFinanceCommit;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tFinanceCommit.id == null ? "../tfinancecommit/save" : "../tfinancecommit/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tFinanceCommit),
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