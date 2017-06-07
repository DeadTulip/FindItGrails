$(document).ready(function() {
    $('#eventStartDateContainer input').datepicker({dateFormat: 'dd/mm/yy'});

    $('#eventEndDateContainer input').datepicker({dateFormat: 'dd/mm/yy'});

    $('#inputSelectedPeople').select2();

    $('#inputSelectedPlaces').select2();

    $('#inputSharedTeams').select2();

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
