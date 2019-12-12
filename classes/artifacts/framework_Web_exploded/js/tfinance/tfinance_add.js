var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tFinance:{status:'1'}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tfinance/info/"+id, function(json){
                $("#name").val(json.tFinance.name);
                $("#lowRate").val(json.tFinance.lowRate);
                $("#lowRefund").val(json.tFinance.lowRefund);
                $("#timeDistance").val(json.tFinance.timeDistance);
                $("#standard").val(json.tFinance.standard);
                $("#financeId").val(json.tFinance.id);
                $("#status").val(json.tFinance.status);
				$("#icon").attr("href",json.tFinance.icon);
				$("#icon").show();
            });
		},
		saveOrUpdate: function (event) {
			var financeId = $("#financeId").val();
			if(financeId == null || financeId == ""){
				//保存
				var url = "../tfinance/save";
				var fileObj = document.getElementById("uFile").files[0];
				var formFile = new FormData();
				if(!vm.tFinance.name){
					alert("请输入产品名称");
					return;
				}
				if(!fileObj){
					alert("请选择产品封面");
					return;
				}
				if(!vm.tFinance.lowRate){
					alert("请输入最低首付比例");
					return;
				}
				if(!vm.tFinance.lowRefund){
					alert("请输入最低贷款额");
					return;
				}
				if(!vm.tFinance.timeDistance){
					alert("请输入贷款期限");
					return;
				}
				if(!vm.tFinance.standard){
					alert("请输入准入标准");
					return;
				}
				vm.tFinance.name=$("#name").val();
				vm.tFinance.lowRate=$("#lowRate").val();
				vm.tFinance.lowRefund=$("#lowRefund").val();
				vm.tFinance.timeDistance=$("#timeDistance").val();
				vm.tFinance.standard=$("#standard").val();
				vm.tFinance.status=$("#status").val();
				formFile.append("uFile", fileObj); 
				formFile.append("tFinance", JSON.stringify(vm.tFinance));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    processData: false,
				    contentType: false,
				    success: function(rr){
				    //	var rr = eval('('+r+')');
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
				var url = "../tfinance/update";
				var fileObj = document.getElementById("uFile").files[0];
				var formFile = new FormData();
				if(!$("#name").val()){
					alert("请输入产品名称");
					return;
				}
				if(!$("#lowRate").val()){
					alert("请输入最低首付比例");
					return;
				}
				if(!$("#lowRefund").val()){
					alert("请输入最低贷款额");
					return;
				}
				if(!$("#timeDistance").val()){
					alert("请输入贷款期限");
					return;
				}
				if(!$("#standard").val()){
					alert("请输入准入标准");
					return;
				}
				formFile.append("uFile", fileObj); 
				vm.tFinance.id=financeId;
				vm.tFinance.name=$("#name").val();
				vm.tFinance.lowRate=$("#lowRate").val();
				vm.tFinance.lowRefund=$("#lowRefund").val();
				vm.tFinance.timeDistance=$("#timeDistance").val();
				vm.tFinance.standard=$("#standard").val();
				vm.tFinance.status=$("#status").val();
				formFile.append("tFinance", JSON.stringify(vm.tFinance));
				$.ajax({
					type: "POST",
				    url: url,
				    data: formFile,
				    processData: false,
				    contentType: false,
				    success: function(rr){
				    //	var rr = eval('('+r+')');
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