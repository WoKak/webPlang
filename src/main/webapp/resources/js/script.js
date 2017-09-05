/**
 * Created by Michał on 2017-05-07.
 */

function modifyApp() {

    changeColour();
    clearAnswer();
}

function changeColour() {

    const pathToResult = $("#is_correct");

    if (pathToResult.text() === 'Źle!') {
        pathToResult.css('color', '#ff0000');
    }

    if (pathToResult.text() === 'Dobrze!') {
        pathToResult.css('color', '#00b300');
    }
}

function clearAnswer() {

    const path = document.getElementById('wordInEnglish');
    path.value = '';
}