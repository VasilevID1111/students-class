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
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8">
            <div class="card">
                <div class="card-body">
                    <form action="/login" method="post">
                        <!-- Login input -->
                        <div class="form-group">
                            <label for="username">Логин</label>
                            <input type="text" id="username" name="username" class="form-control"/>
                        </div>

                        <!-- Password input -->
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input type="password" id="password" name="password" class="form-control"/>
                        </div>

                        <!-- Checkbox -->
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked/>
                            <label class="form-check-label" for="form2Example31"> Запомнить пароль </label>
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}">

                        <!-- Submit button -->
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block" style="width: 100%;">Войти</button>
                        </div>

                    </form>

                    <!-- Forgot password link -->
                    <div class="text-center">
                        <a href="#!" class="text-muted">Забыли пароль?</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>