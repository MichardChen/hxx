$(function () {
    $("#jqGrid").jqGrid({
        url: '../tfishorder/list?orderNo=&date=',
        datatype: "json",
        colModel: [			
			{ label: '订单编号', name: 'orderNo', width: 150 },
			{ label: '订单类型', name: 'orderTypeCd', width: 70 },
			{ label: '卖家', name: 'fromUserId', width: 90 },
			{ label: '买家', name: 'toUserId', width: 90 },
			{ label: '预付款', name: 'firstPay', width: 40 },
			{ label: '尾款', name: 'secondPay', width: 40 },
			{ label: '订单状态', name: 'status', width: 40 },
			{ label: '下单时间', name: 'createTime', width: 80 }
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {
        search:function(event){
            var orderNo = $("#orderNo").val();
            var date = $("#date").val();
            $("#jqGrid").jqGrid('setGridParam',{
                url:"../tfishorder/list",
                postData:{'orderNo':orderNo,'date':date}, //发送数据
                page:1
            }).trigger("reloadGrid");
        },
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "tfishorder_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tfishorder/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		}
	}
});