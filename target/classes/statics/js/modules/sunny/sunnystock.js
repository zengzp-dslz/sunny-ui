$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'sunnystock/list',
		datatype : "json",
		colModel : [{
			label : '商户编号',
			name : 'mercId',
			index : 'merc_id',
			width : 80
		}, {
			label : '商户名称',
			name : 'mercNm',
			index : 'merc_nm',
			width : 100
		}, {
			label : '商品编号',
			name : 'goodsId',
			index : 'goods_id',
			width : 50
		}, {
			label : '商品名称',
			name : 'goodsName',
			index : 'goods_name',
			width : 120
		}, {
			label : '条形码',
			name : 'barcode',
			index : 'barcode',
			width : 80
		}, {
			label : '成本价',
			name : 'goodsCid',
			index : 'goods_cid',
			width : 50
		}, {
			label : '售价（单位分）',
			name : 'goodsBid',
			index : 'goods_bid',
			width : 70
		}, {
			label : '库存总数',
			name : 'goodsNum',
			index : 'goods_num',
			width : 50
		}, {
			label : '即时库存',
			name : 'goodsIns',
			index : 'goods_ins',
			width : 50
		}, {
			label : '修改日期',
			name : 'updateDt',
			index : 'update_dt',
			width : 50
		}, {
			label : '修改时间',
			name : 'updateTm',
			index : 'update_tm',
			width : 50
		}, {
			label : '创建时间',
			name : 'tmSmp',
			index : 'tm_smp',
			width : 80
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
			mercId : null,
			mercNm : null,
			barcode : null,
			status : 0
		},
		showList : true,
		title : null,
		sunnystock : {
			goodsBid:'',
			goodsNm: '',
			mercId : '',
			mercNm : ''
		}
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
        mercTree: function(path){
        	vm.getMerc(function(){
        		 layer.open({
                     type: 1,
                     offset: '50px',
                     skin: 'layui-layer-molv',
                     title: "选择便利店",
                     area: ['300px', '280px'],
                     shade: 0,
                     shadeClose: false,
                     content: jQuery("#mercLayer"),
                     btn: ['确定', '取消'],
                     btn1: function (index) {
                         var node = ztree.getSelectedNodes();
                         //选择便利店
                         if(path && path == 'add'){
                        	 vm.sunnystock.mercId = node[0].id;
                             vm.sunnystock.mercNm = node[0].name;
                         }else{
                        	 vm.q.mercId = node[0].id;
                             vm.q.mercNm = node[0].name;
                         }
                         

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
			vm.mercTree('add');
		},
		update : function(event) {
			var stockId = getSelectedRow();
			if (stockId == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(stockId)
		},
		queryGoods : function() {
			if(!vm.sunnystock.mercId || vm.sunnystock.mercId == 0){
				alert('未选择便利店');
				return
			}
			$.ajax({
				type : "POST",
				url : baseURL + "sunnygoods/queryBarcode",
				data : 'barcode=' + vm.sunnystock.barcode,
				success : function(r) {
					if (null != r.goodsEntity) {
						vm.sunnystock.goodsBid = r.goodsEntity.goodsBid;
						vm.sunnystock.goodsNm = r.goodsEntity.goodsName;
					} else {
						alert(r.msg);
					}
				}
			});
		},
		saveOrUpdate : function(event) {
			var url = vm.sunnystock.stockId == null ? "sunnystock/save"
					: "sunnystock/update";	
			var sunnystock = {
					'mercId' : vm.sunnystock.mercId,
					'barcode' : vm.sunnystock.barcode,
					'goodsCid' : vm.sunnystock.goodsCid,
					'goodsIns' : vm.sunnystock.goodsIns
			};
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(sunnystock),
				success : function(r) {
					if (r.code === 0) {
						alert('操作成功', function(index) {
							vm.reload();
							vm.sunnystock.mercId = "";
							vm.sunnystock.mercNm = "";
							vm.sunnystock.barcode = "";
							vm.sunnystock.goodsNm = "";
							vm.sunnystock.goodsBid = "";
							vm.sunnystock.goodsCid = "";
							vm.sunnystock.goodsIns = "";
						});
					} else {
						alert(r.msg);
					}
				}
			});
		},
		del : function(event) {
			var stockIds = getSelectedRows();
			if (stockIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "sunnystock/delete",
					contentType : "application/json",
					data : JSON.stringify(stockIds),
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
		getInfo : function(stockId) {
			$.get(baseURL + "sunnystock/info/" + stockId, function(r) {
				vm.sunnystock = r.sunnystock;
			});
		},
		reload : function(event) {
			vm.sunnystock.mercId = "";
			vm.sunnystock.mercNm = "";
			vm.sunnystock.barcode = "";
			vm.sunnystock.goodsNm = "";
			vm.sunnystock.goodsBid = "";
			vm.sunnystock.goodsCid = "";
			vm.sunnystock.goodsIns = "";
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'mercId' : vm.q.mercId,
					'barcode' : vm.q.barcode,
					'status' : vm.q.status
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});