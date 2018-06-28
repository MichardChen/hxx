$(function () {
	loadData();
});
function loadData(){
	$("#jqGrid").jqGrid({
        url: '../tcarlease/list?queryBrand=0&queryCarName=',
        datatype: "json",
        colModel: [		
        	{ label: 'id', name: 'id', width: 50, key: true },
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
}
var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	created: function() {
		$.get("../tbrand/queryAllBrand", function(r){
			//var r =eval('('+rr+')');
	        var list = r.tBrandList;
	    	if(r.code === 0){
	    		var html = "";
	    		for(var b in list){
	    			//向品牌下拉框添加品牌
	    			html += "<option value='" + list[b].id + "'>" +list[b].brand + "</option>";
	    		}
	    		 $("#brand").append(html);
	    }});
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "tcarlease_add.html?id="+id;
		},
		chooseFile:function(event){
			$("#btn_file").click();
		},
		modifyParams:function(event){
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "params.html?typeCd=110001&id="+id;
		},
		search:function(event){
			var queryCarName = $("#queryCarName").val();
			var queryBrand = $("#brand").val();
			$("#jqGrid").jqGrid('setGridParam',{ 
                url:"../tcarlease/list", 
                postData:{'queryCarName':queryCarName,'queryBrand':queryBrand}, //发送数据 
                page:1 
            }).trigger("reloadGrid"); 
		},
		upload:function(event){
			var fileObj = document.getElementById("btn_file").files[0];
			var formFile = new FormData();
			if(fileObj==null || fileObj == ""){
				alert("请选择xls文件");
				return;
			}
			var url = "../common/uploadExcelFile";
			formFile.append("typeCd","110001");
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
			
			confirm('确定要下架？', function(){
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