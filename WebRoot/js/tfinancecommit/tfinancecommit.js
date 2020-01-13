$(function () {
    $("#jqGrid").jqGrid({
        url: '../tfinancecommit/list?date=',
        datatype: "json",
        colModel: [			
			{ label: '申请金融产品', name: 'financeId', width: 80 }, 			
			/*{ label: '汽车品牌', name: 'brandId', width: 80 },
			{ label: '车系', name: 'brandSeriesId', width: 80 }, 			
			{ label: '所在省份', name: 'provinceId', width: 80 }, 			
			{ label: '所在城市', name: 'cityId', width: 80 }, 		*/
			{ label: '申请人', name: 'name', width: 80 }, 			
			{ label: '手机号码', name: 'mobile', width: 80 }, 			
			{ label: '申请时间', name: 'createTime', width: 80 }, 			
			{ label: '状态', name: 'status', width: 80 }			
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
		search:function(event){
			var date = $("#date").val();
			$("#jqGrid").jqGrid('setGridParam',{ 
                url:"../tfinancecommit/list", 
                postData:{'date':date}, //发送数据 
                page:1 
            }).trigger("reloadGrid"); 
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