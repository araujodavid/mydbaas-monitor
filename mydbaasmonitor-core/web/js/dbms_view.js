$(document).ready(function() {
	Highcharts.setOptions({
        global: {
            useUTC: false
        }
	});
	
	var defaultOptions1 = {
			chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
                    	var series = this.series[0];
                        
                        setInterval(function() {
                            var current_time = undefined;
                            var y1 = undefined;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "ProcessStatus", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].processStatusCpu);
		                        
		                        series.addPoint([y1], true, true);
                          	});
                            
                        }, 5000);
                    }
                }
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false
            },
            xAxis: {
                type: 'datetime',
                pointStart: Date.now()
            },
            yAxis: {
                title: {
                    text: 'Percentage'
                },
                min: 0, 
                max: 100,
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
            	formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    this.y + "%";
            	}
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'CPU Utilization',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#FA9616'
            }]
        };
	
	var defaultOptions2 = {
			chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
                    	var series = this.series[0];
                        
                        setInterval(function() {
                            var current_time = undefined;
                            var y1 = undefined;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "ProcessStatus", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].processStatusMemory);
		                        
		                        series.addPoint([y1], true, true);
                          	});
                            
                        }, 5000);
                    }
                }
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false
            },
            xAxis: {
                type: 'datetime',
                pointStart: Date.now()
            },
            yAxis: {
                title: {
                    text: 'Percentage'
                },
                min: 0, 
                max: 100,
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
            	formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    this.y + "%";
            	}
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Memory Utilization',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#5E2D79'
            }]
        };
	
	var defaultOptions3 = {
            chart: {
                type: 'area',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function() {
                        var series = this.series[0];
                        
                        setInterval(function() {
                            var current_time = undefined;
                            var y1 = undefined;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "ActiveConnection", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].activeConnectionAmount);
		                        
		                        series.addPoint([y1], true, true);
                          	});
                            
                        }, 5000);
                    }
                }
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false
            },
            xAxis: {
                type: 'datetime',
                pointStart: Date.now()
            },
            yAxis: {
                title: {
                    text: 'Amount',
                },
                min: 0, 
                max: 50,
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.y + " units";
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            plotOptions: {
                area: {
                    marker: {
                        enabled: true
                    }
                }
            },
            series: [{
                name: 'Active Connection',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#3CB371'
            }]
        };
	
	
	var defaultOptions4 = {
			chart: {
                type: 'area',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function() {
                        var series = this.series[0];
                        
                        setInterval(function() {
                            var current_time = undefined;
                            var y1 = undefined;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "Size", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].sizeUsed);
		                        
		                        series.addPoint([y1], true, true);
                          	});
                            
                        }, 5000);
                    }
                }
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false
            },
            xAxis: {
                type: 'datetime',
                pointStart: Date.now()
            },
            yAxis: {
                title: {
                    text: 'MB',
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.y + " MB";
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            plotOptions: {
                area: {
                    marker: {
                        enabled: true
                    }
                }
            },
            series: [{
                name: 'Size',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#4682B4'
            }]
        };
	
		$('#container1').highcharts(defaultOptions1);
		$('#container2').highcharts(defaultOptions2);
		$('#container3').highcharts(defaultOptions3);
		$('#container4').highcharts(defaultOptions4);
});