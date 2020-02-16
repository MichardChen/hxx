$(function () {
    $("#jqGrid").jqGrid({
        url: '../tfishsupply/list?orderNo=&date=',
        datatype: "json",
        colModel: [			
			{ label: '订单号', name: 'orderNo', width: 100 },
            { label: '品类', name: 'productType', width: 60 },
			{ label: '主要类型', name: 'mainType', width: 60 },
			{ label: '规格', name: 'size', width: 60 },
			{ label: '价格', name: 'price', width: 60 },
			{ label: '供应量', name: 'weight', width: 80 },
			{ label: '发布时间', name: 'createTime', width: 80 },
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
                url:"../tfishsupply/list",
                postData:{'orderNo':orderNo,'date':date}, //发送数据
                page:1
            }).trigger("reloadGrid");
        },
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "tfishsupply_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tfishsupply/delete",
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