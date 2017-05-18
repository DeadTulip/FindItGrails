<g:applyLayout name="main">
    <r:require module="userInfo"/>
    <div class="container">
        <h2>User</h2>
        <div><label>Item owned</label> 123</div>
        <label>My teams</label>
        <table data-toggle="table">
            <thead>
            <tr>
                <th>#</th>
                <th data-sortable="true">name</th>
                <th>manage</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${teams}" var="team">
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
            <g:each in="${teams}" var="team">
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