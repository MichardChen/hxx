//用户ID
var userId = T.p("userId");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增管理员",
		roleList:{},
		user:{
			status:1,
			roleIdList:[]
		}
	},
	created: function() {
		if(userId != null){
			this.title = "修改管理员";
			this.getUser(userId)
		}
		//获取角色信息
		this.getRoleList();
    },
	methods: {
		getUser: function(userId){
			$.get("../sys/user/info/"+userId, function(r){
				vm.user = r.user;
			});
		},
		getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
		saveOrUpdate: function (event) {
			var formFile = new FormData();
			var fileObj = document.getElementById("uFile").files[0];
			var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
			formFile.append("uFile", fileObj); 
			formFile.append("user", JSON.stringify(vm.user));
			$.ajax({
				type: "POST",
			    url: url,
			    processData: false,
			    contentType: false,
			    data: formFile,
			    success: function(r){
			    	var rr = eval('('+r+')');
			    	if(rr.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(rr.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});