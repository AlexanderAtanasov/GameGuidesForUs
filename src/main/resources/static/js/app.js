getGames()

function getGames() {
    $("#authors-container").empty();

    fetch("http://localhost:8080/home")
        .then(response => response.json())
        .then(json => {

            if (json.screenshotUrl === null) {
                $("#authors-container")
            } else {
            let srcRoute = json.screenshotUrl;
            let tableCol = '<tc >' + '<td>' + json.gameTitle + '</td>' + '</tc>'
            let tableRow = '<tr>' + '<td> + <img style="max-height: 50%; max-width: 50% " src="' + srcRoute + '"> + </td>' + '</tr>'
            $("#authors-container").append(tableCol).append(tableRow)
        }
        })
}

// let tableRow =
//     '<tr style="border: white solid">' +
//     '<td>' + game.gameTitle + '</td>' +
//     '<td>' + game.screenshotUrl + '</td>' +
//     '<td>' +
//     '<button class="edit-btn" data-book-id="' + game.id + '">View</button>' +
//     '</td>' +
//     '</tr>'


$('body').on('click', 'button.delete-btn', function () {
    let bookId = $(this).data('book-id');
    console.log("Book id to delete is " + bookId);

    fetch('http://localhost:8080/books/' + bookId, {
        method: 'DELETE'
    }).then(_ => reloadBooks())
});