var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tInsuranceCommit:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tinsurancecommit/info/"+id, function(r){
                vm.tInsuranceCommit = r.tInsuranceCommit;
                $("#status").val(r.tInsuranceCommit.status);
                $("#insuranceId").val(r.tInsuranceCommit.id);
            });
		},
		saveOrUpdate: function (event) {
			if(vm.tInsuranceCommit.id != null){
				var id=vm.tInsuranceCommit.id;
				var status = vm.tInsuranceCommit.status;
				var mark = vm.tInsuranceCommit.mark;
				var formFile = new FormData();
				formFile.append("id",id);
				formFile.append("status",status);
				formFile.append("mark",mark);
				var url = "../tinsurancecommit/update";
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
				var url = "../tinsurancecommit/save";
				$.ajax({
					type: "POST",
				    url: url,
				    data: JSON.stringify(vm.tInsuranceCommit),
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