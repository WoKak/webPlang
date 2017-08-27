/**
 * Created by Michał on 2017-05-07.
 */

change_colour();
clear_answer();


function change_colour() {

    var pathToResult = document.getElementById('result');

    if (pathToResult.textContent === 'Źle!') {
        pathToResult.style.color = '#ff0000';
    }

    if (pathToResult.textContent === 'Dobrze!') {
        pathToResult.style.color = '#00b300';
    }
}

function clear_answer() {

    var path = document.getElementById('wordInEnglish');
    path.value = '';
}