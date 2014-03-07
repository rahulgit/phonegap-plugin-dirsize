function DirSize() {}


DirSize.prototype.getSize = function(dir, successCallback, errorCallback) {
	cordova.exec(successCallback, errorCallback, "DirSize", "getSize", [dir]);
}


DirSize.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.dirsize = new DirSize();
  return window.plugins.dirsize;
};

cordova.addConstructor(DirSize.install);