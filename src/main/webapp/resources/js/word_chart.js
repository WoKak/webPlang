/**
 * Created by Michał on 2017-10-20.
 */

//script runs after submitting the criteria
$("#word_criteria").submit(function (event) {

    event.preventDefault();
    searchWordViaAjax();
});

//AJAX request and its configuration
function searchWordViaAjax() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/processWordCriteria?word="
        + $("#word").val(),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            displayWordResponse(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function () {
            console.log("DONE");
        }
    });
}

//function responsible for displaying data
function displayWordResponse(data) {

    clearCanvas();

    const response = JSON.parse(JSON.stringify(data, null, 4));

    var ctx = document.getElementById('myChart').getContext('2d');

    var myDoughnutChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: response.signatures,
            datasets: [{
                label: "wyniczki",
                backgroundColor: ['rgb(204, 255, 153)', 'rgb(255, 128, 128)'] ,
                borderColor: ['rgb(102, 204, 0)', 'rgb(204, 0, 0)'],
                data: response.numbers,
            }]
        },
        options: {}
    });
}

function clearCanvas() {

    $("#myChart").remove();
    $('<canvas>').attr({id: "myChart"}).css({width: 680 + 'px', height: 500 + 'px'}).appendTo('#canvas_side');
}