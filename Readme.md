# Prerequisites

* vagrant ruby gem
* [VirtualBox][vb-dl]

If you have trouble with vagrant starting the Virtual Machine correctly, often installing the corresponding [VirtualBox Extension Pack][vb-dl] helps.

[vb-dl]: https://www.virtualbox.org/wiki/Downloads

# Installation

Clone this git repository and run

    rake setup

This tasks initializes the git submodules, which basically are all the chef cookbooks.
After this task has run successfully, you can boot up the virtual machine with

    vagrant up

This downloads an ubuntu box and sets up the virtual machine through chef-solo.
When the machine is ready, you can jump in there with

    vagrant ssh

Vagrant also sets up some port forwarding.
If you visit localhost:8088 on your host system, you should get to a hue web interface.
Hue is a web application by Cloudera and makes browsing jobs and the HDFS more comfortable.

Now if you have run `vagrant ssh` and are in the VM, change to the projects directory with `cd /vagrant`.

Here you can run a basic job with

    gradle runJar

To initiate the performance tests, run

    grade comparePerformance