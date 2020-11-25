# simple-ping-application

## What makes simple-ping-application

this project contains 3 scheduled functions which for 'ping with icmp', 'ping with tcp' and 'trace route' process.
when application start up, it reads to dynamic properties from yml file.
each scheduled function creates threads as much as dynamic host count in yml. these threads work as parallel.
each process's success results, stored in local file.
each process's error results, reported as HTTP POST to given url in yml file.

## Requirements
+ Maven 3.6.1 or later
+ JDK 11 or later
+ Some of values in yml file need to be updated according your OS(Linux or Windows)

