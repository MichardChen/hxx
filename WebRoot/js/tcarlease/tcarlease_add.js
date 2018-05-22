var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCarLease:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tcarlease/info/"+id, function(r){
                vm.tCarLease = r.tCarLease;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tCarLease.id == null ? "../tcarlease/save" : "../tcarlease/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tCarLease),
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