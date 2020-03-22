var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tFishOrderEvaluation:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tfishorderevaluation/info/"+id, function(r){
                vm.tFishOrderEvaluation = r.tFishOrderEvaluation;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.tFishOrderEvaluation.id == null ? "../tfishorderevaluation/save" : "../tfishorderevaluation/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tFishOrderEvaluation),
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