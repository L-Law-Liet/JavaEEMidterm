<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="Hello">Home</a>
            </li>
            <li>
                <a class="nav-link" href="Students">Students</a>
            </li>
            <% if(request.getAttribute("user") == null) { %>
            <li class="nav-item">
                <a class="nav-link" href="Login">Log-in</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Register">Sign-up</a>
            </li>
            <% } else { %>
            <li class="nav-item">
                <a class="nav-link" href="Cabinet">Cabinet</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Logout">Logout</a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>
