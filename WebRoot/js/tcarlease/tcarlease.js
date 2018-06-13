$(function () {
    $("#jqGrid").jqGrid({
        url: '../tcarlease/list',
        datatype: "json",
        colModel: [			
			{ label: '品牌', name: 'brand', width: 80 }, 			
			{ label: '车名', name: 'carName', width: 80 }, 			
			{ label: '首付', name: 'firstPayment', width: 80 }, 			
			{ label: '月供', name: 'monthPayment', width: 80 }, 			
			{ label: '车系', name: 'carSeriesId', width: 80 }, 			
			{ label: '创建者', name: 'createBy', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', width: 80 }, 			
			{ label: '更新者', name: 'updateBy', width: 80 }, 			
			{ label: '状态', name: 'flg', width: 80 }, 			
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
			location.href = "tcarlease_add.html?id="+id;
		},
		upload:function(event){
			var fileObj = document.getElementById("btn_file").files[0];
			var formFile = new FormData();
			var url = "../common/uploadExcelFile";
			formFile.append("uploadFile", fileObj); 
			$.ajax({
				type: "POST",
			    url: url,
			    data: formFile,
			    contentType: "application/json",
			    processData: false,
			    contentType: false,
			    success: function(r){
			    	if(r.code === 0){
						alert(r.msg);
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tcarlease/delete",
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