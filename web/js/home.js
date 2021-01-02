function doUpdate(modalId, id, url) {
    
    var values = [];

    if(url.includes("Rental")) {
        values.push($('#' + id).val());
        values.push($("#carOption option:selected").val());
        values.push($("#modelOption option:selected").val());
    } else {
        values.push($('#' + id).val());
        values.push($('#name').val());
        values.push($('#surname').val());
        values.push($('#date').val());
        values.push($('#username').val());
        // $.each(values, function (index, val) {
        //     console.log(this + "\n");
        // })
    }

    $.ajax({
        method:"POST",
        url:url, //dynamic url
        data:{"values": values},
        success: function(data) {
            $('#' + modalId).modal('hide');
            if(data !== null) {values.push(data);}
            updateRow(values, id);
        },
        error: function(xhr) {
            $('#' + modalId).modal('hide');
            $('#modalMessage').modal('show');
            $('.modal-message-err').text(
                xhr.statusText + " " + xhr.status + ": " + xhr.responseText);
        }
    }).then(function() {
        reset(id);
    });
}

function updateRow(values, id) {

    var trId = values[0];
    if(id.includes("User")) {
        $('#tableAdmin #user' + trId).children("td:eq(1)").text(values[1]);
        $('#tableAdmin #user' + trId).children("td:eq(2)").text(values[2]);
        $('#tableAdmin #user' + trId).children("td:eq(3)").text(values[3]);
        $('#tableAdmin #user' + trId).children("td:eq(4)").text(values[4]);
    } else {
        $('#rental' + trId).children("td:eq(1)").text(new Date().toDateString());
        $('#rental' + trId).children("td:eq(3)").text(values[1]);
        $('#rental' + trId).children("td:eq(4)").text(values[2]);
        $('#rental' + trId).children("td:eq(5)").text(values[3]);
    }
}

function reset(id) {
    $("#" + id + " input[type=text], input[type=password]").val("");
}

function openModal(trId, modalId, inputId) {
    $('#' + modalId).modal('show');
    $("#" + inputId).val(trId);
}

function searchFilter(inputID, tableID) {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById(inputID);
    filter = input.value.toUpperCase();
    table = document.getElementById(tableID);
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for(i = 0; i < tr.length; i++) {

        if(inputID.includes("Admin")) {
            td = tr[i].getElementsByTagName("td")[1];
        } else {
            td = tr[i].getElementsByTagName("td")[0];
        }

        if(td) {
            txtValue = td.textContent || td.innerText;
            if(txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
