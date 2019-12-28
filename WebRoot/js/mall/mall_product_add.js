var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "mallId",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};

//角色ID
var mallId = T.p("mallId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增角色",
		mallProduct:{}
	},
	created: function() {
		//加载完界面
		$.get("../sys/menu/perms", function(r){
			if(mallId != null){
				vm.title = "修改用户";
				vm.getMallProduct(mallId);
			}
		});
    },
	methods: {
		getMallProduct: function(mallId){
            $.get("../basic/member/info/"+mallId, function(r){
            	vm.mallProduct = r.mallProduct;
    		});
		},
		saveOrUpdate: function (event) {
			var url = vm.mallProduct.id == null ? "../basic/member/save" : "../basic/member/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.mallProduct),
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