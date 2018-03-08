$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'noticeuser/list',
        datatype: "json",
        colModel: [			
			{ label: 'nationCode', name: 'nationCode', index: 'nation_code', width: 50, key: true },
			{ label: '手机号码', name: 'mblNo', index: 'mbl_no', width: 80 }, 			
			{ label: '姓名', name: 'userNm', index: 'user_nm', width: 80 }, 			
			{ label: '性别', name: 'userSex', index: 'user_sex', width: 80 }, 			
			{ label: '通知开始时间', name: 'beginTm', index: 'begin_tm', width: 80 }, 			
			{ label: '通知结束时间', name: 'endTm', index: 'end_tm', width: 80 }, 			
			{ label: '管理省份', name: 'mngProv', index: 'mng_prov', width: 80 }, 			
			{ label: '管理地市', name: 'mngCity', index: 'mng_city', width: 80 }, 			
			{ label: '管理县区', name: 'mngCountry', index: 'mng_country', width: 80 }, 			
			{ label: '是否开启 1开启 0 关闭', name: 'status', index: 'status', width: 80 }, 			
			{ label: '时间戳', name: 'tmSmp', index: 'tm_smp', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		noticeuser: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.noticeuser = {};
		},
		update: function (event) {
			var nationCode = getSelectedRow();
			if(nationCode == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(nationCode)
		},
		saveOrUpdate: function (event) {
			var url = vm.noticeuser.nationCode == null ? "noticeuser/save" : "noticeuser/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.noticeuser),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var nationCodes = getSelectedRows();
			if(nationCodes == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "noticeuser/delete",
				    contentType: "application/json",
				    data: JSON.stringify(nationCodes),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(nationCode){
			$.get(baseURL + "noticeuser/info/"+nationCode, function(r){
                vm.noticeuser = r.noticeuser;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});