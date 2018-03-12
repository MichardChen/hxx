var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "memberId",
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
var memberId = T.p("memberId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增角色",
		member:{}
	},
	created: function() {
		//加载菜单树
		$.get("../sys/menu/perms", function(r){
			if(memberId != null){
				vm.title = "修改用户";
				vm.getMember(memberId);
			}
		});
    },
	methods: {
		getMember: function(memberId){
            $.get("../basic/member/info/"+memberId, function(r){
            	vm.member = r.member;
    		});
		},
		saveOrUpdate: function (event) {
			var url = vm.member.id == null ? "../basic/member/save" : "../basic/member/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.member),
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