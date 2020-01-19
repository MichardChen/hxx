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
    },
	methods: {
		getInfo: function(id){
			$.get("../tmarketprice/info/"+id, function(r){
                vm.tMarketPrice = r.tMarketPrice;
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
            vm.tMarketPrice.whendate = $("#date").val();
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