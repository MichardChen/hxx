$(function () {
    $("#jqGrid").jqGrid({
        url: '../mall/points/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', width: 45, key: true, hidden: true},
			{ label: '用户类型', name: 'userTypeCd', width: 75,
				formatter: function(cellvalue, options, rowObject){
					if(typeof cellvalue !=='undefined' && cellvalue){
						if(cellvalue == "1001"){
							return "个人";
						} else {
							return "企业";
						}
					}
					return cellvalue;
				}
			},
			{ label: '用户ID', name: 'userId', width: 75 },
			{ label: '操作类型', name: 'operateTypeCd', width: 90,
				formatter: function(cellvalue, options, rowObject){
					if(typeof cellvalue !=='undefined' && cellvalue){
						if(cellvalue == "190001"){
							return "兑换商品";
						} else {
							return "注册";
						}
					}
					return cellvalue;
				}
			},
			{ label: '备注', name: 'mark', width: 280 },
			{ label: '获得积分', name: 'point', width: 75 },
			{ label: '创建时间', name: 'createTime', width: 120}
		],
		// shrinkToFit: false,
		// autoScroll: true,
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
		productTitle: null,
		price: null,
		status: null
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
		offLoading: function (event) {
			var mallId = getSelectedRows();
			if(mallId == null){
				return ;
			}
			confirm('确定要下架改商品吗？', function(){
				$.ajax({
					type: "POST",
					url: "../mall/off",
					data: JSON.stringify(mallId[0]),
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
		show:function(event){
			var rowKey = getSelectedRow();
			if(rowKey == null){
				return ;
			}
			var rowData = $( "#jqGrid" ).getRowData(rowKey);
			debugger
			window.open(rowData.productDetailUrl);
		},
		query:function(){
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'productTitle': vm.productTitle,'price':vm.price, 'status': vm.status},
                page:1 
            }).trigger("reloadGrid");
		}
	}
});