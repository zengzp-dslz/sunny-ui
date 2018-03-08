$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sunnyuser/list',
        datatype: "json",
        colModel: [			
			{ label: 'userType', name: 'userType', index: 'user_type', width: 50, key: true },
			{ label: '', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '', name: 'nickName', index: 'nick_name', width: 80 }, 			
			{ label: '', name: 'sex', index: 'sex', width: 80 }, 			
			{ label: '', name: 'avatarUrl', index: 'avatar_url', width: 80 }, 			
			{ label: '', name: 'country', index: 'country', width: 80 }, 			
			{ label: '', name: 'prov', index: 'prov', width: 80 }, 			
			{ label: '', name: 'city', index: 'city', width: 80 }, 			
			{ label: '', name: 'nationCode', index: 'nation_code', width: 80 }, 			
			{ label: '', name: 'mblNo', index: 'mbl_no', width: 80 }, 			
			{ label: '', name: 'regDt', index: 'reg_dt', width: 80 }, 			
			{ label: '', name: 'regTm', index: 'reg_tm', width: 80 }, 			
			{ label: '', name: 'ip', index: 'ip', width: 80 }, 			
			{ label: '', name: 'longitude', index: 'longitude', width: 80 }, 			
			{ label: '', name: 'latitude', index: 'latitude', width: 80 }, 			
			{ label: '', name: 'addr', index: 'addr', width: 80 }, 			
			{ label: '', name: 'tmSmp', index: 'tm_smp', width: 80 }, 			
			{ label: '', name: 'isBlack', index: 'is_black', width: 80 }			
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
		sunnyuser: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sunnyuser = {};
		},
		update: function (event) {
			var userType = getSelectedRow();
			if(userType == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(userType)
		},
		saveOrUpdate: function (event) {
			var url = vm.sunnyuser.userType == null ? "sunnyuser/save" : "sunnyuser/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.sunnyuser),
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
			var userTypes = getSelectedRows();
			if(userTypes == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sunnyuser/delete",
				    contentType: "application/json",
				    data: JSON.stringify(userTypes),
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
		getInfo: function(userType){
			$.get(baseURL + "sunnyuser/info/"+userType, function(r){
                vm.sunnyuser = r.sunnyuser;
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