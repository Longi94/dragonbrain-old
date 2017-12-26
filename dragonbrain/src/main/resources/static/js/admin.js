var fabExpanded = false;

var selectedProject, selectedPhoto = -1;

var projectMenu;
var photoMenu;
var newProjectDialog;
$('document').ready(function () {
    projectMenu = new mdc.menu.MDCSimpleMenu(document.querySelector('#menu-project'));
    photoMenu = new mdc.menu.MDCSimpleMenu(document.querySelector('#menu-photo'));

    newProjectDialog = new mdc.dialog.MDCDialog(document.querySelector('#dialog-add-project'));

    $("#list-photo.mdc-list").find(".mdc-list-item").click(function () {
        projectMenu.open = false;
        photoMenu.open = false;
    });

    $("#list-project.mdc-list").find(".mdc-list-item").click(function () {
        projectMenu.open = false;
        photoMenu.open = false;
    });
});

function toggleFab() {
    if (fabExpanded) {
        fabExpanded = false;
        var delay = 0;
        $(".mdc-fab--mini").each(function (index, element) {
            setTimeout(function () {
                $(element).addClass("mdc-fab--exited");
            }, delay);
            delay += 50;
        });
    } else {
        fabExpanded = true;
        var delay = 0;
        $(".mdc-fab--mini").each(function (index, element) {
            setTimeout(function () {
                $(element).removeClass("mdc-fab--exited");
            }, delay);
            delay += 50;
        });
    }
}

function openProjectMenu(event, element) {
    event.stopPropagation();

    photoMenu.open = false;

    var $element = $(element);
    var pos = $element.offset();
    var rt = ($(window).width() - (pos.left + $element.outerWidth()));

    $("#menu-project").css({right: rt, top: pos.top});

    projectMenu.open = true;
}

function openPhotoMenu(event, element) {
    event.stopPropagation();

    projectMenu.open = false;

    var $element = $(element);
    var pos = $element.offset();
    var rt = ($(window).width() - (pos.left + $element.outerWidth()));

    $("#menu-photo").css({right: rt, top: pos.top});

    photoMenu.open = true;
}

function openNewProjectDialog(event) {
    newProjectDialog.lastFocusedTarget = event.target;
    newProjectDialog.show();
}