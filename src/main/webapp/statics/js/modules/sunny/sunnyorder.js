$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'sunnyorder/list',
		datatype : "json",
		colModel : [ {
			label : '订单编号',
			name : 'ordNo',
			index : 'ord_no',
			width : 140,
			key : true
		}, {
			label : '订单日期',
			name : 'ordDt',
			index : 'ord_dt',
			width : 50
		}, {
			label : '订单时间',
			name : 'ordTm',
			index : 'ord_tm',
			width : 50
		}, {
			label : '订单金额（分）',
			name : 'ordAmt',
			index : 'ord_amt',
			width : 70
		}, {
			label : '优惠金额（分）',
			name : 'freeAmt',
			index : 'free_amt',
			width : 70
		}, {
			label : '支付金额（分）',
			name : 'payAmt',
			index : 'pay_amt',
			width : 70
		}, {
			label : '订单状态',
			name : 'ordStatus',
			index : 'ord_status',
			width : 60
		}, {
			label : '用户来源',
			name : 'userType',
			index : 'user_type',
			width : 50
		}, {
			label : '用户电话',
			name : 'mblNo',
			index : 'mbl_no',
			width : 80
		}, {
			label : '支付日期',
			name : 'payDt',
			index : 'pay_dt',
			width : 70
		}, {
			label : '支付时间',
			name : 'payTm',
			index : 'pay_tm',
			width : 70
		}],
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 30, 50 ],
		rownumbers : true,
		rownumWidth : 25,
		autowidth : true,
		multiselect : true,
		pager : "#jqGridPager",
		height : 317,
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
			ordNo : null,
			mblNo : null,
			starDate : null,
			endDate : null,
			mercId : null,
			mercNm : null,
			ordSts : 0
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
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.sunnyorder = {};
		},
		update : function(event) {
			var ordNo = getSelectedRow();
			if (ordNo == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(ordNo)
		},
		saveOrUpdate : function(event) {
			var url = vm.sunnyorder.ordNo == null ? "sunnyorder/save"
					: "sunnyorder/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.sunnyorder),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功', function(index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		del : function(event) {
			var ordNos = getSelectedRows();
			if (ordNos == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "sunnyorder/delete",
					contentType : "application/json",
					data : JSON.stringify(ordNos),
					success : function(r) {
						if (r.code == 0) {
							alert('操作成功', function(index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo : function(ordNo) {
			$.get(baseURL + "sunnyorder/info/" + ordNo, function(r) {
				vm.sunnyorder = r.sunnyorder;
			});
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
					'ordNo' : vm.q.ordNo,
					'mblNo' : vm.q.mblNo,
					'starDate' : vm.q.starDate,
					'endDate' : vm.q.endDate,
					'mercId' : vm.q.mercId,
					'ordSts' : vm.q.ordSts
				},
				page : page
			}).trigger("reloadGrid");
		},
		formatDate: function(olddate){
			var date = new Date(olddate);
			var year = date.getFullYear();
			var month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
			var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
			return year + "" + month + "" + day;
		}
	}
});