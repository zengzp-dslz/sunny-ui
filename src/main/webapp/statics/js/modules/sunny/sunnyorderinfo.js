$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'sunnyorderinfo/list',
		datatype : "json",
		colModel : [ {
			label : '订单号',
			name : 'ordNo',
			index : 'ord_no',
			width : 140,
			key : true
		}, {
			label : '产品编号',
			name : 'goodsId',
			index : 'goods_id',
			width : 80
		}, {
			label : '产品名称',
			name : 'goodsName',
			index : 'goods_name',
			width : 120
		}, {
			label : '产品类型',
			name : 'goodsType',
			index : 'goods_type',
			width : 50
		}, {
			label : '单价（分）',
			name : 'goodsBid',
			index : 'goods_bid',
			width : 80
		}, {
			label : '订单时间',
			name : 'tmSmp',
			index : 'tm_smp',
			width : 80
		} ],
		viewrecords : true,
		height : 317,
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
			ordNo : null,
			starDate : null,
			mercId : null,
			mercNm : null,
			endDate : null
		},
		showList : true,
		title : null,
		sunnyorderinfo : {}
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
			vm.sunnyorderinfo = {};
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
			var url = vm.sunnyorderinfo.ordNo == null ? "sunnyorderinfo/save"
					: "sunnyorderinfo/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.sunnyorderinfo),
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
					url : baseURL + "sunnyorderinfo/delete",
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
			$.get(baseURL + "sunnyorderinfo/info/" + ordNo, function(r) {
				vm.sunnyorderinfo = r.sunnyorderinfo;
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			if(vm.q.starDate){
				vm.q.starDate = vm.formatDate(vm.q.starDate) + "000000";
			}
			if(vm.q.endDate){
				vm.q.endDate = vm.formatDate(vm.q.endDate) + "235959";
			}
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'ordNo' : vm.q.ordNo,
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
			var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
			return year + "" + month + "" + day;
		}
	}
});