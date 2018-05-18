var id = T.p("id");
var ue = UE.getEditor("container");
function getPath(obj) {
	 if (obj) {
	  if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
	   obj.select();
	   return document.selection.createRange().text;
	  } else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
	   if (obj.files) {
	    return obj.files.item(0).getAsDataURL();
	   }
	   return obj.value;
	  }
	  return obj.value;
	 }
}
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		tStory:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../tstory/info/"+id, function(r){
                vm.tStory = r.tStory;
            });
		},
		saveOrUpdate: function (event) {
			var html = ue.getContent();
			var txt = ue.getContentTxt();
			alert(html);
			return;
			var url = document.getElementById("icon").value;
			vm.tStory.icon=url;
			vm.tStory.content=ue.getContent();
			var url = vm.tStory.id == null ? "../tstory/save" : "../tstory/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tStory),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		back: function (event) {
			history.go(-1);
		}
	}
});