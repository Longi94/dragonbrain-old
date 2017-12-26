var fabExpanded = false;

var selectedProject, selectedPhoto = -1;

var projectMenu;
var photoMenu;
var newProjectDialog;
var newPhotoDialog;
$('document').ready(function () {
    projectMenu = new mdc.menu.MDCSimpleMenu(document.querySelector('#menu-project'));
    photoMenu = new mdc.menu.MDCSimpleMenu(document.querySelector('#menu-photo'));

    newProjectDialog = new mdc.dialog.MDCDialog(document.querySelector('#dialog-add-project'));
    newPhotoDialog = new mdc.dialog.MDCDialog(document.querySelector('#dialog-add-photo'));

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

    selectedProject = parseInt($element.parent().val());

    projectMenu.open = true;
}

function openPhotoMenu(event, element) {
    event.stopPropagation();

    projectMenu.open = false;

    var $element = $(element);
    var pos = $element.offset();
    var rt = ($(window).width() - (pos.left + $element.outerWidth()));

    $("#menu-photo").css({right: rt, top: pos.top});

    selectedPhoto = parseInt($element.parent().val());

    photoMenu.open = true;
}

function openNewProjectDialog(event) {
    newProjectDialog.lastFocusedTarget = event.target;
    newProjectDialog.show();

    newProjectDialog.listen('MDCDialog:accept', function() {
        var params = {
            name: $("#new-project-name").val(),
            type: $("#new-project-type").val(),
            description: $("#new-project-description").val(),
            sourceUrl: $("#new-project-source").val(),
            url: $("#new-project-link").val(),
            image:  $("#new-project-image").val()
        };

        authAjax({
            url: "/admin/projects",
            type: "POST",
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                var $listProject = $("#list-project");

                $listProject.append('<li role="separator" class="mdc-list-divider"></li>')
                    .append(
                        '<li class="mdc-list-item" value="' + data.id + '">' +
                        '<span class="mdc-list-item__text">' + data.name +
                        '<span class="mdc-list-item__secondary-text">' + data.type + '</span>' +
                        '</span>' +
                        '<i class="mdc-list-item__end-detail material-icons" onclick="openProjectMenu(event, this);">more_vert</i>' +
                        '</li>'
                    );

                projects.push(data);
            }
        });

        $("#dialog-add-project").find("input,textarea").val("");
    });

    newProjectDialog.listen('MDCDialog:cancel', function() {
        $("#dialog-add-project").find("input,textarea").val("");
    });
}

function openNewPhotoDialog(event) {
    newPhotoDialog.lastFocusedTarget = event.target;
    newPhotoDialog.show();

    newPhotoDialog.listen('MDCDialog:accept', function() {
        var params = {
            location: $("#new-photo-location").val(),
            device: $("#new-photo-device").val(),
            date: $("#new-photo-date").val(),
            path: $("#new-photo-image").val()
        };

        authAjax({
            url: "/admin/photos",
            type: "POST",
            data: JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                var $listPhoto = $("#list-photo");

                $listPhoto.append('<li role="separator" class="mdc-list-divider"></li>')
                    .append(
                        '<li class="mdc-list-item" value="' + data.id + '">' +
                        '<span class="mdc-list-item__text">' + data.location +
                        '<span class="mdc-list-item__secondary-text">' + data.device + '</span>' +
                        '</span>' +
                        '<i class="mdc-list-item__end-detail material-icons" onclick="openPhotoMenu(event, this);">more_vert</i>' +
                        '</li>'
                    );

                photos.push(data);
            }
        });

        $("#dialog-add-photo").find("input").val("");
    });

    newPhotoDialog.listen('MDCDialog:cancel', function() {
        $("#dialog-add-photo").find("input").val("");
    });
}

function deleteSelectedProject() {
    authAjax({
        url: '/admin/projects/' + selectedProject,
        type: 'DELETE',
        success: function () {
            var $toRemove = $("#list-project").find(".mdc-list-item[value='" + selectedProject + "']");

            if ($toRemove.prev().length > 0) {
                $toRemove.prev().remove();
            } else {
                $toRemove.next().remove();
            }

            $toRemove.remove();
            selectedProject = -1;
        }
    });
}

function deleteSelectedPhoto() {
    authAjax({
        url: '/admin/photos/' + selectedPhoto,
        type: 'DELETE',
        success: function () {
            var $toRemove = $("#list-photo").find(".mdc-list-item[value='" + selectedPhoto + "']");

            if ($toRemove.prev().length > 0) {
                $toRemove.prev().remove();
            } else {
                $toRemove.next().remove();
            }

            $toRemove.remove();
            selectedPhoto = -1;
        }
    });
}