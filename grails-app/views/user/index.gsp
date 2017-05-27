<g:applyLayout name="main">
    <r:require module="userInfo"/>
    <div class="container">
        <h2>User</h2>
        <div><label>Item owned</label> 123</div>
        <label>My teams</label>
        <g:form controller="user" action="createTeam">
            <div class="input-group">
                <input type="text" class="form-control" name="teamName">
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="submit">Create team</button>
                </span>
            </div>
            <g:if test="${createTeamError}" >
                <div class="alert alert-danger">${createTeamError}</div>
            </g:if>
        </g:form>
        <table data-toggle="table">
            <thead>
            <tr>
                <th>#</th>
                <th data-sortable="true">name</th>
                <th>manage</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${ownedTeams}" var="team">
                <tr>
                    <td></td>
                    <td>${team.teamName}</td>
                    <td>
                        <a href="#">
                            <span class="glyphicon glyphicon-cog"></span>
                        </a>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>


        <label>Joined Teams</label>
        <table data-toggle="table">
            <thead>
            <tr>
                <th>#</th>
                <th data-sortable="true">name</th>
                <th>leave</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${joinedTeams}" var="team">
                <tr>
                    <td></td>
                    <td>${team.teamName}</td>
                    <td>
                        <a href="#">
                            <span class="glyphicon glyphicon-log-out"></span>
                        </a>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</g:applyLayout>