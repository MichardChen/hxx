var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "modelId",
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

//ID
var modelId = T.p("modelId");
var viewType = T.p("viewType");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title: "修改兑换/购买信息",
		model: {},
		validField: {}
	},
	created: function() {
		//加载完界面
		$.get("../sys/menu/perms", function(r){
			if(modelId && modelId != null){
				if(viewType == "edit"){
					vm.title = "修改兑换/购买信息";
				}else if(viewType == "view"){
					vm.title = "查看兑换/购买信息";
				}
				vm.getModel(modelId);
			}
		});
    },
	methods: {
		getModel: function(modelId){
            $.get("../mall/exchange/info/"+modelId, function(res){
				var resObj;
				if(typeof res.model === 'undefined'){
					resObj = JSON.parse(res);
				} else {
					resObj = res.model;
				}
            	vm.model = resObj;
    		});
		},
		saveOrUpdate: function (event) {
			if(!validNotEmpty()){
				return false;
			}
			var url = vm.model.id == null ? "../mall/exchange/save" : "../mall/exchange/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.model),
			    success: function(res){
					var resObj;
					if(typeof res.code === 'undefined'){
						resObj = JSON.parse(res);
					} else {
						resObj = res;
					}
					if(resObj.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(resObj.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});

function validNotEmpty(){
	for(let field in vm.validField){
		if(vm.model[field] == null || vm.model[field] == ""){
			alert(vm.validField[field]);
			return false;
		}
	}
	return true;
}