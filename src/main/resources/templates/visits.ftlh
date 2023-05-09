<!DOCTYPE html>
<html>
<head>
    <title>
        Запланированные посещения
    </title>
    <link rel="stylesheet" type="text/css" href="../static/styles/style.css">
</head>
<body>
<h1>Посещения</h1>
<hr>
<#list visits as visit>
    <div>
        <p>
            <b>${visit.date?date}</b>
            | ${visit.timed_blocks}
            | ${visit.computer.name}
            | ${visit.computer.purpose}
            | <a href="/computers/delete/${visit.visit_id}">Удалить</a>
        </p>
    </div>
</#list>
</body>
</html>
