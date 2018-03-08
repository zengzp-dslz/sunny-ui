$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'sunnygoods/list',
		datatype : "json",
		colModel : [ {
			label : '商品编号',
			name : 'goodsId',
			index : 'goods_id',
			width : 80,
			key : true
		}, {
			label : '条形码',
			name : 'barcode',
			index : 'barcode',
			width : 120
		}, {
			label : '商品名称',
			name : 'goodsName',
			index : 'goods_name',
			width : 100
		}, {
			label : '商品简称',
			name : 'goodsSname',
			index : 'goods_sname',
			width : 100
		}, {
			label : '商品类型',
			name : 'goodsType',
			index : 'goods_type',
			width : 80
		}, {
			label : '库存',
			name : 'goodsNum',
			index : 'goods_num',
			width : 80
		}, {
			label : '过期天数',
			name : 'goodsExp',
			index : 'goods_exp',
			width : 90
		}, {
			label : '单价',
			name : 'goodsBid',
			index : 'goods_bid',
			align : 'right',
			width : 80
		}, {
			label : '规格',
			name : 'goodsSize',
			index : 'goods_size',
			width : 80
		}, {
			label : '修改日期',
			name : 'updateDt',
			index : 'update_dt',
			width : 90
		}, {
			label : '修改时间',
			name : 'updateTm',
			index : 'update_tm',
			width : 80
		}, {
			label : '修改用户',
			name : 'updateUser',
			index : 'update_user',
			width : 100
		} ],
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

var vm = new Vue({
	el : '#rrapp',
	data : {
		q : {
			goodsId : null,
			barcode : null,
			goodsName : null,
			goodsBid : null
		},
		showList : true,
		title : null,
		sunnygoods : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.sunnygoods = {};
		},
		update : function(event) {
			var goodsId = getSelectedRow();
			if (goodsId == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(goodsId)
		},
		saveOrUpdate : function(event) {
			var url = vm.sunnygoods.goodsId == null ? "sunnygoods/save"
					: "sunnygoods/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.sunnygoods),
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
			var goodsIds = getSelectedRows();
			if (goodsIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "sunnygoods/delete",
					contentType : "application/json",
					data : JSON.stringify(goodsIds),
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
		getInfo : function(goodsId) {
			$.get(baseURL + "sunnygoods/info/" + goodsId, function(r) {
				vm.sunnygoods = r.sunnygoods;
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'goodsId' : vm.q.goodsId,
					'barcode' : vm.q.barcode,
					'goodsName' : vm.q.goodsName,
					'goodsBid' : vm.q.goodsBid
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});