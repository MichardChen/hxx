var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tFinanceCommit:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tfinancecommit/info/"+id, function(r){
                vm.tFinanceCommit = r.tFinanceCommit;
                $("#status").val(r.tFinanceCommit.status);
                $("#financeId").val(r.tFinanceCommit.id);
            });
		},
		saveOrUpdate: function (event) {
			if(vm.tFinanceCommit.id != null){
				var id=vm.tFinanceCommit.id;
				var status = vm.tFinanceCommit.status;
				var mark = vm.tFinanceCommit.mark;
				var formFile = new FormData();
				formFile.append("id",id);
				formFile.append("status",status);
				formFile.append("mark",mark);
				var url = "../tfinancecommit/update";
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    processData: false,
				    contentType: false,
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
			}else{
				var url = "../tfinancecommit/save";
				$.ajax({
					type: "POST",
				    url: url,
				    data: JSON.stringify(vm.tFinanceCommit),
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
			}
		},
		back: function (event) {
			history.go(-1);
		}
	}
});