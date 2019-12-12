$(function () {
    $("#jqGrid").jqGrid({
        url: '../tcartparams/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '', name: 'cartId', width: 80 }, 			
			{ label: '', name: 'biaopei', width: 80 }, 			
			{ label: '', name: 'length', width: 80 }, 			
			{ label: '', name: 'width', width: 80 }, 			
			{ label: '', name: 'height', width: 80 }, 			
			{ label: '', name: 'zhouju', width: 80 }, 			
			{ label: '', name: 'qianlunju', width: 80 }, 			
			{ label: '', name: 'houlunju', width: 80 }, 			
			{ label: '', name: 'lidijianxi', width: 80 }, 			
			{ label: '', name: 'zhiliang', width: 80 }, 			
			{ label: '', name: 'cheshenjiegou', width: 80 }, 			
			{ label: '', name: 'chemenshu', width: 80 }, 			
			{ label: '', name: 'zuoweishu', width: 80 }, 			
			{ label: '', name: 'youxiangrongji', width: 80 }, 			
			{ label: '', name: 'xinglirongji', width: 80 }, 			
			{ label: '', name: 'fadongjixinghao', width: 80 }, 			
			{ label: '', name: 'pailiang', width: 80 }, 			
			{ label: '', name: 'jinqixingshi', width: 80 }, 			
			{ label: '', name: 'qgpxxingshi', width: 80 }, 			
			{ label: '', name: 'qigangshu', width: 80 }, 			
			{ label: '', name: 'qimenshu', width: 80 }, 			
			{ label: '', name: 'gangjing', width: 80 }, 			
			{ label: '', name: 'xingcheng', width: 80 }, 			
			{ label: '', name: 'maxmali', width: 80 }, 			
			{ label: '', name: 'maxgonglv', width: 80 }, 			
			{ label: '', name: 'maxgonglvzhuansu', width: 80 }, 			
			{ label: '', name: 'maxniuju', width: 80 }, 			
			{ label: '', name: 'maxnjzhuansu', width: 80 }, 			
			{ label: '', name: 'fadongjityjs', width: 80 }, 			
			{ label: '', name: 'ranliaotype', width: 80 }, 			
			{ label: '', name: 'ranliaocode', width: 80 }, 			
			{ label: '', name: 'gongyoutype', width: 80 }, 			
			{ label: '', name: 'ganggaicailiao', width: 80 }, 			
			{ label: '', name: 'gangticailiao', width: 80 }, 			
			{ label: '', name: 'huanbobiaozhun', width: 80 }, 			
			{ label: '', name: 'dangweigeshu', width: 80 }, 			
			{ label: '', name: 'biansuxiangtype', width: 80 }, 			
			{ label: '', name: 'qudongtype', width: 80 }, 			
			{ label: '', name: 'siqutype', width: 80 }, 			
			{ label: '', name: 'chasuxiangjg', width: 80 }, 			
			{ label: '', name: 'qianxuanliangtype', width: 80 }, 			
			{ label: '', name: 'houxuanliangtype', width: 80 }, 			
			{ label: '', name: 'zhulitype', width: 80 }, 			
			{ label: '', name: 'chetijiegou', width: 80 }, 			
			{ label: '', name: 'qianzhidongqitype', width: 80 }, 			
			{ label: '', name: 'houzhidongqitype', width: 80 }, 			
			{ label: '', name: 'zhuchezhidongtype', width: 80 }, 			
			{ label: '', name: 'qianluntaiguige', width: 80 }, 			
			{ label: '', name: 'houluntaiguige', width: 80 }, 			
			{ label: '', name: 'beitaiguige', width: 80 }, 			
			{ label: '', name: 'zhujiashiqinang', width: 80 }, 			
			{ label: '', name: 'fujiashiqinang', width: 80 }, 			
			{ label: '', name: 'qiancepaiqinang', width: 80 }, 			
			{ label: '', name: 'houcepaiqinang', width: 80 }, 			
			{ label: '', name: 'qiantouqinang', width: 80 }, 			
			{ label: '', name: 'houtouqinang', width: 80 }, 			
			{ label: '', name: 'qibuqinang', width: 80 }, 			
			{ label: '', name: 'taiyajianceqinang', width: 80 }, 			
			{ label: '', name: 'lingtaiyajxxs', width: 80 }, 			
			{ label: '', name: 'anquandaiweixts', width: 80 }, 			
			{ label: '', name: 'ertongzuoyijiekou', width: 80 }, 			
			{ label: '', name: 'fadongjifangdao', width: 80 }, 			
			{ label: '', name: 'cheneizhognkongsuo', width: 80 }, 			
			{ label: '', name: 'createTime', width: 80 }, 			
			{ label: '', name: 'updateTime', width: 80 }			
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
			
			location.href = "tcartparams_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tcartparams/delete",
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