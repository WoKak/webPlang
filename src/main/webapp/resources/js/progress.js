/**
 * Created by Michał on 2017-10-19.
 */

//script runs after submitting the criteria
$("#criteria").submit(function (event) {

    event.preventDefault();
    searchViaAjax();
});

//AJAX request and its configuration
function searchViaAjax() {

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/processCriteria?howMany="
            + $("#how_many").val()
            + "&order="
            + $('input[name=order]:checked', '#criteria').val(),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            displayResponse(data);
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
function displayResponse(data) {

    clearCanvas();

    const response = JSON.parse(JSON.stringify(data, null, 4));

    var ctx = document.getElementById('myChart').getContext('2d');

    var myBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: response.words,
            datasets: [{
                label: "Wyniki w procentach",
                backgroundColor: 'rgb(179, 204, 255)' ,
                borderColor: 'rgb(102, 153, 255)',
                data: response.percentages,
            }]
        },
        options: {}
    });
}

function clearCanvas() {

    $("#myChart").remove();
    $('<canvas>').attr({id: "myChart"}).css({width: 680 + 'px', height: 500 + 'px'}).appendTo('#canvas_side');
}