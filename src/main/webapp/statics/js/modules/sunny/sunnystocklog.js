$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sunnystocklog/list',
        datatype: "json",
        colModel: [			
			{ label: 'stocklogId', name: 'stocklogId', index: 'stocklog_id', width: 50, key: true },
			{ label: '调用方法', name: 'method', index: 'method', width: 80 }, 			
			{ label: '参数', name: 'params', index: 'params', width: 80 }, 			
			{ label: '创建时间', name: 'tmSmp', index: 'tm_smp', width: 80 }			
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
		sunnystocklog: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sunnystocklog = {};
		},
		update: function (event) {
			var stocklogId = getSelectedRow();
			if(stocklogId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(stocklogId)
		},
		saveOrUpdate: function (event) {
			var url = vm.sunnystocklog.stocklogId == null ? "sunnystocklog/save" : "sunnystocklog/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.sunnystocklog),
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
			var stocklogIds = getSelectedRows();
			if(stocklogIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sunnystocklog/delete",
				    contentType: "application/json",
				    data: JSON.stringify(stocklogIds),
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
		getInfo: function(stocklogId){
			$.get(baseURL + "sunnystocklog/info/"+stocklogId, function(r){
                vm.sunnystocklog = r.sunnystocklog;
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