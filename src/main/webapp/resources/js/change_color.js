/**
 * Created by Michał on 2017-05-07.
 */

//changes color of the result on application site

var pathToResult = document.getElementById('result');

var color = '';

if(pathToResult.textContent === 'Źle!') {
    pathToResult.style.color = '#ff0000';
}

if(pathToResult.textContent === 'Dobrze!') {
    pathToResult.style.color = '#00b300';
}