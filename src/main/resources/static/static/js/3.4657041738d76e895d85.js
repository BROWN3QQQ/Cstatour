webpackJsonp([3],{UTc4:function(t,n){},g48e:function(t,n,e){"use strict";var s=e("mvHQ"),i=e.n(s),o={name:"tipsBox",components:{writings:e("rmSj").default},props:["content"],methods:{getArticle:function(t){localStorage.setItem("article",i()(t))}}},a={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",[e("div",{attrs:{id:"fh5co-features"}},[e("div",{staticClass:"container"},[e("div",{staticClass:"row"},t._l(t.content,function(n,s){return e("div",{staticClass:"col-md-4 animate-box",staticStyle:{padding:"20px"}},[e("div",{staticClass:"feature-left"},[t._m(0,!0),t._v(" "),e("div",{staticClass:"feature-copy"},[e("h3",[t._v(t._s(n.soncolumn.cnname))]),t._v(" "),e("p",{staticStyle:{height:"100px",overflow:"hidden"},domProps:{innerHTML:t._s(n.name)}}),t._v(" "),e("p",{on:{click:function(e){t.getArticle(n)}}},[e("router-link",{attrs:{to:/article/+n.id}},[t._v(t._s(t.$t("more.more")))])],1)])])])}))])])])},staticRenderFns:[function(){var t=this.$createElement,n=this._self._c||t;return n("span",{staticClass:"icon"},[n("i",{staticClass:"el-icon-star-off"})])}]};var c=e("VU/8")(o,a,!1,function(t){e("u5yA")},"data-v-6e4426d6",null);n.a=c.exports},o66D:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var s=e("mvHQ"),i=e.n(s),o=e("Xxa5"),a=e.n(o),c=e("exGp"),r=e.n(c),l=e("g48e"),u=e("uOUG"),d={name:"tripKnow",components:{TipsBox:l.a,essay:u.a},data:function(){return{route:this.$route.path.split("/")[2],list:[],contents:[],content:[],box:!1}},created:function(){var t=this;return r()(a.a.mark(function n(){return a.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.next=2,t.getContents().then(t.getList);case 2:case"end":return n.stop()}},n,t)}))()},watch:{$route:function(){this.route=this.$route.path.split("/")[2],this.list=[],this.content=[],this.box=!1,this.getList()}},methods:{getList:function(){var t=this;return this.axios({method:"POST",url:this.GLOBAL.getcolumn,headers:{"Content-Type":"application/json"},crossDomain:!0,data:i()({a:123,b:123}),xhrFields:{withCredentials:!0},async:!1}).then(function(n){return t.getSort(n.data)}).catch(function(t){console.log(t)})},getNeed:function(t){for(var n in t)if(t[n].id===this.route)this.list=t[n].son;else for(var e in t[n].son)t[n].son[e].id===this.route&&this.list.push(t[n].son[e])},getContents:function(){var t=this;return this.axios({method:"POST",url:this.GLOBAL.getarticle,headers:{"Content-Type":"application/json"},crossDomain:!0,data:i()({a:123}),xhrFields:{withCredentials:!0},async:!1}).then(function(n){t.contents=n.data}).catch(function(t){console.log(t)})},getSort:function(t){var n=this;return r()(a.a.mark(function e(){var s,i,o;return a.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,n.getNeed(t);case 2:if(n.list.length>1)for(s in n.contents)n.content.push(n.contents[s]);else for(i in console.log(n.list.length),n.list)for(o in n.contents)n.contents[o].soncolumn.cnname===n.list[i].cnname&&n.content.push(n.contents[o]);n.box=!0;case 4:case"end":return e.stop()}},e,n)}))()}}},h={render:function(){var t=this.$createElement,n=this._self._c||t;return this.box?n("div",[this.content?n("TipsBox",{attrs:{content:this.content}}):this._e()],1):this._e()},staticRenderFns:[]};var f=e("VU/8")(d,h,!1,function(t){e("y7an")},"data-v-61d931c0",null);n.default=f.exports},u5yA:function(t,n){},uOUG:function(t,n,e){"use strict";var s=e("mvHQ"),i=e.n(s),o={name:"essay",props:["content"],methods:{getArticle:function(t){localStorage.setItem("article",i()(t)),console.log(t)}}},a={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{staticClass:"fh5co-section-gray",attrs:{id:"fh5co-tours"}},[e("div",{staticClass:"container"},[e("div",{staticClass:"row"},[e("div",{staticClass:"col-md-12 animate-box"},[e("h2",{staticClass:"heading-title"},[t._v(t._s(t.content.commodityname))])]),t._v(" "),e("div",{staticClass:"col-md-12 animate-box",domProps:{innerHTML:t._s(t.content.content)}}),t._v(" "),e("div",{on:{click:function(n){t.getArticle(t.content)}}},[e("router-link",{attrs:{to:/article/+t.content.id}},[t._v(t._s(t.$t("more.more")))])],1)])])])},staticRenderFns:[]};var c=e("VU/8")(o,a,!1,function(t){e("UTc4")},"data-v-e38a37a0",null);n.a=c.exports},y7an:function(t,n){}});
//# sourceMappingURL=3.4657041738d76e895d85.js.map