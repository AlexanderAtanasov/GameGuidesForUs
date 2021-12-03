getGames()

function getGames() {
    $("#game-container").empty();

    fetch("http://localhost:8080/home")
        .then(response => response.json())
        .then(json => {

            if (json.screenshotUrl === null) {
                $("#game-container")
            } else {
            let srcRoute = json.screenshotUrl;
            let tableCol = '<tc class="bg-blur" style="font-size: 3rem" >' + '<td>' + json.gameTitle + '</td>' + '</tc>'
            let tableRow = '<tr>' + '<td> <a href="/games/'+ json.gameId +'/view/"> <img style="max-height: 50%; max-width: 50% " src="' + srcRoute + '"> </a>  </td>' + '</tr>'
            $("#game-container").append(tableCol).append(tableRow)
        }
        })
}


