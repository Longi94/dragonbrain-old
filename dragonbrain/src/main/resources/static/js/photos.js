var index;

function openImage(element) {
    index = parseInt(element.attributes.value.nodeValue);

    $("#photo-overlay").show();
    $("body").addClass("scroll-lock");
    selectPhoto();
}

function closeOverlay() {
    $("#photo-overlay").hide();
    $("body").removeClass("scroll-lock");
}

function next() {
    if (index < photos.length - 1) {
        index++;
        selectPhoto();
    }
}

function previous() {
    if (index > 0) {
        index--;
        selectPhoto();
    }
}

function selectPhoto() {
    $("#full-photo").css({'background-image': 'url("' + photos[index].path + '")'});
    $("#title").text(photos[index].title);
    $("#device").text(photos[index].device);
    $("#date").text(photos[index].date.substring(0, 10));
}