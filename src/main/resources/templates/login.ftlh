<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        Компьютерный класс
    </title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/styles/style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container login-container">
    <div class="row justify-content-center">
        <div class="col-md-6 login-form-1">
            <div>
                <h3>Компьютерный класс</h3>
            </div>
            <div class="card">
                <div class="card-body">
                    <form action="/login" method="post">
                        <!-- Login input -->
                        <div class="form-group">
                            <label for="username">Логин</label>
                            <input type="text" id="username" name="username" class="form-control" autocomplete="on"/>
                        </div>

                        <!-- Password input -->
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input type="password" id="password" name="password" class="form-control" autocomplete="on"/>
                        </div>

                        <!-- Checkbox -->
                        <div class="form-group d-flex justify-content-center">
                            <div class="form-check mb-2 " style="text-align: left;">
                                <input class="form-check-input" type="checkbox" value="" id="rememberPassword" checked/>
                                <label class="form-check-label" for="rememberPassword" > Запомнить пароль </label>
                            </div>
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}">

                        <!-- Submit button -->
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block" style="width: 100%;">Войти</button>
                        </div>

                    </form>

                    <!-- Forgot password link -->
                    <div class="text-center">
                        <a href="/registration" class="text-muted">Не зарегистрированы? Отправьте запрос</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var rememberPasswordCheckbox = document.getElementById('rememberPassword');
    var usernameField = document.getElementById('username');
    var passwordField = document.getElementById('password');

    rememberPasswordCheckbox.addEventListener('change', function() {
        if (rememberPasswordCheckbox.checked) {
            usernameField.setAttribute('autocomplete', 'on');
            passwordField.setAttribute('autocomplete', 'on');
        } else {
            usernameField.setAttribute('autocomplete', 'off');
            passwordField.setAttribute('autocomplete', 'off');
        }
    });
</script>
</body>
</html>