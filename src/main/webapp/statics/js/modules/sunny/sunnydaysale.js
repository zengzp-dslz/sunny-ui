$(function() {

});
var chartCtx = document.getElementById("daySaleChart").getContext('2d');
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
		sunnyorder : {},
        datasets: [],
        rowData: [],
        spinShow:true
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
            if(vm.q.starDate){
				vm.q.starDate = vm.formatDate(vm.q.starDate);
			}
			if(vm.q.endDate){
				vm.q.endDate = vm.formatDate(vm.q.endDate);
			}
			var data = {
                'starDate' : vm.q.starDate,
				'endDate' : vm.q.endDate,
				'mercId' : vm.q.mercId
			}
            vm.getListData(data)
		},
		formatDate: function(olddate){
			var date = new Date(olddate);
			var year = date.getFullYear();
			var month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
			var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
			return year + "" + month + "" + day;
		},
        //渲染图表
        renderChart: function (data,chartType) {
		    vm.spinShow = false
            var rowData = [],
                colData = [];
            $.each(data, function (i,item) {
                rowData.push(item.ordDt)
                colData.push((item.payAmt/100).toFixed(2))
            })
            var mercNm = (vm.q.mercNm&&vm.q.mercNm!==null?vm.q.mercNm:'全部')
            var chartSettings = {
                type: chartType||'bar',
                data: {
                    labels: rowData.reverse(),
                    datasets: [{
                        label:mercNm,
                        data: colData.reverse(),
                        borderColor:  '#007bf4',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero:true
                            }
                        }]
                    }
                }
            }
            if(vm.chart){
                vm.chart.data.labels = chartSettings.data.labels
                vm.chart.data.datasets = chartSettings.data.datasets
                vm.chart.update()
            }else {
            	vm.chart = new Chart(chartCtx,chartSettings)
            }
        },
        //获取列表数据
        getListData: function (data) {
            var reqData = {
                _search:false,
                nd: (new Date()).getTime(),
                limit:30,
                page:1,
                sidx:'',
                order:'asc'
            }
            $.extend(data,reqData)
            $.get(baseURL + 'sunnyorder/daysale',data, function (result) {
                vm.renderChart(result.page.list,'line')
            },'json')
        }
	}
});
vm.reload()