$(document).ready(function() {

    $("div#leaveTeamDialog").dialog({
        autoOpen: false,
        dialogClass: "no-close",
        buttons: [
            {
                text: "Continue",
                primary: true,
                click: function() {
                    $( this ).dialog( "close" );
                    $("form#leaveTeamForm").submit();
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

function leaveTeam(teamId, teamName) {
    $('input#teamId').val(teamId);
    $('div#leaveTeamDialog').html('<p>Are you sure to leave team "' + teamName + '"</p>');
    $('div#leaveTeamDialog').dialog('open');
};
