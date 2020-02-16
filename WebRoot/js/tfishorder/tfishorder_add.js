var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tFishOrder:{},
		fromUser:"",
		toUser:"",
        supplyInfo:""
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tfishorder/info/"+id, function(r){
                vm.tFishOrder = r.tFishOrder;
                vm.fromUser = r.fromUser;
                vm.toUser = r.toUser;
                vm.supplyInfo = r.supplyInfo;
                /*if(vm.tFishOrder.status == "280004" || vm.tFishOrder.status == "280001"){
                	//待支付
                    $('#secondPay').attr("disabled",false);
                    $('#firstPay').attr("disabled",false);
				}else if(vm.tFishOrder.status == "280006" ||
                    vm.tFishOrder.status == "280007" ||
                    vm.tFishOrder.status == "280008" ||
                    vm.tFishOrder.status == "280009"){
					//代发货后,可以编辑物流信息
                    $('#logisticsInfo').attr("disabled",false);
                    $('#logisticsNo').attr("disabled",false);
				}else if(vm.tFishOrder.status == "280002"){
					//审核失败
                    $('#reason').attr("disabled",false);
				}*/
                $("#ostatus").change(function(){
                    var ostatus=($(this).val());
                    if(ostatus == "280001" || ostatus == "280004"){
                        $('#secondPay').attr("disabled",false);
                        $('#firstPay').attr("disabled",false);
                        $('#logisticsInfo').attr("disabled",true);
                        $('#logisticsNo').attr("disabled",true);
                        $('#reason').attr("disabled",true);
                    }
                    if(ostatus == "280006" ||
                        ostatus == "280007" ||
                        ostatus == "280008" ||
                        ostatus == "280009"){
                        $('#secondPay').attr("disabled",true);
                        $('#firstPay').attr("disabled",true);
                        $('#reason').attr("disabled",true);
                        //代发货后,可以编辑物流信息
                        $('#logisticsInfo').attr("disabled",false);
                        $('#logisticsNo').attr("disabled",false);
                    }

                    if(ostatus == "280002"){
                        $('#secondPay').attr("disabled",true);
                        $('#firstPay').attr("disabled",true);
                        $('#logisticsInfo').attr("disabled",true);
                        $('#logisticsNo').attr("disabled",true);
                        //审核失败
                        $('#reason').attr("disabled",false);
                    }
                });
            });


		},
		saveOrUpdate: function (event) {
			var ostatus = $("#ostatus").val();
			if(ostatus == "280001"){
				//待审核,预付款尾款、物流信息、失败原因不用填
				if(vm.tFishOrder.firstPay != "" && vm.tFishOrder.firstPay != null){
					alert("待审核状态下，无需填写预付款");
                    return;
				}
                if(vm.tFishOrder.secondPay != "" && vm.tFishOrder.secondPay != null){
                    alert("待审核状态下，无需填写尾款");
                    return;
                }
                if(vm.tFishOrder.logisticsInfo != "" && vm.tFishOrder.logisticsInfo != null){
                    alert("待审核状态下，无需填写物流信息");
                    return;
                }
                if(vm.tFishOrder.logisticsNo != "" && vm.tFishOrder.logisticsNo != null){
                    alert("待审核状态下，无需填写物流单号");
                    return;
                }
                if(vm.tFishOrder.reason != "" && vm.tFishOrder.reason != null){
                    alert("待审核状态下，无需填写失败原因");
                    return;
                }
			}else if(ostatus == "280002"){
				//审核失败
                if(vm.tFishOrder.firstPay != "" && vm.tFishOrder.firstPay != null){
                    alert("审核失败状态下，无需填写预付款");
                    return;
                }
                if(vm.tFishOrder.secondPay != "" && vm.tFishOrder.secondPay != null){
                    alert("审核失败状态下，无需填写尾款");
                    return;
                }
                if(vm.tFishOrder.logisticsInfo != "" && vm.tFishOrder.logisticsInfo != null){
                    alert("审核失败状态下，无需填写物流信息");
                    return;
                }
                if(vm.tFishOrder.logisticsNo != "" && vm.tFishOrder.logisticsNo != null){
                    alert("审核失败状态下，无需填写物流单号");
                    return;
                }
                if(vm.tFishOrder.reason == "" || vm.tFishOrder.reason == null){
                    alert("审核失败状态下，需填写失败原因");
                    return;
                }
			}else if(ostatus == "280004"){
                //待支付
                if(vm.tFishOrder.firstPay == "" || vm.tFishOrder.firstPay == null){
                    alert("待支付状态下，需填写预付款");
                    return;
                }
                if(vm.tFishOrder.secondPay == "" || vm.tFishOrder.secondPay == null){
                    alert("待支付状态下，需填写尾款");
                    return;
                }
                if(vm.tFishOrder.logisticsInfo != "" && vm.tFishOrder.logisticsInfo != null){
                    alert("待支付状态下，无需填写物流信息");
                    return;
                }
                if(vm.tFishOrder.logisticsNo != "" && vm.tFishOrder.logisticsNo != null){
                    alert("待支付状态下，无需填写物流单号");
                    return;
                }
                if(vm.tFishOrder.reason != "" && vm.tFishOrder.reason != null){
                    alert("待支付状态下，无需填写失败原因");
                    return;
                }
            }else if(ostatus == "280005"){
				//已取消
                if(vm.tFishOrder.logisticsInfo != "" && vm.tFishOrder.logisticsInfo != null){
                    alert("已取消状态下，无需填写物流信息");
                    return;
                }
                if(vm.tFishOrder.logisticsNo != "" && vm.tFishOrder.logisticsNo != null){
                    alert("已取消状态下，无需填写物流单号");
                    return;
                }
                if(vm.tFishOrder.reason != "" && vm.tFishOrder.reason != null){
                    alert("已取消状态下，无需填写失败原因");
                    return;
                }
            }else if(ostatus == "280006"){
				//待发货
                if(vm.tFishOrder.firstPay == "" || vm.tFishOrder.firstPay == null){
                    alert("待发货状态下，需填写预付款");
                    return;
                }
                if(vm.tFishOrder.secondPay == "" || vm.tFishOrder.secondPay == null){
                    alert("待发货状态下，需填写尾款");
                    return;
                }
                if(vm.tFishOrder.logisticsInfo != "" && vm.tFishOrder.logisticsInfo != null){
                    alert("待发货状态下，无需填写物流信息");
                    return;
                }
                if(vm.tFishOrder.logisticsNo != "" && vm.tFishOrder.logisticsNo != null){
                    alert("待发货状态下，无需填写物流单号");
                    return;
                }
                if(vm.tFishOrder.reason != "" && vm.tFishOrder.reason != null){
                    alert("待发货状态下，无需填写失败原因");
                    return;
                }
            }else if(ostatus == "280007"){
				//已发货
                if(vm.tFishOrder.firstPay == "" || vm.tFishOrder.firstPay == null){
                    alert("已发货状态下，需填写预付款");
                    return;
                }
                if(vm.tFishOrder.secondPay == "" || vm.tFishOrder.secondPay == null){
                    alert("已发货状态下，需填写尾款");
                    return;
                }
                if(vm.tFishOrder.logisticsInfo == "" || vm.tFishOrder.logisticsInfo == null){
                    alert("已发货状态下，需填写物流信息");
                    return;
                }
                if(vm.tFishOrder.logisticsNo == "" || vm.tFishOrder.logisticsNo == null){
                    alert("已发货状态下，需填写物流单号");
                    return;
                }
                if(vm.tFishOrder.reason != "" && vm.tFishOrder.reason != null){
                    alert("已发货状态下，无需填写失败原因");
                    return;
                }
            }else if(ostatus == "280008"){
				//已收货
                if(vm.tFishOrder.firstPay == "" || vm.tFishOrder.firstPay == null){
                    alert("已收货状态下，需填写预付款");
                    return;
                }
                if(vm.tFishOrder.secondPay == "" || vm.tFishOrder.secondPay == null){
                    alert("已收货状态下，需填写尾款");
                    return;
                }
                if(vm.tFishOrder.logisticsInfo == "" || vm.tFishOrder.logisticsInfo == null){
                    alert("已收货状态下，需填写物流信息");
                    return;
                }
                if(vm.tFishOrder.logisticsNo == "" || vm.tFishOrder.logisticsNo == null){
                    alert("已收货状态下，需填写物流单号");
                    return;
                }
                if(vm.tFishOrder.reason != "" && vm.tFishOrder.reason != null){
                    alert("已收货状态下，无需填写失败原因");
                    return;
                }
            }else if(ostatus == "280009"){
				//已完成
                if(vm.tFishOrder.firstPay == "" || vm.tFishOrder.firstPay == null){
                    alert("已完成状态下，需填写预付款");
                    return;
                }
                if(vm.tFishOrder.secondPay == "" || vm.tFishOrder.secondPay == null){
                    alert("已完成状态下，需填写尾款");
                    return;
                }
                if(vm.tFishOrder.logisticsInfo == "" || vm.tFishOrder.logisticsInfo == null){
                    alert("已完成状态下，需填写物流信息");
                    return;
                }
                if(vm.tFishOrder.logisticsNo == "" || vm.tFishOrder.logisticsNo == null){
                    alert("已完成状态下，需填写物流单号");
                    return;
                }
                if(vm.tFishOrder.reason != "" && vm.tFishOrder.reason != null){
                    alert("已完成状态下，无需填写失败原因");
                    return;
                }
            }
			var url = vm.tFishOrder.id == null ? "../tfishorder/save" : "../tfishorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tFishOrder),
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