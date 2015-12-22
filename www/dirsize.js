var exec = require("cordova/exec");
var DirSize = function () {
    this.name = "DirSize";
};

DirSize.prototype.size = function (dir, func) {
	if (!dir) {
        return 'notDir';
    }

	exec(func, null, "DirSize", "size", [dir]);
};

DirSize.prototype.formatSize = function (dir, func) {
	if (!dir) {
        return;
    }

	exec(func, null, "DirSize", "formatSize", [dir]);
};

module.exports = new DirSize();
