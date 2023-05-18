<#macro template>
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
        <!-- Bootstrap CSS -->
<#--        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"-->
<#--              integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">-->
        <link rel="stylesheet" href="/styles/style.css">
        <script src="/scripts/calendar.js"></script>
        <script src="/scripts/timeday.js"></script>
        <script src="/scripts/sendtime.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>
    <nav class="navbar navbar-expand-sm navbar-light bg-light text-dark">
        <a class="navbar-brand p-2">${globalUserName}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <#if globalRole = "ROLE_WORKER">
                     <li class="nav-item">
                        <a class="nav-link" href="/computers">Записать студента</a>
                     </li>
                     <li class="nav-item">
                       <a class="nav-link" href="/check">Отметить студента</a>
                     </li>
                     <li class="nav-item">
                       <a class="nav-link" href="/report">Отчет</a>
                     </li>
                     <li class="nav-item">
                         <a class="nav-link" href="/users">Пользователи</a>
                     </li>
                <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="/computers">Записаться</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/visits">Мои записи</a>
                    </li>
                </#if>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Поменять пароль</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" onclick="logout()">Выйти</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested>
    </div>
    <script>
        function logout() {
            var form = document.createElement('form');
            form.method = 'post';
            form.action = '/logout';

            var csrfTokenInput = document.createElement('input');
            csrfTokenInput.type = 'hidden';
            csrfTokenInput.name = '${_csrf.parameterName}';
            csrfTokenInput.value = '${_csrf.token}';

            form.appendChild(csrfTokenInput);
            document.body.appendChild(form);
            form.submit();
        }
    </script>

    <!-- Optional JavaScript for bootstrap -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
            integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
            integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>