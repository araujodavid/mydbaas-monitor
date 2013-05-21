$(document).ready(function() {
	Highcharts.setOptions({
        global: {
            useUTC: false
        }
	});
	
	var defaultOptions = {
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
	
		$('#container1').highcharts(defaultOptions);
		$('#container2').highcharts(defaultOptions);
		$('#container3').highcharts(defaultOptions);
		$('#container4').highcharts(defaultOptions);
			
		
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

            	

            
            
            
            