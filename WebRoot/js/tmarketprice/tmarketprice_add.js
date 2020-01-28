var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tMarketPrice:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}

        $.ajaxSetup({
            async : false
        });
        //Synchronous XMLHttpRequest on the main thread is deprecated because of its detrimental effects to the end user's experience.
        //加上同步，会出现上面的错误，把getInfo写在前面就不会出现错误
        // 查看所有品牌
        $.get("../tbrandseries/queryAllBrandSeries", function(r) {
            //var r = eval('('+json+')');
            var brandList = r.tBrandSeriesList;
            var html = "";
            for (var i = 0; i < brandList.length; i++) { // 循环
                html += "<option value='" + brandList[i].carSerial + "'>"
                    + brandList[i].carSerial + "</option>";
            }
            $("#brandSeries").append(html);
        });
        //获取省市区
        $.get("../common/queryProvince", function(r){
            //var r =eval('('+rr+')');
            var list = r.provinceList;
            if(r.code === 0){
                var html = "";
                for(var b in list){
                    //向品牌下拉框添加品牌
                    html += "<option value='" + list[b].id + "'>" +list[b].name + "</option>";
                }
                $("#provinceId").append(html);
            }});
    },
	methods: {
        selectCity:function(){
            var id=$("#provinceId").val();
            if(id==""){
                $("#cityId").empty();
                $("#cityId").append("<option value=\"\">请选择城市</option>");
            }else{
                $.get("../common/queryCity/"+id, function(r){
                    //var r = eval('('+rr+')');
                    var list = r.cityList;
                    if(r.code === 0){
                        var html = "";
                        for(var b in list){
                            //向品牌下拉框添加品牌
                            html += "<option value='" + list[b].id + "'>" +list[b].name + "</option>";
                        }
                        $("#cityId").empty();
                        $("#cityId").append(html);
                    }});
            }
        },
		getInfo: function(id){
			$.get("../tmarketprice/info/"+id, function(r){
                vm.tMarketPrice = r.tMarketPrice;
                $.get("../common/queryCity/"+r.tMarketPrice.province, function(dr){
                    //var dr = eval('('+rr+')');
                    var list = dr.cityList;
                    if(dr.code === 0){
                        var html = "";
                        for(var b in list){
                            //向品牌下拉框添加品牌
                            if(list[b].id == r.tMarketPrice.city){
                                html += "<option value='" + list[b].id + "' selected='selected'>" +list[b].name + "</option>";
                            }else{
                                html += "<option value='" + list[b].id + "'>" +list[b].name + "</option>";
                            }
                        }
                        $("#cityId").empty();
                        $("#cityId").append(html);
                    }});
            });


		},
		saveOrUpdate: function (event) {
			var url = vm.tMarketPrice.id == null ? "../tmarketprice/save" : "../tmarketprice/update";
			if(!$("#brandSeries").val()){
				alert("请选择品类");
				return;
			}
            if(!$("#size").val()){
                alert("请选择规格");
                return;
            }
            if(!$("#price").val()){
                alert("请选择价格");
                return;
            }
            if(!$("#date").val()){
                alert("请选择行情日期");
                return;
            }
            if(!$("#provinceId").val()){
                alert("请选择省份");
                return;
            }
            if(!$("#cityId").val()){
                alert("请选择城市");
                return;
            }
            vm.tMarketPrice.whendate = $("#date").val();
            vm.tMarketPrice.city = $("#cityId").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tMarketPrice),
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