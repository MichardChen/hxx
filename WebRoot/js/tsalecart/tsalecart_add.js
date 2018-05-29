var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tSalecart:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tsalecart/info/"+id, function(r){
                vm.tSalecart = r.tSalecart;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tSalecart.id == null ? "../tsalecart/save" : "../tsalecart/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tSalecart),
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