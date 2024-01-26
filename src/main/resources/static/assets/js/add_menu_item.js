function clickFileChooser() {
    let fileChooser = document.getElementById('fileChooser');
    fileChooser.click();
}

function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var imgElement = document.getElementById('imagePreview');
        imgElement.src = reader.result;
        imgElement.style.display = 'inline-block';
    }
    reader.readAsDataURL(event.target.files[0]);
}