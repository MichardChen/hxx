$(function () {
    $("#jqGrid").jqGrid({
        url: '../mall/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', width: 45, key: true },
			{ label: '产品名称', name: 'productTitle', width: 75 },
			{ label: '价格', name: 'price', width: 75 },
			{ label: '所需兑换积分', name: 'needPoints', width: 90 },
			{ label: '库存', name: 'quality', width: 75 },
			{ label: '状态', name: 'status', width: 75 },
			{ label: '产品类型', name: 'productTypeCd', width: 100 },
			{ label: '用户类型', name: 'userTypeCd', width: 80},
			{ label: '创建时间', name: 'createTime', width: 80}
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
		productTitle:null,
		price:null
	},
	methods: {
		update: function (event) {
			var mallId = getSelectedRow();
			if(mallId == null){
				return ;
			}
			location.href = "mall_product_add.html?mallId="+mallId;
		},
		del: function (event) {
			var mallId = getSelectedRows();
			if(mallId == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../mall/delete",
				    data: JSON.stringify(mallId),
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
		},
		query:function(){
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'productTitle': vm.productTitle,'price':vm.price},
                page:1 
            }).trigger("reloadGrid");
		}
	}
});