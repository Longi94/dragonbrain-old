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

    newProjectDialog.listen('MDCDialog:cancel', function () {
        $("#dialog-add-project").find("input,textarea").val("");
        newProjectDialog.unlisten('MDCDialog:accept', newProjectAccept);
        newProjectDialog.unlisten('MDCDialog:accept', editProjectAccept);
        closeFab();
    });

    newPhotoDialog.listen('MDCDialog:cancel', function () {
        $("#dialog-add-photo").find("input").val("");
        newPhotoDialog.unlisten('MDCDialog:accept', newPhotoAccept);
        newPhotoDialog.unlisten('MDCDialog:accept', editPhotoAccept);
        closeFab();
    });

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
        closeFab();
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

function closeFab() {
    fabExpanded = false;
    var delay = 0;
    $($(".mdc-fab--mini").get().reverse()).each(function (index, element) {
        setTimeout(function () {
            $(element).addClass("mdc-fab--exited");
        }, delay);
        delay += 50;
    });
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
    //closeFab();

    newProjectDialog.listen('MDCDialog:accept', newProjectAccept);
}

var newProjectAccept = function () {
    var params = {
        name: $("#new-project-name").val(),
        type: $("#new-project-type").val(),
        description: $("#new-project-description").val(),
        sourceUrl: $("#new-project-source").val(),
        url: $("#new-project-link").val(),
        image: $("#new-project-image").val()
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
        }
    });

    $("#dialog-add-project").find("input,textarea").val("");
    newProjectDialog.unlisten('MDCDialog:accept', newProjectAccept);
    closeFab();
};

function openNewPhotoDialog(event) {
    newPhotoDialog.lastFocusedTarget = event.target;
    newPhotoDialog.show();
    //closeFab();

    newPhotoDialog.listen('MDCDialog:accept', newPhotoAccept);
}

var newPhotoAccept = function () {
    var params = {
        title: $("#new-photo-title").val(),
        device: $("#new-photo-device").val(),
        date: $("#new-photo-date").val(),
        path: $("#new-photo-image").val(),
        thumbnail: $("#new-photo-thumbnail").val()
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
                    '<span class="mdc-list-item__text">' + data.title +
                    '<span class="mdc-list-item__secondary-text">' + data.device + '</span>' +
                    '</span>' +
                    '<i class="mdc-list-item__end-detail material-icons" onclick="openPhotoMenu(event, this);">more_vert</i>' +
                    '</li>'
                );
        }
    });

    $("#dialog-add-photo").find("input").val("");
    newPhotoDialog.unlisten('MDCDialog:accept', newPhotoAccept);
    closeFab();
};

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

function moveSelectedProject(up) {
    authAjax({
        url: '/admin/projects/' + selectedProject + '/move',
        type: 'POST',
        data: {up: up},
        success: function () {
            var $toMove = $("#list-project").find(".mdc-list-item[value='" + selectedProject + "']");

            if (up) {
                swapNodes($toMove.get(0), $toMove.prev().prev().get(0));
            } else {
                swapNodes($toMove.get(0), $toMove.next().next().get(0));
            }

            selectedProject = -1;
        }
    });
}

function openEditProjectDialog(event) {
    authAjax({
        url: "/admin/projects/" + selectedProject,
        type: "GET",
        success: function (project) {
            $("#new-project-name").val(project.name);
            $("#new-project-type").val(project.type);
            $("#new-project-description").val(project.description);
            $("#new-project-source").val(project.sourceUrl);
            $("#new-project-link").val(project.url);
            $("#new-project-image").val(project.image);

            newProjectDialog.root_.projectId = project.id;
            newProjectDialog.lastFocusedTarget = event.target;
            newProjectDialog.show();

            newProjectDialog.listen('MDCDialog:accept', editProjectAccept);
        }
    });
}

var editProjectAccept = function (evt) {
    var params = {
        name: $("#new-project-name").val(),
        type: $("#new-project-type").val(),
        description: $("#new-project-description").val(),
        sourceUrl: $("#new-project-source").val(),
        url: $("#new-project-link").val(),
        image: $("#new-project-image").val()
    };

    authAjax({
        url: "/admin/projects/" + evt.target.projectId,
        type: "PUT",
        data: JSON.stringify(params),
        contentType: "application/json",
        dataType: "json",
        success: function (project) {
            var $toEdit = $("#list-project").find(".mdc-list-item[value='" + selectedProject + "']");

            $toEdit.find(".mdc-list-item__text").text(project.name)
                .append('<span class="mdc-list-item__secondary-text">' + project.type + '</span>')
        }
    });

    $("#dialog-add-project").find("input,textarea").val("");
    newProjectDialog.unlisten('MDCDialog:accept', editProjectAccept);
    closeFab();
};

function openEditPhotoDialog(event) {

    authAjax({
        url: "/admin/photos/" + selectedPhoto,
        type: "GET",
        success: function (photo) {
            $("#new-photo-title").val(photo.title);
            $("#new-photo-device").val(photo.device);
            $("#new-photo-date").val(photo.date);
            $("#new-photo-image").val(photo.path);
            $("#new-photo-thumbnail").val(photo.thumbnail);

            newPhotoDialog.root_.photoId = photo.id;
            newPhotoDialog.lastFocusedTarget = event.target;
            newPhotoDialog.show();

            newPhotoDialog.listen('MDCDialog:accept', editPhotoAccept);
        }
    });
}

var editPhotoAccept = function (evt) {
    var params = {
        title: $("#new-photo-title").val(),
        device: $("#new-photo-device").val(),
        date: $("#new-photo-date").val(),
        path: $("#new-photo-image").val(),
        thumbnail: $("#new-photo-thumbnail").val()
    };

    authAjax({
        url: "/admin/photos/" + evt.target.photoId,
        type: "PUT",
        data: JSON.stringify(params),
        contentType: "application/json",
        dataType: "json",
        success: function (photo) {
            var $toEdit = $("#list-photo").find(".mdc-list-item[value='" + selectedPhoto + "']");

            $toEdit.find(".mdc-list-item__text").text(photo.title)
                .append('<span class="mdc-list-item__secondary-text">' + photo.device + '</span>')
        }
    });

    $("#dialog-add-photo").find("input").val("");
    newPhotoDialog.unlisten('MDCDialog:accept', editPhotoAccept);
    closeFab();
};
