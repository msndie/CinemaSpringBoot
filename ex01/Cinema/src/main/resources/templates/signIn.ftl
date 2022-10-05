<!DOCTYPE html>
<html>

<head>
    <#import "/spring.ftl" as spring/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <title><@spring.message code="sign.in.title"/></title>
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
        }
    </style>
</head>

<body class="text-center">

    <main class="form-signin w-100 m-auto">
        <form class="row g-3" action="/signIn" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <h1 class="h3 mb-4 fw-normal"><@spring.message code="sign.in.header"/></h1>

            <div class="col-12">
                <input type="email" id="email" name="email" class="form-control" placeholder="<@spring.message code="email"/>"
                       aria-label="Email" required>
            </div>
            <div class="col-12">
                <input type="password" id="pass" name="pass" class="form-control" placeholder="<@spring.message code="password"/>"
                    aria-label="Password" required>
            </div>
            <div class="col-12">
                <input type="checkbox" id="remember-me" name="rememberMe" value="yes" class="form-check-input">
                <label class="form-check-label" for="remember-me"><@spring.message code="sign.in.remember.me"/></label>
            </div>

            <div class="col-12">
                <button type="submit" value="Submit" class="w-100 btn btn-md btn-primary"><@spring.message code="sign.in"/></button>
                <p class="mt-3 mb-3 text-muted"><@spring.message code="sign.in.sign.up.text"/> <a href="/signUp" id="link">
                        <@spring.message code="sign.up"/>
                    </a>
                </p>
                <p class="mt-3 mb-3 text-muted">&copy; vseel, sclam | 2022</p>
            </div>
        </form>
        <div class="pb-2">
            <span><@spring.message code="lang.change"/>:</span>
        </div>
        <a class="btn btn-outline-primary btn-sm" href="?lang=en"><@spring.message code="lang.eng" /></a>
        <a class="btn btn-outline-primary btn-sm" href="?lang=ru"><@spring.message code="lang.rus" /></a>
    </main>

    <script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('signIn?lang=' + selectedOption);
            }
        });
    });
    </script>
</body>

</html>