#[MyDBaaSMonitor](http://github.com/araujodavid/mydbaasmonitor)
--------------

A framework that provides an environment monitoring for database-as-a-service.

## Informations

* Front-end developed using [Twitter Bootstrap](http://twitter.github.com/bootstrap).
* Back-end developed using [VRaptor Framework](http://vraptor.caelum.com.br).
* API for monitoring system information and hardware, [Hyperic SIGAR API](http://www.hyperic.com/products/sigar).
* HTTP client library, [HttpClient](http://hc.apache.org/httpcomponents-client-ga/index.html).

## Team

**David Araújo**

+ http://github.com/araujodavid
+ http://araujodavid.com

**José Antônio F. de Macêdo**

## Modules

The monitoring environment has four modules:

`mydbaasmonitor-core`
+ **Core**: managing the monitoring agents, knowledge base, API requests and the web management interface - [details](http://github.com/araujodavid/mydbaasmonitor/tree/master/mydbaasmonitor-core).

`mydbaasmonitor-agent`
+ **Agent**: responsible for collecting metrics in the environment nodes and send to the server - [details](http://github.com/araujodavid/mydbaasmonitor/tree/master/mydbaasmonitor-agent).

`mydbaasmonitor-api`
+ **API**: set of routines and standards to use features to implement other softwares - [details](http://github.com/araujodavid/mydbaasmonitor/tree/master/mydbaasmonitor-api).

`mydbaasmonitor-common`
+ **Common**: package of common classes between modules - [details](http://github.com/araujodavid/mydbaasmonitor/tree/master/mydbaasmonitor-common).

## License

*Federal University of Ceará - Brazil, ARiDa Research Group.*
