$(document).ready(function() {

    $("div#removeMemberDialog").dialog({
        autoOpen: false,
        dialogClass: "no-close",
        buttons: [
            {
                text: "Continue",
                primary: true,
                click: function() {
                    $( this ).dialog( "close" );
                    $("form#removeMemberForm").submit();
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

function removeMemberFromTeam(teamId, teamName, userId, userName) {
    $('input#teamId').val(teamId);
    $('input#userId').val(userId);
    $('div#removeMemberDialog').html('<p>Are you sure to remove user "' + userName + '" from  team "' + teamName + '"</p>');
    $('div#removeMemberDialog').dialog('open');
};
