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
                    <td>${userItems.count}</td>
                    <td>
                        <g:if test="${cmd.team.creator != userItems.user}">
                            <a href="#">
                                <span class="glyphicon glyphicon-ban-circle"></span>
                            </a>
                        </g:if>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <p>
        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Type member name">
                <span class="input-group-btn">
                    <button type="button" class="btn btn-primary">Add member</button>
                </span>
            </div>
        </div>
    </div>
</g:applyLayout>