$(function () {
    $("#jqGrid").jqGrid({
        url: '../tfinancecommit/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '', name: 'financeId', width: 80 }, 			
			{ label: '', name: 'brandId', width: 80 }, 			
			{ label: '', name: 'brandSeriesId', width: 80 }, 			
			{ label: '', name: 'provinceId', width: 80 }, 			
			{ label: '', name: 'cityId', width: 80 }, 			
			{ label: '', name: 'name', width: 80 }, 			
			{ label: '', name: 'age', width: 80 }, 			
			{ label: '', name: 'idcardNo', width: 80 }, 			
			{ label: '', name: 'sex', width: 80 }, 			
			{ label: '', name: 'mobile', width: 80 }, 			
			{ label: '', name: 'mark', width: 80 }, 			
			{ label: '', name: 'createTime', width: 80 }, 			
			{ label: '', name: 'updateBy', width: 80 }, 			
			{ label: '', name: 'updateTime', width: 80 }, 			
			{ label: '', name: 'status', width: 80 }			
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
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "tfinancecommit_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tfinancecommit/delete",
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