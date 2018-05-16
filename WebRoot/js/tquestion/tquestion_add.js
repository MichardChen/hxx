var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tQuestion:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tquestion/info/"+id, function(r){
                vm.tQuestion = r.tQuestion;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tQuestion.id == null ? "../tquestion/save" : "../tquestion/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tQuestion),
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