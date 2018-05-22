$(function () {
    $("#jqGrid").jqGrid({
        url: '../tstory/list',
        datatype: "json",
        colModel: [			
			{ label: '故事ID', name: 'id', width: 50, key: true },
			{ label: '故事封面图片', name: 'storyIcon', width: 80 }, 			
			{ label: '故事标题', name: 'storyTitle', width: 80 }, 			
			{ label: '故事链接', name: 'descUrl', width: 80 }, 			
			{ label: '创建者', name: 'createBy', width: 80 }, 	
			{ label: '创建时间', name: 'createTime', width: 80 },
			{ label: '更新者', name: 'updateBy', width: 80 }, 	
			{ label: '更新时间', name: 'updateTime', width: 80 }	,
			{ label: '状态', name: 'flg', width: 80 }	
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
			
			location.href = "tstory_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tstory/delete",
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