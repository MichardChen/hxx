webpackJsonp([19],{"8PwT":function(t,a,s){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var e=s("QVVQ"),n=function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",{staticClass:"container"},[s("section",{staticClass:"row"},[s("div",{staticClass:"col-xs-12"},[s("v-breadnav",[t._v("关于我们")])],1)]),t._v(" "),s("section",{staticClass:"row "},[s("div",{staticClass:"col-xs-12"},[s("div",{staticClass:"content about-us clearfix"},[s("ul",{staticClass:"nav-list pull-left"},t._l(t.aboutUsList,function(a,e){return s("li",{key:e,class:0==e?"active":""},[s("a",{attrs:{href:t.hrefArr[e],role:"tab","data-toggle":"tab"}},[t._v(t._s(a.documentName))])])})),t._v(" "),s("div",{staticClass:"tab-content"},t._l(t.aboutUsList,function(a,e){return s("div",{key:e,class:0==e?"tab-pane active":"tab-pane",attrs:{role:"tabpanel",id:t.idArr[e]}},[s("div",{staticClass:"title"},[s("h3",[t._v(t._s(a.documentName))])]),t._v(" "),s("div",{staticClass:"aboutus-item",domProps:{innerHTML:t._s(a.documentUrl)}})])}))])])])])},c=[],i={render:n,staticRenderFns:c},o=i,r=s("vSla"),u=r(e.a,o,!1,null,null,null);a.default=u.exports},QVVQ:function(t,a,s){"use strict";(function(t){var e=s("QQ8u"),n=s("2Uyi");a.a={data:function(){return{aboutUsList:[],hrefArr:["#about-us","#add-us","#services","#about-saleafter","#contact-us","#buycar-flow"],idArr:["about-us","add-us","services","about-saleafter","contact-us","buycar-flow"]}},components:{vBreadnav:e.a},methods:{getListData:function(){var a=this,s={};s.platForm="020003",t.ajax({url:n.b+"/queryDocumentList",type:"get",data:s,dataType:"json",asnyc:!0,success:function(t){t.code==n.c&&(console.log(t.data),a.aboutUsList=t.data.documentList)}})}},mounted:function(){this.getListData()}}}).call(a,s("slT5"))}});
//# sourceMappingURL=19.54ee62f3c33767e6c38d.js.map