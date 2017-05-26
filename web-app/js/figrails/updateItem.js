$(document).ready(function() {
    $('#eventStartDateContainer input').datepicker({});

    $('#eventEndDateContainer input').datepicker({});

    $('#inputSelectedPeople').select2();

    $('#inputSelectedPlaces').select2();

    $("div#successDialog").dialog({
        autoOpen: false,
        dialogClass: "no-close",
        buttons: [
            {
                text: "Update added item",
                click: function() {
                    $( this ).dialog( "close" );
                }
            },
            {
                text: "Close",
                click: function() {
                    $( this ).dialog( "close" );
                }
            }
        ]
    });
});

$('button#btnAdd').click(function() {
    $.ajax({
        url: '/FindItGrails/item/create',
        data: $('form#itemForm').serialize(),
        success: function () {
            $('div#successDialog').dialog('open');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //TODO
        }
    });
});
