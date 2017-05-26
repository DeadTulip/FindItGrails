<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">FindIt</a>
        </div>
        <sec:ifLoggedIn>
            <ul class="nav navbar-nav">
                <li><g:link controller="item" action="open">Add Item</g:link></li>
                <li><g:link controller="item" action="list">List Items</g:link></li>
                <li><g:link controller="user" action="index">User info</g:link></li>
                <li><g:link controller="team" action="index">Team info</g:link></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><g:link controller="user" action="index">
                    <span class="glyphicon glyphicon-user"></span> <sec:loggedInUserInfo field="username"/>
                </g:link></li>
                <li><g:link controller="logout" action="index">
                    <span class="glyphicon glyphicon-log-out"></span> Logout
                </g:link></li>
            </ul>


        </sec:ifLoggedIn>
    </div>
</nav>