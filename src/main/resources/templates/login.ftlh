<#--<!DOCTYPE html>-->
<#--<html>-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>-->
<#--        Расписание-->
<#--    </title>-->

<#--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">-->
<#--    <!-- Bootstrap CSS &ndash;&gt;-->
<#--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">-->
<#--    <link rel="stylesheet" href="/styles/style.css">-->
<#--    <script src="/scripts/calendar.js"></script>-->
<#--</head>-->

<form action="/login" method="post">
    <!-- Login input -->
    <div class="form-outline mb-4">
        <input type="text" id="login" name="username" class="form-control" />
        <label class="form-label" for="login">Login</label>
    </div>

    <!-- Password input -->
    <div class="form-outline mb-4">
        <input type="password" id="password" name="password" class="form-control" />
        <label class="form-label" for="password">Password</label>
    </div>

    <!-- 2 column grid layout for inline styling -->
    <div class="row mb-4">
        <div class="col d-flex justify-content-center">
            <!-- Checkbox -->
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                <label class="form-check-label" for="form2Example31"> Запомнить пароль </label>
            </div>
        </div>

        <div class="col">
            <!-- Simple link -->
            <a href="#!">Забыли пароль?</a>
        </div>
    </div>

    <!-- Submit button -->
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" class="btn btn-primary btn-block mb-4">Войти</input>

</form>

<#--<!-- Optional JavaScript for bootstrap &ndash;&gt;-->
<#--<!-- jQuery first, then Popper.js, then Bootstrap JS &ndash;&gt;-->
<#--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"-->
<#--        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"-->
<#--        crossorigin="anonymous"></script>-->
<#--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"-->
<#--        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"-->
<#--        crossorigin="anonymous"></script>-->
<#--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"-->
<#--        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"-->
<#--        crossorigin="anonymous"></script>-->
<#--</body>-->
<#--</html>-->