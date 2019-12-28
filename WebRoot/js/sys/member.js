$(function () {
    $("#jqGrid").jqGrid({
        url: '../basic/member/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'id', width: 45, key: true },
			{ label: '用户名', name: 'name', width: 75 },
			{ label: '昵称', name: 'nickName', width: 90 },
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 80}                   
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
		mobile:null,
		name:null
	},
	methods: {
		update: function (event) {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			location.href = "member_add.html?memberId="+userId;
		},
		del: function (event) {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../basic/member/delete",
				    data: JSON.stringify(userIds),
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
                postData:{'mobile': vm.mobile,'name':vm.name},
                page:1 
            }).trigger("reloadGrid");
		}
	}
});