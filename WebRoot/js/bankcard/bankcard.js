$(function () {
    $("#jqGrid").jqGrid({
        url: '../bankcard/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true, hidden: true },
			{ label: '卡号', name: 'bankNo', width: 160 },
			{ label: '开户行', name: 'bankOpen', width: 180 },
			{ label: '银行', name: 'bank', width: 160 },
			{ label: '开户人', name: 'bankOwner', width: 80 },
			{ label: '创建时间', name: 'createTime', width: 130 },
			{ label: '创建人', name: 'createBy', width: 80 },
			{ label: '修改时间', name: 'updateTime', width: 80, hidden: true },
			{ label: '修改人', name: 'updateBy', width: 80, hidden: true },
			{ label: '备注', name: 'mark', width: 80, hidden: true},
			{ label: '是否有效', name: 'flg', width: 80 ,
				formatter: function(cellvalue, options, rowObject){
					if(typeof cellvalue!=undefined){
						if(cellvalue == 0){
							return "失效";
						}else if(cellvalue == 1){
							return "有效";
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
		bankNo: null,
		bankOpen: null,
		bank: null,
		bankOwner: null,
		createBy: null,
		flg: null
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "bankcard_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../bankcard/disable",
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
		enable: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('确定要启用选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../bankcard/enable",
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
					'bankNo': vm.bankNo,
					'bankOpen':vm.bankOpen,
					'bank': vm.bank,
					'bankOwner': vm.bankOwner,
					'createBy': vm.createBy,
					'flg': vm.flg
				},
				page:1
			}).trigger("reloadGrid");
		}
	}
});