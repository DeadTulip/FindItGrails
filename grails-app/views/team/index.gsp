<g:applyLayout name="main">
    <r:require module="teamInfo"/>
    <div class="container">
        <h2>${teamName}</h2>
        <div><label>Creator</label> ${creator}</div>
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
            <g:each in="${members}" var="user">
                <tr>
                    <td></td>
                    <td>${user.username}</td>
                    <td>123</td>
                    <td>
                        <a href="#">
                            <span class="glyphicon glyphicon-ban-circle"></span>
                        </a>
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