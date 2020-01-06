$(function () {
    $("#jqGrid").jqGrid({
        url: '../mall/points/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', width: 45, key: true, hidden: true},
			{ label: '用户ID', name: 'userId', width: 75, hidden: true},
			{ label: '用户名', name: 'userName', width: 100 },
			{ label: '操作类型', name: 'operateTypeCd', width: 90,
				formatter: function(cellvalue, options, rowObject){
					if(typeof cellvalue !=='undefined' && cellvalue){
						if(cellvalue == "190001"){
							return "下单积分";
						} else {
							return "注册积分";
						}
					}
					return cellvalue;
				}
			},
			{ label: '备注', name: 'mark', width: 280 },
			{ label: '获得积分', name: 'point', width: 75 },
			{ label: '创建时间', name: 'createTime', width: 120}
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
		userName: null
	},
	methods: {
		view: function (event) {
			var modelId = getSelectedRow();
			if(modelId == null){
				return ;
			}
			location.href = "mall_points_view.html?modelId="+modelId;
		},
		del: function (event) {
			var modelId = getSelectedRows();
			if(modelId == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../mall/points/delete",
				    data: JSON.stringify(modelId),
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
                postData:{'userName': vm.userName},
                page:1 
            }).trigger("reloadGrid");
		}
	}
});