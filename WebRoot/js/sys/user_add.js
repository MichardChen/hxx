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
				$("#userId").val(r.user.userId);
				$("#icon").attr("href",r.user.icon);
				$("#icon").show();
			});
		},
		getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
		saveOrUpdate: function (event) {
			var userId = $("#userId").val();
			if(userId == 0 || userId == null){
				//保存
				var formFile = new FormData();
				var fileObj = document.getElementById("uFile").files[0];
				var url = "../sys/user/save";
				formFile.append("uFile", fileObj); 
				formFile.append("user", JSON.stringify(vm.user));
				$.ajax({
					type: "POST",
				    url: url,
				    processData: false,
				    contentType: false,
				    data: formFile,
				    success: function(rr){
				    	if(rr.code === 0){
							alert('操作成功', function(index){
								vm.back();
							});
						}else{
							alert(rr.msg);
						}
					}
				});
			}else{
				//更新
				var formFile = new FormData();
				var fileObj = document.getElementById("uFile").files[0];
				var url = "../sys/user/update";
				formFile.append("uFile", fileObj); 
				vm.user.userId = userId;
				formFile.append("user", JSON.stringify(vm.user));
				$.ajax({
					type: "POST",
				    url: url,
				    processData: false,
				    contentType: false,
				    data: formFile,
				    success: function(rr){
				    	if(rr.code === 0){
							alert('操作成功', function(index){
								vm.back();
							});
						}else{
							alert(rr.msg);
						}
					}
				});
			}
		},
		back: function (event) {
			history.go(-1);
		}
	}
});