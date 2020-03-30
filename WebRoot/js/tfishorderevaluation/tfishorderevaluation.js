$(function () {
    $("#jqGrid").jqGrid({
        url: '../tfishorderevaluation/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true, hidden: true},
			{ label: '订单编号', name: 'orderNo', width: 130 },
			{ label: '订单类型', name: 'orderTypeCd', width: 80 },
			{ label: '评价', name: 'evaluation', width: 300 },
			{ label: '分数', name: 'points', width: 80 },
			{ label: '创建时间', name: 'createTime', width: 120 },
			{ label: '创建人', name: 'createBy', width: 80, hidden: true  },
			{ label: '修改时间', name: 'updateTime', width: 80, hidden: true },
			{ label: '修改人', name: 'updateBy', width: 80, hidden: true },
			{ label: '审核状态', name: 'flg', width: 80,
				formatter: function(cellvalue, options, rowObject){
					if(typeof cellvalue!=undefined){
						if(cellvalue == 0){
							return "待审核";
						}else if(cellvalue == 1){
							return "审核通过";
						}else if(cellvalue == 2){
							return "审核失败";
						}
					}
					return "";
				}
			}
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
		orderNo: null,
		orderTypeCd: null,
		flg: null
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "tfishorderevaluation_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tfishorderevaluation/delete",
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
		},
		auditPass: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('确定要审核通过选中的记录？', function(){
				$.ajax({
					type: "POST",
					url: "../tfishorderevaluation/auditPass",
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
		},
		auditFail: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('确定要审核失败选中的记录？', function(){
				$.ajax({
					type: "POST",
					url: "../tfishorderevaluation/auditFail",
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
		},
		query:function(){
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{
					'orderNo': vm.orderNo,
					'orderTypeCd':vm.orderTypeCd,
					'flg': vm.flg
				},
				page:1
			}).trigger("reloadGrid");
		}
	}
});