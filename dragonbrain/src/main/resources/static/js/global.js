function openInNewTab(url) {
    var win = window.open(url, '_blank');
    win.focus();
}

var authAjax = function (settings) {
    settings.headers = typeof settings.headers !== 'undefined' ? settings.headers : {};
    jQuery.extend(settings.headers, csfHeader);
    jQuery.ajax(settings);
};

function swapNodes(a, b) {
    var aparent = a.parentNode;
    var asibling = a.nextSibling === b ? a : a.nextSibling;
    b.parentNode.insertBefore(a, b);
    aparent.insertBefore(b, asibling);
}
