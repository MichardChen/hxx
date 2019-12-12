$(function () {
    $("#jqGrid").jqGrid({
        url: '../tcartparam2/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '', name: 'cartId', width: 80 }, 			
			{ label: '', name: 'yaokongyaosi', width: 80 }, 			
			{ label: '', name: 'wuyaosiqidong', width: 80 }, 			
			{ label: '', name: 'wuyaosixitong', width: 80 }, 			
			{ label: '', name: 'absbaosi', width: 80 }, 			
			{ label: '', name: 'zhidonglifenpei', width: 80 }, 			
			{ label: '', name: 'shachefuzhu', width: 80 }, 			
			{ label: '', name: 'qianyinlikongzhi', width: 80 }, 			
			{ label: '', name: 'chenshenwending', width: 80 }, 			
			{ label: '', name: 'shangpofuzhu', width: 80 }, 			
			{ label: '', name: 'zidongzhuche', width: 80 }, 			
			{ label: '', name: 'duopohuanjiang', width: 80 }, 			
			{ label: '', name: 'kebianxuanlinag', width: 80 }, 			
			{ label: '', name: 'kongqixuanliang', width: 80 }, 			
			{ label: '', name: 'diandongtianchuang', width: 80 }, 			
			{ label: '', name: 'quanjingtianchuan', width: 80 }, 			
			{ label: '', name: 'yundongwaiguantaojian', width: 80 }, 			
			{ label: '', name: 'lvjinjinlunquan', width: 80 }, 			
			{ label: '', name: 'diandongxihemen', width: 80 }, 			
			{ label: '', name: 'cehuamen', width: 80 }, 			
			{ label: '', name: 'diandonghoubeixiang', width: 80 }, 			
			{ label: '', name: 'ganyinghoubeixiang', width: 80 }, 			
			{ label: '', name: 'chedingxinglijia', width: 80 }, 			
			{ label: '', name: 'zhenpifangxiangpan', width: 80 }, 			
			{ label: '', name: 'fangxiangpantiaojie', width: 80 }, 			
			{ label: '', name: 'fangxiangpandiandong', width: 80 }, 			
			{ label: '', name: 'duogongnengfangxiang', width: 80 }, 			
			{ label: '', name: 'fxphuandang', width: 80 }, 			
			{ label: '', name: 'fxpjiare', width: 80 }, 			
			{ label: '', name: 'dignweixunhang', width: 80 }, 			
			{ label: '', name: 'qianhouzhucheleida', width: 80 }, 			
			{ label: '', name: 'daocheyingxiang', width: 80 }, 			
			{ label: '', name: 'xingchediannao', width: 80 }, 			
			{ label: '', name: 'quanyejingyibiaopan', width: 80 }, 			
			{ label: '', name: 'zuoyicaizhi', width: 80 }, 			
			{ label: '', name: 'zuoyigaoditiaojie', width: 80 }, 			
			{ label: '', name: 'yaobuzhichengtiaojie', width: 80 }, 			
			{ label: '', name: 'juanbuzhichengtiaojie', width: 80 }, 			
			{ label: '', name: 'zhufujiashidiandong', width: 80 }, 			
			{ label: '', name: 'houpaizuoyidiandong', width: 80 }, 			
			{ label: '', name: 'qianhouzuoyijiare', width: 80 }, 			
			{ label: '', name: 'gpsdaohang', width: 80 }, 			
			{ label: '', name: 'zhongkongtaicaise', width: 80 }, 			
			{ label: '', name: 'lanyachezai', width: 80 }, 			
			{ label: '', name: 'waijieyinyuan', width: 80 }, 			
			{ label: '', name: 'duomeitixitong', width: 80 }, 			
			{ label: '', name: 'yangshengqishu', width: 80 }, 			
			{ label: '', name: 'jinguangdeng', width: 80 }, 			
			{ label: '', name: 'yuanguangdeng', width: 80 }, 			
			{ label: '', name: 'rijianxingchedeng', width: 80 }, 			
			{ label: '', name: 'zishiyingyuanjindeng', width: 80 }, 			
			{ label: '', name: 'zidongtoudeng', width: 80 }, 			
			{ label: '', name: 'zxfuzhudeng', width: 80 }, 			
			{ label: '', name: 'zxtoudeng', width: 80 }, 			
			{ label: '', name: 'qianwudeng', width: 80 }, 			
			{ label: '', name: 'dadenggaoduketiao', width: 80 }, 			
			{ label: '', name: 'dadengqixizhuangzhi', width: 80 }, 			
			{ label: '', name: 'qiandiandongchechuan', width: 80 }, 			
			{ label: '', name: 'houdiandongchechuan', width: 80 }, 			
			{ label: '', name: 'chechuanfangjia', width: 80 }, 			
			{ label: '', name: 'houshijingdiandong', width: 80 }, 			
			{ label: '', name: 'houshijingjiare', width: 80 }, 			
			{ label: '', name: 'neiwaihoushijing', width: 80 }, 			
			{ label: '', name: 'houshijingdiandongzd', width: 80 }, 			
			{ label: '', name: 'zybhuazhuangjing', width: 80 }, 			
			{ label: '', name: 'houyushua', width: 80 }, 			
			{ label: '', name: 'ganyingyushua', width: 80 }, 			
			{ label: '', name: 'kongtiaokongzhitype', width: 80 }, 			
			{ label: '', name: 'houpaidulikongtiao', width: 80 }, 			
			{ label: '', name: 'houzuochufengkou', width: 80 }, 			
			{ label: '', name: 'wendufenqu', width: 80 }, 			
			{ label: '', name: 'cheneikongqitj', width: 80 }, 			
			{ label: '', name: 'chezaibingxiang', width: 80 }, 			
			{ label: '', name: 'zidongbocheruwei', width: 80 }, 			
			{ label: '', name: 'fadongjiqidong', width: 80 }, 			
			{ label: '', name: 'bingxianfuzhu', width: 80 }, 			
			{ label: '', name: 'chedaopianli', width: 80 }, 			
			{ label: '', name: 'zhudongshache', width: 80 }, 			
			{ label: '', name: 'zhengtizhudong', width: 80 }, 			
			{ label: '', name: 'yeshixitong', width: 80 }, 			
			{ label: '', name: 'zhongkongyejing', width: 80 }, 			
			{ label: '', name: 'zishiyingxunhang', width: 80 }, 			
			{ label: '', name: 'quanjingshexiangtou', width: 80 }, 			
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
			
			location.href = "tcartparam2_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tcartparam2/delete",
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