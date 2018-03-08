$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'sunnyorder/monthsale',
		datatype : "json",
		colModel : [ {
			label : '日期',
			name : 'ordDt',
			index : 'ord_dt',
			width : 100
		}, {
			label : '支付金额（分）',
			name : 'payAmt',
			index : 'pay_amt',
			width : 100
		}, {
			label : '成本价（分）',
			name : 'costAmt',
			index : 'cost_amt',
			width : 100
		}, {
			label : '支付笔数',
			name : 'count',
			index : 'count',
			width : 100
		}],
		viewrecords : true,
		height : 385,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		pager : "#jqGridPager",
		jsonReader : {
			root : "page.list",
			page : "page.currPage",
			total : "page.totalPage",
			records : "page.totalCount"
		},
		prmNames : {
			page : "page",
			rows : "limit",
			order : "order"
		},
		gridComplete : function() {
			// 隐藏grid底部滚动条
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
				"overflow-x" : "hidden"
			});
		}
	});
});

var setting = {
	    data: {
	        simpleData: {
	            enable: true,
	            idKey: "mercId"
	        },
	        key: {
	            url:"nourl"
	        }
	    }
	};
	var ztree;

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			starDate : null,
			endDate : null,
			mercId : null,
			mercNm : null,
		},
		showList : true,
		title : null,
		sunnyorder : {}
	},
	methods : {
		getMerc : function(cb){
            //加载部门树
            $.get(baseURL + "sunnymerc/select", function(r){
                ztree = $.fn.zTree.init($("#mercTree"), setting, r.mercList);
                /*var node = ztree.getNodeByParam("mercId", mercId);
                ztree.selectNode(node);
                
                vm.q.mercNm = node.mercNm;*/
                typeof cb === "function" && cb();
            })
        },
        mercTree: function(){
        	vm.getMerc(function(){
        		 layer.open({
                     type: 1,
                     offset: '50px',
                     skin: 'layui-layer-molv',
                     title: "选择便利店",
                     area: ['300px', '450px'],
                     shade: 0,
                     shadeClose: false,
                     content: jQuery("#mercLayer"),
                     btn: ['确定', '取消'],
                     btn1: function (index) {
                         var node = ztree.getSelectedNodes();
                         //选择便利店
                         vm.q.mercId = node[0].id;
                         vm.q.mercNm = node[0].name;

                         layer.close(index);
                     }
                 });
        	});
        },
		query : function() {
			vm.reload();
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			if(vm.q.starDate){
				vm.q.starDate = vm.formatDate(vm.q.starDate);
			}
			if(vm.q.endDate){
				vm.q.endDate = vm.formatDate(vm.q.endDate);
			}
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'starDate' : vm.q.starDate,
					'endDate' : vm.q.endDate,
					'mercId' : vm.q.mercId
				},
				page : page
			}).trigger("reloadGrid");
		},
		formatDate: function(olddate){
			var date = new Date(olddate);
			var year = date.getFullYear();
			var month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
			return year + "" + month;
		}
	}
});