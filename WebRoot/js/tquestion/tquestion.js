$(function () {
    $("#jqGrid").jqGrid({
        url: '../tquestion/list',
        datatype: "json",
        colModel: [			
			{ label: '联系电话', name: 'mobile', width: 50 }, 			
			{ label: '联系人', name: 'linkMan', width: 50 }, 			
			{ label: '咨询内容', name: 'question', width: 120 }, 			
			{ label: '销售经理', name: 'employeeId', width: 50 }, 
			{ label: '反馈时间', name: 'createTime', width: 80 }, 
			{ label: '处理者', name: 'updateBy', width: 50 }, 
			/*{ label: '处理时间', name: 'updateTime', width: 80 }, */
			{ label: '状态', name: 'status', width: 50 }			
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
			
			location.href = "tquestion_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tquestion/delete",
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