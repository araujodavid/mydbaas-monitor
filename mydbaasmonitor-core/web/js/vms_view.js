/* Network */
var lastNetworkBytesSent = 0.0;
var lastNetworkBytesReceived = 0.0;
var lastNetworkPacketsSent = 0.0;
var lastNetworkPacketsReceived = 0.0;

/* Disk */
var lastDiskReads = 0.0;
var lastDiskWrites = 0.0;
var lastDiskBytesRead = 0.0;
var lastDiskBytesWritten = 0.0;


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
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
                                y = Math.random();
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: 'Live random data'
            },
            credits: {
                enabled: false
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Value'
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
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                })()
            }]
        };
	
	
	var defaultOptions2 = {
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
                                y = Math.random();
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: 'Live random data'
            },
            credits: {
                enabled: false
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Value'
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
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                })()
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
                        var series2 = this.series[1];

                        
                        setInterval(function() {
                            var current_time = undefined;
                            var y1 = undefined;
                            var y2 = undefined;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "Memory", resourceType:"machine", metricType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].memoryUsedPercent);
	                          	y2 = parseFloat(data[0].memorySwapUsedPercent);
		                        
		                        series.addPoint([y1], true, true);
		                        series2.addPoint([y2], true, true);
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
                    text: 'Percentage',
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
            plotOptions: {
                area: {
                    marker: {
                        enabled: false
                    }
                }
            },
            series: [{
                name: 'Memory Percent',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#5E2D79'
            },{
                name: 'Swap Percent',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#008000'
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
                        var series2 = this.series[1];

                        
                        setInterval(function() {
                            var networkBytesSent = 0.0;
                            var networkBytesReceived = 0.0;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "Network", resourceType:"machine", metricType: 1, resourceID: resource_id }, function(data) {
                            	networkBytesSent = parseFloat(data[0].networkBytesSent);
                            	networkBytesReceived = parseFloat(data[0].networkBytesReceived);
	                          	                        
		                        series.addPoint([networkBytesSent-lastNetworkBytesSent], true, true);
		                        lastNetworkBytesSent = networkBytesSent;
		                        series2.addPoint([networkBytesReceived-lastNetworkBytesReceived], true, true);
		                        lastNetworkBytesReceived = networkBytesReceived; 
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
                    text: 'Bytes',
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
                        this.y + " bytes";
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
                name: 'Bytes Sent',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            },{
                name: 'Bytes Received',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
            	color: '#BB2A3C'
            }]
        };
	
	
	var defaultOptions5 = {
            chart: {
                type: 'area',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function() {
                        var series = this.series[0];
                        var series2 = this.series[1];

                        
                        setInterval(function() {
                            var networkPacketsSent = 0.0;
                            var networkPacketsReceived = 0.0;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "Network", resourceType:"machine", metricType: 1, resourceID: resource_id }, function(data) {
                            	networkPacketsSent = parseFloat(data[0].networkPacketsSent);
                            	networkPacketsReceived = parseFloat(data[0].networkPacketsReceived);
	                          	                        
		                        series.addPoint([networkPacketsSent-lastNetworkPacketsSent], true, true);
		                        lastNetworkPacketsSent = networkPacketsSent;
		                        series2.addPoint([networkPacketsReceived-lastNetworkPacketsReceived], true, true);
		                        lastNetworkPacketsReceived = networkPacketsReceived; 
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
                    text: 'Packets',
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
                        this.y + " packets";
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
                name: 'Packets Sent',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            },{
                name: 'Packets Received',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
            	color: '#BB2A3C'
            }]
        };
	
	
	var defaultOptions6 = {
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
                                y = Math.random();
                            series.addPoint([x, y], true, true);
                        }, 1000);
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
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Value'
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
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                })()
            }]
        };
	
	
	var defaultOptions7 = {
            chart: {
                type: 'area',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function() {
                        var series = this.series[0];
                        var series2 = this.series[1];

                        
                        setInterval(function() {
                            var diskReads = 0.0;
                            var diskWrites = 0.0;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "Disk", resourceType:"machine", metricType: 1, resourceID: resource_id }, function(data) {
                            	diskReads = parseFloat(data[0].diskReads);
                            	diskWrites = parseFloat(data[0].diskWrites);
	                          	                        
		                        series.addPoint([diskReads-lastDiskReads], true, true);
		                        lastDiskReads = diskReads;
		                        series2.addPoint([diskWrites-lastDiskWrites], true, true);
		                        lastDiskWrites = diskWrites; 
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
                    text: 'Requests',
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
                        this.y + " requests";
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
                name: 'Reads',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            },{
                name: 'Writes',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            }]
        };
	
	
	var defaultOptions8 = {
            chart: {
                type: 'area',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function() {
                        var series = this.series[0];
                        var series2 = this.series[1];

                        
                        setInterval(function() {
                            var diskBytesRead = 0.0;
                            var diskBytesWritten = 0.0;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "Disk", resourceType:"machine", metricType: 1, resourceID: resource_id }, function(data) {
                            	diskBytesRead = parseFloat(data[0].diskBytesRead);
                            	diskBytesWritten = parseFloat(data[0].diskBytesWritten);
	                          	                        
		                        series.addPoint([diskBytesRead-lastDiskBytesRead], true, true);
		                        lastDiskBytesRead = diskBytesRead;
		                        series2.addPoint([diskBytesWritten-lastDiskBytesWritten], true, true);
		                        lastDiskBytesWritten = diskBytesWritten; 
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
                    text: 'Bytes',
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
                        this.y + " bytes";
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
                name: 'Bystes Read',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            },{
                name: 'Bytes Written',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            }]
        };
	
	
	var defaultOptions9 = {
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
                                y = Math.random();
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: 'Live random data'
            },
            credits: {
                enabled: false
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Value'
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
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: Math.random()
                        });
                    }
                    return data;
                })()
            }]
        };
	

	
	
	
		$('#container1').highcharts(defaultOptions1);
		$('#container2').highcharts(defaultOptions2);
		$('#container3').highcharts(defaultOptions3);
		$('#container4').highcharts(defaultOptions4);
		$('#container5').highcharts(defaultOptions5);
		$('#container6').highcharts(defaultOptions6);
		$('#container7').highcharts(defaultOptions7);
		$('#container8').highcharts(defaultOptions8);
		$('#container9').highcharts(defaultOptions9);
			
		
});

function getLabel(id){
	var labels = {};
	labels.cpu_time = "CPU Time";
	labels.memory_time = "Memory Time";
	
	return labels[id];
}

function getURL(id){
	if(id == "cpu_time"){
		return "http://www.highcharts.com/samples/data/jsonp.php?filename=aapl-c.json&callback=?";
	}
	else if(id == "memory_time"){
		return "http://www.highcharts.com/samples/data/jsonp.php?filename=aapl-c.json&callback=?";
	}
}

function setModalValues(identifier){
	$("#myModalLabel").html(getLabel(identifier));
	$("#modal_body").html("<div align='center'><img src='/mydbaasmonitor/img/ajax-loader.gif' /></div>");
	$.getJSON(getURL(identifier), function(data) {
		// Create the chart
		$('#modal_body').highcharts('StockChart', {
			rangeSelector : {
				selected : 1
			},

			title : {
				text : 'AAPL Stock Price'
			},
			credits: {
	            enabled: false
	         },
			series : [{
				name : 'AAPL',
				data : data,
				tooltip: {
					valueDecimals: 2
				}
			}]
		});
	});
	

}

            	

            
            
            
            