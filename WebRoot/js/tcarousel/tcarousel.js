$(function () {
    $("#jqGrid").jqGrid({
        url: '../tcarousel/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '', name: 'imgUrl', width: 80 }, 			
			{ label: '', name: 'realUrl', width: 80 }, 			
			{ label: '', name: 'mark', width: 80 }, 			
			{ label: '', name: 'createTime', width: 80 }, 			
			{ label: '', name: 'updateTime', width: 80 }, 			
			{ label: '', name: 'flg', width: 80 }, 			
			{ label: '', name: 'createBy', width: 80 }, 			
			{ label: '', name: 'updateBy', width: 80 }, 			
			{ label: '', name: 'typeCd', width: 80 }			
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
			
			location.href = "tcarousel_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tcarousel/delete",
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