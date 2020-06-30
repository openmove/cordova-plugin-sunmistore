module.exports = {
    openStore: function (successCallback, errorCallback, packageName) {
        cordova.exec(successCallback, errorCallback, "SunmiStore", "openstore", [packageName]);
    }
};
