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
                var json = eval('('+r+')');
                $("#status").val(json.tSalecart.status);
                $("#mobile").val(json.tSalecart.mobile);
                $("#name").val(json.tSalecart.name);
                $("#mark").val(json.tSalecart.mark);
                $("#saleCartId").val(json.tSalecart.id);
            });
		},
		saveOrUpdate: function (event) {
			vm.tSalecart.id = $("#saleCartId").val();
			var url = vm.tSalecart.id == null ? "../tsalecart/save" : "../tsalecart/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tSalecart),
			    processData: false,
			    contentType: false,
			    contentType: "application/json",
			    success: function(rr){
			    	var r = eval('('+rr+')');
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