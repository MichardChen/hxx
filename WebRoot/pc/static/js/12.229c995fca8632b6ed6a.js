webpackJsonp([12],{LQIH:function(t,s,a){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var e=a("d5OS"),i=function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"container"},[a("section",{staticClass:"row"},[a("div",{staticClass:"col-xs-12"},[a("v-breadnav",[t._v("惠群问答")])],1)]),t._v(" "),a("section",{staticClass:"row question-list"},[a("div",{staticClass:"col-xs-12 clearfix"},[t._m(0),t._v(" "),a("ul",{staticClass:"nav-list col-xs-2"},[a("li",{staticClass:"active"},[a("a",{attrs:{href:"#buy-explain","aria-controls":"buy-explain",role:"tab","data-toggle":"tab"},on:{click:function(s){t.getListData("080001")}}},[t._v("购车说明")])]),t._v(" "),a("li",[a("a",{attrs:{href:"#cost-explain","aria-controls":"cost-explain",role:"tab","data-toggle":"tab"},on:{click:function(s){t.getListData("080002")}}},[t._v("费用说明")])]),t._v(" "),a("li",[a("a",{attrs:{href:"#rent-plan","aria-controls":"rent-plan",role:"tab","data-toggle":"tab"},on:{click:function(s){t.getListData("080003")}}},[t._v("租后方案")])]),t._v(" "),a("li",[a("a",{attrs:{href:"#Transport-rules","aria-controls":"Transport-rules",role:"tab","data-toggle":"tab"},on:{click:function(s){t.getListData("080004")}}},[t._v("用车规则")])]),t._v(" "),a("li",[a("a",{attrs:{href:"#Insurance-services","aria-controls":"Insurance-services",role:"tab","data-toggle":"tab"},on:{click:function(s){t.getListData("080005")}}},[t._v("保险服务")])]),t._v(" "),a("li",[a("a",{attrs:{href:"#other-questions","aria-controls":"other-questions",role:"tab","data-toggle":"tab"},on:{click:function(s){t.getListData("080006")}}},[t._v("其他问题")])])]),t._v(" "),a("div",{staticClass:"tab-content col-xs-10"},[a("div",{staticClass:"tab-pane active",attrs:{role:"tabpanel",id:"buy-explain"}},[0!=t.questionAnswerList.length?a("ul",{staticClass:"questions-list"},t._l(t.questionAnswerList,function(s,e){return a("li",{key:e,staticClass:"question",attrs:{"data-id":s.id}},[a("h4",[t._v(t._s(s.question))]),t._v(" "),a("p",[t._v(t._s(s.answer))])])})):t._e()]),t._v(" "),a("div",{staticClass:"tab-pane",attrs:{role:"tabpanel",id:"cost-explain"}},[0!=t.questionAnswerList.length?a("ul",{staticClass:"questions-list"},t._l(t.questionAnswerList,function(s,e){return a("li",{key:e,staticClass:"question",attrs:{"data-id":s.id}},[a("h4",[t._v(t._s(s.question))]),t._v(" "),a("p",[t._v(t._s(s.answer))])])})):t._e()]),t._v(" "),a("div",{staticClass:"tab-pane",attrs:{role:"tabpanel",id:"rent-plan"}},[0!=t.questionAnswerList.length?a("ul",{staticClass:"questions-list"},t._l(t.questionAnswerList,function(s,e){return a("li",{key:e,staticClass:"question",attrs:{"data-id":s.id}},[a("h4",[t._v(t._s(s.question))]),t._v(" "),a("p",[t._v(t._s(s.answer))])])})):t._e()]),t._v(" "),a("div",{staticClass:"tab-pane",attrs:{role:"tabpanel",id:"Transport-rules"}},[0!=t.questionAnswerList.length?a("ul",{staticClass:"questions-list"},t._l(t.questionAnswerList,function(s,e){return a("li",{key:e,staticClass:"question",attrs:{"data-id":s.id}},[a("h4",[t._v(t._s(s.question))]),t._v(" "),a("p",[t._v(t._s(s.answer))])])})):t._e()]),t._v(" "),a("div",{staticClass:"tab-pane",attrs:{role:"tabpanel",id:"Insurance-services"}},[0!=t.questionAnswerList.length?a("ul",{staticClass:"questions-list"},t._l(t.questionAnswerList,function(s,e){return a("li",{key:e,staticClass:"question",attrs:{"data-id":s.id}},[a("h4",[t._v(t._s(s.question))]),t._v(" "),a("p",[t._v(t._s(s.answer))])])})):t._e()]),t._v(" "),a("div",{staticClass:"tab-pane",attrs:{role:"tabpanel",id:"other-questions"}},[0!=t.questionAnswerList.length?a("ul",{staticClass:"questions-list"},t._l(t.questionAnswerList,function(s,e){return a("li",{key:e,staticClass:"question",attrs:{"data-id":s.id}},[a("h4",[t._v(t._s(s.question))]),t._v(" "),a("p",[t._v(t._s(s.answer))])])})):t._e()])])])])])},n=[function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"title"},[a("h3",[t._v("惠群问答")])])}],l={render:i,staticRenderFns:n},r=l,o=a("vSla"),c=o(e.a,r,!1,null,null,null);s.default=c.exports},d5OS:function(t,s,a){"use strict";(function(t){var e=a("QQ8u"),i=a("2Uyi");s.a={data:function(){return{questionAnswerList:[]}},components:{vBreadnav:e.a},methods:{getListData:function(s,a){var e=this,n={};n.pageNum=a||1,n.typeCd=s,n.pageSize=10,n.platForm="020003",t.ajax({url:i.b+"/queryQuestionAnswerList",type:"get",data:n,dataType:"json",asnyc:!0,success:function(t){t.code==i.c&&(console.log(t.data),e.questionAnswerList=t.data.questionAnswerList)}})}},mounted:function(){this.getListData()}}}).call(s,a("slT5"))}});
//# sourceMappingURL=12.229c995fca8632b6ed6a.js.map