var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tCarLease:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
		//获取所有品牌
		$.get("../tbrand/queryAllBrand", function(r){
            var list =eval(r.tBrandList);
	    	if(r.code === 0){
	    		var html = "";
	    		for(var b in list){
	    			//向品牌下拉框添加品牌
	    			html += "<option value='" + list[b].id + "'>" +list[b].brand + "</option>";
	    		}
	    		 $("#brand").append(html);
	    		 $("#series").append("<option value=\"0\">请选择车系</option>");
        }});
		
    },
	methods: {
		getInfo: function(id){
			$.get("../tcarlease/info/"+id, function(r){
                vm.tCarLease = r.tCarLease;
            });
		},
		selectBrand:function(){
			
			var id=$("#brand").val();
			if(id==0){
				$("#series").empty();
				$("#series").append("<option value=\"0\">请选择车系</option>");
			}else{
				$.get("../tbrandseries/queryBrandSeries/"+id, function(r){
		            var list =eval(r.tBrandSeries);
			    	if(r.code === 0){
			    		var html = "";
			    		for(var b in list){
			    			//向品牌下拉框添加品牌
			    			html += "<option value='" + list[b].id + "'>" +list[b].carSerial + "</option>";
			    		}
			    		$("#series").empty();
			    		 $("#series").append(html);
		        }});
			}
		},
		saveOrUpdate: function (event) {
			var fileObj = document.getElementById("uFile").files[0];
			var formFile = new FormData();
			var url = vm.tCarLease.id == null ? "../tcarlease/save" : "../tcarlease/update";
			formFile.append("uFile", fileObj); 
			formFile.append("tCarLease", JSON.stringify(vm.tCarLease));
			$.ajax({
				type: "POST",
			    url: url,
			    data: formFile,
			    contentType: "application/json",
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
		},
		back: function (event) {
			history.go(-1);
		}
	}
});