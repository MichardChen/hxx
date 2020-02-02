$(function () {
    $("#jqGrid").jqGrid({
        url: '../tracesource/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true, hidden: true},
			{ label: '订单号', name: 'orderNo', width:150 },
			{ label: '来源', name: 'orderTypeCd', width: 200,
				formatter: function(cellvalue, options, rowObject){
					if(typeof cellvalue!=undefined && cellvalue){
						if(cellvalue == "270001"){
							return "求购订单表";
						}else if(cellvalue == "270002"){
							return "供应订单表";
						}else if(cellvalue == "270003"){
							return "自行添加的数据,与订单无关";
						}
					}
					return "";
				}
			},
			{ label: '内容', name: 'content', width: 80, hidden: true },
			{ label: '生成的网页url', name: 'contentUrl', width: 80, hidden: true},
			{ label: '生成的二维码url', name: 'qrCodeUrl', width: 80, hidden: true},
			{ label: '创建时间', name: 'createTime', width: 80 },
			{ label: '更新时间', name: 'updateTime', width: 80 }
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
		orderTypeCd: null,
		orderNo: null
	},
	methods: {
		query:function(){
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'orderTypeCd': vm.orderTypeCd,'orderNo':vm.orderNo},
				page:1
			}).trigger("reloadGrid");
		},
		qrDownload: function(){
			var id = getSelectedRow();
			var grid = $("#jqGrid");
			var qrCodeUrl = $("#jqGrid").jqGrid('getRowData', id).qrCodeUrl;
			if(typeof qrCodeUrl !=="undefined" && qrCodeUrl){            // 获取图片地址
				window.open("../file/download/img?filePath="+qrCodeUrl);
			} else {
				alert("未生成二维码！");
			}
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "tracesource_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tracesource/delete",
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