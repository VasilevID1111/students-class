<#import "blocks/template.ftlh" as t>
<@t.template>
    <div class="container">
        <div class="row mb-3">
            <div class="col-lg-3">
                <label for="loginInput">Логин пользователя:</label>
                <input type="text" id="loginInput" class="form-control" placeholder="Введите логин">
            </div>
            <div class="col-lg-3">
                <label for="computerSelect">Имя компьютера:</label>
                <select id="computerSelect" class="form-select">
                    <option value="0">Любой</option>
                    <#list computers as computer>
                        <option value="${computer.compId}">id=${computer.compId}, ${computer.name}</option>
                    </#list>
                </select>
            </div>
            <div class="col-lg-3">
                <label for="dateFromInput">Дата с:</label>
                <input type="date" id="dateFromInput" class="form-control">
            </div>
            <div class="col-lg-3">
                <label for="dateToInput">Дата по:</label>
                <input type="date" id="dateToInput" class="form-control">
            </div>
        </div>
        <div class="row mb-3">
            <div class="d-flex justify-content-end">
                <form action="/report/xlsx" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="btn btn-primary me-2">Скачать (xlsx)</button>
                </form>
                <form id="getReport" action="/report" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="login" id="loginSend">
                    <input type="hidden" name="compId" id="compIdSend">
                    <input type="hidden" name="dateFrom" id="dateFromSend">
                    <input type="hidden" name="dateTo" id="dateToSend">
                    <button id="sendButton" type="button" class="btn btn-primary" onclick="sendDataReport()">
                        Сформировать отчет
                    </button>
                </form>

            </div>
        </div>
        <table class="table table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Активность</th>
                <th>Логин</th>
                <th>ФИО</th>
                <th>Почта</th>
                <th>Время и Дата</th>
                <th>Компьютер</th>
                <th>Статус</th>
            </tr>
            </thead>
            <script>
                let timeRangeElement, timeRange
            </script>
            <#list visits as visit>
                <tr>
                    <th><input type="checkbox" name="active"
                               value="${visit.user.active?c}" ${visit.user.active?string('checked', '')} disabled/></th>
                    <td>${visit.user.login}</td>
                    <td>${visit.user.fio}</td>
                    <td>${visit.user.email}</td>
                    <td><span id="time-range${visit.visit_id}"></span> ${visit.date?date?string(' dd MMMM, yyyy')} </td>
                    <td>id=${visit.computer.compId}, ${visit.computer.name}</td>
                    <td>
                        <#switch visit.status>
                            <#case "sign">Записался(лась)<#break>
                            <#case "absent">Пропустил(а)<#break>
                            <#case "attended">Присутствовал(а)<#break>
                            <#default>Не определено
                        </#switch>
                    </td>
                </tr>
                <script>
                    timeRangeElement = document.getElementById('time-range${visit.visit_id}');
                    timeRange = convertTimeRange("${visit.timed_blocks}");
                    timeRangeElement.textContent = timeRange;
                </script>
            </#list>
        </table>
    </div>
</@t.template>