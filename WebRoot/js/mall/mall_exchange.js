$(function () {
    $("#jqGrid").jqGrid({
        url: '../mall/exchange/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', width: 45, key: true, hidden: true},
			{ label: '用户类型', name: 'userTypeCd', width: 75, hidden: true },
			{ label: '用户ID', name: 'userId', width: 75 ,hidden: true},
			{ label: '用户名', name: 'userName', width: 75},
			{ label: '产品ID', name: 'productId', width: 90, hidden: true },
			{ label: '产品名称', name: 'productTitle', width: 200 },
			{ label: '状态', name: 'status', width: 70,
				formatter: function(cellvalue, options, rowObject){
					if(typeof cellvalue !=="undefined" && cellvalue){
						if(cellvalue == "200001"){
							return "待审核";
						} else if(cellvalue == "200002"){
							return "已处理";
						}
					}
					return cellvalue;
				}
			},
			{ label: '地址', name: 'address', width: 80, hidden: true},
			{ label: '消耗积分', name: 'points', width: 75 },
			{ label: '兑换数量', name: 'quality', width: 75 },
			{ label: '消耗金额', name: 'moneys', width: 75 },
			{ label: '兑换类型', name: 'buyTypeCd', width: 80,
				formatter: function(cellvalue, options, rowObject){
					if(typeof cellvalue !=="undefined" && cellvalue){
						if(cellvalue == "210001"){
							return "积分兑换";
						} else if(cellvalue == "210002"){
							return "银联支付";
						}
					}
					return cellvalue;
				}
			},
			{ label: '收件人', name: 'receiveName', width: 75, hidden: true },
			{ label: '电话号码', name: 'receiveMobile', width: 95, hidden: true },
			{ label: '创建时间', name: 'createTime', width: 130}
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
		productTitle: null,
		userName: null,
		status: null,
		buyTypeCd: null
	},
	methods: {
		update: function (event) {
			var exchangeId = getSelectedRow();
			if(exchangeId == null){
				return ;
			}
			location.href = "mall_exchange_edit.html?exchangeId="+exchangeId;
		},
		view: function(event){
			var exchangeId = getSelectedRow();
			if(exchangeId == null){
				return ;
			}
			location.href = "mall_exchange_view.html?exchangeId="+exchangeId;
		},
		del: function (event) {
			var exchangeId = getSelectedRows();
			if(exchangeId == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../mall/delete",
				    data: JSON.stringify(exchangeId),
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
			var exchangeId = getSelectedRows();
			if(exchangeId == null){
				return ;
			}
			confirm('确定要下架改商品吗？', function(){
				$.ajax({
					type: "POST",
					url: "../mall/off",
					data: JSON.stringify(exchangeId[0]),
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
                postData:{'productTitle': vm.productTitle,'userName':vm.price, 'buyTypeCd': vm.buyTypeCd, 'status': vm.status},
                page:1 
            }).trigger("reloadGrid");
		}
	}
});