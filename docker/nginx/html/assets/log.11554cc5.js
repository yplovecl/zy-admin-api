import{H as G,z as de,r as g,a as pe,A as ce,L as me,d as s,K,o as d,c as k,C as w,D as Y,f as l,e,w as o,i as _e,M as Q,N as E,j as v,B as T,m as r,k as F,x as h,l as O,s as fe}from"./index.d87ad0ed.js";import{g as be}from"./job.91488fdd.js";function ge(V){return G({url:"/monitor/jobLog/list",method:"get",params:V})}function ve(V){return G({url:"/monitor/jobLog/"+V,method:"delete"})}function he(){return G({url:"/monitor/jobLog/clean",method:"delete"})}const we={class:"app-container"},je={key:0},ye={key:1},ke={class:"dialog-footer"},Ve=de({name:"JobLog"}),Se=Object.assign(Ve,{setup(V){const{proxy:m}=fe(),{sys_common_status:R,sys_job_group:U}=m.useDict("sys_common_status","sys_job_group"),$=g([]),j=g(!1),N=g(!0),C=g(!0),L=g([]),I=g(!0),S=g(0),y=g([]),P=pe(),A=ce({form:{},queryParams:{pageNum:1,pageSize:10,dictName:void 0,dictType:void 0,status:void 0}}),{queryParams:n,form:p,rules:Ce}=me(A);function f(){N.value=!0,ge(m.addDateRange(n.value,y.value)).then(u=>{$.value=u.rows,S.value=u.total,N.value=!1})}function H(){const u={path:"/monitor/job"};m.$tab.closeOpenPage(u)}function D(){n.value.pageNum=1,f()}function W(){y.value=[],m.resetForm("queryRef"),D()}function X(u){L.value=u.map(a=>a.jobLogId),I.value=!u.length}function Z(u){j.value=!0,p.value=u}function ee(u){m.$modal.confirm('\u662F\u5426\u786E\u8BA4\u5220\u9664\u8C03\u5EA6\u65E5\u5FD7\u7F16\u53F7\u4E3A"'+L.value+'"\u7684\u6570\u636E\u9879?').then(function(){return ve(L.value)}).then(()=>{f(),m.$modal.msgSuccess("\u5220\u9664\u6210\u529F")}).catch(()=>{})}function oe(){m.$modal.confirm("\u662F\u5426\u786E\u8BA4\u6E05\u7A7A\u6240\u6709\u8C03\u5EA6\u65E5\u5FD7\u6570\u636E\u9879?").then(function(){return he()}).then(()=>{f(),m.$modal.msgSuccess("\u6E05\u7A7A\u6210\u529F")}).catch(()=>{})}function le(){m.download("monitor/jobLog/export",{...n.value},`job_log_${new Date().getTime()}.xlsx`)}return(()=>{const u=P.params&&P.params.jobId;u!==void 0&&u!=0?be(u).then(a=>{n.value.jobName=a.data.jobName,n.value.jobGroup=a.data.jobGroup,f()}):f()})(),f(),(u,a)=>{const te=s("el-input"),i=s("el-form-item"),q=s("el-option"),B=s("el-select"),ae=s("el-date-picker"),b=s("el-button"),J=s("el-form"),c=s("el-col"),ne=s("right-toolbar"),M=s("el-row"),_=s("el-table-column"),z=s("dict-tag"),ue=s("el-table"),se=s("pagination"),re=s("el-dialog"),x=K("hasPermi"),ie=K("loading");return d(),k("div",we,[w(e(J,{model:l(n),ref:"queryRef",inline:!0,"label-width":"68px"},{default:o(()=>[e(i,{label:"\u4EFB\u52A1\u540D\u79F0",prop:"jobName"},{default:o(()=>[e(te,{modelValue:l(n).jobName,"onUpdate:modelValue":a[0]||(a[0]=t=>l(n).jobName=t),placeholder:"\u8BF7\u8F93\u5165\u4EFB\u52A1\u540D\u79F0",clearable:"",style:{width:"240px"},onKeyup:_e(D,["enter"])},null,8,["modelValue","onKeyup"])]),_:1}),e(i,{label:"\u4EFB\u52A1\u7EC4\u540D",prop:"jobGroup"},{default:o(()=>[e(B,{modelValue:l(n).jobGroup,"onUpdate:modelValue":a[1]||(a[1]=t=>l(n).jobGroup=t),placeholder:"\u8BF7\u9009\u62E9\u4EFB\u52A1\u7EC4\u540D",clearable:"",style:{width:"240px"}},{default:o(()=>[(d(!0),k(Q,null,E(l(U),t=>(d(),v(q,{key:t.value,label:t.label,value:t.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1}),e(i,{label:"\u6267\u884C\u72B6\u6001",prop:"status"},{default:o(()=>[e(B,{modelValue:l(n).status,"onUpdate:modelValue":a[2]||(a[2]=t=>l(n).status=t),placeholder:"\u8BF7\u9009\u62E9\u6267\u884C\u72B6\u6001",clearable:"",style:{width:"240px"}},{default:o(()=>[(d(!0),k(Q,null,E(l(R),t=>(d(),v(q,{key:t.value,label:t.label,value:t.value},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1}),e(i,{label:"\u6267\u884C\u65F6\u95F4",style:{width:"308px"}},{default:o(()=>[e(ae,{modelValue:l(y),"onUpdate:modelValue":a[3]||(a[3]=t=>T(y)?y.value=t:null),"value-format":"YYYY-MM-DD",type:"daterange","range-separator":"-","start-placeholder":"\u5F00\u59CB\u65E5\u671F","end-placeholder":"\u7ED3\u675F\u65E5\u671F"},null,8,["modelValue"])]),_:1}),e(i,null,{default:o(()=>[e(b,{type:"primary",icon:"Search",onClick:D},{default:o(()=>[r("\u641C\u7D22")]),_:1}),e(b,{icon:"Refresh",onClick:W},{default:o(()=>[r("\u91CD\u7F6E")]),_:1})]),_:1})]),_:1},8,["model"]),[[Y,l(C)]]),e(M,{gutter:10,class:"mb8"},{default:o(()=>[e(c,{span:1.5},{default:o(()=>[w((d(),v(b,{type:"danger",plain:"",icon:"Delete",disabled:l(I),onClick:ee},{default:o(()=>[r("\u5220\u9664")]),_:1},8,["disabled"])),[[x,["monitor:job:remove"]]])]),_:1},8,["span"]),e(c,{span:1.5},{default:o(()=>[w((d(),v(b,{type:"danger",plain:"",icon:"Delete",onClick:oe},{default:o(()=>[r("\u6E05\u7A7A")]),_:1})),[[x,["monitor:job:remove"]]])]),_:1},8,["span"]),e(c,{span:1.5},{default:o(()=>[w((d(),v(b,{type:"warning",plain:"",icon:"Download",onClick:le},{default:o(()=>[r("\u5BFC\u51FA")]),_:1})),[[x,["monitor:job:export"]]])]),_:1},8,["span"]),e(c,{span:1.5},{default:o(()=>[e(b,{type:"warning",plain:"",icon:"Close",onClick:H},{default:o(()=>[r("\u5173\u95ED")]),_:1})]),_:1},8,["span"]),e(ne,{showSearch:l(C),"onUpdate:showSearch":a[4]||(a[4]=t=>T(C)?C.value=t:null),onQueryTable:f},null,8,["showSearch"])]),_:1}),w((d(),v(ue,{data:l($),onSelectionChange:X},{default:o(()=>[e(_,{type:"selection",width:"55",align:"center"}),e(_,{label:"\u65E5\u5FD7\u7F16\u53F7",width:"80",align:"center",prop:"jobLogId"}),e(_,{label:"\u4EFB\u52A1\u540D\u79F0",align:"center",prop:"jobName","show-overflow-tooltip":!0}),e(_,{label:"\u4EFB\u52A1\u7EC4\u540D",align:"center",prop:"jobGroup","show-overflow-tooltip":!0},{default:o(t=>[e(z,{options:l(U),value:t.row.jobGroup},null,8,["options","value"])]),_:1}),e(_,{label:"\u8C03\u7528\u76EE\u6807\u5B57\u7B26\u4E32",align:"center",prop:"invokeTarget","show-overflow-tooltip":!0}),e(_,{label:"\u65E5\u5FD7\u4FE1\u606F",align:"center",prop:"jobMessage","show-overflow-tooltip":!0}),e(_,{label:"\u6267\u884C\u72B6\u6001",align:"center",prop:"status"},{default:o(t=>[e(z,{options:l(R),value:t.row.status},null,8,["options","value"])]),_:1}),e(_,{label:"\u6267\u884C\u65F6\u95F4",align:"center",prop:"createTime",width:"180"},{default:o(t=>[F("span",null,h(u.parseTime(t.row.createTime)),1)]),_:1}),e(_,{label:"\u64CD\u4F5C",align:"center","class-name":"small-padding fixed-width"},{default:o(t=>[w((d(),v(b,{link:"",type:"primary",icon:"View",onClick:xe=>Z(t.row)},{default:o(()=>[r("\u8BE6\u7EC6")]),_:2},1032,["onClick"])),[[x,["monitor:job:query"]]])]),_:1})]),_:1},8,["data"])),[[ie,l(N)]]),w(e(se,{total:l(S),page:l(n).pageNum,"onUpdate:page":a[5]||(a[5]=t=>l(n).pageNum=t),limit:l(n).pageSize,"onUpdate:limit":a[6]||(a[6]=t=>l(n).pageSize=t),onPagination:f},null,8,["total","page","limit"]),[[Y,l(S)>0]]),e(re,{title:"\u8C03\u5EA6\u65E5\u5FD7\u8BE6\u7EC6",modelValue:l(j),"onUpdate:modelValue":a[8]||(a[8]=t=>T(j)?j.value=t:null),width:"700px","append-to-body":""},{footer:o(()=>[F("div",ke,[e(b,{onClick:a[7]||(a[7]=t=>j.value=!1)},{default:o(()=>[r("\u5173 \u95ED")]),_:1})])]),default:o(()=>[e(J,{model:l(p),"label-width":"100px"},{default:o(()=>[e(M,null,{default:o(()=>[e(c,{span:12},{default:o(()=>[e(i,{label:"\u65E5\u5FD7\u5E8F\u53F7\uFF1A"},{default:o(()=>[r(h(l(p).jobLogId),1)]),_:1}),e(i,{label:"\u4EFB\u52A1\u540D\u79F0\uFF1A"},{default:o(()=>[r(h(l(p).jobName),1)]),_:1})]),_:1}),e(c,{span:12},{default:o(()=>[e(i,{label:"\u4EFB\u52A1\u5206\u7EC4\uFF1A"},{default:o(()=>[r(h(l(p).jobGroup),1)]),_:1}),e(i,{label:"\u6267\u884C\u65F6\u95F4\uFF1A"},{default:o(()=>[r(h(l(p).createTime),1)]),_:1})]),_:1}),e(c,{span:24},{default:o(()=>[e(i,{label:"\u8C03\u7528\u65B9\u6CD5\uFF1A"},{default:o(()=>[r(h(l(p).invokeTarget),1)]),_:1})]),_:1}),e(c,{span:24},{default:o(()=>[e(i,{label:"\u65E5\u5FD7\u4FE1\u606F\uFF1A"},{default:o(()=>[r(h(l(p).jobMessage),1)]),_:1})]),_:1}),e(c,{span:24},{default:o(()=>[e(i,{label:"\u6267\u884C\u72B6\u6001\uFF1A"},{default:o(()=>[l(p).status==0?(d(),k("div",je,"\u6B63\u5E38")):l(p).status==1?(d(),k("div",ye,"\u5931\u8D25")):O("",!0)]),_:1})]),_:1}),e(c,{span:24},{default:o(()=>[l(p).status==1?(d(),v(i,{key:0,label:"\u5F02\u5E38\u4FE1\u606F\uFF1A"},{default:o(()=>[r(h(l(p).exceptionInfo),1)]),_:1})):O("",!0)]),_:1})]),_:1})]),_:1},8,["model"])]),_:1},8,["modelValue"])])}}});export{Se as default};
