$(document).ready(function() {
	Highcharts.setOptions({
        global: {
            useUTC: false
        }
	});
	
	var defaultOptions1 = {
            chart: {
                type: 'spline',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function() {
                        var series = this.series[0];
                        
                        setInterval(function() {
                            var current_time = undefined;
                            var y1 = undefined;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "ActiveConnection", metricType:"database", resourceType:"database", queryType: 1, resourceID: resource_id },function(data) {
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
                        enabled: false
                    }
                }
            },
            series: [{
                name: 'Active Connection',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [1,1,1,1,2,2,2],
                color: '#3CB371'
            }]
        };
	
	
	var defaultOptions2 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "Size", metricType:"database", resourceType:"database", queryType: 1, resourceID: resource_id },function(data) {
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
                data: [8.5,8.5,11.5,12,14.5,14.5,14.5],
                color: '#4682B4'
            }]
        };
	
		$('#container1').highcharts(defaultOptions1);
		$('#container2').highcharts(defaultOptions2);		
});