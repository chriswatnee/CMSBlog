// Show delete confirmation
function showDeleteConfirmation(type, id, name) {
    var param = type === 'User' ? 'username' : type.toLowerCase() + 'Id';
    var href = 'delete' + type + '?' + param + '=' + id;
    if (type === 'Post') {
        var postType = document.location.href.match(/[^\/]+$/)[0];
        href += '&referer=' + postType;
    }
    $('#delete-confirmation-yes-button').attr('href', href);
    $('#modal-text-name').text(name);
    $('#delete-confirmation-div').modal('show');
    return false;
}