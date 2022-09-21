<!DOCTYPE html>
<html lang="en">

<head>
    <#import "/spring.ftl" as spring/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <title><@spring.message code="sign.up.title"/></title>
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

        .form-signup {
            max-width: 330px;
            padding: 15px;
        }
    </style>
</head>

<body class="text-center">

<#if verification?has_content>
    <h1><@spring.message code="sign.up.verification.text"/></h1>
<#else>
    <main class="form-signup w-100 m-auto">
        <form class="row g-3" action="/signUp" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <h1 class="h3 mb-3 fw-normal"><@spring.message code="sign.up.header"/></h1>
            <div class="col-12">
                <@spring.bind 'userForm.firstName'/>
                <input type="text" id="fname" name="${spring.status.expression}" class="form-control" placeholder="<@spring.message code="first.name"/>"
                       aria-label="First name">
                <#if spring.status.errorMessage?has_content>
                    <p>${spring.status.errorMessage}</p>
                </#if>
            </div>
            <div class="col-12">
                <@spring.bind 'userForm.lastName'/>
                <input type="text" id="lname" name="${spring.status.expression}" class="form-control" placeholder="<@spring.message code="last.name"/>"
                       aria-label="Last name">
                <#if spring.status.errorMessage?has_content>
                    <p>${spring.status.errorMessage}</p>
                </#if>
            </div>
            <div class="col-12">
                <@spring.bind 'userForm.email'/>
                <input type="text" id="email" name="${spring.status.expression}" class="form-control" placeholder="<@spring.message code="email"/>"
                       aria-label="Email">
                <#if spring.status.errorMessage?has_content>
                    <p>${spring.status.errorMessage}</p>
                </#if>
            </div>
            <div class="col-12">
                <@spring.bind 'userForm.phoneNumber'/>
                <input type="text" id="phone" name="${spring.status.expression}" class="form-control"
                       placeholder="<@spring.message code="phone"/> +7(777)777777" aria-label="Phone">
                <#if spring.status.errorMessage?has_content>
                    <p>${spring.status.errorMessage}</p>
                </#if>
            </div>
            <div class="col-12">
                <@spring.bind 'userForm.password'/>
                <input type="password" id="pass" name="password" class="form-control" placeholder="<@spring.message code="password"/>"
                       aria-label="Password">
                <#if spring.status.errorMessage?has_content>
                    <p>${spring.status.errorMessage}</p>
                </#if>
            </div>
            <div class="col-12">
                <button type="submit" value="Submit" class="w-100 btn btn-md btn-primary"><@spring.message code="sign.up"/></button>
                <p class="mt-3 mb-3 text-muted"><@spring.message code="sign.up.sign.in.text"/>
                    <a href="/signIn" id="link"><@spring.message code="sign.in"/></a>
                </p>
                <p class="mt-3 mb-3 text-muted">&copy; vseel, sclam | 2022</p>
            </div>
        </form>
        <p style="color: red">
            <#if error?has_content>
                ${error}
            </#if>
        </p>
    </main>
</#if>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>

</html>