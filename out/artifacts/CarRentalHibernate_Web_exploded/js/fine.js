function doFine() {

    var values = [];
    values.push($("#multa").val());
    values.push($("#improto").val());
    values.push($("#prenotazione").val());
    
    $.ajax({
        method:"POST",
        url:'create/fine',
        data:{"values":values},
        success:function(data) {
            $('#modalFine').modal('hide');
            var resp = data.split(";");
            putRow(resp);
        },
        error:function(xhr) {
            $('#modalFine').modal('hide');
            $('#modalMessage').modal('show');
            $('.modal-message-err').text(
                xhr.statusText + " " + xhr.status + ": " + xhr.responseText);
        }
    }).then(function() {
       reset();
    });
}

function putRow(r) {
    $('#finesTable tr:last').after(
        '<tr>' +
                    '<td>' + r[0] + '</td>' +
                    '<td>' + r[1] + '</td>' +
                    '<td>' + r[2] + '</td>' +
                    '<td>' + r[3] + '</td>' +
                    '<td>' + r[4] + '</td>' +
        '</tr>');
}

function reset() {
    $('#modalFine input[type=text], input[type=password]').val("");
}
