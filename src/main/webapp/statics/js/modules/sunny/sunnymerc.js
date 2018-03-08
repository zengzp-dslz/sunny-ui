$(function() {
	$("#jqGrid").jqGrid({
		url : baseURL + 'sunnymerc/list',
		datatype : "json",
		colModel : [ {
			label : '商户编号',
			name : 'mercId',
			index : 'merc_id',
			width : 120,
			key : true
		}, {
			label : '名称',
			name : 'mercNm',
			index : 'merc_nm',
			width : 100
		}, {
			label : '地址',
			name : 'mercAddr',
			index : 'merc_addr',
			width : 160
		}, {
			label : '电话',
			name : 'mercTele',
			index : 'merc_tele',
			width : 100
		}, {
			label : '省份编号',
			name : 'mercProv',
			index : 'merc_prov',
			width : 80
		}, {
			label : '城市编号',
			name : 'mercCity',
			index : 'merc_city',
			width : 60
		}, {
			label : '区域编号',
			name : 'mercCountry',
			index : 'merc_country',
			width : 60
		}, {
			label : '商户状态',
			name : 'mercSts',
			index : 'merc_sts',
			width : 60
		}, {
			label : '设备id',
			name : 'deviceId',
			index : 'device_id',
			width : 100
		}, {
			label : '时间戳',
			name : 'tmSmp',
			index : 'tm_smp',
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
			mercId : null,
			mercSts : null,
			deviceId : null,
			mercProv : null,
			mercCity : null,
			mercCountry : null
		},
		showList : true,
		title : null,
		sunnymerc : {}
	},
	methods : {
		query : function() {
			vm.reload();
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.sunnymerc = {};
			 //加载部门树
            $.ajax({
            	type : "post",
            	url : baseURL + "provcitycountry/selectProv",
            	async : false,
            	success : function(r){
            		$("#mercProv").append("<option value='0'>请选择</option>");
            		$.each(r.provList, function(i,item){
                		var str = "<option value='" + item.provCd + "'>" + item.provNm + "</option>";
    					$("#mercProv").append(str);
    				});
            		$("#mercProv").prop("selectedIndex", 0);
                }   
            	
            });
            vm.changeCity();
		},
		changeCity : function () {
			$("#mercCity").empty(); 
			$.ajax({
            	type : "post",
            	url : baseURL + "provcitycountry/selectCity",
            	data : "provId=" + $("#mercProv").val(),
            	async : false,
            	success : function(r){    
            		$("#mercCity").append("<option value='0'>请选择</option>");
            		$.each(r.cityList, function(i,item){
                		var str = "<option value='" + item.cityCd + "'>" + item.cityNm + "</option>";
    					$("#mercCity").append(str);
    				});
            		$("#mercCity").prop("selectedIndex", 0);
                }   
            });
           vm.changeCountry();
		},
		changeCountry : function () {
			$("#mercCountry").empty();
			$.ajax({
            	type : "post",
            	url : baseURL + "provcitycountry/selectCountry",
            	data : "cityId=" + $("#mercCity").val(),
            	async : false,
            	success : function(r){    
            		$("#mercCountry").append("<option value='0'>请选择</option>");
            		$.each(r.countryList, function(i,item){
            			var str = "<option value='" + item.countryCd + "'>" + item.countryNm + "</option>";
    					$("#mercCountry").append(str);
    				});
            		$("#mercCountry").prop("selectedIndex", 0);
                }   
            });
		},
		update : function(event) {
			var mercId = getSelectedRow();
			if (mercId == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(mercId)
		},
		saveOrUpdate : function(event) {
			var url = vm.sunnymerc.mercId == null ? "sunnymerc/save"
					: "sunnymerc/update";
			$.ajax({
				type : "POST",
				url : baseURL + url,
				contentType : "application/json",
				data : JSON.stringify(vm.sunnymerc),
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
			var mercIds = getSelectedRows();
			if (mercIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : baseURL + "sunnymerc/delete",
					contentType : "application/json",
					data : JSON.stringify(mercIds),
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
		getInfo : function(mercId) {
			$.get(baseURL + "sunnymerc/info/" + mercId, function(r) {
				vm.sunnymerc = r.sunnymerc;
			});
		},
		reload : function(event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'mercId' : vm.q.mercId,
					'mercSts' : vm.q.mercSts,
					'deviceId' : vm.q.deviceId,
					'mercProv' : vm.q.mercProv,
					'mercCity' : vm.q.mercCity,
					'mercCountry' : vm.q.mercCountry
				},
				page : page
			}).trigger("reloadGrid");
		}
	}
});