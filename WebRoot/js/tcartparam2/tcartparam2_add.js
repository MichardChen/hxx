var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCartParam2:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tcartparam2/info/"+id, function(r){
                vm.tCartParam2 = r.tCartParam2;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tCartParam2.id == null ? "../tcartparam2/save" : "../tcartparam2/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tCartParam2),
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