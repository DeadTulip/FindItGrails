<g:applyLayout name="main">
    <r:require module="teamInfo"/>
    <div class="container">
        <h2>${cmd.team.teamName}</h2>
        <div><label>Creator</label> ${cmd.team.creator.username}</div>
        <label>Members</label>
        <table data-toggle="table">
            <thead>
            <tr>
                <th>#</th>
                <th data-sortable="true">member name</th>
                <th data-sortable="true">items</th>
                <th>manage</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${cmd.userItemCountList}" var="userItems">
                <tr>
                    <td></td>
                    <td>${userItems.user.username}</td>
                    <td>
                        <g:if test="${userItems.count}">
                            <g:link controller="item" action="list" params="[userId: userItems.user.id]">
                                ${userItems.count}
                            </g:link>
                        </g:if>
                        <g:else>
                            ${userItems.count}
                        </g:else>
                    </td>
                    <td>
                        <g:if test="${cmd.team.creator != userItems.user}">
                            <a href="#" onclick="removeMemberFromTeam(${cmd.team.id}, '${cmd.team.teamName}', ${userItems.user.id}, '${userItems.user.username}')">
                                <span class="glyphicon glyphicon-ban-circle"></span>
                            </a>
                        </g:if>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <p>
        <g:form controller="team" action="addMember" >
            <div class="col-lg-4">
                <div class="input-group">
                    <g:hiddenField name="teamId" value="${cmd.team.id}" />
                    <input type="text" class="form-control" placeholder="Type member name" name="memberName">
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-primary">Add member</button>
                    </span>
                </div>
            </div>
        </g:form>
    </div>

    <div id="removeMemberDialog" title="Basic dialog">
    </div>

    <g:form name="removeMemberForm" controller="team" action="removeMember" >
        <g:hiddenField name="teamId" value="" />
        <g:hiddenField name="userId" value="" />
    </g:form>

</g:applyLayout>