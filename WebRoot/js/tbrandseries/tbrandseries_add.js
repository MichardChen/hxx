var id = T.p("id");
var vm = new Vue({
	el : '#rrapp',
	data : {
		title : "新增",
		tBrandSeries : {}
	},
	created : function() {
		
		$.ajaxSetup({ 
		    async : false 
		});
		// 查看所有品牌
		$.get("../tbrand/queryAllBrand", function(r) {
			var brandList = r.tBrandList;
			var html = "";
			for (var i = 0; i < brandList.length; i++) { // 循环
				html += "<option value='" + brandList[i].id + "'>"
						+ brandList[i].brand + "</option>";
			}
			$("#brandId").append(html);
		});
		if (id != null) {
			this.title = "修改";
			this.getInfo(id)
		}
	},
	methods : {
		getInfo : function(id) {
			$.get("../tbrandseries/info/" + id, function(rr) {
				var r = eval('('+rr+')');
				$("#brandSeriesId").val(r.tBrandSeries.id);
				$("#brandId").val(r.tBrandSeries.brandId);
				$("#carSerial").val(r.tBrandSeries.carSerial);
			});
		},
		saveOrUpdate : function(event) {
			if (!$("#brandId").val()) {
					alert("请选择汽车品牌");
					return;
			}
			if (!$("#carSerial").val()) {
					alert("请输入车系");
					return;
			}
			var brandSeriesId = $("#brandSeriesId").val();
			if (brandSeriesId == null || brandSeriesId == "") {
				var url = "../tbrandseries/save";
				$.ajax({
					type : "POST",
					url : url,
					data : JSON.stringify(vm.tBrandSeries),
					success : function(r) {
						if (r.code === 0) {
							alert('操作成功', function(index) {
								vm.back();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			} else {
				var url = "../tbrandseries/update";
				vm.tBrandSeries.id = brandSeriesId;
				$.ajax({
					type : "POST",
					url : url,
					data : JSON.stringify(vm.tBrandSeries),
					success : function(r) {
						if (r.code === 0) {
							alert('操作成功', function(index) {
								vm.back();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			}
		},
		back : function(event) {
			history.go(-1);
		}
	}
});