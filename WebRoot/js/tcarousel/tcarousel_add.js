var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCarousel:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tcarousel/info/"+id, function(r){
                vm.tCarousel = r.tCarousel;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tCarousel.id == null ? "../tcarousel/save" : "../tcarousel/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tCarousel),
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