<#import "blocks/template.ftlh" as t>
<@t.template>
    <h1>Запланированные посещения</h1>
    <hr>
    <table class="table table-striped table-rounded">
        <thead>
        <tr>
            <th>Дата и Время</th>
            <th>Компьютер</th>
            <th>Описание</th>
            <th>Отменить запись</th>
        </tr>
        </thead>
        <tbody>
        <script>
            let timeRangeElement,timeRange
        </script>
        <#list visits as visit>
            <tr>
                <td><span id="time-range${visit.visit_id}"></span> ${visit.date?date?string(' dd MMMM, yyyy')} </td>
                <td>${visit.computer.name}</td>
                <td>${visit.computer.purpose}</td>
                <td><a href="/visits/delete/${visit.visit_id}" class="btn btn-primary">Отменить запись</a></td>
            </tr>
            <script>
                timeRangeElement = document.getElementById('time-range${visit.visit_id}');
                timeRange = convertTimeRange("${visit.timed_blocks}");
                console.log(timeRange);
                timeRangeElement.textContent = timeRange;
            </script>
        </#list>
        </tbody>
    </table>
</@t.template>

