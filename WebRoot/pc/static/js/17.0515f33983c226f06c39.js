webpackJsonp([17],{dBtE:function(s,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a("lAhe"),e=function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("div",{staticClass:"car-desc"},[a("section",{staticClass:"container"},[a("div",{staticClass:"row"},[a("div",{staticClass:"col-xs-12"},[a("v-breadnav",[s._v(s._s(s.importCarDesc.carName+"  "+s.importCarDesc.carSeriesName))])],1)]),s._v(" "),a("div",{staticClass:"row"},[a("div",{staticClass:"col-xs-12"},[a("section",{staticClass:"desc-header clearfix"},[a("div",{staticClass:"col-xs-5"},[a("v-slider",{attrs:{slideArr:s.getSliderArr}})],1),s._v(" "),a("div",{staticClass:"col-xs-7"},[a("h3",[s._v(s._s(s.importCarDesc.carName+"  "+s.importCarDesc.carSeriesName))]),s._v(" "),a("p",{staticClass:"price-refer"},[s._v("本报价参考：")]),s._v(" "),a("ul",{staticClass:"price-desc clearfix"},[a("li",{staticClass:"current-price"},[a("p",[s._v("￥"),a("span",{staticClass:"price-num"},[s._v(s._s(s.importCarDesc.referenPrice))])]),s._v(" "),s._m(0)]),s._v(" "),a("li",{staticClass:"old-price"},[a("p",[a("del",[s._v("￥"),a("span",{staticClass:"price-num"},[s._v(s._s(s.importCarDesc.marketPrice))])])]),s._v(" "),s._m(1)]),s._v(" "),a("li",{staticClass:"save-price"},[a("p",[s._v("￥"),a("span",{staticClass:"price-num"},[s._v(s._s(s.importCarDesc.maxSavePrice))])]),s._v(" "),a("p",[s._v("最高节省")])])]),s._v(" "),a("div",{staticClass:"color-list"},[a("span",[s._v("车身颜色：")]),s._v(" "),s._l(s.getColorArr,function(s,t){return a("span",{key:t,staticClass:"color",style:{background:s}})})],2)])])])]),s._v(" "),a("div",{staticClass:"row"},[a("div",{staticClass:"col-xs-12"},[a("section",{staticClass:"desc-prefer "},[a("ul",{staticClass:"prefer-list clearfix"},[a("li",[a("p",{staticClass:"prefer-title"},[s._v("参考底价")]),s._v(" "),a("p",[s._v("￥"),a("span",{staticClass:"price-num"},[s._v(s._s(s.importCarDesc.referenPrice))])])]),s._v(" "),s._m(2),s._v(" "),s._m(3),s._v(" "),s._m(4),s._v(" "),s._m(5)])])])]),s._v(" "),a("div",{staticClass:"row"},[a("div",{staticClass:"col-xs-12"},[s.importCarDesc?a("section",{staticClass:"car-detail ",attrs:{"data-target":"#fixed-navbar","data-offset":"0"}},[s._m(6),s._v(" "),a("article",[s._m(7),s._v(" "),a("ul",{staticClass:"configs clearfix"},[a("li",{staticClass:"col-xs-6"},[a("div",{staticClass:"config-item"},[a("label",[s._v("车身机构")]),s._v(" "),a("span",[s._v(s._s(s.importCarDesc.cheshenjiegou))])])]),s._v(" "),a("li",{staticClass:"col-xs-6"},[a("div",{staticClass:"config-item"},[a("label",[s._v("长/宽/高")]),s._v(" "),a("span",[s._v(s._s(s.importCarDesc.length+"/"+s.importCarDesc.width+"/"+s.importCarDesc.height+"mm"))])])]),s._v(" "),a("li",{staticClass:"col-xs-6"},[a("div",{staticClass:"config-item"},[a("label",[s._v("发动机")]),s._v(" "),a("span",[s._v(s._s(s.importCarDesc.fadongjixinghao))])])]),s._v(" "),a("li",{staticClass:"col-xs-6"},[a("div",{staticClass:"config-item"},[a("label",[s._v("变速箱")]),s._v(" "),a("span",[s._v(s._s(s.importCarDesc.biansuxiangtype))])])]),s._v(" "),a("li",{staticClass:"col-xs-6"},[a("div",{staticClass:"config-item"},[a("label",[s._v("驱动方式")]),s._v(" "),a("span",[s._v(s._s(s.importCarDesc.qudongtype))])])]),s._v(" "),a("li",{staticClass:"col-xs-6"},[a("div",{staticClass:"config-item"},[a("label",[s._v("燃料形式")]),s._v(" "),a("span",[s._v(s._s(s.importCarDesc.ranliaotype))])])]),s._v(" "),a("li",{staticClass:"col-xs-6"},[a("div",{staticClass:"config-item"},[a("label",[s._v("综合油耗")]),s._v(" "),a("span",[s._v(s._s(s.importCarDesc.cheshenjiegou))])])]),s._v(" "),a("li",{staticClass:"col-xs-6"},[a("div",{staticClass:"config-item"},[a("label",[s._v("车辆规格")]),s._v(" "),a("span",[s._v(s._s(s.importCarDesc.classType))])])])]),s._v(" "),s._m(8),s._v(" "),a("div",{staticClass:"question-img"},[a("img",{staticClass:"img-responsive",attrs:{src:s.importCarDesc.oftenQuestionUrl,alt:""}})]),s._v(" "),s._m(9),s._v(" "),a("div",{staticClass:"car-detail-iframe",domProps:{innerHTML:s._s(s.importCarDesc.descUrl)}})])]):s._e()])])]),s._v(" "),a("section",{directives:[{name:"show",rawName:"v-show",value:s.scrollHeight>=620,expression:"scrollHeight>=620"}],staticClass:"fixed-nav"},[s._m(10)]),s._v(" "),a("section",{staticClass:"config-modal"},[a("v-config",{attrs:{config:s.configArr}})],1)])},r=[function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("p",[s._v("参考价 "),a("i",{staticClass:"glyphicon glyphicon-question-sign"})])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("p",[s._v("市场价 "),a("i",{staticClass:"glyphicon glyphicon-question-sign"})])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("li",[a("p",{staticClass:"prefer-title"},[s._v("优惠条件")]),s._v(" "),a("p",[s._v("凭单优惠（预购单）至少5000元")])])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("li",[a("p",{staticClass:"prefer-title"},[s._v("服务优势")]),s._v(" "),a("p",[s._v("4s经销商或指定服务商售后挂牌按揭一站式汽车金融服务")])])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("li",[a("p",{staticClass:"prefer-title"},[s._v("热度")]),s._v(" "),a("p",[a("span",[s._v("360000")]),s._v("人查看过")])])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("li",[a("button",{staticClass:"btn btn-default"},[s._v("预约到店")])])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("nav",[a("a",{staticClass:"base-config active",attrs:{href:"#base-config"}},[s._v("基本配置")]),s._v(" "),a("a",{staticClass:"questions",attrs:{href:"#questions"}},[s._v("常见问题")]),s._v(" "),a("a",{staticClass:"car-desc",attrs:{href:"#car-details"}},[s._v("车辆信息")])])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("div",{staticClass:"title",attrs:{id:"base-config"}},[a("h3",[s._v("基本配置")]),s._v(" "),a("a",{staticClass:"more pull-right",attrs:{"data-toggle":"modal","data-target":"#config-modal"}},[s._v("查看全部")])])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("div",{staticClass:"title",attrs:{id:"questions"}},[a("h3",[s._v("常见问题")])])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("div",{staticClass:"title",attrs:{id:"car-details"}},[a("h3",[s._v("车辆信息")])])},function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("nav",{staticClass:"navbar navbar-default navbar-fixed-top"},[a("div",{staticClass:"container"},[a("ul",{staticClass:"nav navbar-nav",attrs:{id:"fixed-navbar"}},[a("li",[a("a",{staticClass:"active",attrs:{href:"#base-config"}},[s._v("基础配置")])]),s._v(" "),a("li",[a("a",{attrs:{href:"#questions"}},[s._v("常见问题")])]),s._v(" "),a("li",[a("a",{attrs:{href:"#car-details"}},[s._v("车辆信息")])])]),s._v(" "),a("button",{staticClass:"btn btn-default pull-right"},[s._v("预约到店")])])])}],c={render:e,staticRenderFns:r},l=c,n=a("vSla"),v=n(i.a,l,!1,null,null,null);t.default=v.exports},lAhe:function(s,t,a){"use strict";(function(s){var i=a("ZLEe"),e=a.n(i),r=a("aA9S"),c=a.n(r),l=a("QQ8u"),n=a("xjx7"),v=a("5Npq"),o=a("2Uyi");t.a={data:function(){return{carId:this.$route.params.id,importCarDesc:{},scrollHeight:0,configArr:{}}},components:{vBreadnav:l.a,vSlider:n.a,vConfig:v.a},methods:{getDescData:function(){var t={},a=this;t.cartId=this.carId,t.platForm="020003",s.ajax({url:o.b+"/queryImportCarDetail",type:"get",data:t,dataType:"json",asnyc:!0,success:function(s){console.log(s),s.code==o.c&&(a.importCarDesc=s.data.carDetail,a.configArr=c()(s.data.params1,s.data.params2))}})}},computed:{getSliderArr:function(){var s=[];if(0!=e()(this.importCarDesc).length){this.importCarDesc.icons.forEach(function(t){var a=new Object;a.imgUrl=t,s.push(a)})}return s},getColorArr:function(){if(0!=e()(this.importCarDesc).length){return this.importCarDesc.colors.split(",")}}},mounted:function(){this.getDescData();var t=this;s(document).scroll(function(){var a=s(document).scrollTop();if(t.scrollHeight=a,s("#base-config").offset()&&s("#questions").offset()&&s("#car-details").offset()){var i=s("#base-config").offset().top,e=s("#questions").offset().top,r=s("#car-details").offset().top;i<=a&&a<e?(s("#fixed-navbar a").removeClass("active"),s("#fixed-navbar li").eq(0).find("a").addClass("active")):e<=a&&a<r?(s("#fixed-navbar a").removeClass("active"),s("#fixed-navbar li").eq(1).find("a").addClass("active")):r<=a&&(s("#fixed-navbar a").removeClass("active"),s("#fixed-navbar li").eq(2).find("a").addClass("active"))}})}}}).call(t,a("slT5"))}});
//# sourceMappingURL=17.0515f33983c226f06c39.js.map