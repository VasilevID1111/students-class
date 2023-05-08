<!DOCTYPE html>
<html>
<head>
    <title>
        Компьютеры
    </title>
    <link rel="stylesheet" type="text/css" href="../static/styles/style.css">
</head>
<body>
<h1>Компьютеры</h1>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти">
</form>
<hr>
<#list computers as computer>
    <div>
        <p>
            <b>${computer.name}</b>
            | ${computer.purpose}
            | <input type="checkbox" name="isWorkable"
                     value="${computer.workable?c}" ${computer.workable?string('checked', '')} disabled />
            | <a href="/computers/${computer.compId}">Записаться</a>
            | <a href="/computers/delete/${computer.compId}">Удалить</a>
        </p>
    </div>
</#list>
<h3>
    <form action="/computers/create" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        Название компьютера: <input type="text" name="name"/><br><br>
        Описание компьютера: <input type="text" name="purpose"/><br><br>
        <input id="html" type="checkbox" name="workable"> <label for="html">Компьютер рабочий</label><br><br>
        <input type="submit" value="Добавить компьютер">
    </form>
</h3>
</body>
</html>
