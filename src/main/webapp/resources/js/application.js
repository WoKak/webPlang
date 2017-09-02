/**
 * Created by Michał on 2017-09-02.
 */

$("#answer_form").submit(function (event) {

    event.preventDefault();
    searchViaAjax();

});

function searchViaAjax() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/process?answer=" + $("#wordInEnglish").val(),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            display_response(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
            display_score_and_next_word(e);
        },
        done: function () {
            console.log("DONE");
        }
    });
}

function display_response(data) {

    var response = JSON.parse(JSON.stringify(data, null, 4));

    $('#word_to_translate').text(response.nextWord);
    $('#is_correct').text(response.isCorrect);
    $('#correct_answer').text(response.correctAnswer);
    $('#points').text(response.points);
    modify_app();
}

function display_score_and_next_word(data) {

    var tab = data.responseText.split(" ");
    $('#word_to_translate').text(tab[1]);
    alert("Koniec ćwiczenia. \nZdobyłeś: " + tab[0] + "/20.");
}