/**
 * Created by Michał on 2017-09-02.
 */

//script runs after submitting the answer
$("#answer_form").submit(function (event) {

    event.preventDefault();
    searchViaAjax();
});

//AJAX request and its configuration
function searchViaAjax() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/process?answer=" + $("#wordInEnglish").val(),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            displayResponse(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
            displayScoreAndNextWord(e);
        },
        done: function () {
            console.log("DONE");
        }
    });
}

//function responsible for displaying data
function displayResponse(data) {

    const response = JSON.parse(JSON.stringify(data, null, 4));

    $('#word_to_translate').text(response.nextWord);
    $('#is_correct').text(response.isCorrect);
    $('#correct_answer').text(response.correctAnswer);
    $('#points').text(response.points);
    modifyApp();
}

//function responsible for displaying score
function displayScoreAndNextWord(data) {

    const tab = data.responseText.split(" ");
    $('#word_to_translate').text(tab[1]);
    $('#is_correct').text("");
    $('#correct_answer').text("");
    $('#points').text("");
    alert("Koniec ćwiczenia. \nZdobyłeś: " + tab[0] + "/20.");
}