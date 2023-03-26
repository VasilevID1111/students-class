<!DOCTYPE html>
<html>
<head>
    <title>
        Компьютеры
    </title>
    <link rel="stylesheet" type="text/css" href="stylesheets/computer.css">
</head>
<body>
<h1>Компьютеры</h1>
<hr>
<#list computers as computer>
    <div>
        <p>
            <b>${computer.name}</b>
            | ${computer.purpose}
            | <input type="checkbox" name="isWorkable"
                     value="${computer.workable?c}" ${computer.workable?string('checked', '')} disabled />
        </p>
    </div>
</#list>
<h3>
    <form action="/computers/create" method="post">
        Название компьютера: <input type="text" name="name"/><br><br>
        Описание компьютера: <input type="text" name="purpose"/><br><br>
        <input id="html" type="checkbox" name="isWorkable"> <label for="html">Компьютер рабочий</label><br><br>
        <input type="submit" value="Добавить компьютер">
    </form>
</h3>
</body>
</html>
