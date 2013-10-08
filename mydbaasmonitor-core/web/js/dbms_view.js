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
	
	var defaultOptions5 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "NetworkTraffic", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].networkTrafficBytesReceived);
		                        
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
                        enabled: true
                    }
                }
            },
            series: [{
                name: 'Bytes Received',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#4682B4'
            }]
        };
	
	var defaultOptions6 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "NetworkTraffic", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].networkTrafficBytesSent);
		                        
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
                        enabled: true
                    }
                }
            },
            series: [{
                name: 'Bytes Sent',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#ff6228'
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
                            var current_time = undefined;
                            var y1 = undefined;
                            var y2 = undefined;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "StatementDML", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].statementDMLInserts);
	                          	y2 = parseFloat(data[0].statementDMLUpdates);
		                        
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
                    text: 'Amount',
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
                        this.y + " executed";
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
                name: 'Inserts',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#3CB371'
            },{
                name: 'Updates',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#fefe38'
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
                            var current_time = undefined;
                            var y1 = undefined;
                            var y2 = undefined;

                            var resource_id = parseInt($("#resource_id_chart").val());
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "StatementDML", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].statementDMLSelects);
	                          	y2 = parseFloat(data[0].statementDMLDeletes);
		                        
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
                    text: 'Amount',
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
                        this.y + " executed";
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
                name: 'Selects',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#6085e6'
            },{
                name: 'Deletes',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#d63928'
            }]
        };
	
	var defaultOptions9 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "StatementTCL", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].statementTCLCommits);
	                          	y2 = parseFloat(data[0].statementTCLRollback);
		                        
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
                    text: 'Amount',
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
                        this.y + " executed";
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
                name: 'Commits',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#3CB371'
            },{
                name: 'Rollbacks',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#d63928'
            }]
        };
	
	var defaultOptions10 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "StatementTCL", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].statementTCLSavepoint);
		                        
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
                    text: 'Amount'
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
                    this.y + " executed";
            	}
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Savepoints',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#FA9616'
            }]
        };
	
	var defaultOptions11 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "StatementDDL", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].statementDDLCreate);
	                          	y2 = parseFloat(data[0].statementDDLAlter);
		                        
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
                    text: 'Amount',
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
                        this.y + " executed";
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
                name: 'Creates',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#EE1D00'
            },{
                name: 'Alters',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#2D7DB3'
            }]
        };
	
	var defaultOptions12 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "StatementDDL", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].statementDDLDrop);
	                          	y2 = parseFloat(data[0].statementDDLTruncate);
		                        
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
                    text: 'Amount',
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
                        this.y + " executed";
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
                name: 'Drops',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#EE1D00'
            },{
                name: 'Truncates',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#2D7DB3'
            }]
        };
	
	var defaultOptions13 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "StatementDCL", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].statementDCLGrant);
		                        
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
                    text: 'Amount'
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
                    this.y + " executed";
            	}
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Grants',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#3CB371'
            }]
        };
	
	var defaultOptions14 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "StatementDCL", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].statementDCLRevoke);
		                        
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
                    text: 'Amount'
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
                    this.y + " executed";
            	}
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Revokes',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0],
                color: '#d63928'
            }]
        };
	
	var defaultOptions16 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "DiskUtilization", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].diskUtilizationPhysicalReads);
	                          	y2 = parseFloat(data[0].diskUtilizationLogicalReads);
		                        
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
                name: 'Physical',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            },{
                name: 'Logical',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]                
            }]
        };
	
	var defaultOptions17 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "DiskUtilization", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].diskUtilizationDataRead);
	                          	y2 = parseFloat(data[0].diskUtilizationDataWritten);
		                        
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
                name: 'Bytes Read',
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
	
	var defaultOptions18 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "DiskUtilization", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].diskUtilizationPagesRead);
	                          	y2 = parseFloat(data[0].diskUtilizationPagesWritten);
		                        
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
                name: 'Pages Read',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            },{
                name: 'Pages Written',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            }]
        };
	
	var defaultOptions19 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "DiskUtilization", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].diskUtilizationKeyRead);
	                          	y2 = parseFloat(data[0].diskUtilizationKeyWrites);
		                        
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
                    text: 'Requests',
                },
                min: 0, 
                max: 20,
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
                name: 'Key Reads',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            },{
                name: 'Key Writes',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
            }]
        };
	
	var defaultOptions20 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "DiskUtilization", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].diskUtilizationPendingReads);
		                        
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
                    text: 'Requests'
                },
                min: 0, 
                max: 20,
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
            series: [{
                name: 'Pending Reads',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,1,0,0,0,0,0]
            }]
        };
	
	var defaultOptions21 = {
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
                            
                            $.post('http://localhost:8080/mydbaasmonitor/metric/single', {metricName : "DiskUtilization", metricType:"dbms", resourceType:"dbms", queryType: 1, resourceID: resource_id },function(data) {
	                            current_time = data[0].recordDate;
	                            
	                          	y1 = parseFloat(data[0].diskUtilizationPendingWrites);
		                        
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
                    text: 'Requests'
                },
                min: 0, 
                max: 20,
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
            series: [{
                name: 'Pending Writes',
                pointStart: Date.now(),
                pointInterval: 6000,
                data: [0,0,0,0,0,0,0]
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
		$('#container10').highcharts(defaultOptions10);
		$('#container11').highcharts(defaultOptions11);
		$('#container12').highcharts(defaultOptions12);
		$('#container13').highcharts(defaultOptions13);
		$('#container14').highcharts(defaultOptions14);
		$('#container16').highcharts(defaultOptions16);
		$('#container17').highcharts(defaultOptions17);
		$('#container18').highcharts(defaultOptions18);
		$('#container19').highcharts(defaultOptions19);
		$('#container20').highcharts(defaultOptions20);
		$('#container21').highcharts(defaultOptions21);
});