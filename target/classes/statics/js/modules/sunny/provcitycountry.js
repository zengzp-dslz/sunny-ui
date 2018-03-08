$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'provcitycountry/list',
        datatype: "json",
        colModel: [			
			{ label: 'provCd', name: 'provCd', index: 'prov_cd', width: 50, key: true },
			{ label: '省名称', name: 'provNm', index: 'prov_nm', width: 80 }, 			
			{ label: '地市编码', name: 'cityCd', index: 'city_cd', width: 80 }, 			
			{ label: '地市名称', name: 'cityNm', index: 'city_nm', width: 80 }, 			
			{ label: '省区编码', name: 'countryCd', index: 'country_cd', width: 80 }, 			
			{ label: '省区名称', name: 'countryNm', index: 'country_nm', width: 80 }, 			
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
		provcitycountry: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.provcitycountry = {};
		},
		update: function (event) {
			var provCd = getSelectedRow();
			if(provCd == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(provCd)
		},
		saveOrUpdate: function (event) {
			var url = vm.provcitycountry.provCd == null ? "provcitycountry/save" : "provcitycountry/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.provcitycountry),
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
			var provCds = getSelectedRows();
			if(provCds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "provcitycountry/delete",
				    contentType: "application/json",
				    data: JSON.stringify(provCds),
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
		getInfo: function(provCd){
			$.get(baseURL + "provcitycountry/info/"+provCd, function(r){
                vm.provcitycountry = r.provcitycountry;
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