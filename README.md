#[MyDBaaSMonitor](http://github.com/araujodavid/mydbaasmonitor)
--------------

A Web Environment for Database-as-a-Service Monitoring.

## Informations

* Front-end developed using [Twitter Bootstrap](http://twitter.github.com/bootstrap).
* Back-end developed using [VRaptor Framework](http://vraptor.caelum.com.br).
* SSH Protocol, [JSch](http://www.jcraft.com/jsch/).
* API for monitoring system information and hardware, [Hyperic SIGAR API](http://www.hyperic.com/products/sigar).
* A toolkit to interact with the  hypervision' virtualization capabilities, [Libvirt Virtualization API](http://http://libvirt.org).
* HTTP client library, [HttpClient](http://hc.apache.org/httpcomponents-client-ga/index.html).

## Team

**David Araújo**

+ [@araujodavid](http://github.com/araujodavid)
+ http://araujodavid.com

**Franzé Jr.**

+ [@franzejr](http://github.com/franzejr)
+ http://franzejr.com

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

`mydbaasmonitor-jdbc`
+ **JDBC**: extends JDBC of the PostgreSQL and MySQL, adding functionality monitoring metrics that require interception of workloads sent to the database. - [details](http://github.com/araujodavid/mydbaasmonitor/tree/master/mydbaasmonitor-jdbc).

## License

*Federal University of Ceará - Brazil, ARiDa Research Group.*
