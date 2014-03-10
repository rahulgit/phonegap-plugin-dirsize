phonegap-plugin-dirsize
=======================

This PhoneGap plugin allows you to get SDCard directory size.

 > only support android platfrom


#### Installation

Automatically (CLI / Plugman)

dirsize is compatible with Cordova Plugman, compatible with PhoneGap 3.0 CLI, here's how it works with the CLI:

    $ phonegap local plugin add https://github.com/weelion/phonegap-plugin-dirsize.git
or

    $ cordova plugin add https://github.com/weelion/phonegap-plugin-dirsize.git


run this command afterwards (backup your project first!):

    $ cordova prepare

dirsize.js is brought in automatically. There is no need to change or add anything in your html.


#### Usage

js

    var path = 'imgcache';
    window.dirsize.size(path, function(size) {
        console.log(size); // output 1048576
    });

    window.dirsize.formatSize(path, function(size) {
        console.log(size); // output 1MB
    });

#### Have a nice day!