$(function () {
    $("#jqGrid").jqGrid({
        url: '../tnews/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '', name: 'newsLogo', width: 80 }, 			
			{ label: '', name: 'newsTitle', width: 80 }, 			
			{ label: '', name: 'newsTypeCd', width: 80 }, 			
			{ label: '', name: 'hotFlg', width: 80 }, 			
			{ label: '', name: 'createUser', width: 80 }, 			
			{ label: '', name: 'createTime', width: 80 }, 			
			{ label: '', name: 'updateTime', width: 80 }, 			
			{ label: '', name: 'flg', width: 80 }, 			
			{ label: '', name: 'content', width: 80 }, 			
			{ label: '', name: 'contentUrl', width: 80 }, 			
			{ label: '', name: 'updateUserId', width: 80 }, 			
			{ label: '', name: 'topFlg', width: 80 }			
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
			
			location.href = "tnews_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tnews/delete",
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