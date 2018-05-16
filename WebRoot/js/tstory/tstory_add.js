var id = T.p("id");
var ue = UE.getEditor("container");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tStory:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tstory/info/"+id, function(r){
                vm.tStory = r.tStory;
            });
		},
		saveOrUpdate: function (event) {
			vm.tStory.content=ue.getContent();
			var url = vm.tStory.id == null ? "../tstory/save" : "../tstory/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tStory),
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