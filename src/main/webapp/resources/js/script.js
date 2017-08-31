/**
 * Created by Michał on 2017-05-07.
 */

change_colour();
clear_answer();


function change_colour() {

    var pathToResult = $("#result");

    if (pathToResult.text() === 'Źle!') {
        pathToResult.css('color', '#ff0000');
    }

    if (pathToResult.text() === 'Dobrze!') {
        pathToResult.css('color', '#00b300');
    }
}

function clear_answer() {

    var path = document.getElementById('wordInEnglish');
    path.value = '';
}