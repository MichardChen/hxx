var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCartParams:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tcartparams/info/"+id, function(r){
                vm.tCartParams = r.tCartParams;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tCartParams.id == null ? "../tcartparams/save" : "../tcartparams/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tCartParams),
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