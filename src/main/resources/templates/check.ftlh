<#import "blocks/template.ftlh" as t>
<@t.template>
    <h2 class="m-3">Отметить студента за <span id="todayDate"></span></h2>
    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Логин</th>
            <th>ФИО</th>
            <th>Время</th>
            <th>Компьютер</th>
            <th>Статус</th>
            <th>Пришел</th>
            <th>Пропустил</th>
        </tr>
        </thead>
        <script>
            let timeRangeElement,timeRange
        </script>
        <#list visits_undefined as visit>
            <tr>
                <td>${visit.user.login}</td>
                <td>${visit.user.fio}</td>
                <td><span id="time-range${visit.visit_id}"></span></td>
                <td>${visit.computer.name}</td>
                <td>
                    <#if visit.status = "sign">
                        Записался(лась)
                    <#else>
                        Не определено
                    </#if>
                </td>
                <td class="text-center"><a href="/check/attended/${visit.visit_id}" class="btn btn-primary">Пришел</a></td>
                <td class="text-center"><a href="/check/absent/${visit.visit_id}" class="btn btn-danger">Пропустил</a></td>
            </tr>
            <script>
                timeRangeElement = document.getElementById('time-range${visit.visit_id}');
                timeRange = convertTimeRange("${visit.timed_blocks}");
                console.log(timeRange);
                timeRangeElement.textContent = timeRange;
            </script>
        </#list>
        <script>
            let todayDate = document.getElementById('todayDate');
            todayDate.textContent = (new Date()).toLocaleDateString('en-GB', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric'
            }).split('/').join('-');;
        </script>
    </table>
    <h2 class="m-3">Студенты отмеченные</h2>
    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Логин</th>
            <th>ФИО</th>
            <th>Время</th>
            <th>Компьютер</th>
            <th>Статус</th>
            <th>Вернуть к неопределенным</th>
        </tr>
        </thead>
        <#list visits_defined as visit>
            <tr>
                <td>${visit.user.login}</td>
                <td>${visit.user.fio}</td>
                <td><span id="time-range${visit.visit_id}"></span></td>
                <td>${visit.computer.name}</td>
                <td>
                    <#if visit.status = "absent">
                        Пропустил(а)
                    <#else>
                        Присутствовал(а)
                    </#if>
                </td>
                <td class="text-center"><a href="/check/undefined/${visit.visit_id}" class="btn btn-primary">Вернуть</a></td>
            </tr>
            <script>
                timeRangeElement = document.getElementById('time-range${visit.visit_id}');
                timeRange = convertTimeRange("${visit.timed_blocks}");
                console.log(timeRange);
                timeRangeElement.textContent = timeRange;
            </script>
        </#list>
    </table>
</@t.template>