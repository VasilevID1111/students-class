<#import "blocks/template.ftlh" as t>
<@t.template>
<h1>Запланированные посещения</h1>
<hr>
<script>
    let timeRangeElement,timeRange
</script>
<#list visits as visit>
    <div>
        <p>
            <b>${visit.date?date?string('EEEE, dd MMMM, yyyy')}</b>
            | <span id="time-range${visit.visit_id}"> </span>
            | ${visit.computer.name}
            | ${visit.computer.purpose}
            | <a href="/visits/delete/${visit.visit_id}">Отменить запись</a>
        </p>
    </div>
    <script>
        timeRangeElement = document.getElementById('time-range${visit.visit_id}');
        timeRange = convertTimeRange("${visit.timed_blocks}");
        console.log(timeRange);
        timeRangeElement.textContent = timeRange;
    </script>
</#list>
</@t.template>

