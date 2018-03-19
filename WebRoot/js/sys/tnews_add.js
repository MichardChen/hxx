var id = T.p("id");
var ue = UE.getEditor("container");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tNews:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tnews/info/"+id, function(r){
                vm.tNews = r.tNews;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tNews.id == null ? "../tnews/save" : "../tnews/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tNews),
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

