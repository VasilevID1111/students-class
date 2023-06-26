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
                <h3>Регистрация</h3>
            </div>
            <div class="card">
                <div class="card-body">
                    <form action="/registration" method="post">
                        <div class="form-group" >
                            <label for="email">Почта</label>
                            <input type="email" class="form-control "name="email" id="email" placeholder="Введите почту">
                        </div>
                        <div class="form-group mt-md-3">
                            <label for="studentNumber">Номер студеческого билета</label>
                            <input type="text" class="form-control" name="login" id="login" placeholder="Введите студенческий номер">
                        </div>
                        <div class="form-group mt-md-3">
                            <label for="fullName">ФИО</label>
                            <input type="text" class="form-control" name="fio" id="fio" placeholder="Введите ФИО">
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button onclick="message()" type="submit" class="btn btn-primary mt-md-3" style="width: 100%;">Отправить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <span id="message" role="alert" class="mt-10"></span>
</div>
<script>
    function message() {
        let message = document.getElementById("message");
        message.classList.add("alert-success");
        message.classList.add("alert");
        message.textContent = "Письмо отправляется, после отправки вас перенаправит на страницу Login"
    }
</script>
</body>
</html>