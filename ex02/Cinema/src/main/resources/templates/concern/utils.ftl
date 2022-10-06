<#macro navbar>
    <nav class="navbar navbar-expand sticky-top bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">Cinema SpringBoot</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            <@spring.message code="lang.change"/>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item" href="?lang=en">
                                    <@spring.message code="lang.eng" />
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="?lang=ru">
                                    <@spring.message code="lang.rus" />
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex" action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input class="btn btn-outline-primary" type="submit" value=<@spring.message code="logout" />>
                </form>
            </div>
        </div>
    </nav>
</#macro>

<#macro adminNavbar>
    <nav class="navbar navbar-expand sticky-top bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">Cinema SpringBoot</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Pages
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item" href="/admin/panel/films">
                                    Films
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="/admin/panel/halls">
                                    Halls
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="/admin/panel/sesfions">
                                    Sessions
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex" action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input class="btn btn-outline-primary" type="submit" value="logout">
                </form>
            </div>
        </div>
    </nav>
</#macro>

<#macro commonStyles>
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: block;
            align-items: flex-start;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        <#--  .avatar_img {
            width: 200px;
            height: 200px;
            object-fit: cover;
            border-radius: 50%;
        }

        .card-table {
            max-height: 50%;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
        }  -->
    </style>
</#macro>