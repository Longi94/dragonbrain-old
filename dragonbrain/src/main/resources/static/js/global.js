function openInNewTab(url) {
    var win = window.open(url, '_blank');
    win.focus();
}

var authAjax = function (settings) {
    settings.headers = typeof settings.headers !== 'undefined' ? settings.headers : {};
    jQuery.extend(settings.headers, csfHeader);
    jQuery.ajax(settings);
};
